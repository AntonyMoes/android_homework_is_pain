package com.example.homework;

import android.content.Context;
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

    private Bundle savedViewState = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("Saving state to brand new");
        outState.putInt(SAVED_NUMBERS, numberAdapter.getItemCount());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("Saving state to saved bundle");
        savedViewState = new Bundle();
        savedViewState.putInt(SAVED_NUMBERS, numberAdapter.getItemCount());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Integer numbers = null;
        if (savedViewState != null) {
            System.out.println("Restoring state from saved bundle");
            numbers = savedViewState.getInt(SAVED_NUMBERS);
        } else if (savedInstanceState != null) {
            System.out.println("Restoring state from brand new bundle");
            numbers = savedInstanceState.getInt(SAVED_NUMBERS);
        } else {
            numbers = 100;
        }

        System.out.println("Numbers: " + numbers.toString());

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

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

        if (context instanceof ListFragmentListener) {
            listener = (ListFragmentListener) context;
        }
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }
}


