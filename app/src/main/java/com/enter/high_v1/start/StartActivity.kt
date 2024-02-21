package com.enter.high_v1.start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.enter.high_v1.R

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        changeStartFragment(0)
    }

    fun changeStartFragment(index: Int) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        when (index) {
            0 -> {
                transaction.replace(R.id.lay_start_frame, StartChoiceFragment())
                fragmentManager.popBackStack()
            }
            1 -> transaction.replace(R.id.lay_start_frame, SignupFragment())
            2 -> transaction.replace(R.id.lay_start_frame, LoginFragment())
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun signupNext(id: String, pw: String, nick: String) {
        supportFragmentManager.beginTransaction()
            .add(R.id.lay_start_frame, SignupNickFragment(id, pw, nick)).commit()
    }
}