package com.tonio337.gamejournal

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GameJournalUnitTest {

    val journal = Journal.demo()

    @Test
    fun game_isCreated() {
        assertEquals("Super Mario Bros. 2", Game("Super Mario Bros. 2").name)
    }

    @Test
    fun journal_getFuns(){
        // games and journal are defined in SampleData
        assertEquals(games, journal.games)
    }
}
