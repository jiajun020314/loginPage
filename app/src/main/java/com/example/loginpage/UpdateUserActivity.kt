package com.example.loginpage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class UpdateUserActivity : AppCompatActivity() {

    private lateinit var  firebaseAuth : FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_user)

        firebaseAuth = FirebaseAuth.getInstance()

        val bundle: Bundle? = intent.extras
        val username = bundle?.getString("username")
        val phoneNumber = bundle?.getString("phone number")
        val address = bundle?.getString("address")
        val userID = bundle?.getString("UserId")

        val updateUser = findViewById<EditText>(R.id.updateUser)
        updateUser.setText(username)
        val updatePhone = findViewById<EditText>(R.id.updateMobile)
        updatePhone.setText(phoneNumber)
        val updateAdd = findViewById<EditText>(R.id.updateAddress)
        updateAdd.setText(address)
        val viewUserId = findViewById<TextView>(R.id.showUserId)
        viewUserId.text = userID



        val upButton = findViewById<Button>(R.id.adminUpdate)

        upButton.setOnClickListener {
            val updatedUsername = updateUser.text.toString()
            val updatedPhoneNumber = updatePhone.text.toString()
            val updatedAddress = updateAdd.text.toString()
            val userId = viewUserId.text.toString()


            val databaseReference = FirebaseDatabase.getInstance().reference.child("Users").child(userId)
            databaseReference.child("username").setValue(updatedUsername)
            databaseReference.child("phone").setValue(updatedPhoneNumber)
            databaseReference.child("address").setValue(updatedAddress)

            Toast.makeText(this,"Updated Successfully!",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AdminCrudUsers::class.java)
            startActivity(intent)
        }

        val backBtn = findViewById<Button>(R.id.adminBack)
        backBtn.setOnClickListener{
            val intent = Intent(this, AdminCrudUsers::class.java)
            startActivity(intent)
        }







    }
}