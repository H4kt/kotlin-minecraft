import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow")
}

val kotlinVersion: String by project
val paperVersion: String by project
val bungeeVersion: String by project

version = kotlinVersion

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-reflect") {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib")
    }

    shadow(project(":stdlib"))
    shadow("net.md-5:bungeecord-api:${bungeeVersion}")
    shadow("com.destroystokyo.paper:paper-api:${paperVersion}")

}

tasks.jar {
    archiveFileName.set("kotlin-${project.name}-${project.version}.jar")
}

tasks.withType<ShadowJar> {
    archiveFileName.set("kotlin-${project.name}-${project.version}.jar")
}