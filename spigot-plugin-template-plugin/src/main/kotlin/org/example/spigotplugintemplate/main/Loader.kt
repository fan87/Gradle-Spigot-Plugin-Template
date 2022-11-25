package org.example.spigotplugintemplate.main

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import org.example.spigotplugintemplate.SpigotPluginTemplate

class Loader: JavaPlugin() {

    companion object {
        lateinit var INSTANCE: Loader
    }

    init {
        INSTANCE = this
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String>? {
        return SpigotPluginTemplate.onTabComplete(sender, command, alias, args)
    }

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        return SpigotPluginTemplate.onCommand(sender, command, label, args)
    }

    override fun onDisable() {
        SpigotPluginTemplate.onDisable()
    }

    override fun onLoad() {
        SpigotPluginTemplate.onLoad()
    }

    override fun onEnable() {
        SpigotPluginTemplate.onEnable()
    }
}