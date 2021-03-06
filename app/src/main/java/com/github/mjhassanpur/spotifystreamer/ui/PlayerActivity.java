package com.github.mjhassanpur.spotifystreamer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.github.mjhassanpur.spotifystreamer.R;

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean twoPane = getResources().getBoolean(R.bool.twoPane);
        if (twoPane) {
            setTheme(R.style.BaseThemeDialog);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        if (twoPane) {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.height = (int) getResources().getDimension(R.dimen.activity_dialog_height);
            params.width = (int) getResources().getDimension(R.dimen.activity_dialog_width);
            getWindow().setAttributes(params);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Track");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fm = getSupportFragmentManager();
        PlayerFragment playerFragment = (PlayerFragment) fm.findFragmentById(R.id.fragment_container);

        if (playerFragment == null) {
            playerFragment = new PlayerFragment();
            fm.beginTransaction().add(R.id.fragment_container, playerFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
