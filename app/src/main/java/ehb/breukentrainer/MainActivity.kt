package ehb.breukentrainer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Create database when starting up main_activity
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        //Instantiate userDao + get all users
        val scoreDao = db.scoreDao()
        val score1 = Score(score = "4", level = "basic", date = "vandaag")
        val score2 = Score(score = "2", level = "advanced", date = "gisteren")
        val score3 = Score(score = "5", level = "basic", date = "morgen")

        lifecycleScope.launch {

            scoreDao.insert(score1)
            scoreDao.insert(score2)
            scoreDao.insert(score3)

            val scores: List<Score> = scoreDao.getAll()
            val wim = "test"

            Toast.makeText(getApplicationContext(), scores[0].score, Toast.LENGTH_LONG).show();
        }
        //userDao.insert(score2)
        //userDao.insert(score3)
    }
}