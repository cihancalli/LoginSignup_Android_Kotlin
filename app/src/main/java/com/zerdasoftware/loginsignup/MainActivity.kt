package com.zerdasoftware.loginsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.zerdasoftware.loginsignup.data.UserPreferences
import com.zerdasoftware.loginsignup.ui.auth.AuthActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)

        userPreferences.authToken.asLiveData().observe(this) {
            println("MainActivity: ${it} Token is Null")
            startActivity(Intent(this, AuthActivity::class.java))
        }

        /*
        finish()

         */
    }
}