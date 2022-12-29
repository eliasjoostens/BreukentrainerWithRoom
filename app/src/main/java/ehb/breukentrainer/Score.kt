package ehb.breukentrainer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_table")
data class Score (

        @PrimaryKey(autoGenerate = true) val uid: Int = 0,
        @ColumnInfo(name = "level") val level: String,
        @ColumnInfo(name = "score") val score: String,
        @ColumnInfo(name = "date") val date: String,

        )