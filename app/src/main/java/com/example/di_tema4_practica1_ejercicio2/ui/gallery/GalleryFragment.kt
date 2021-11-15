package com.example.di_tema4_practica1_ejercicio2.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        adaptador.onClick = {}

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}