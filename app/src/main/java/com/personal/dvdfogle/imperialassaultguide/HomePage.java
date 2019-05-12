package com.personal.dvdfogle.imperialassaultguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class HomePage extends AppCompatActivity {
    DeckManager deckManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        deckManager = DeckManager.getInstance();
        deckManager.setContext(this);
    }

    public void newOrLoad(View view) {
        Intent nextPage;
        switch (view.getId()) {
            case R.id.new_game:
                nextPage = new Intent(this, ChooseExpansionsActivity.class);
                break;
            case R.id.load_game:
                nextPage = new Intent(this, LoadGame.class);
                break;
            default:
                return;
        }
        startActivity(nextPage);
    }
}
