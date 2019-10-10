package com.example.homework;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberHolder> {

    private int limit;
    private ListFragmentListener listener;

    NumberAdapter(int limit, ListFragmentListener listener) {
        this.limit = limit;
        this.listener = listener;
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
        int number = position + 1;
        holder.bindNumber(number, getColorByNumber(number));
    }

    @Override
    public int getItemCount() {
        return limit;
    }

    public void addItem() {
        this.notifyItemChanged(++limit);
    }

    private int getColorByNumber(int number) {
        if (number % 2 == 0) {
            return R.color.even;
        } else {
            return R.color.odd;
        }
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
            TextView textView = v.findViewById(R.id.number);
            listener.onListElementClicked(textView.getText().toString(), textView.getCurrentTextColor());
        }

        void bindNumber(int number, int color) {
            TextView textView = view.findViewById(R.id.number);
            textView.setText(Integer.toString(number));
            textView.setTextColor(ContextCompat.getColor(view.getContext(), color));
        }
    }
}
