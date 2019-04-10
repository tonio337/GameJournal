package com.tonio337.gamejournal

// # DAO Constants
const val GET_ALL_GAMES_QUERY = "SELECT * from games_table ORDER BY name ASC"

// # Giant Bomb API Constants
const val GIANT_BOMB_API = BuildConfig.GiantBombApiKey
const val GIANT_BOMB_REQUEST_URL = "http://www.giantbomb.com/api"
const val RES_TYPE = "game"