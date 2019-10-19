package com.example.lohith.propagandasubscriber

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class PropagandaAdapter: BaseAdapter {

    var subscriptions: MutableList<String> = ArrayList<String>()
    private var context: Context

    constructor(context: Context) : super() {
        this.context = context
        subscriptions.add("GreyParty")
    }

    fun add(subscription: String) {
        this.subscriptions.add(subscription)
        notifyDataSetChanged() // to render the list we need to notify
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val messageInflater = context!!.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var setView =  messageInflater.inflate(R.layout.propaganda_text, null)
        var textView = setView.findViewById(R.id.Propaganda_Subscription) as TextView
        textView.text = subscriptions[p0]
        return setView!!
    }

    override fun getItem(p0: Int): Any {
        return this.subscriptions[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return this.subscriptions.count()
    }
}