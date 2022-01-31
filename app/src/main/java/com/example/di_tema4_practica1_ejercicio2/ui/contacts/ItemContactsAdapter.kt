package com.example.di_tema4_practica1_ejercicio2

import android.animation.ObjectAnimator
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.RecyclerView

class ItemContactsAdapter(var listaItems: ArrayList<ItemContacts>) :
    RecyclerView.Adapter<ItemContactsAdapter.ItemViewHolder>() {
    lateinit var onClick : (View) -> Unit

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

        fun bindTarjeta(item: ItemContacts, onClick: (View) -> Unit) = with(itemView) {
            var isCrossfaded = false
            titulo.setText(item.contactText)
            imagen.setImageResource(item.contactImageFilter)

            val startAnimation = ObjectAnimator.ofFloat(imagen, "rotationY",0f,180f )
            imagen.setOnClickListener {
                //TODO: implementar animacion, o bien cambiando el layout a motion o con logica aqui

                if(!isCrossfaded) {
                    startAnimation.duration = 1000
                    startAnimation.start()
                    startAnimation.doOnEnd {
                        imagen.crossfade = 1f
                        isCrossfaded = true }
                } else if(isCrossfaded){
                    startAnimation.duration = 1000
                    startAnimation.start()
                    startAnimation.doOnEnd {
                        imagen.crossfade = 0f
                        isCrossfaded = false }
                }
                Toast.makeText(context, "ImageClicked", Toast.LENGTH_SHORT).show()
            }
            setOnClickListener{ onClick(itemView) }
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
        viewHolder.bindTarjeta(itemContacts, onClick)

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