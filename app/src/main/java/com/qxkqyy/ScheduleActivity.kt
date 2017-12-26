package com.qxkqyy

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.provider.CalendarContract.Events
import android.widget.TextView
import android.R.attr.phoneNumber
import android.net.Uri
import com.facebook.drawee.view.SimpleDraweeView


class ScheduleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        val addCalender = findViewById(R.id.addCalender) as Button
        val tel = findViewById(R.id.DoctorTel) as TextView
        val pic = findViewById(R.id.picURL) as SimpleDraweeView
        val bbs = findViewById(R.id.DoctorBBS) as TextView
        addCalender.setOnClickListener {
            val intent = Intent(Intent.ACTION_INSERT)
                    .setData(Events.CONTENT_URI)
                    .putExtra(Events.TITLE, "把预约添加到日程表")
                    .putExtra(Events.EVENT_LOCATION, "预约的地点")
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, 1513739532000)//unix时间戳毫秒
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, 1513743132000)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {/*没有日历*/
            }
        }
        tel.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + "10000")
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
        bbs.setOnClickListener {
            startActivity(Intent(overridePendingTransition@ this, ChatActivity::class.java))
            overridePendingTransition(R.anim.upout, R.anim.upin)
        }
        val uri = Uri.parse("http://qxkqyy.v1.1252.cn/uploadfile/2016/0107/20160107083323831.jpg")
        pic.setImageURI(uri)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.upin, R.anim.upout2)
    }
}
