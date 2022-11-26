package org.example.spigotplugintemplate.commands.impl

import org.bukkit.command.CommandSender
import org.example.spigotplugintemplate.commands.Command

class ExampleCommand: Command("example") {
    override fun execute(sender: CommandSender, alias: String, args: Array<out String>): Boolean {
        sender.sendMessage("Hello, World!")
        return true
    }

    override fun tabComplete(sender: CommandSender, alias: String, args: Array<out String>): MutableList<String> {
        return arrayListOf("Hello", "World").filter { it.lowercase().startsWith(args.last().lowercase()) } as MutableList<String>
    }
}