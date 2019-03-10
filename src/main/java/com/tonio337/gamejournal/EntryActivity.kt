package com.tonio337.gamejournal

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_entry.*
import kotlinx.android.synthetic.main.content_entry.*

class EntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        button.setOnClickListener {
            intent = Intent(this, GamesActivity::class.java)
            startActivity(intent)
        }

        populateEntry(intent.getIntExtra("id", -1))
    }

    private fun populateEntry(id: Int){
        // val entry = journal.entries[id]

        val entry = journal.entries.firstOrNull{it.id == id} ?: return
        //spinner.setSelection(2)
        editText.setText(entry.title)
        editText2.setText(entry.text)
    }

}
