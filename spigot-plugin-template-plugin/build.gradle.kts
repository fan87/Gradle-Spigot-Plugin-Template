import com.github.jengelman.gradle.plugins.shadow.tasks.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    java
    id("com.github.johnrengelman.shadow") version "7.1.2"
}


tasks.shadowJar {
    archiveClassifier.set("")
}

tasks.getByName("build").dependsOn("shadowJar")

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(files(GradleUtils.getServerJar(project).absolutePath))

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.20")
}


tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.processResources {
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand("version" to version)
    }
}

tasks {

    register("reloadPlugin") {
        group = "run"
        description = "Reload the main plugin"

        val pluginFile: File = project.tasks.getByName<Jar>("jar").archiveFile.get().asFile

        dependsOn("shadowJar")

        inputs.files(pluginFile)
        doLast {
            val runDir = File(rootProject.projectDir, "run/")
            val destPluginFile = File(runDir, "/plugins/spigot-plugin-template-plugin-DEV.jar")
            if (!destPluginFile.parentFile.exists()) {
                destPluginFile.parentFile.mkdirs()
            }
            if (!destPluginFile.exists()) {
                destPluginFile.createNewFile()
            }
            destPluginFile.writeBytes(pluginFile.readBytes())
            println("Server Responded ${GradleUtils.execute("reload")[0]}")
        }
    }

}


sourceSets {
    main {
        java {
            setSrcDirs(listOf(File(projectDir, "src/main/kotlin")))
        }
    }
}