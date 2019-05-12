package com.personal.dvdfogle.imperialassaultguide;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

class DeckManager {
    private final static DeckManager oneInstance = new DeckManager();
    private DatabaseHelper db;

    private DeckManager() {}
    public static DeckManager getInstance() {
        return oneInstance;
    }

    public void setContext(Context context) {
        if (db != null) return;

        db = new DatabaseHelper(context);
        db.createDataBase();
    }

    public JSONObject getExpansionLists() throws JSONException {
        Cursor cursor = db.queryFor("expansions");
        JSONObject lists = new JSONObject();
        String[] columns = cursor.getColumnNames();
        while (cursor.moveToNext()) {
            JSONObject item = new JSONObject();
            for (int i=0; i<columns.length; i++) {
                item.put(columns[i], cursor.getString(i));
            }
            lists.accumulate(item.getString("type"), item);
        }
        cursor.close();
        return lists;
    }

    public JSONArray getCharactersFromExpansions(String expansionArgs) throws JSONException {
        Cursor cursor = db.queryFor("heroes", expansionArgs);
        JSONArray heroes = new JSONArray();
        String[] columns = cursor.getColumnNames();
        while (cursor.moveToNext()) {
            JSONObject item = new JSONObject();
            for (int i=0; i<columns.length; i++) {
                item.put(columns[i], cursor.getString(i));
            }
            heroes.put(item);
        }
        cursor.close();
        return heroes;
    }

    public ArrayList<String> getSideMissionsOfColor(String color, String args) throws JSONException {
        // Gray or green: args is an expansion list. Red: args is a character list.
        Cursor cursor = db.queryFor(color+"_side_missions", args, "4");
        if (color.equals("gray") || color.equals("red")) {
            ArrayList<String> ids = new ArrayList<String>();
            while (cursor.moveToNext()) {
                ids.add(cursor.getString(0));
            }
            cursor.close();
            return ids;
        }

        ArrayList<String> greenMissions = new ArrayList<String>();
        String[] columns = cursor.getColumnNames();
        while (cursor.moveToNext()) {
            JSONObject item = new JSONObject();
            for (int i=0; i<columns.length; i++) {
                item.put(columns[i], cursor.getString(i));
            }
            greenMissions.add(item.toString());
        }
        cursor.close();
        return greenMissions;
    }
}

/* public Cursor query (SQLiteDatabase db,
                String[] projectionIn,
                String selection,
                String[] selectionArgs,
                String groupBy,
                String having,
                String sortOrder,
                String limit)
*/

class DatabaseHelper extends SQLiteOpenHelper{
    class Table {
        final static String EXPANSIONS = "expansions";
        final static String EXPANSION_TYPES = "expansion_types";
        final static String AGENDAS = "agenda_sets";
        final static String CLASSES = "imperial_classes";
        final static String HERO_ITEMS = "hero_items";
        final static String HEROES = "heroes";
        final static String ITEMS = "items";
        final static String REWARDS = "rewards";
        final static String SIDE_MISSIONS = "side_missions";
        final static String STORY_MISSIONS = "story_missions";
    }

    private static String DB_NAME = "card_decks.db";
    private static String DB_PATH;
    private SQLiteDatabase deckDB;
    private final Context myContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        myContext = context;
        DB_PATH = context.getDatabasePath(DB_NAME).getAbsolutePath();
    }

    public void createDataBase() {
 //       if (!checkDataBase()) {
            // This creates a blank database that we can overwrite.
            getReadableDatabase();
            try {
                copyDataBase();
                openDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
 //   }

    private boolean checkDataBase() {
        try {
            DB_PATH = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
            openDataBase();
        } catch (Exception e) {
            // There isn't a database in the expected spot.
        }

        return deckDB != null;
    //    return false;
    }

    private void copyDataBase() throws IOException {
        close();

        InputStream input = myContext.getAssets().open(DB_NAME);
        OutputStream output = new FileOutputStream(DB_PATH);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }

        output.flush();
        output.close();
        input.close();

        getReadableDatabase().close();
    }

    private void openDataBase() throws SQLException {
        deckDB = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
    }

    public Cursor queryFor(String item, String... arguments) {
        Cursor cursor = null;
        String[] columns;
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        switch(item) {
            case "expansions":
                queryBuilder.setTables(Table.EXPANSIONS + " AS e INNER JOIN " + DatabaseHelper.Table.EXPANSION_TYPES + " AS t ON e.type = t._id");
                columns = new String[]{"e._id as id", "e.name", "t.name as type"};
                cursor = queryBuilder.query(
                        deckDB,
                        columns,
                        "e.name <> 'Core Set'",
                        null,
                        null,
                        null,
                        null,
                        null);
                break;
            case "heroes":
                queryBuilder.setTables(Table.HEROES);
                columns = new String[]{"_id as id", "hero_name as name"};
                cursor = queryBuilder.query(
                        deckDB,
                        columns,
                        "expansion_id in (" + arguments[0] + ")",
                        null,
                        null,
                        null,
                        null,
                        null);
                break;
            case "gray_side_missions":
                queryBuilder.setTables(Table.SIDE_MISSIONS);
                columns = new String[]{"_id as id"};
                cursor = queryBuilder.query(
                        deckDB,
                        columns,
                        "expansion_id in (" + arguments[0] + ") AND category='Grey'",
                        null,
                        null,
                        null,
                        "RANDOM()",
                        arguments[1]);
                break;
            case "green_side_missions":
                queryBuilder.setTables(Table.SIDE_MISSIONS);
                columns = new String[]{"_id as id", "name", "reward"};
                cursor = queryBuilder.query(
                        deckDB,
                        columns,
                        "expansion_id in (" + arguments[0] + ") AND category='Green'",
                        null,
                        null,
                        null,
                        null,
                        null);
                break;
            case "red_side_missions":
                queryBuilder.setTables(Table.SIDE_MISSIONS);
                columns = new String[]{"_id as id"};
                cursor = queryBuilder.query(
                        deckDB,
                        columns,
                        "hero_id in (" + arguments[0] + ")",
                        null,
                        null,
                        null,
                        null,
                        null);
                break;
        }
        return cursor;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
