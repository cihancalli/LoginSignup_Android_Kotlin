package com.zerdasoftware.loginsignup


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.auth0.android.jwt.JWT
import com.zerdasoftware.loginsignup.data.UserPreferences
import com.zerdasoftware.loginsignup.ui.auth.AuthActivity
import com.zerdasoftware.loginsignup.ui.home.HomeActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this) {
            val activity = if (it == null || JWT(it.toString()).isExpired(10)) AuthActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)
        }
    }
}