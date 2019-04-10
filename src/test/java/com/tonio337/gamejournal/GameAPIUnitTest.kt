package com.tonio337.gamejournal

import org.junit.Assert
import org.junit.Test

import khttp.get

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GameAPIUnitTest {

    val expectedName = "Metroid Prime 3: Corruption"
    val requestURL = "http://www.giantbomb.com/api"

    val resourceType = "game"
    val resourceID = "3030-4725"

//    @Test
//    fun giant_bomb_api_lookup_khttp() {
//        val payload = mapOf("api_key" to GIANT_BOMB_API, "format" to "json", "field_list" to "name")
//        val mp3Obj = get("$requestURL/$resourceType/$resourceID",params=payload).jsonObject
//
//        Assert.assertEquals(expectedName, mp3Obj.getJSONObject("results").get("name"))
//
//        Assert.assertEquals(expectedName, getGame(resourceID)!!.name)
//
//        val query = "contra"
//
//        println(getGames(query))
//    }
}