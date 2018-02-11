package com.henrique.olxapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.henrique.olxapp.FormAnuncioActivity;
import com.henrique.olxapp.R;
import com.henrique.olxapp.model.Anuncio;

import java.util.List;

import io.objectbox.Box;

//Created by enriq on 10/02/2018.

public class AnuncioRVAdapter extends RecyclerView.Adapter<AnuncioRVAdapter.ViewHolder> {

    private Context context;
    private List<Anuncio> anuncios;
    private Box<Anuncio> anuncioBox;

    public AnuncioRVAdapter(Context context, List<Anuncio> anuncios, Box<Anuncio> anuncioBox) {
        this.context = context;
        this.anuncios = anuncios;
        this.anuncioBox = anuncioBox;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvAnuncioDescricao;
        private TextView tvAnuncioValor;
        private TextView tvAnuncioLugar;

        public ViewHolder(View itemView) {
            super(itemView);

            tvAnuncioDescricao = itemView.findViewById(R.id.tv_anuncio_descricao);
            tvAnuncioValor = itemView.findViewById(R.id.tv_anuncio_valor);
            tvAnuncioLugar = itemView.findViewById(R.id.tv_anuncio_lugar);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_anuncio_rv, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        Anuncio anuncio = this.anuncios.get(position);

        viewHolder.tvAnuncioDescricao.setText(anuncio.getDescricao());
        viewHolder.tvAnuncioValor.setText(anuncio.getValor());
        viewHolder.tvAnuncioLugar.setText(anuncio.getLugar());

        configurarClick(viewHolder.itemView, anuncio, position);

    }

    @Override
    public int getItemCount() {
        return this.anuncios.size();
    }

    private void configurarClick(View itemView, final Anuncio anuncio, int position) {

        itemView.setOnLongClickListener((view ) -> {
            PopupMenu popup = new PopupMenu(context, view);
            popup.getMenuInflater().inflate(R.menu.popup_menu_lista_anuncio, popup.getMenu());

            popup.setOnMenuItemClickListener((item) -> {

                switch (item.getItemId()){
                    case R.id.opcao_editar:
                        acaoEditar(itemView, anuncio, position);
                        break;
                    case R.id.opcao_remover:
                        acaoRemover(itemView, anuncio, position);
                        break;
                }

                return false;
            });

            popup.show();

            return true;
        });

    }

    private void acaoRemover(View view, Anuncio anuncio, int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);

        builder.setTitle("OlxApp");
        builder.setMessage("Confirma a remoção de: " + anuncio.getDescricao()+ "?");
        builder.setPositiveButton("SIM", (dialog, which) -> {
            // remover só em caso de "SIM"
            this.anuncios.remove(anuncio);
            this.anuncioBox.remove(anuncio);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
            Snackbar.make(view, "Anúncio removido: " + anuncio.getDescricao(),
                    Snackbar.LENGTH_LONG).show();
        });
        builder.setNegativeButton("NÃO", (dialog, which) -> {
            Snackbar.make(view, "Anúncio não removido", Snackbar.LENGTH_LONG).show();
        });

        builder.create().show();

    }

    private void acaoEditar(View view, Anuncio anuncio, int position) {

        // Enviar o id do Celular selecionado
        Intent intent = new Intent(context, FormAnuncioActivity.class);
        intent.putExtra("idAnuncio", anuncio.getId());

        //Iniciar o formulario
        context.startActivity(intent);

        //Avisar à intent que um item mudou.
        notifyItemChanged(position);

    }
}
