package activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.my.apleu.random.R
import models.Player
import kotlin.random.Random

class RandomActivity : AppCompatActivity() {

    private val TAG = RandomActivity::class.simpleName

    //Some constants for ticker

    private val useTicker = true

    private val countDownStartValue = 3000L

    private val countDownInterval = 1000L

    //Layout

    private lateinit var textCurrentPlayer: TextView

    private lateinit var nextButton: Button

    private lateinit var textTicker: TextView

    //Layout End

    private lateinit var playersList: ArrayList<Player>

    private var currentPlayer: Player? = null

    private var chosenPlayer: Player? = null

    private var beginCountDown = false

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        //Defining layout
        textCurrentPlayer = findViewById(R.id.text_currentplayer)
        nextButton = findViewById(R.id.next_button)
        textTicker = findViewById(R.id.text_ticker)

        playersList = intent.getSerializableExtra("playersList") as ArrayList<Player>

        nextButton.setOnClickListener {
            if (beginCountDown) {
                textTicker.text = ""

                nextButton.text = resources.getString(R.string.text_next_random)
                invokeRandom()
            } else {
                nextButton.text = resources.getString(R.string.text_next)
                if(useTicker)
                    createTicker(countDownStartValue, countDownInterval)
                else
                    textTicker.text = chosenPlayer?.name
            }

            beginCountDown = !beginCountDown
        }

        invokeRandom()
    }

    private fun invokeRandom() {
        counter++

        if (counter - 1 == playersList.size)
            finishRandom()
        else {
            randomCurrentPlayer()
            textCurrentPlayer.text = currentPlayer?.name
            randomChosenPlayer()
        }
    }

    private fun randomCurrentPlayer() {
        var index: Int

        do {
            index = Random.nextInt(0, playersList.size)
        } while (playersList[index].havePair)

        playersList[index].havePair = true

        currentPlayer = playersList[index]
    }

    private fun randomChosenPlayer() {
        if (currentPlayer == null)
            Log.e(TAG, "Current player should be initialized before choosing!")

        var index: Int

        do {
            index = Random.nextInt(0, playersList.size)

            if (!playersList[index].chosen && playersList[index].id != currentPlayer?.id) {
                chosenPlayer = playersList[index]
                chosenPlayer?.chosen = true
                break
            }

        } while (true)
    }

    private fun createTicker(duration: Long, pause: Long) {
        textTicker.textSize = resources.getDimension(R.dimen.textsize_ticker)
        nextButton.visibility = View.INVISIBLE

        object : CountDownTimer(duration, pause) {

            override fun onTick(millisUntilFinished: Long) {
                textTicker.text = (millisUntilFinished / 1000 + 1).toString()
            }

            override fun onFinish() {
                textTicker.textSize = resources.getDimension(R.dimen.textsize_names)
                textTicker.text = chosenPlayer?.name
                nextButton.visibility = View.VISIBLE
            }
        }.start()
    }

    private fun finishRandom() {
        val nextScreenIntent = Intent(this, FinishActivity::class.java)

        startActivity(nextScreenIntent)
    }

}