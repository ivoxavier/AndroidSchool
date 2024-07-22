package com.ivoxavier.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ivoxavier.motivation.infra.MotivationConstants
import com.ivoxavier.motivation.R
import com.ivoxavier.motivation.infra.SecurityPreferences
import com.ivoxavier.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener(this)

        supportActionBar?.hide()

        handleSave()
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.button_save) {
            val name = binding.editTextName.text.toString()
            if (name.isNotEmpty()) {
                SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, R.string.enter_your_name, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun handleSave() {
        val name : String = binding.editTextName.text.toString()
        if(name == ""){
            Toast.makeText(this, R.string.enter_your_name, Toast.LENGTH_SHORT)
                .show()
        } else{
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)
            finish()
        }
    }

}