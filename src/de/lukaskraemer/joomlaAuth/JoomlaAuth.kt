package de.lukaskraemer.joomlaAuth

import org.bukkit.plugin.java.JavaPlugin
import java.io.File


class JoomlaAuth : JavaPlugin() {
    var maindir = "plugins/JoomlaAuth/"
    var dbUser: String = ""
    var dbPasswd: String= ""
    var dbDatabase: String = ""
    var usertable: String = ""

    override fun onDisable() {
        println("JoomlaAuth disabled")
    }

    override fun onEnable() {
        println("JoomlaAuth started")
        File(maindir).mkdir()
        this.saveDefaultConfig()
        config.load(maindir+"config.yml")
        dbUser= config.getString("username").toString()
        dbPasswd=config.getString("passwd").toString()
        dbDatabase= config.getString("database").toString()
        usertable= config.getString("userTable").toString()
        DatabaseHelper.connect(dbUser,dbPasswd,dbDatabase,usertable)

        server.pluginManager.registerEvents(JoomlaListener(), this);
    }

}

