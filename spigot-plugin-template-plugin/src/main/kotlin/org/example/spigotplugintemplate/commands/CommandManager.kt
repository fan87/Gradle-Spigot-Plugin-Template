package org.example.spigotplugintemplate.commands

import org.apache.logging.log4j.Level
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil
import org.bukkit.Bukkit
import org.bukkit.command.SimpleCommandMap
import org.bukkit.plugin.SimplePluginManager
import org.example.spigotplugintemplate.SpigotPluginTemplate
import org.example.spigotplugintemplate.main.Loader
import java.lang.reflect.Modifier
import java.net.URI

internal object CommandManager {

    init {
        val resolver = ResolverUtil()
        resolver.classLoader = javaClass.classLoader
        resolver.findInPackage(object: ResolverUtil.Test {
            override fun matches(p0: Class<*>): Boolean {
                return SpigotPluginTemplateCommand::class.java.isAssignableFrom(p0) && !Modifier.isAbstract(p0.modifiers)
            }

            override fun matches(p0: URI): Boolean {
                return false
            }

            override fun doesMatchClass(): Boolean {
                return true
            }

            override fun doesMatchResource(): Boolean {
                return false
            }

        }, javaClass.`package`.name)

        for (clazz in resolver.classes) {
            registerCommand(clazz as Class<SpigotPluginTemplateCommand>)
        }
    }

    fun registerCommand(clazz: Class<SpigotPluginTemplateCommand>) {
        SpigotPluginTemplate.logger.debug("Registered command ${clazz.simpleName}")

        val field = SimplePluginManager::class.java.getDeclaredField("commandMap")
        field.isAccessible = true
        val simpleCommandMap = field.get(Bukkit.getServer().pluginManager) as SimpleCommandMap
        simpleCommandMap.register(Loader.INSTANCE.name.lowercase().replace("-", ""), clazz.newInstance())
    }

}