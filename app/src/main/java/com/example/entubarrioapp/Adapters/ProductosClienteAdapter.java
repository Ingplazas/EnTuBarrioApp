package com.example.entubarrioapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entubarrioapp.Entidades.Producto;
import com.example.entubarrioapp.R;

import java.util.ArrayList;

public class ProductosClienteAdapter extends RecyclerView.Adapter<ProductosClienteAdapter.ProductoViewHolder> {

    private ArrayList<Producto> listaProductos;

    public ProductosClienteAdapter(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_prodcomprar, null, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.tvNombreP.setText(listaProductos.get(position).getNombreP());
        holder.tvDescP.setText(listaProductos.get(position).getDescripcionP());
        holder.tvPrecioP.setText("$ " + listaProductos.get(position).getPrecioP().toString());
        holder.tvCantidadP.setText(String.valueOf("Disponibles: " + listaProductos.get(position).getCantidadP()));
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombreP, tvDescP, tvPrecioP, tvCantidadP;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombreP = itemView.findViewById(R.id.tv_ProdNombre);
            tvDescP = itemView.findViewById(R.id.tv_ProdDesc);
            tvPrecioP = itemView.findViewById(R.id.tv_ProdPrecio);
            tvCantidadP = itemView.findViewById(R.id.tv_ProdCant);
        }
    }
}
