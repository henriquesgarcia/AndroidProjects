package com.henrique.olxapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.henrique.olxapp.model.Anuncio;

import io.objectbox.Box;

//Created by enriq on 10/02/2018.

public class FormAnuncioActivity extends AppCompatActivity{

    private EditText editDescricao;
    private EditText editPreco;
    private EditText editLugar;

    private Box<Anuncio> anuncioBox;
    private Anuncio anuncio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_anuncio);

        // Box de anúncios
        anuncioBox = ((App) getApplication()).getBoxStore().boxFor(Anuncio.class);

        // Instanciar um anúncio vazio
        anuncio = new Anuncio();

        Intent intent = getIntent();
        long id = intent.getLongExtra("idAnuncio",-1);

        // binding
        editDescricao = findViewById(R.id.edit_descricao);
        editPreco = findViewById(R.id.edit_preco);
        editLugar = findViewById(R.id.edit_lugar);

        if (id != -1){
            anuncio = anuncioBox.get(id);
            editDescricao.setText(anuncio.getDescricao());
            editPreco.setText(anuncio.getValor());
            editLugar.setText(anuncio.getLugar());
        }
    }

    public void salvarAnuncio(View view){

        // obter dados inseridos nas EditText
        String descricao = editDescricao.getText().toString();
        String preco = editPreco.getText().toString();
        String lugar = editLugar.getText().toString();

        // preencher os atributos
        anuncio.setDescricao(descricao);
        anuncio.setValor(preco);
        anuncio.setLugar(lugar);

        // salvar ou atualizar
        anuncioBox.put(anuncio);

        // encerra
        finish();
    }
}
