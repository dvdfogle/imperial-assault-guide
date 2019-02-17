package com.personal.dvdfogle.imperialassaultguide;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void newOrLoad(View view) {
        Intent nextPage;
        switch (view.getId()) {
            case R.id.new_game:
                nextPage = new Intent(this, NewGame.class);
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
