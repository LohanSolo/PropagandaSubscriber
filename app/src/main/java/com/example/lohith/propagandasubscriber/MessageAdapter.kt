package com.example.lohith.propagandasubscriber

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.app.Activity
import android.view.LayoutInflater
import android.widget.TextView
import android.annotation.SuppressLint
import android.content.SharedPreferences


class MessageAdapter: BaseAdapter {
    val MyPREFERENCES = "MyPrefs"
    val MessageContent = "ContentKey"
    val Color = "ColorKey"
    val MasterKey = "MasterKey"

    var messages: MutableList<Message> = ArrayList<Message>() // Convert to dictionary
    var context: Context? = null

    constructor(context: Context) : super() {
        this.context = context
    }

    fun add(message: Message) {
        this.messages.add(message)
        notifyDataSetChanged() // to render the list we need to notify
    }

    @SuppressLint("SetTextI18n")
    override fun getView(postion: Int, convertView: View?, parent: ViewGroup?): View {
        val messageInflater = context!!.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val (messageContent, color) = messages[postion]
        var setView =  messageInflater.inflate(R.layout.my_message, null)
        var textView = setView!!.findViewById(R.id.message_body) as TextView
        textView.text = messageContent + "::" + color
        return setView!!
    }

    override fun getItem(p0: Int): Any {
        return this.messages[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return this.messages.count()
    }

}