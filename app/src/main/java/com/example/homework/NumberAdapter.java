package com.example.homework;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberHolder> {
    private Integer limit;

    NumberAdapter(Integer limit) {
        this.limit = limit;
    }

    @NonNull
    @Override
    public NumberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.number_cell, parent, false);

        return new NumberHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberHolder holder, int position) {
        holder.bindNumber(position + 1);
    }

    @Override
    public int getItemCount() {
        return limit;
    }



    public class NumberHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View view;

        NumberHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

        void bindNumber(int number) {
            TextView textView = view.findViewById(R.id.number);
//            textView.setText(String.format(Locale.getDefault(),"%d", number));
            textView.setText(Integer.toString(number));
        }
    }
}
