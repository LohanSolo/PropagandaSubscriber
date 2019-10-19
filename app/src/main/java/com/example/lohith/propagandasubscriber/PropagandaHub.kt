package com.example.lohith.propagandasubscriber

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_propaganda_hub.*
import kotlinx.android.synthetic.main.propaganda_list.*

val EXTRA_MESSAGE = "com.example.lohith.propagandasubscriber.Sender"

class PropagandaHub : AppCompatActivity() {
    private var subscriptionAdapter: PropagandaAdapter? = null
    private val clickListener = View.OnClickListener { view ->
        val intent = Intent(this, Messages::class.java).apply {
            putExtra(EXTRA_MESSAGE, "Hello World")
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.propaganda_list)
        setSupportActionBar(toolbar)
        // fab.setOnClickListener(clickListener)
        this.subscriptionAdapter = PropagandaAdapter(this)
        propaganda_list.adapter = subscriptionAdapter
        propaganda_list.setOnItemClickListener{ parent, view, position, id ->
            val item = view.findViewById<TextView>(R.id.Propaganda_Subscription)
            val intent = Intent(this, Messages::class.java).apply {
                putExtra(EXTRA_MESSAGE, item.text)
            }
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_propaganda_hub, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
