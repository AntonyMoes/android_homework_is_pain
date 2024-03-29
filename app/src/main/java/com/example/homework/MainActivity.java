package com.example.homework;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ListFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new ListFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onListElementClicked(String number, int color) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, OneNumberFragment.newInstance(number, color))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count <= 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
