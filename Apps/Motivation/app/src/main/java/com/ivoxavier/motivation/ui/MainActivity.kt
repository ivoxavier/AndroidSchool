package com.ivoxavier.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ivoxavier.motivation.R
import com.ivoxavier.motivation.data.Mock
import com.ivoxavier.motivation.infra.MotivationConstants
import com.ivoxavier.motivation.infra.SecurityPreferences
import com.ivoxavier.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        setListeners()

        handleFilter(R.id.image_all_inclusive)
        handleNextPhrase()
        showUserName()

        //Esconder a ActionBar
        supportActionBar?.hide()


    }
    override fun onResume() {
        super.onResume()
        showUserName()
    }
    private fun setListeners() {
        //Eventos
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAllInclusive.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.textName.setOnClickListener(this)
    }

    //tratamento de eventos
    override fun onClick(v: View) {
        if (v.id == R.id.button_new_phrase) {
            handleNextPhrase()
        }else if (v.id in listOf(
                R.id.image_all_inclusive,
                R.id.image_happy,
                R.id.image_sunny)) {
            handleFilter(v.id)
        }else if (v.id == R.id.text_name){
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    private fun handleNextPhrase() {
        binding.textPhrase.text = Mock().getPhrase(categoryId)
    }
    private fun handleFilter(id: Int){
        binding.imageAllInclusive.setColorFilter(ContextCompat.getColor(this, R.color.purple_700))

        when (id) {
            R.id.image_all_inclusive -> {
                binding.imageAllInclusive.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            else -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }
    }
    private fun showUserName() {
        val name =    SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textName.text = "Olá, $name!"
    }
}