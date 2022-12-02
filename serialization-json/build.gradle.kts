import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow")
}

val paperVersion: String by project
val bungeeVersion: String by project
val serializationVersion: String by project

version = serializationVersion

dependencies {

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion") {
        exclude(group = "org.jetbrains.kotlin")
        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-serialization-core-jvm")
    }

    shadow(project(":stdlib"))
    shadow("net.md-5:bungeecord-api:${bungeeVersion}")
    shadow("com.destroystokyo.paper:paper-api:${paperVersion}")

}

tasks.jar {
    archiveFileName.set("kotlinx-${project.name}-${project.version}.jar")
}

tasks.withType<ShadowJar> {
    archiveFileName.set("kotlinx-${project.name}-${project.version}.jar")
}