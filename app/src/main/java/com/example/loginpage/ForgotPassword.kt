package com.example.loginpage

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginpage.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()


        binding.resetBtn.setOnClickListener{
            val strEmail = binding.forgotEmail.text.toString().trim()
            if(strEmail.isEmpty()){
                resetPassword()
            }else{
                Toast.makeText(this,"Field cannot be empty",Toast.LENGTH_SHORT).show()
            }
        }





    }

    private fun resetPassword(){
        val strEmail = binding.forgotEmail.text.toString().trim()
        firebaseAuth.sendPasswordResetEmail(strEmail).addOnCompleteListener {
            Toast.makeText(this,"Reset Password link has been sent to your registered Email",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
            .addOnFailureListener{
                Toast.makeText(this,"INVALID EMAIL",Toast.LENGTH_SHORT).show()
            }


    }

}


