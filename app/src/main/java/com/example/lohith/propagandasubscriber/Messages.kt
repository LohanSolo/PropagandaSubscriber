package com.example.lohith.propagandasubscriber

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

data class Message(val message : String = "", val color : String = "", val key : String = "")

class Messages : AppCompatActivity() {

    private var messageAdapter: MessageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        (findViewById(R.id.textView) as TextView).apply {
            text = message
        }
        messageAdapter = MessageAdapter(this)
        val messagesView = (findViewById(R.id.messages_view)as ListView)
        messagesView.adapter = messageAdapter

        FirebaseApp.initializeApp(this)
        val dataBase = FirebaseDatabase.getInstance()
        val myRef = dataBase.getReference(message)
        myRef.child("Messages").addValueEventListener(valueEventListener)
        // myRef.setValue("jelly", "bob")
        val key = myRef.child("Messages").push().key
        // if (key != null) {
        //    myRef.child("Messages").child(key).setValue(Message("tomato", "Red"))
        // }

    }

    private  val valueEventListener = object : ValueEventListener {
        override fun onCancelled(p0: DatabaseError) {
            println("Load cancelled" + p0.message)
        }

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            if(dataSnapshot.value is String){
                val message = dataSnapshot.value as String
                messageAdapter!!.add(Message(message, "Red"))
            }
            val menu: MutableList<Message> = mutableListOf()
            dataSnapshot.children.mapNotNullTo(menu) { it.getValue(Message::class.java)}
            menu.forEach { messageAdapter!!.add(it) }
        }
    }
}

