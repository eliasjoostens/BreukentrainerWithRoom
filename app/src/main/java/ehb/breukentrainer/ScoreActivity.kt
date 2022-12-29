package ehb.breukentrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.system.exitProcess

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val score = intent.getStringExtra("Score")
        val time = intent.getStringExtra("Time")
        val mScoreView = findViewById<TextView>(R.id.scorefinal);
        val mTimeView = findViewById<TextView>(R.id.timefinal);
        mScoreView.text = score
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

        val mButtonDB = findViewById<Button>(R.id.ButtonDB);
        mButtonDB.setOnClickListener {

        }
    }
}