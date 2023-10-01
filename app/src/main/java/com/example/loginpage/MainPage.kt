package com.example.loginpage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loginpage.databinding.ActivityMainPageBinding


class MainPage : AppCompatActivity() {

    private lateinit var binding: ActivityMainPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.UserLogin.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.AdminLogin.setOnClickListener {
            val intent = Intent(this, AdminLogin::class.java)
            startActivity(intent)
        }




    }
}