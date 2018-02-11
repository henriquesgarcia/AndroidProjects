package com.henrique.olxapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.henrique.olxapp.adapters.AnuncioRVAdapter;
import com.henrique.olxapp.model.Anuncio;

import java.util.List;

import io.objectbox.Box;

public class MainActivity extends AppCompatActivity {

    Box<Anuncio> anuncioBox;
    RecyclerView rvAnuncios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anuncioBox = ((App) getApplication()).getBoxStore().boxFor(Anuncio.class);

        // binding
        rvAnuncios = findViewById(R.id.rv_anuncios);

    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Anuncio> anuncios = anuncioBox.getAll();
        AnuncioRVAdapter adapter = new AnuncioRVAdapter(this, anuncios, anuncioBox);

        rvAnuncios.setAdapter(adapter);
        rvAnuncios.setLayoutManager(new LinearLayoutManager(this));
        rvAnuncios.hasFixedSize();

    }

    public void novoAnuncio(View view){

        startActivity(new Intent(this, FormAnuncioActivity.class));
    }
}
