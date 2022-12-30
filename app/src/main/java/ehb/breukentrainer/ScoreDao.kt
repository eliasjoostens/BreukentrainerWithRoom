package ehb.breukentrainer

import androidx.room.*

@Dao
interface ScoreDao {
    @Query("SELECT * FROM score_table Where level = 'Basic' ORDER BY score DESC")
    fun getScoresBasic(): List<Score>

    @Query("SELECT * FROM score_table Where level = 'Advanced' ORDER BY score DESC")
    fun getScoresAdvanced(): List<Score>

    @Query("DELETE FROM score_table")
    fun delete()

    @Insert
    fun insertAll(vararg scores: Score)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(score: Score)

    @Delete
    fun delete(score: Score)
}