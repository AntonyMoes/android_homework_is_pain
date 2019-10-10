package com.example.homework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OneNumberFragment extends Fragment {

    private static String ARGS_NUMBER = "args:number";
    private static String ARGS_COLOR = "args:color";

    public static OneNumberFragment newInstance(String number, int color) {
        OneNumberFragment fragment = new OneNumberFragment();

        Bundle bundle = new Bundle();
        bundle.putString(ARGS_NUMBER, number);
        bundle.putInt(ARGS_COLOR, color);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.one_number_fragment, container, false);

        TextView textView = view.findViewById(R.id.one_number);

        String number = getArguments().getString(ARGS_NUMBER);
        int color = getArguments().getInt(ARGS_COLOR);

        textView.setText(number);
        textView.setTextColor(color);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
