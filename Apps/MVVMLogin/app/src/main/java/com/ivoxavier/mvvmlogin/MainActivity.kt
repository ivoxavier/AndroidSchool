package com.ivoxavier.mvvmlogin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ivoxavier.mvvmlogin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setObservers()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setObservers() {
        viewModel.welcome().observe(this, Observer {
            binding.textWelcome.text = it
            })
        viewModel.login().observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "SUCESSO", Toast.LENGTH_SHORT)
                    .show()
            }else{
                Toast.makeText(applicationContext, "FALHA", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

}