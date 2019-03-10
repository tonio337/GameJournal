package com.tonio337.gamejournal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_journal.*

class JournalActivity : AppCompatActivity() {

    // private val journal = Journal.demo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journal)

        populateEntries()
        floatingActionButton.setOnClickListener { editEntry(journal.entries[0]) }
    }

    private fun populateEntries() {
        journal.entries.forEach { entry ->
            linearLayout.addView(TextView(this).apply {
                text = getString(R.string.journal_listing, entry.title, entry.game.name)
            })
        }
    }

    private fun editEntry(entry: Journal.Entry?){
        val intent = Intent(this,EntryActivity::class.java).apply{
            putExtra("id",entry?.id)
        }
        startActivity(intent)
    }

    private fun addEntry() = editEntry(null)
}
