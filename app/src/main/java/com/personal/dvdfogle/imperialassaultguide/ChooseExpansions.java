package com.personal.dvdfogle.imperialassaultguide;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class ChooseExpansions extends AppCompatActivity implements TogglerActivity{
    ChooseExpansionPagerAdapter pagerAdapter;
    ViewPager pager;

    DeckManager deckManager;
    ArrayList<Integer> chosenExpansions;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_expansions);
        ToolbarHelper.setActionToolbar(this);

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