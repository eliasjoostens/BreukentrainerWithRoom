package ehb.breukentrainer

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.widget.Button
import android.widget.ImageView
import java.util.concurrent.Executors
import kotlin.system.exitProcess

class TipOfTheDayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_of_the_day)

        var imageURL = ""
        val tipNr = intent.getIntExtra("TipNr", 1)
        when (tipNr) {
            0 -> imageURL =
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_i3vzTsG2AqONDJBuv9jxiWBj6rIJpT1BkA&usqp=CAU"
            1 -> imageURL =
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXSlAOckeZB57nLdBBe1CsAD7vSuAZo6C_6w&usqp=CAU"
            else -> {
                imageURL =
                    "https://www.examenoverzicht.nl/pub/media/wysiwyg/Wiskunde/Rekenregel_bij_het_optellen_en_aftrekken_van_breuken_a.PNG"
            }
        }

        //Hard code action for clicking left arrow in toolbar
        val left_arrow = findViewById<ImageView>(R.id.left_arrow)

        left_arrow.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val exit_icon = findViewById<ImageView>(R.id.exit_button)
        exit_icon.setOnClickListener {
            exitProcess(0)
        }

        // Declaring and initializing the ImageView
        val imageView = findViewById<ImageView>(R.id.imageTipView)
        // Declaring executor
        val executor = Executors.newSingleThreadExecutor()
        // Executor receives image and loads it into ImageView
        val handler = Handler(Looper.getMainLooper())
        // Initializing the image
        var image: Bitmap? = null

        // Only for Background process (can take time depending on the Internet speed)
        executor.execute {

            // Tries to get the image and post it in the ImageView
            // with the help of Handler
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)

                // Only for making changes in UI
                handler.post {
                    imageView.setImageBitmap(image)
                }
            }

            // Catch false URL or other exceptions
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}