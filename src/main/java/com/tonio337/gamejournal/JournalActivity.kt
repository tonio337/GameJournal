package com.tonio337.gamejournal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_journal.*

class JournalActivity : AppCompatActivity() {

    private val journal = Journal.demo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journal)

        populateEntries()
    }

    private fun populateEntries() {
        journal.entries.forEach { entry ->
            linearLayout.addView(TextView(applicationContext).apply {
                text = entry.title + " - " + entry.game.name
            })
        }
    }

    private fun editEntry(entry: Journal.Entry?){

    }

    private fun addEntry(){

    }
}
