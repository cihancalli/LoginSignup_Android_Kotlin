package com.zerdasoftware.loginsignup

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.asLiveData
import com.auth0.android.jwt.JWT
import com.zerdasoftware.loginsignup.data.UserPreferences
import com.zerdasoftware.loginsignup.data.model.DateModel
import com.zerdasoftware.loginsignup.ui.auth.AuthActivity
import com.zerdasoftware.loginsignup.ui.home.HomeActivity
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var jwt : JWT

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)

        userPreferences.authToken.asLiveData().observe(this) {
            jwt = JWT(it.toString())
            val activity = if (it == null || isExpired()) AuthActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun jwtPayload() {
        val header = jwt.header
        val signature = jwt.signature
        val isExpired = jwt.isExpired(10)
        val username = getClaim("username")
        val email = getClaim("email")
        val uid = getClaim("uid")
        val iat = dateParse(convertTimeStampToDateTime(getClaim("iat").toLong()))

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun isExpired() :Boolean {
        val exp = dateParse(convertTimeStampToDateTime(getClaim("exp").toLong()))
        val currentTime = getToday()

        return exp.year <= currentTime.year && exp.month <= currentTime.month && exp.day <= currentTime.day && exp.hour <= currentTime.hour && exp.minute <= currentTime.minute
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getToday(): DateModel {
        return DateModel(
            LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")).toInt(),
            LocalDate.now().format(DateTimeFormatter.ofPattern("MM")).toInt(),
            LocalDate.now().format(DateTimeFormatter.ofPattern("dd")).toInt(),
            Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
            Calendar.getInstance().get(Calendar.MINUTE)
        )
    }

    fun dateParse(exp: String) : DateModel {
        return DateModel(
            exp.split('-')[0].toInt(),
            exp.split('-')[1].toInt(),
            exp.split('-')[2].split('T')[0].toInt(),
            exp.split('-')[2].split('T')[1].split(':')[0].toInt(),
            exp.split('-')[2].split('T')[1].split(':')[1].toInt()
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertTimeStampToDateTime(date:Long) :String {
        return Instant.ofEpochSecond(date)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime().toString()
    }

    fun getClaim(data:String):String {
        return jwt.getClaim(data).asString()!!
    }
}