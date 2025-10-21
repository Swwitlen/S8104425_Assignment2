package com.example.s8104425_assignment2.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.s8104425_assignment2.ui.login.LoginActivity

//MainActivity is the launcher activity of the app
//its sole purpose is to immediately redirect the user to the login screen
class MainActivity : AppCompatActivity() {
    //onCreate() is called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //start LoginActivity with an intent
        startActivity(Intent(this, LoginActivity::class.java))
        //finish MainActivity so it is removed from the back stack
        //this prevent the user from returning to this activity by pressing back
        finish()
    }
}