package org.example.spigotplugintemplate.feature

import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil
import org.bukkit.Bukkit
import org.example.spigotplugintemplate.SpigotPluginTemplate
import java.lang.reflect.Modifier
import java.net.URI

class FeatureManager {

    val features = ArrayList<Feature>()

    init {
        val resolver = ResolverUtil()
        resolver.classLoader = javaClass.classLoader
        resolver.findInPackage(object: ResolverUtil.Test {
            override fun matches(p0: Class<*>): Boolean {
                return Feature::class.java.isAssignableFrom(p0) && !Modifier.isAbstract(p0.modifiers)
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
            registerFeature(clazz as Class<Feature>)
        }
    }

    fun registerFeature(featureClazz: Class<Feature>) {
        val feature = featureClazz.newInstance()
        SpigotPluginTemplate.logger.debug("Feature ${feature.name} has been registered")

        features.add(feature)
        Bukkit.getServer().pluginManager.registerEvents(feature, SpigotPluginTemplate.plugin)
    }

}