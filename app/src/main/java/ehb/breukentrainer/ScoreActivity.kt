package ehb.breukentrainer

import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.system.exitProcess

class ScoreActivity : AppCompatActivity() {
    private var mLevel: String = ""
    private var mTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val score = intent.getIntExtra("Score", 0)
        val level = intent.getStringExtra("Level")
        if (level != null) mLevel = level

        val time = intent.getStringExtra("Time")
        if (time != null) mTime = time

        val mScoreView = findViewById<TextView>(R.id.scorefinal);
        val mTimeView = findViewById<TextView>(R.id.timefinal);
        mScoreView.text = score.toString()
        mTimeView.text = time

        val mButtonNewGame = findViewById<Button>(R.id.buttonNewGame);
        mButtonNewGame.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // start your next activity
            startActivity(intent)
            finish()
        }

        val mButtonQuit = findViewById<Button>(R.id.buttonQuit);
        mButtonQuit.setOnClickListener {
            exitProcess(0)
        }

        //Create database when starting up main_activity
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        //Instantiate ScoreDao
        val scoreDao = db.scoreDao()
        val calender = Calendar.getInstance()

        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH) + 1
        val day = calender.get(Calendar.DAY_OF_MONTH)
        val date = "$year-$month-$day"

        val score1 = Score(score = score, level = mLevel, time = mTime, date = date)

        lifecycleScope.launch {

            scoreDao.insert(score1)
            var scores: List<Score>

            if (mLevel == "Basic") {
                scores = scoreDao.getScoresBasic()
            } else {
                scores = scoreDao.getScoresAdvanced()
            }

            val textView1: TextView = findViewById(R.id.textView_topscore1) as TextView
            textView1.text = scores[0].score.toString()

            val textViewTijd1: TextView = findViewById(R.id.textView_tijd1) as TextView
            textViewTijd1.text = scores[0].time.toString()

            val textViewDatum1: TextView = findViewById(R.id.textView_datum1) as TextView
            textViewDatum1.text = scores[0].date.toString()

            if (scores.size >= 2) {
                val textView2: TextView = findViewById(R.id.textView_topscore2) as TextView
                textView2.text = scores[1].score.toString()

                val textViewTijd2: TextView = findViewById(R.id.textView_tijd2) as TextView
                textViewTijd2.text = scores[1].time.toString()

                val textViewDatum2: TextView = findViewById(R.id.textView_datum2) as TextView
                textViewDatum2.text = scores[1].date.toString()
            }

            if (scores.size >= 3) {
                val textView3: TextView = findViewById(R.id.textView_topscore3) as TextView
                textView3.text = scores[2].score.toString()

                val textViewTijd3: TextView = findViewById(R.id.textView_tijd3) as TextView
                textViewTijd3.text = scores[2].time.toString()

                val textViewDatum3: TextView = findViewById(R.id.textView_datum3) as TextView
                textViewDatum3.text = scores[2].date.toString()
            }
        }
    }
}