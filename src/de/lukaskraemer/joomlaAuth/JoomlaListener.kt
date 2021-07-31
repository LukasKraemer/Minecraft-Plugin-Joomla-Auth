package de.lukaskraemer.joomlaAuth


import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent


class JoomlaListener : Listener {
    @EventHandler
    fun onPlayerLogin(ev: PlayerLoginEvent) {
        val playername = ev.player.displayName
        val userdate = DatabaseHelper.loadUser(playername)

        if(!userdate.first && userdate.second != 0){
            return userHasNoPermission(ev)
        }

        if(ev.player.isOp){
            Bukkit.broadcastMessage("Der ehrenwerte Administrator $playername ist Online")
        }else{
            Bukkit.broadcastMessage("$playername ist Online")
        }
    }

    fun userHasNoPermission(ev: PlayerLoginEvent){
        ev.result = PlayerLoginEvent.Result.KICK_OTHER
        ev.kickMessage = "Bitte zuerst im Forum registrieren\nIP-Adresse.de"
    }
}
