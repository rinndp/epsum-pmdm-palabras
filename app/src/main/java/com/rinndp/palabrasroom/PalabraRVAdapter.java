package com.rinndp.palabrasroom;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class PalabraRVAdapter extends ListAdapter<Palabra, PalabraRVAdapter.WordViewHolder> {
    Context context;
    View view;
    PalabraViewModel mWordViewModel;

    public PalabraRVAdapter(@NonNull DiffUtil.ItemCallback<Palabra> diffCallback, Context context) {
        super(diffCallback);
        this.context = context;

    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        this.view = inflater.inflate(R.layout.cv_row_word, parent, false);
        return WordViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        Palabra currentPalabra = getItem(position);
        holder.bind(currentPalabra.getPalabra());

        holder.botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context)
                        .setTitle("Eliminar palabra")
                        .setMessage("¿Está seguro que desea eliminar la palabra '"+currentPalabra.getPalabra()+"'")
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mWordViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(PalabraViewModel.class);
                                mWordViewModel.delete(currentPalabra);
                                notifyItemRemoved(holder.getAdapterPosition());
                                Toast toast = Toast.makeText(context, "Se ha borrado la palabra correctamente", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}
                        });
            materialAlertDialogBuilder.show();
            }
        });

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
        ImageView botonEliminar;

        public WordViewHolder (@NonNull View itemView) {
            super(itemView);
            tvPalabra = itemView.findViewById(R.id.palabraTV);
            botonEliminar = itemView.findViewById(R.id.deleteButton);
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
