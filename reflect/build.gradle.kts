import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import org.jetbrains.kotlin.gradle.dsl.jvm.JvmTargetValidationMode

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
    archiveFileName.set("kotlin-${project.name}-${project.version}-slim.jar")
}

tasks.withType<ShadowJar> {
    archiveFileName.set("kotlin-${project.name}-${project.version}.jar")
}

tasks.withType<KotlinJvmCompile> {
    jvmTargetValidationMode.set(JvmTargetValidationMode.IGNORE)
}

tasks.withType<ProcessResources> {
    expand("version" to project.version)
}
