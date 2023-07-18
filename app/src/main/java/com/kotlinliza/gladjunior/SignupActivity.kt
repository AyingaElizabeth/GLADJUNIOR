package com.kotlinliza.gladjunior

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.voice.VoiceInteractionSession.VisibleActivityCallback
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.hide()

        auth= FirebaseAuth.getInstance()

        val signupTxt = findViewById<TextView>(R.id.log_txt)
        val signupBtn = findViewById<Button>(R.id.butt)
        val email = findViewById<TextInputEditText>(R.id.emailTxt)
        val password = findViewById<TextInputEditText>(R.id.passwordTxt)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val mainContainer = findViewById<LinearLayout>(R.id.main)


        signupTxt.setOnClickListener {
                startActivity(Intent(this,LoginActivity::class.java))
            }


        signupBtn.setOnClickListener {
            val emailString=email.text.toString()
            val passwordString=password.text.toString()


            progressBar.visibility=View.VISIBLE
            mainContainer.visibility=View.GONE
            if(emailString.isNotEmpty()||passwordString.isNotEmpty()){

                auth.createUserWithEmailAndPassword(emailString,passwordString)
                    .addOnCompleteListener(this){ task->
                        if (task.isSuccessful){
                            Toast.makeText(baseContext,"Signup Successfull",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,LoginActivity::class.java))
                        }else{
                            Toast.makeText(baseContext,"Failed to signup. Try again later",Toast.LENGTH_SHORT).show()
                        }

            }



        }else
            Toast.makeText(baseContext,"please fill in all the missing fields",Toast.LENGTH_SHORT).show()
            progressBar.visibility=View.GONE
            mainContainer.visibility=View.VISIBLE

    }


}}