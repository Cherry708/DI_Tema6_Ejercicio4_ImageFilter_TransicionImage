package com.example.di_tema4_practica1_ejercicio2.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.di_tema4_practica1_ejercicio2.ItemPlaces
import com.example.di_tema4_practica1_ejercicio2.ItemPlacesAdapter
import com.example.di_tema4_practica1_ejercicio2.R
import com.example.di_tema4_practica1_ejercicio2.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listaPlaces = ArrayList<ItemPlaces>()
        listaPlaces.add(ItemPlaces(R.drawable.image1, R.string.card1))
        listaPlaces.add(ItemPlaces(R.drawable.image2, R.string.card2))
        listaPlaces.add(ItemPlaces(R.drawable.image3, R.string.card3))
        listaPlaces.add(ItemPlaces(R.drawable.image4, R.string.card4))
        listaPlaces.add(ItemPlaces(R.drawable.image5, R.string.card5))
        listaPlaces.add(ItemPlaces(R.drawable.image6, R.string.card6))
        listaPlaces.add(ItemPlaces(R.drawable.image7, R.string.card7))
        listaPlaces.add(ItemPlaces(R.drawable.image8, R.string.card8))
        listaPlaces.add(ItemPlaces(R.drawable.image9, R.string.card9))

        val recView = binding.recView
        recView.setHasFixedSize(true)

        val adaptador = ItemPlacesAdapter(listaPlaces)
        recView.adapter = adaptador
        recView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        //Que sucede en onLongClick
        adaptador.onLongClick = { view ->
            view.startActionMode(modeCallBack)
        }

        return root
    }

    /*
    Barra de opciones
     */
    var modeCallBack: ActionMode.Callback = object : ActionMode.Callback {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            val id = item?.itemId
            when (id) {
                R.id.action_editar -> {
                    Log.i("MainActivity", "editar")
                    mode?.finish()
                }
                R.id.action_eliminar -> {
                    Log.i("MainActivity", "eliminar")
                    mode?.finish()
                }
                R.id.action_compartir -> {
                    Log.i("MainActivity", "compartir")
                    mode?.finish()
                }
                else -> return false
            }
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            var mode = mode
            mode = null
        }

        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            mode.setTitle("Options")
            mode.getMenuInflater().inflate(R.menu.menu_context, menu)
            return true
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}