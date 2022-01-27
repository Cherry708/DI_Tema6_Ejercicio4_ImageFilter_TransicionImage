package com.example.di_tema4_practica1_ejercicio2.ui.contacts

import android.icu.lang.UCharacter
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.di_tema4_practica1_ejercicio2.*
import com.example.di_tema4_practica1_ejercicio2.databinding.FragmentContactsBinding

class ContactsFragment : Fragment() {

    private lateinit var contactsViewModel: ContactsViewModel
    private var _binding: FragmentContactsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contactsViewModel =
            ViewModelProvider(this).get(ContactsViewModel::class.java)

        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listaContacts = ArrayList<ItemContacts>()
        listaContacts.add(ItemContacts(R.drawable.ic_mail, R.string.name1))
        listaContacts.add(ItemContacts(R.drawable.ic_mail, R.string.name2))
        listaContacts.add(ItemContacts(R.drawable.ic_mail, R.string.name3))
        listaContacts.add(ItemContacts(R.drawable.ic_mail, R.string.name4))
        listaContacts.add(ItemContacts(R.drawable.ic_mail, R.string.name5))
        listaContacts.add(ItemContacts(R.drawable.ic_mail, R.string.name6))
        listaContacts.add(ItemContacts(R.drawable.ic_mail, R.string.name7))
        listaContacts.add(ItemContacts(R.drawable.ic_mail, R.string.name8))
        listaContacts.add(ItemContacts(R.drawable.ic_mail, R.string.name9))
        listaContacts.add(ItemContacts(R.drawable.ic_mail, R.string.name10))

        val contactsRecView = binding.contactsRecView
        contactsRecView.setHasFixedSize(true)

        val adaptador = ItemContactsAdapter(listaContacts)
        contactsRecView.adapter = adaptador

        contactsRecView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)

        //Que sucede en onLongClick
        adaptador.onLongClick = { view ->
            view.startActionMode(modeCallBack)
        }

        //TODO: cambiar a onClick en el adaptador
        /*
            val item = lista[seleccionado]...
            startActivity..
            Copiar de Palette
         */
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