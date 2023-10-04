package com.example.loginpage

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginpage.databinding.ActivityAddFeedbackBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AddFeedbackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddFeedbackBinding
    private lateinit var reference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = FirebaseAuth.getInstance().currentUser
        val userId = user?.uid
        val databaseReference = FirebaseDatabase.getInstance().reference.child("Orders")
        databaseReference.orderByChild("userID").equalTo(userId).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (orderSnapshot in dataSnapshot.children) {
                    val orderId = orderSnapshot.key // This is the orderId
                    // Do something with orderId, for example, print it
                    println("Order ID: $orderId")

                    if(orderId !=null){

                        binding.idOrder.text = "Order ID : $orderId"
                        binding.ratingBar.rating = 2.5f
                        binding.ratingBar.stepSize = .5f

                        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                            binding.ratingNum.text = "Rating : $rating"
                            binding.submitBtn.setOnClickListener {
                                reference = FirebaseDatabase.getInstance().getReference("Orders")
                                val user = FirebaseAuth.getInstance().currentUser
                                val userId = user?.uid
                                val feedback = binding.feedbackEditText.text.toString()

                                Toast.makeText(this@AddFeedbackActivity,"Submitted, Thank you for your response",Toast.LENGTH_SHORT).show()

                                val intent = Intent(this@AddFeedbackActivity, Feedback::class.java)
                                startActivity(intent)
                                finish()


                                if (userId != null && feedback != null) {
                                    if (userId == user?.uid) {
                                        reference.child(orderId).child("rating").setValue(rating)
                                        reference.child(orderId).child("feedback").setValue(feedback)

                                    } else {

                                    }
                                }
                            }
                        }
                    }
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors
                println("Error: ${databaseError.message}")
            }
        })


    }
}
