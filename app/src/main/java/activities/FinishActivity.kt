package activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.my.apleu.random.R

class FinishActivity : AppCompatActivity() {

    //Layout

    private lateinit var mainMenuButton: Button

    //Layout End

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        //Defining Layout

        mainMenuButton = findViewById(R.id.button_mainmenu)

        mainMenuButton.setOnClickListener{
            val mainMenuIntent = Intent(this, MainActivity::class.java)

            startActivity(mainMenuIntent)
        }
    }

}