package models

import java.io.Serializable

class Player(val id: Int, val name: String) : Serializable{

    var chosen = false
    var havePair = false

    companion object{
        private var GLOBAL_ID = 0

        fun getId(): Int{
            return GLOBAL_ID++
        }
    }

}