package com.example.di_tema4_practica1_ejercicio2

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView

class ItemPlacesAdapter(var listaItems: ArrayList<ItemPlaces>) : RecyclerView.Adapter<ItemPlacesAdapter.ItemViewHolder>() {
    lateinit var onClick : (View) -> Unit


    //La clase declarada como itemViewHolder hereda, es, un ViewHolder
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /*
        Si son privadas no son accesibles en onBindViewHolder
        y no se podran usar.
        */
        var btnAceptar: Button
        var btnCancelar: Button
        var imagen: ImageView
        var titulo: TextView

        init {
            btnAceptar = itemView.findViewById(R.id.btnAceptar)
            btnCancelar = itemView.findViewById(R.id.btnCancelar)
            titulo = itemView.findViewById(R.id.tvTitulo)
            imagen = itemView.findViewById(R.id.ivImage)

        }

        fun bindTarjeta(item: ItemPlaces, onClick: (View) -> Unit) = with(itemView) {
            titulo.setText(item.titulo)
            imagen.setImageResource(item.imagen)
            setOnClickListener { onClick(itemView) }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_places, viewGroup, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, pos: Int) {
        val itemPlaces = listaItems.get(pos)
        viewHolder.bindTarjeta(itemPlaces, onClick)


        /*viewHolder.itemToolbar.setOnMenuItemClickListener(object: Toolbar.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem): Boolean{
                when(item.itemId){
                    (R.id.action_copiar) ->{
                        listaItems.add(viewHolder.adapterPosition, ItemCard(itemCard.imagen,itemCard.titulo))
                        notifyItemInserted(viewHolder.adapterPosition)
                    }
                    (R.id.action_eliminar) ->{
                        listaItems.removeAt(viewHolder.adapterPosition)
                        notifyItemRemoved(viewHolder.adapterPosition)
                    }
                }
                return true
            }

        })

         */
    }

    override fun getItemCount(): Int {
        return listaItems.size
    }
}