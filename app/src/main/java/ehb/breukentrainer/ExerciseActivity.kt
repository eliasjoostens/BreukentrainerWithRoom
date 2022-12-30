package ehb.breukentrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast

class ExerciseActivity : AppCompatActivity() {
    private var mExerciseLibrary: ExerciseLibrary = ExerciseLibrary(0)

    private var mAnswer: String? = null
    private var mScore = 0
    private var mExerciseNumber = 0
    private var maxNumber = 0
    private var mLevel: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        val mScoreView = findViewById<TextView>(R.id.score);
        val mQuestionView = findViewById<TextView>(R.id.question);
        val mButtonChoice1 = findViewById<Button>(R.id.choice1);
        val mButtonChoice2 = findViewById<Button>(R.id.choice2);
        val mButtonChoice3 = findViewById<Button>(R.id.choice3);
        val mButtonChoice4 = findViewById<Button>(R.id.choice4);
        // access the chronometer from XML file
        var meter = findViewById<Chronometer>(R.id.c_meter);
        meter.start()

        mScoreView.text = "0/0"

        mLevel = intent.getStringExtra("Level")
        if (mLevel == "Basic") maxNumber = 5
        if (mLevel == "Advanced") maxNumber = 10

        mExerciseLibrary = ExerciseLibrary(maxNumber)

        updateQuestion();

        //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener {
            //My logic for Button goes in here

            if (mButtonChoice1.getText() == mAnswer) {
                mScore = mScore + 1;
                //This line of code is optiona
                Toast.makeText(applicationContext, "correct", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(applicationContext, "wrong", Toast.LENGTH_SHORT).show();
            }
            updateScore(mScore);
            updateQuestion();
        }

        //Start of Button Listener for Button2
        mButtonChoice2.setOnClickListener {
            if (mButtonChoice2.getText() == mAnswer) {
                mScore = mScore + 1;
                //This line of code is optiona
                Toast.makeText(applicationContext, "correct", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(applicationContext, "wrong", Toast.LENGTH_SHORT).show();
            }
            updateScore(mScore);
            updateQuestion();
        }

        //Start of Button Listener for Button3
        mButtonChoice3.setOnClickListener {
            if (mButtonChoice3.getText() == mAnswer) {
                mScore = mScore + 1;
                //This line of code is optiona
                Toast.makeText(applicationContext, "correct", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(applicationContext, "wrong", Toast.LENGTH_SHORT).show();
            }
            updateScore(mScore);
            updateQuestion();
        }

        //Start of Button Listener for Button4
        mButtonChoice4.setOnClickListener {
            if (mButtonChoice4.getText() == mAnswer) {
                mScore = mScore + 1;
                //This line of code is optional
                Toast.makeText(applicationContext, "correct", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(applicationContext, "wrong", Toast.LENGTH_SHORT).show();
            }
            updateScore(mScore);
            updateQuestion();
        }
    }

    private fun updateQuestion() {
        if (mExerciseNumber == 5) {
            val intent = Intent(this, ScoreActivity::class.java)
            var meter = findViewById<Chronometer>(R.id.c_meter);
            meter.text
            val mScoreView = findViewById<TextView>(R.id.score);

            intent.putExtra("Level", mLevel)
            intent.putExtra("Time", meter.text)
            intent.putExtra("Score", mScore)
            // start your next activity
            startActivity(intent)

            finish()
        }

        val mQuestionView = findViewById<TextView>(R.id.question);
        val mButtonChoice1 = findViewById<Button>(R.id.choice1);
        val mButtonChoice2 = findViewById<Button>(R.id.choice2);
        val mButtonChoice3 = findViewById<Button>(R.id.choice3);
        val mButtonChoice4 = findViewById<Button>(R.id.choice4);
        mQuestionView.text = mExerciseLibrary.getNextQuestion()
        mButtonChoice1.text = mExerciseLibrary.getChoice1()
        mButtonChoice2.text = mExerciseLibrary.getChoice2()
        mButtonChoice3.text = mExerciseLibrary.getChoice3()
        mButtonChoice4.text = mExerciseLibrary.getChoice4()
        mAnswer = mExerciseLibrary.getCorrectAnswer()
        mExerciseNumber++
    }

    fun updateScore(point: Int) {
        var mScoreView = findViewById<TextView>(R.id.score);
        mScoreView.setText("".plus(mScore).plus( "/").plus( mExerciseNumber))
    }
}