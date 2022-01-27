package com.example.di_tema4_practica1_ejercicio2

import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.RecyclerView

class ItemContactsAdapter(var listaItems: ArrayList<ItemContacts>) :
    RecyclerView.Adapter<ItemContactsAdapter.ItemViewHolder>() {
    /*Sustituimos onClick con on LongClick*/
    lateinit var onLongClick : (View) -> Unit

    //La clase declarada como itemViewHolder hereda, es, un ViewHolder
    class ItemViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView)
        /*es: View.OnCreateContextMenuListener*/ {

        /*
        Si son privadas no son accesibles en onBindViewHolder
        y no se podran usar.
        */
        var imagen: ImageFilterView
        var titulo: TextView

        init {
            titulo = itemView.findViewById(R.id.contactText)
            imagen = itemView.findViewById(R.id.contactImageFilter)
            //itemView.setOnCreateContextMenuListener(this)

        }

        fun bindTarjeta(item: ItemContacts, onLongClick: (View) -> Unit) = with(itemView) {
            var isCrossfaded = false
            titulo.setText(item.contacText)
            imagen.setImageResource(item.contactImageFilter)
            imagen.setOnClickListener {
                //TODO: implementar animacion, o bien cambiando el layout a motion o con logica aqui
                if(!isCrossfaded) {
                    //La rotacion no tiene animacion
                    imagen.rotationY = 180f
                    imagen.crossfade = 1f
                    isCrossfaded = true
                } else if(isCrossfaded){
                    imagen.crossfade = 0f
                    isCrossfaded = false
                }
                Toast.makeText(context, "ImageClicked", Toast.LENGTH_SHORT).show()
            }

            setOnLongClickListener { onLongClick(itemView)
                true }
        }


        //Menu contextual
        /*
        override fun onCreateContextMenu(
            contextMenu: ContextMenu,
            view: View,
            contextMenuInfo: ContextMenu.ContextMenuInfo?
        ) {
            contextMenu.add(0, 0, adapterPosition, "Editar")     //groupId, itemId, order, title
            contextMenu.add(0, 1, adapterPosition, "Eliminar")
            contextMenu.add(0, 2, adapterPosition, "Compartir")
        }
         */
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_contacts, viewGroup, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, pos: Int) {
        val itemContacts = listaItems.get(pos)
        viewHolder.bindTarjeta(itemContacts, onLongClick)

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