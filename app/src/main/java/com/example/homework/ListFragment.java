package com.example.homework;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListFragment extends Fragment {

    private static String SAVED_NUMBERS = "saved:numbers";

    private NumberAdapter numberAdapter = null;
    private ListFragmentListener listener = null;
    private Integer numbers = null;

    private Bundle savedViewState = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("LIST_FRAGMENT VIEW CREATE STARTED");
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    // Saving in case of screen rotation
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (numberAdapter != null) {
            outState.putInt(SAVED_NUMBERS, numberAdapter.getItemCount());
        }
    }

    // Saving in case of fragment replacement
    @Override
    public void onDestroyView() {
        System.out.println("LIST_FRAGMENT VIEW DESTROYED");
        super.onDestroyView();
        savedViewState = new Bundle();
        savedViewState.putInt(SAVED_NUMBERS, numberAdapter.getItemCount());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("LIST_FRAGMENT VIEW CREATE ENDED");

        int rows = 3;
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            rows = 4;
        }


        if (savedViewState != null) {
            System.out.println("Getting numbers from saved state");
            numbers = savedViewState.getInt(SAVED_NUMBERS);
        } else if (savedInstanceState != null) {
            System.out.println("Getting numbers from new state");
            numbers = savedInstanceState.getInt(SAVED_NUMBERS);
        } else{
            numbers = 100;
        }


        System.out.println("Numbers: " + Integer.toString(numbers));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), rows));

        numberAdapter = new NumberAdapter(numbers, listener);
        recyclerView.setAdapter(numberAdapter);

        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(viewHolder ->
            numberAdapter.addItem()
        );
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        System.out.println("LIST_FRAGMENT ATTACHED");

        if (context instanceof ListFragmentListener) {
            listener = (ListFragmentListener) context;
        }
    }

    @Override
    public void onDetach() {
        listener = null;
        System.out.println("LIST_FRAGMENT DEATTACHED");
        super.onDetach();
    }
}


