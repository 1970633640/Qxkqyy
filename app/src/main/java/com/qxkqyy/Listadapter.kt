package com.qxkqyy

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import android.support.v7.widget.CardView

class Listadapter(val con: Context, val items: List<doctor_result>, val itemLayout: Int) :
        RecyclerView.Adapter<Listadapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.text1.text = items[position].doctor.Name
        holder.text2.text = items[position].doctor.Good
        val uri = Uri.parse(items[position].doctor.PicURL)
        holder.pic.setImageURI(uri)
        holder.card.setOnClickListener {
           // startActivity(con,Intent(con,Login2Activity::class.java))

        }
    }

    override fun getItemCount(): Int = items.size

    //class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // var image: ImageView
        var text1: TextView = itemView.findViewById(R.id.id_DoctorName) as TextView
        var text2: TextView = itemView.findViewById(R.id.DoctorSpecial) as TextView
        var card: CardView = itemView.findViewById(R.id.doctorcard) as CardView
        var pic: SimpleDraweeView = itemView.findViewById(R.id.picURL) as SimpleDraweeView

    }
}