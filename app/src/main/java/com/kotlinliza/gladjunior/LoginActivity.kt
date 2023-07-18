package com.kotlinliza.gladjunior

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        //initiating firebase auth
        auth= FirebaseAuth.getInstance()

        //referencing
        val signupTxt = findViewById<TextView>(R.id.sign_txt)
        val loginBtn = findViewById<Button>(R.id.butt)
        val email = findViewById<TextInputEditText>(R.id.emailTxt)
        val password = findViewById<TextInputEditText>(R.id.passwordTxt)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val mainContainer = findViewById<LinearLayout>(R.id.main)

        //calling the signup text
        signupTxt.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
        }

        // calling the login button
        loginBtn.setOnClickListener {
            progressBar.visibility= View.VISIBLE
            mainContainer.visibility=View.GONE

            // creating a variable to get information from email as a text string
            val emailString=email.text.toString()
            val passwordString=password.text.toString()

            if(emailString.isNotEmpty()||passwordString.isNotEmpty()){
                // connecting to firebase auth using signin with email and passoword
                auth.signInWithEmailAndPassword(emailString,passwordString)
                    .addOnCompleteListener(this){ task ->
                        if(task.isSuccessful){
                            //if login is successful, do
                            startActivity(Intent(this,MainActivity::class.java))
                            Toast.makeText(
                                baseContext,
                                "login Successful",
                                Toast.LENGTH_SHORT
                            ).show()
                        }else{
                            //if login not successful
                            Toast.makeText(baseContext, "Please check your email or password", Toast.LENGTH_SHORT).show()
                        }
                    }

            }else{
                progressBar.visibility= View.GONE
                mainContainer.visibility=View.VISIBLE
                Toast.makeText(baseContext,"please fill in all the fields",Toast.LENGTH_SHORT).show()

            }


        }
    }
}