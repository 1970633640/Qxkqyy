package com.qxkqyy

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.R.id.edit
import android.content.Context
import android.content.SharedPreferences.Editor
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences



class Login2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        val button1 = findViewById(R.id.login)
        button1.setOnClickListener(){
            val preferences = getSharedPreferences("user", 0)
            val editor = preferences.edit()
            editor.putString("logged","1")
            editor.commit()
            finish()
        }
    }


    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.leftout2, R.anim.leftin2)
    }
}
