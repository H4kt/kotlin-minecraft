import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

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

    api("org.postgresql:postgresql:42.6.0") {
        exclude(group = "org.slf4j")
    }

    shadow(project(":stdlib"))
    shadow(project(":reflect"))
    shadow(project(":coroutines"))

    shadow("net.md-5:bungeecord-api:${bungeeVersion}")
    shadow("com.destroystokyo.paper:paper-api:${paperVersion}")

}

tasks.jar {
    archiveFileName.set("${project.name}-${project.version}.jar")
    from(configurations.shadow) {
        exclude("META-INF/MANIFEST.MF")
    }
}

tasks.withType<ShadowJar> {
    archiveFileName.set("${project.name}-${project.version}.jar")
}
