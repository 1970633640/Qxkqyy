package com.qxkqyy

import android.content.Context
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.facebook.drawee.backends.pipeline.Fresco
import com.donkingliang.banner.CustomBanner
import com.facebook.drawee.view.SimpleDraweeView
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import android.content.Intent

import com.facebook.drawee.drawable.ScalingUtils
import android.content.SharedPreferences
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private var mBanner: CustomBanner<String>? = null
    private fun setBean(beans: List<String>) {
        mBanner?.setPages(object : CustomBanner.ViewCreator<String> {
            override fun createView(context: Context, position: Int): View {
                val imageView = SimpleDraweeView(context)
                imageView.hierarchy.setPlaceholderImage(R.drawable.picture, ScalingUtils.ScaleType.CENTER_INSIDE)
                return imageView
            }

            override fun updateUI(context: Context, view: View, position: Int, entity: String) {
                val uri1 = Uri.parse("android.resource://" + context.packageName + "/" + R.drawable.picture)
                val uri = Uri.parse(entity)
                val draweeView = view as SimpleDraweeView
                draweeView.setImageURI(uri);
            }

        }, beans)
                //                //设置指示器为普通指示器
                //                .setIndicatorStyle(CustomBanner.IndicatorStyle.ORDINARY)
                //                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                //                .setIndicatorRes(R.drawable.shape_point_select, R.drawable.shape_point_unselect)
                //                //设置指示器的方向
                //                .setIndicatorGravity(CustomBanner.IndicatorGravity.CENTER)
                //                //设置指示器的指示点间隔
                //                .setIndicatorInterval(20)
                //设置自动翻页
                ?.scrollDuration = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val textView1 = findViewById(R.id.textView1)
        textView1.setOnClickListener {
            //            val intent = Intent(Intent.ACTION_INSERT)
//                    .setData(Events.CONTENT_URI)
//                    .putExtra(Events.TITLE, "把预约添加到日程表")
//                    .putExtra(Events.EVENT_LOCATION, "预约的地点")
//                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, 1513739532000)//unix时间戳毫秒
//                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, 1513743132000)
//            if (intent.resolveActivity(packageManager) != null) {
//                startActivity(intent)
//            }
            startActivity(Intent(overridePendingTransition@ this, ScheduleActivity::class.java))
            overridePendingTransition(R.anim.upout, R.anim.upin)
        }
        val account = findViewById(R.id.account)
        account.setOnClickListener {
            startActivity(Intent(overridePendingTransition@ this, Login2Activity::class.java))
            overridePendingTransition(R.anim.leftout, R.anim.leftin)
        }
        val account2 = findViewById(R.id.account2)
        account2.setOnClickListener {
            startActivity(Intent(overridePendingTransition@ this, AccountActivity::class.java))
            overridePendingTransition(R.anim.leftout, R.anim.leftin)
        }
        val drlist = findViewById(R.id.doctorList)
        drlist.setOnClickListener {
            startActivity(Intent(overridePendingTransition@ this, ListActivity::class.java))
            overridePendingTransition(R.anim.upout, R.anim.upin)
        }
        val chat = findViewById(R.id.chat)
        chat.setOnClickListener {
            val preferences = getSharedPreferences("user", 0)
            val logged = preferences.getString("logged", "0")
            if (logged == "1") {
                startActivity(Intent(overridePendingTransition@ this, BoardActivity::class.java))
                overridePendingTransition(R.anim.upout, R.anim.upin)
            } else {
                Toast.makeText(getApplicationContext(), "请登录后再留言！",
                        Toast.LENGTH_SHORT).show();
            }
        }
        val newtask = findViewById(R.id.newTask)
        newtask.setOnClickListener {
            val preferences = getSharedPreferences("user", 0)
            val logged = preferences.getString("logged", "0")
            if (logged == "1") {
                startActivity(Intent(overridePendingTransition@ this, NewActivity::class.java))
                overridePendingTransition(R.anim.upout, R.anim.upin)
            } else {
                Toast.makeText(getApplicationContext(), "请登录后再新建预约！",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    override fun onStart() {
        super.onStart()
        mBanner = findViewById(R.id.banner) as CustomBanner<String>
        val images = mutableListOf<String>()
        images.add("https://1970633640.github.io/webapp/banner1.png")
        images.add("https://1970633640.github.io/webapp/banner2.jpg")
        setBean(images)
        val account = findViewById(R.id.account)
        val account2 = findViewById(R.id.account2)
        val my1 = findViewById(R.id.my1)
        val my2 = findViewById(R.id.textView1)
        val preferences = getSharedPreferences("user", 0)
        val logged = preferences.getString("logged", "0")
        if (logged == "0") {
            account.visibility = View.VISIBLE
            account2.visibility = View.GONE
            my1.visibility = View.GONE;my2.visibility = View.GONE
        }
        if (logged == "1") {
            account2.visibility = View.VISIBLE
            my1.visibility = View.VISIBLE
            my2.visibility = View.VISIBLE
            account.visibility = View.GONE
        }
        mBanner?.setOnPageClickListener(CustomBanner.OnPageClickListener
        { i: Int, t: String -> startActivity(Intent(this, WebActivity::class.java)) })
    }

}
