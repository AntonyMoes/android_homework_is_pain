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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int numbers = 100;
        if (savedInstanceState != null) {
            numbers = savedInstanceState.getInt(SAVED_NUMBERS);
        }
        numberAdapter = new NumberAdapter(numbers, listener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (numberAdapter != null) {
            outState.putInt(SAVED_NUMBERS, numberAdapter.getItemCount());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        int cols = getResources().getInteger(R.integer.cols);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), cols));
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


