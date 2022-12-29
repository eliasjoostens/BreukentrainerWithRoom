package ehb.breukentrainer

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Score::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scoreDao(): ScoreDao
}