package com.tonio337.gamejournal

val gameTitles = listOf("Super Mario Bros.",
                        "Contra",
                        "Metroid")

 val games = gameTitles.map{Game("_", it)}

// Giant Bomb API Resource IDs (Super Mario Bros., Contra, Metroid)
/*val resIDs = listOf("3030-24621",
                    "3030-21761",
                    "3030-4350")

val games = resIDs.map{getGame(it)!!}*/

val entryList = listOf(
    Journal.Entry(games[0],"The Classic",
        """I did this really awesome thing in Mario today.
            |I learned that you could hold B to run and then jump super high.
            |It was so amazing!
        """.trimMargin()),
    Journal.Entry(games[1], "Classic Runner Shooter",
        """Can't beat this one without using the Konami code.
            |Everyone say it with me now...
            |Up Up Down Down Left Right Left Right B A (Select) Start.
        """.trimMargin()),
    Journal.Entry(games[2], "Seriously???",
        """Okay. What the heck am I supposed to even do here.
            |I'm lost, everything kills me in 2 hits, and there's no real progression.
            |About to give up. Or maybe just look at a guide or something.
        """.trimMargin())
)

val journal = Journal.demo()