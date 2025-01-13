package com.rinndp.palabrasroom;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    LiveData<List<Palabra>> listPalabras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewPalabras = findViewById(R.id.recyclerViewPalabras);
        PalabraRVAdapter adapter =new PalabraRVAdapter(this, listPalabras);
        recyclerViewPalabras.setAdapter(adapter);
        recyclerViewPalabras.setLayoutManager(new LinearLayoutManager(this));
    }
}