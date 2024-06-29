package com.ivoxavier.gastosviagem

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.view.View;
import android.widget.Toast;
import androidx.core.view.WindowInsetsCompat
import com.ivoxavier.gastosviagem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    ///para que os componetes sejam acedidos dentro da classe e em funções
    /*
    Adicionado no build.gradle
    * buildFeatures {
        viewBinding = true
    }
    *
    * */
    private lateinit var binding: ActivityMainBinding

  /*Função para criar a MainAcitivity*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnCalc.setOnClickListener(this)
    }

    /*Função para tratar qualquer evento onClick*/
    override fun onClick(view: View) {
    if (view.id == R.id.btn_calc) {
        calculate()

    }
    }

    private fun isValidate(): Boolean {
        return (binding.edtDst.text.toString() != ""
                && binding.edtPrc.text.toString() != ""
                && binding.edtAut.text.toString() != ""
                && binding.edtAut.text.toString().toFloat() != 0f)
    }

    private fun calculate() {
        if(isValidate()){
            val distance = binding.edtDst.text.toString().toFloat()
            val preco = binding.edtPrc.text.toString().toFloat()
            val autonomia = binding.edtAut.text.toString().toFloat()
            val total = (distance * preco) / autonomia
            val totalStr = "R$ ${"%.2f".format(total)}"
            binding.labelValor.text = totalStr
        }else{
            Toast.makeText(this, R.string.validation, Toast.LENGTH_SHORT).show()
        }


    }
}