package com.rinndp.palabrasroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PalabraRVAdapter extends ListAdapter<Palabra, PalabraRVAdapter.WordViewHolder> {

    ArrayList<Palabra> listaPalabras = new ArrayList<>();

    public PalabraRVAdapter (@NonNull DiffUtil.ItemCallback<Palabra> diffCallback) {
        super(diffCallback);
    }

    public void addWordListaPalabras(String mPalabra) {
        Palabra palabra = new Palabra(mPalabra);
        listaPalabras.add(palabra);
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return WordViewHolder.create(parent);
    }

    @NonNull
    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        Palabra current = listaPalabras.get(position);
        holder.bind(current.getPalabra());
    }

    static class WordDiff extends DiffUtil.ItemCallback<Palabra> {

        @Override
        public boolean areItemsTheSame(@NonNull Palabra oldItem, @NonNull Palabra newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Palabra oldItem, @NonNull Palabra newItem) {
            return oldItem.getPalabra().equals(newItem.getPalabra());
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    public static class WordViewHolder extends RecyclerView.ViewHolder {
        TextView tvPalabra;

        public WordViewHolder (@NonNull View itemView) {
            super(itemView);
            tvPalabra = itemView.findViewById(R.id.palabraTV);
        }

        public void bind(String text) {
            tvPalabra.setText(text);
        }

        static WordViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cv_row_word, parent, false);
            return new WordViewHolder(view);
        }
    }
}
