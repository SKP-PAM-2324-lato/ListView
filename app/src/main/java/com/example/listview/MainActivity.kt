package com.example.listview

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listValues = arrayListOf("pomidor", "cebula", "marchewka", "czosnek", "seler")
        val listView = findViewById<ListView>(R.id.listView)
        val editText = findViewById<EditText>(R.id.products)
        val button = findViewById<Button>(R.id.buttonAdd)

        val arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, listValues)

        listView.adapter = arrayAdapter
        val textView = TextView(this)
        textView.text = "Co chcesz kupić?"
        textView.textSize = 30.0F
        textView.setPadding(0,10,0,30)
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        listView.addHeaderView(textView)

        listView.setOnItemClickListener{ adapterView: AdapterView<*>, view2: View, i: Int, l: Long ->
            Toast.makeText(baseContext, "Kliknieto ${listValues[l.toInt()]}", Toast.LENGTH_SHORT).show()
            editText.text.append("\n${listValues[i-1]}")
        }

        button.setOnClickListener{
            val newProduct = findViewById<EditText>(R.id.newProduct)
            val product = newProduct.text.toString()
            newProduct.setText("")

            listValues.add(product)
            arrayAdapter.notifyDataSetChanged()
        }
    }

}

