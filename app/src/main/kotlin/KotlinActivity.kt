/**
 * Created by root on 5/26/17.
 */
package com.example.root.myvolleydemo
import android.content.Intent
import android.os.Bundle
import android.app.Activity
import android.view.Menu
import android.widget.Button
import android.widget.Toast
import butterknife.ButterKnife

class KotlinActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        val next = findViewById(R.id.buttonHi) as Button
        next.setOnClickListener {
           Toast.makeText(this,"Welcome to Kotlin",Toast.LENGTH_SHORT).show()
        }
    }

}