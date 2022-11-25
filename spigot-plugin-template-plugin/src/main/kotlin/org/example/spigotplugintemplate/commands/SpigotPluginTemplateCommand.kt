package org.example.spigotplugintemplate.commands

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import java.lang.reflect.Method

abstract class SpigotPluginTemplateCommand(name: String, description: String = "", usage: String = "", vararg aliases: String): Command(name, description, "${ChatColor.RED}Usage: $usage", listOf(*aliases)) {

    abstract override fun execute(sender: CommandSender, alias: String, args: Array<out String>): Boolean

}