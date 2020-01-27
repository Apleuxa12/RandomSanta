package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.my.apleu.random.R
import models.Player

class PlayerAdapter(private val players: ArrayList<Player>) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(itemView)
    }

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindData(players[position])
    }

    fun add(player: Player){
        players.add(player)
        notifyDataSetChanged()
    }

    fun clear(){
        players.clear()
        notifyDataSetChanged()
    }

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var name = itemView.findViewById<TextView>(R.id.text_name)

        fun bindData(player: Player){
            name.text = player.name
        }

    }
}