package ehb.breukentrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStartBasic = findViewById<Button>(R.id.buttonBasic)
        val btnStartAdvanced = findViewById<Button>(R.id.buttonAdvanced)
        val btnTip = findViewById<Button>(R.id.buttonTip)
        var tipNr = 0;


        btnStartBasic.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            intent.putExtra("Level","Basic")
            // start your next activity
            startActivity(intent)

            finish()
        }

        btnStartAdvanced.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            intent.putExtra("Level","Advanced")
            // start your next activity
            startActivity(intent)

            finish()
        }

        btnTip.setOnClickListener {
            val intent = Intent(this, TipOfTheDayActivity::class.java)

            tipNr = (0..2).random()
            intent.putExtra("TipNr", tipNr)
            // start your next activity
            startActivity(intent)


            // start your next activity
            startActivity(intent)

            finish()
        }


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

            //Toast.makeText(getApplicationContext(), scores[0].score, Toast.LENGTH_LONG).show();
        }
        //userDao.insert(score2)
        //userDao.insert(score3)
    }
}