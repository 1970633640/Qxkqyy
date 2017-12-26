package com.qxkqyy

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import android.content.DialogInterface
import android.support.v7.app.AlertDialog




class AccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)


        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
                finish()
        }
        val button1=findViewById(R.id.logout)
        button1.setOnClickListener(){
            val builder = AlertDialog.Builder(this)  //先得到构造器
            builder.setTitle("退出账号") //设置标题
            builder.setMessage("\n确认退出账号?\n") //设置内容
            builder.setPositiveButton("确定", DialogInterface.OnClickListener { dialog, _ ->
                //设置确定按钮
                dialog.dismiss() //关闭dialog
                val preferences = getSharedPreferences("user", 0)
                val editor = preferences.edit()
                editor.putString("logged","0")
                editor.commit()
                finish()
            })
            builder.setNegativeButton("取消", DialogInterface.OnClickListener { dialog, which ->
                //设置取消按钮
                dialog.dismiss()
            })
            //参数都设置完成了，创建并显示出来
            builder.create().show()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.leftout2, R.anim.leftin2)
    }
}
