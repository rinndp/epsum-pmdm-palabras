package com.rinndp.palabrasroom;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PalabraViewModel mWordViewModel;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewPalabras = findViewById(R.id.recyclerViewPalabras);
        PalabraRVAdapter adapter =new PalabraRVAdapter(new PalabraRVAdapter.WordDiff());
        recyclerViewPalabras.setAdapter(adapter);
        recyclerViewPalabras.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton addWord = findViewById(R.id.addWord);

        mWordViewModel.getPalabras().observe(this, words -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(words);
        });


        addWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                View inflator = layoutInflater.inflate(R.layout.custom_alert_dialog, null);
                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this)
                        .setTitle("Añadir palabra")
                        .setView(inflator)
                        .setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText palabraNuevaET = inflator.findViewById(R.id.addWordET);
                                String textoPalabraNueva = palabraNuevaET.getText().toString();
                                Palabra palabraNueva = new Palabra(textoPalabraNueva);
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
}