package com.tonio337.gamejournal

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_games.*
import kotlinx.android.synthetic.main.content_games.*

class GamesActivity : AppCompatActivity() {

    companion object {
        const val newGameActivityRequestCode = 1
    }

    private lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = GameListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        gameViewModel.allGames.observe(this, Observer { games ->
            // Update the cached copy of the games in the adapter.
            games?.let{ adapter.setGames(it) }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newGameActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {
                // get new game data from NewGameActivity result
                val game = Game("_", "New Game")
                gameViewModel.insert(game)
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Not Saved",
                Toast.LENGTH_LONG).show()
        }
    }

}
