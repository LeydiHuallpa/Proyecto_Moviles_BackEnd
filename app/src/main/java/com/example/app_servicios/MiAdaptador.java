package com.example.app_servicios;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.ViewHolder> {
    private LayoutInflater inflador;
    private Vector<String> lista;
    private int[] colors;
    Context micontext;
    public MiAdaptador(Context context, Vector<String> lista, int[] colors) {
        this.lista = lista;
        this.colors = colors;
        micontext=context;
        inflador = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.miitem, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {
        String lst = lista.get(i);

        holder.titulo.setText(lista.get(i));
        String initial = lst.toUpperCase().substring(0, 1);
        holder.mInitialsTextView.setText(initial);
        holder.mInitialsBackground.setColor(colors[i % colors.length]);    }
    @Override
    public int getItemCount() {
        return lista.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo;
        private TextView mInitialsTextView;
        private GradientDrawable mInitialsBackground;
        ViewHolder(View itemView) {
            super(itemView);
            mInitialsTextView = itemView.findViewById(R.id.initialsTextView);
            mInitialsBackground = (GradientDrawable) mInitialsTextView.getBackground();
            titulo = (TextView)itemView.findViewById(R.id.titulo);
        }
    }
}
