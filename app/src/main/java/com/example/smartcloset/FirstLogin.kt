package com.example.smartcloset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.login.*

class FirstLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        login.setOnClickListener{}
        findid.setOnClickListener{}
        findpw.setOnClickListener{}
        register.setOnClickListener{}
    }

}