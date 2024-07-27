package com.ivoxavier.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivoxavier.convidados.constants.DataBaseConstants
import com.ivoxavier.convidados.databinding.FragmentPresentBinding
import com.ivoxavier.convidados.view.adapter.GuestAdapter
import com.ivoxavier.convidados.view.listener.OnGuestListener
import com.ivoxavier.convidados.viewmodel.GuestsViewModel

class PresentFragment : Fragment() {

    private var _binding: FragmentPresentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: GuestsViewModel
    private val adapter = GuestAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
         viewModel = ViewModelProvider(this)[GuestsViewModel::class.java]

        _binding = FragmentPresentBinding.inflate(inflater, container, false)

        //Layout
        binding.recyclerPresentGuests.layoutManager = LinearLayoutManager(context)

        //Adapter, cola entre lista e interface
        binding.recyclerPresentGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.Guest.ID,id)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getPresent()
            }

        }

        adapter.attachListener(listener)

        observe()


        return binding.root
    }


    override fun onResume() {
        super.onResume()
        viewModel.getPresent()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe(){
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}