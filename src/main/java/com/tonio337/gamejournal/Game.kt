package com.tonio337.gamejournal

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "games_table")
data class Game(
    @PrimaryKey @ColumnInfo (name = "resID") val resID: String,
    @ColumnInfo(name = "name") val name: String)

//const val GIANT_BOMB_REQUEST_URL = "http://www.giantbomb.com/api"
//const val RES_TYPE = "game"
//
//fun getGame(resID: String): Game? {
//
//    // TODO: look in database for resID first
//
//    val payload = mapOf("api_key" to GIANT_BOMB_API, "format" to "json", "field_list" to "name")
//    val response = get("$GIANT_BOMB_REQUEST_URL/$RES_TYPE/$resID",params=payload)
//
//    return Game(resID,response.jsonObject.getJSONObject("results").getString("name"))
//}
//
//fun getGames(query: String): List<Game> {
//    val games = mutableListOf<Game>()
//
//    val payload = mapOf("api_key" to GIANT_BOMB_API,
//        "format" to "json",
//        "field_list" to "name,guid",
//        "resources" to RES_TYPE,
//        "query" to query)
//    val response = get("$GIANT_BOMB_REQUEST_URL/search",params=payload)
//    val results = response.jsonObject.getJSONArray("results")
//
//    for (i in 0 until results.length()) {
//        val obj = results[i] as JSONObject
//        games.add(Game(obj.getString("guid"), obj.getString("name")))
//    }
//
//    return games.toList()
//
//}