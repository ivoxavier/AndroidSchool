package com.ivoxavier.convidados.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ivoxavier.convidados.R
import com.ivoxavier.convidados.constants.DataBaseConstants
import com.ivoxavier.convidados.databinding.ActivityGuestFormBinding
import com.ivoxavier.convidados.model.GuestModel
import com.ivoxavier.convidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityGuestFormBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnSave.setOnClickListener(this)


        observe()
        loadData()


    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_save){
            val name = binding.editName.text.toString()
            val presence = binding.rdbPresent.isChecked

            val model = GuestModel().apply {
                this.id = guestId
                this.name = name
                this.presence = presence
            }

            viewModel.save(model)



        }
    }

    private fun observe(){
    viewModel.guest.observe(this, Observer {
        binding.editName.setText(it.name)
        if(it.presence){
            binding.rdbPresent.isChecked = true
        }else{
            binding.rdbAbsent.isChecked = true
        }
    })
        viewModel.saveGuest.observe(this, Observer {
            if(it){
                finish()
            }
        })
    }


    private fun loadData(){
        val bundle = intent.extras
        if(bundle != null){
            guestId = bundle.getInt(DataBaseConstants.Guest.ID)
            viewModel.get(guestId)
        }

    }
}