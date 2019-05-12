package com.personal.dvdfogle.imperialassaultguide;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class ChooseExpansionsActivity extends AppCompatActivity implements TogglerActivity{
    ChooseExpansionPagerAdapter pagerAdapter;
    ViewPager pager;

    DeckManager deckManager;
    ArrayList<Integer> chosenExpansions;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_expansions);

        deckManager = DeckManager.getInstance();
        chosenExpansions = new ArrayList<>();
        // Auto populate Core Set.
        chosenExpansions.add(0);

        try {
            pagerAdapter = new ChooseExpansionPagerAdapter(getSupportFragmentManager(), deckManager.getExpansionLists());
            pager = findViewById(R.id.pager);
            pager.setAdapter(pagerAdapter);
        }
        catch (JSONException e) {
            throw new Error(e.getMessage());
        }

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);

        Toolbar actionBar = findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_next: {
                StringBuilder expansionArgs = new StringBuilder();
                for (int i=0; i < chosenExpansions.size()-1; i++) {
                    expansionArgs.append(chosenExpansions.get(i));
                    expansionArgs.append(",");
                }
                expansionArgs.append(chosenExpansions.get(chosenExpansions.size()-1));

                Intent nextPage = new Intent(this, SetupGameDetailsActivity.class);
                nextPage.putExtra("ChosenExpansions", expansionArgs.toString());
                startActivity(nextPage);
            }
        }
        return true;
    }

    public void toggleActive(int id) {
        if (chosenExpansions.indexOf(id) == -1) {
            chosenExpansions.add(id);
        } else {
            chosenExpansions.remove(Integer.valueOf(id));
        }
    }
}

class ChooseExpansionPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<JSONArray> expansionLists;
    private ArrayList<String> expansionListNames;

    public ChooseExpansionPagerAdapter(FragmentManager fm, JSONObject lists) {
        super(fm);
        expansionLists = new ArrayList<>();
        expansionListNames = new ArrayList<>();
        Iterator<String> listNames = lists.keys();
        while (listNames.hasNext()) {
            String listName = listNames.next();
            try {
                expansionListNames.add(listName);
                expansionLists.add(lists.getJSONArray(listName));
            } catch (JSONException e) {}
        }
    }

    public int getCount() {
        return expansionLists.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return expansionListNames.get(position);
    }

    public Fragment getItem(int i) {
        ImageSelectFragment fragment = new ImageSelectFragment();
        Bundle args = new Bundle();
        args.putString("array", expansionLists.get(i).toString());
        fragment.setArguments(args);
        return fragment;
    }
}