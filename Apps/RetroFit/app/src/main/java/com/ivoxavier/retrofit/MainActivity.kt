package com.ivoxavier.retrofit

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


// https://jsonplaceholder.typicode.com/posts   - retorna uma lista



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        val service = RetrofitClient.createService(PostService::class.java)
        val call: Call<List<PostEntity>> = service.list()
        call.enqueue(object: Callback<List<PostEntity>>{
            override fun onResponse(call: Call<List<PostEntity>>, r: Response<List<PostEntity>>)  {
               val list = r.body()
            }

            override fun onFailure(call: Call<List<PostEntity>>, t: Throwable) {
                val s = ""
            }
            })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}