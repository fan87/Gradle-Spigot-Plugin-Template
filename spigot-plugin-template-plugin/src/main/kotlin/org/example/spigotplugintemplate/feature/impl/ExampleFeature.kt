package org.example.spigotplugintemplate.feature.impl

import org.bukkit.event.EventHandler
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.example.spigotplugintemplate.feature.Feature

class ExampleFeature: Feature("Example") {

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {
        event.player.sendMessage("Chat Event!")
    }

}