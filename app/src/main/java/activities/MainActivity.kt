package activities

import adapters.PlayerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my.apleu.random.R
import models.Player

class MainActivity : AppCompatActivity() {

    //Layout

    private lateinit var beginRandomButton: Button

    private lateinit var addPlayerButton: Button

    private lateinit var editTextPlayer: EditText

    private lateinit var rvPlayers: RecyclerView

    private lateinit var clearButton: Button

    //Layout End

    private var playersList = ArrayList<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Defining layout

        beginRandomButton = findViewById(R.id.button_begin_random)

        addPlayerButton = findViewById(R.id.button_add_player)

        editTextPlayer = findViewById(R.id.edit_text_player)

        clearButton = findViewById(R.id.button_clear)

        //Setting RecyclerView

        rvPlayers = findViewById(R.id.players_list)
        rvPlayers.layoutManager = LinearLayoutManager(this)
        var adapter = PlayerAdapter(playersList)
        rvPlayers.adapter = adapter

        //Set OnClickListeners

        addPlayerButton.setOnClickListener{
            if(editTextPlayer.text.isEmpty())
                return@setOnClickListener

            val player = Player(Player.getId(), editTextPlayer.text.toString())
            adapter.add(player)

            editTextPlayer.setText("")
        }

        clearButton.setOnClickListener{
            if(playersList.isEmpty())
                return@setOnClickListener

            adapter.clear()
        }

        beginRandomButton.setOnClickListener{
            if(playersList.isEmpty())
                return@setOnClickListener

            val nextScreenIntent = Intent(this, RandomActivity::class.java)

            nextScreenIntent.putExtra("playersList", playersList)

            startActivity(nextScreenIntent)
        }
    }
}
