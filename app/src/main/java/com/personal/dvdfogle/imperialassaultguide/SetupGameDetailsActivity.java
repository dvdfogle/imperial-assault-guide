package com.personal.dvdfogle.imperialassaultguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class SetupGameDetailsActivity extends AppCompatActivity implements TogglerActivity{
    ArrayList<Integer> heroes;
    String expansionArg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_game_details);
        Toolbar actionBar = findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            expansionArg = extras.getString("ChosenExpansions");
        } else {
            expansionArg = (String) savedInstanceState.getSerializable("ChosenExpansions");
        }
        DeckManager dm = DeckManager.getInstance();
        heroes = new ArrayList<>();

        try {
            JSONArray availableCharacters = dm.getCharactersFromExpansions(expansionArg);
            ImageSelectFragment heroFragment = (ImageSelectFragment) getSupportFragmentManager().findFragmentById(R.id.hero_selector);
            Bundle args = new Bundle();
            args.putString("array", availableCharacters.toString());
            heroFragment.setArguments(args);
        }
        catch (JSONException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_next: {
                StringBuilder heroArgs = new StringBuilder();
                for (int i=0; i < heroes.size()-1; i++) {
                    heroArgs.append(heroes.get(i));
                    heroArgs.append(",");
                }
                heroArgs.append(heroes.get(heroes.size()-1));

                Intent nextPage = new Intent(this, SetupSideMissionsActivity.class);
                nextPage.putExtra("ChosenCharacters", heroArgs.toString());
                nextPage.putExtra("ChosenExpansions", expansionArg);
                startActivity(nextPage);
            }
        }
        return true;
    }

    public void toggleActive(int id) {
        if (heroes.indexOf(id) == -1) {
            heroes.add(id);
        } else {
            heroes.remove(Integer.valueOf(id));
        }
    }
}
