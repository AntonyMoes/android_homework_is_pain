package com.example.homework;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ListFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("ACTIVITY CREATED");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new ListFragment())
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
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("ACTIVITY DESTROYED");
    }
}
