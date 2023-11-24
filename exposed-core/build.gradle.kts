import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.github.jengelman.gradle.plugins.shadow.transformers.ManifestAppenderTransformer
import com.github.jengelman.gradle.plugins.shadow.transformers.ManifestResourceTransformer
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import org.jetbrains.kotlin.gradle.dsl.jvm.JvmTargetValidationMode
import java.util.jar.Attributes

plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow")
}

val paperVersion: String by project
val bungeeVersion: String by project
val exposedVersion: String by project

version = exposedVersion

dependencies {

    api("org.jetbrains.exposed:exposed-jdbc:$exposedVersion") {
        exclude(group = "org.jetbrains.kotlin")
        exclude(group = "org.jetbrains.kotlinx")
    }

    api("org.postgresql:postgresql:42.6.0")

    compileOnly(project(":stdlib"))
    compileOnly(project(":reflect"))
    compileOnly(project(":coroutines"))

    compileOnly("net.md-5:bungeecord-api:${bungeeVersion}")
    compileOnly("io.papermc.paper:paper-api:${paperVersion}")

}

kotlin {
    compilerOptions {
        val jvmTarget: String by project
        this.jvmTarget.set(JvmTarget.valueOf(jvmTarget))
    }
}

tasks.jar {
    archiveFileName.set("${project.name}-${project.version}-slim.jar")
}

tasks.withType<ShadowJar> {
    archiveFileName.set("${project.name}-${project.version}.jar")
    mergeServiceFiles()
}

tasks.withType<KotlinJvmCompile> {
    jvmTargetValidationMode.set(JvmTargetValidationMode.IGNORE)
}

tasks.withType<ProcessResources> {
    expand("version" to project.version)
}
