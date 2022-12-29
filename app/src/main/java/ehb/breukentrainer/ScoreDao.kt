package ehb.breukentrainer

import androidx.room.*

@Dao
interface ScoreDao {
    @Query("SELECT * FROM score_table ORDER BY score")
    fun getAll(): List<Score>

    @Query("DELETE FROM score_table")
    fun delete()

    @Insert
    fun insertAll(vararg scores: Score)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(score: Score)

    @Delete
    fun delete(score: Score)
}