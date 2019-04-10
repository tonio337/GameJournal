package com.tonio337.gamejournal

import io.mockk.every
import io.mockk.mockk
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
        assertEquals("Super Mario Bros. 2", Game("0","Super Mario Bros. 2").name)
    }

    @Test
    fun journal_getFuns(){
        // games and journal are defined in SampleData
        assertEquals(games, journal.games)
    }
}

class GameJournalUnitMockTest {

    class SystemUnderTest(
        val game: Game,
        val entry: Journal.Entry
    ){
        fun calculate(){

        }
    }

    @Test
    fun addGameToEntry(){
        val entry = mockk<Journal.Entry>()
        val game = mockk<Game>()

        every { game.name } returns "Metroid"
        every { entry.title } returns "Run and Gun Fun"

        val sut = SystemUnderTest(game, entry)

        sut.calculate()
    }
}