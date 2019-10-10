package com.example.homework;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter<NumberAdapter.NumberHolder> numberAdapter = null;
    private Integer numbers = 100;
//    private DataAdapter mAdapter;
//    private int numberCount = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

//        recycleView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        numberAdapter = new NumberAdapter(numbers);
        recyclerView.setAdapter(numberAdapter);
    }
}
