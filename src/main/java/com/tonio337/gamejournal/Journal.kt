package com.tonio337.gamejournal

class Journal{

    data class Entry(val game: Game, var title: String, var text: String)

    val entries = mutableListOf<Entry>()

    val titles
        get() = entries.map{it.title}
}

