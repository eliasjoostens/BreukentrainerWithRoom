package ehb.breukentrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStartBasic = findViewById<Button>(R.id.buttonBasic)
        val btnStartAdvanced = findViewById<Button>(R.id.buttonAdvanced)
        val btnTip = findViewById<Button>(R.id.buttonTip)
        var tipNr = 0;

        val exit_icon = findViewById<ImageView>(R.id.exit_button)
        exit_icon.setOnClickListener {
            exitProcess(0)
        }

        btnStartBasic.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            intent.putExtra("Level", "Basic")
            // start your next activity
            startActivity(intent)
            finish()
        }

        btnStartAdvanced.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            intent.putExtra("Level", "Advanced")
            startActivity(intent)
            finish()
        }

        btnTip.setOnClickListener {
            val intent = Intent(this, TipOfTheDayActivity::class.java)
            tipNr = (0..2).random()
            intent.putExtra("TipNr", tipNr)
            startActivity(intent)
            finish()
        }
    }
}