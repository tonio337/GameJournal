package com.tonio337.gamejournal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        games.forEach{
            linearLayout.addView(
                TextView(applicationContext)
                    .apply{text = it.name}
            )
        }
    }
}
