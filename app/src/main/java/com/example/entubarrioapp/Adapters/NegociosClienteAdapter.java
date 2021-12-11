package com.example.entubarrioapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entubarrioapp.Entidades.Negocio;
import com.example.entubarrioapp.R;

import java.util.ArrayList;

public class NegociosClienteAdapter extends RecyclerView.Adapter<NegociosClienteAdapter.NegocioViewHolder> {

    private ArrayList<Negocio> listaNegocios;

    public NegociosClienteAdapter(ArrayList<Negocio> listaNegocios) {
        this.listaNegocios = listaNegocios;
    }

    @NonNull
    @Override
    public NegocioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_producto, null, false);
        return new NegocioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NegocioViewHolder holder, int position) {
        holder.tvNombreN.setText(listaNegocios.get(position).getNombreN());
        holder.tvDireccionN.setText(listaNegocios.get(position).getDireccionN());
        holder.tvTelefonoN.setText(listaNegocios.get(position).getTelefonoN());
        holder.tvPagWebN.setText(listaNegocios.get(position).getPagWebN());
    }

    @Override
    public int getItemCount() {
        return listaNegocios.size();
    }

    public class NegocioViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombreN, tvDireccionN, tvTelefonoN, tvPagWebN;

        public NegocioViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombreN = itemView.findViewById(R.id.tv_ProdNombre);
            tvDireccionN = itemView.findViewById(R.id.tv_ProdDesc);
            tvTelefonoN = itemView.findViewById(R.id.tv_ProdPrecio);
            tvPagWebN = itemView.findViewById(R.id.tv_ProdCant);
        }
    }
}
