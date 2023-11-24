package dev.h4kt.kotlin.plugin.paper

import dev.h4kt.kotlin.plugin.bukkit.KotlinReflect
import io.papermc.paper.plugin.bootstrap.BootstrapContext
import io.papermc.paper.plugin.bootstrap.PluginBootstrap
import io.papermc.paper.plugin.bootstrap.PluginProviderContext
import org.bukkit.plugin.java.JavaPlugin

class KotlinReflectBootstrapper : PluginBootstrap {

    override fun bootstrap(context: BootstrapContext) {}

    override fun createPlugin(context: PluginProviderContext): JavaPlugin {
        return KotlinReflect()
    }

}
