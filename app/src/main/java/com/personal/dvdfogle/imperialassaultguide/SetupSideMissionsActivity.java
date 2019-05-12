package com.personal.dvdfogle.imperialassaultguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class SetupSideMissionsActivity extends AppCompatActivity implements TogglerActivity{
    ArrayList<Integer> sideMissions;
    int totalGreenMissions;
    TextView missionCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_side_missions);
        Toolbar actionBar = findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);

        totalGreenMissions = 4;
        missionCounter = findViewById(R.id.missionCounter);
        missionCounter.setText("Missions Remaining: " + String.valueOf(totalGreenMissions));

        String expansionArg, characterArg;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            expansionArg = extras.getString("ChosenExpansions");
            characterArg = extras.getString("ChosenCharacters");
        } else {
            expansionArg = (String) savedInstanceState.getSerializable("ChosenExpansions");
            characterArg = (String) savedInstanceState.getSerializable("ChosenCharacters");
        }
        DeckManager dm = DeckManager.getInstance();
        sideMissions = new ArrayList<>();

        try {
            // Get RED character-based side missions automatically
            addMissionIDsFrom(dm.getSideMissionsOfColor("red", characterArg));
            // Get GRAY evil side missions automatically
            addMissionIDsFrom(dm.getSideMissionsOfColor("gray", expansionArg));
            Log.d("All Things", sideMissions.toString());
            // Get GREEN good side missions and let Rebels choose
            StringBuilder greenMissions = new StringBuilder("[");
            Iterator<String> iter = dm.getSideMissionsOfColor("green", expansionArg).iterator();
            greenMissions.append(iter.next());
            while (iter.hasNext()) {
                greenMissions.append(",");
                greenMissions.append(iter.next());
            }
            greenMissions.append("]");

            ImageSelectFragment missionFragment = (ImageSelectFragment) getSupportFragmentManager().findFragmentById(R.id.mission_selector);
            Bundle args = new Bundle();
            args.putInt("step", 3);
            args.putString("array", greenMissions.toString());
            missionFragment.setArguments(args);
        }
        catch (JSONException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void addMissionIDsFrom(ArrayList<String> newIds) {
        Iterator<String> iter = newIds.iterator();
        while (iter.hasNext()) {
            sideMissions.add(Integer.valueOf(iter.next()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_items, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.save_game).setVisible(false);
        return true;
    }

    public void toggleActive(int id) {
        if (sideMissions.indexOf(id) == -1) {
            sideMissions.add(id);
            totalGreenMissions--;
        } else {
            sideMissions.remove(Integer.valueOf(id));
            totalGreenMissions++;
        }

        missionCounter.setText("Missions Remaining: " + String.valueOf(totalGreenMissions));
    }
}