package com.zerdasoftware.loginsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.zerdasoftware.loginsignup.data.UserPreferences
import com.zerdasoftware.loginsignup.ui.auth.AuthActivity
import com.zerdasoftware.loginsignup.ui.home.HomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)

        userPreferences.authToken.asLiveData().observe(this) {
            println("MainActivity: Token $it")
            val activity = if (it == null) AuthActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)
        }
    }
}