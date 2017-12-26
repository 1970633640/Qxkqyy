package com.qxkqyy

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.R.menu
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu


class ListActivity : AppCompatActivity() {
    private var items = mutableListOf<doctor_result>(

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }

    }

    override fun onStart() {
        super.onStart()
        val recyclerview: RecyclerView = findViewById(R.id.id_resultlist) as RecyclerView
        recyclerview.adapter = Listadapter(applicationContext,items, R.layout.search_result)
        recyclerview.layoutManager = LinearLayoutManager(applicationContext)
        //recyclerview.layoutManager = GridLayoutManager(context,2)
        recyclerview.itemAnimator = DefaultItemAnimator()
        val test: doctor_result= doctor_result(Doctor("乔秀娥(名誉主任)","擅长项目：成人及儿童正畸治疗，尤其擅长前突及反颌的治疗。","http://qxkqyy.v1.1252.cn/uploadfile/2016/0111/20160111074738209.jpg"))
        val test1: doctor_result= doctor_result(Doctor("张蕾(主治医师)","擅长项目：各类错颌畸形的矫治，尤其擅长成人深覆颌、深覆盖对刃的治疗。","http://qxkqyy.v1.1252.cn/uploadfile/2016/0111/20160111074725966.jpg"))
        val test2: doctor_result= doctor_result(Doctor("贾兰(主治医师)","擅长项目：各类常见牙齿畸形的治疗。","http://qxkqyy.v1.1252.cn/uploadfile/2016/0111/20160111074711990.jpg"))
        val test3: doctor_result= doctor_result(Doctor("张攀(副主任医师)","擅长项目：儿童正畸早期治疗，成年人短期正畸治疗，新型无托槽隐形矫治及牙周病正畸治疗。","http://qxkqyy.v1.1252.cn/uploadfile/2016/0113/20160113025711770.jpg"))
        val test4: doctor_result= doctor_result(Doctor("赵廷(主治医师)","擅长项目：青少年正畸治疗，儿童反颌治疗，新型无托槽隐形矫治等。","http://qxkqyy.v1.1252.cn/uploadfile/2016/0111/20160111074637416.jpg"))
        val test5: doctor_result= doctor_result(Doctor("赵宇翔(副主任医师)","擅长项目：各类错颌畸形的治疗，尤其擅长青少年“兜齿”，成人双牙列前突，埋伏牙的治疗。","http://qxkqyy.v1.1252.cn/uploadfile/2016/0111/20160111074620417.jpg"))

        items.add(test)
        items.add(test1)
        items.add(test2)
        items.add(test3)
        items.add(test4)
        items.add(test5)

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)//指定Toolbar上的视图文件
        return true
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.upin, R.anim.upout2)
    }
}
