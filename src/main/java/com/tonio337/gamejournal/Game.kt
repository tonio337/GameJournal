package com.tonio337.gamejournal

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "games_table")
data class Game(
    @PrimaryKey @ColumnInfo (name = "name") val name: String)