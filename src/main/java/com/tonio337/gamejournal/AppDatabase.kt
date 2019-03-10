package com.tonio337.gamejournal

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.*
import android.content.Context
import android.support.annotation.WorkerThread
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.content_entry.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


@Dao
interface GameDao{

    @Query("SELECT * from games_table ORDER BY name ASC")
    fun getAllGames(): LiveData<List<Game>>

    @Query("SELECT name from games_table ORDER BY name ASC")
    fun getAllNames(): LiveData<List<String>>

    @Insert
    fun insert(game: Game)

    @Query("DELETE from games_table")
    fun deleteAll()

}

@Database(entities = [Game::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao() : GameDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "database-name"
                ).addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase){
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO){
                    populateDatabase(database.gameDao())
                }
            }
        }
    }
}

class AppRepository(private val gameDao: GameDao){
    val allGames: LiveData<List<Game>> = gameDao.getAllGames()

    val allNames: LiveData<List<String>> = gameDao.getAllNames()

    @WorkerThread
    fun insert(game: Game){
        gameDao.insert(game)
    }
}

class GameViewModel(application: Application): AndroidViewModel(application){

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    private val repository: AppRepository
    val allGames: LiveData<List<Game>>

    init{
        val gamesDao = AppDatabase.getDatabase(application, scope).gameDao()
        repository = AppRepository(gamesDao)

        allGames = repository.allGames
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    fun insert(game: Game) = scope.launch(Dispatchers.IO){
        repository.insert(game)
    }
}

class GameListAdapter internal constructor( context: Context) :
    RecyclerView.Adapter<GameListAdapter.GameViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var games = emptyList<Game>()

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val gameItemView: TextView = itemView.textView2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder{
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val current = games[position]
        holder.gameItemView.text = current.name
    }

    internal fun setGames(games: List<Game>) {
        this.games = games
        notifyDataSetChanged()
    }

    override fun getItemCount() = games.size
}

fun populateDatabase(gameDao: GameDao){
    gameDao.deleteAll()

    games.forEach { game ->
        gameDao.insert(game)
    }
}