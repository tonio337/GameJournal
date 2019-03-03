package com.tonio337.gamejournal

class Journal{

    data class Entry(val game: Game, var title: String, var text: String){

        companion object{
            var nextID = 0
        }

        val id = nextID++
    }

    private val entries = mutableListOf<Entry>()

    val titles
        get() = entries.map{it.title}

    val games
        get() = entries.map{it.game}.toSet().toList()

    fun addEntry(entry: Entry){ entries.add(entry) }
    fun removeEntry(id: Int){ entries.removeAt(id) }
}

