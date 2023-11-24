import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.jvm.JvmTargetValidationMode
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow")
}

val paperVersion: String by project
val bungeeVersion: String by project

subprojects {

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "com.github.johnrengelman.shadow")

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public")
    }

    dependencies {
        compileOnly("net.md-5:bungeecord-api:$bungeeVersion")
        compileOnly("io.papermc.paper:paper-api:$paperVersion")
    }

    kotlin {
        compilerOptions {
            val jvmTarget: String by project
            this.jvmTarget.set(JvmTarget.valueOf(jvmTarget))
        }
    }

    tasks.register<Copy>("deploy") {
        dependsOn("shadowJar")
        from(tasks["shadowJar"].outputs.files)
        into("../../build/libs")
    }

    tasks.withType<KotlinJvmCompile> {
        jvmTargetValidationMode.set(JvmTargetValidationMode.IGNORE)
    }

    afterEvaluate {

        tasks.withType<ProcessResources> {
            expand("version" to project.version)
        }

        tasks.jar {
            archiveFileName.set("kotlinx-serialization-${project.name}-${project.version}-slim.jar")
        }

        tasks.withType<ShadowJar> {
            archiveFileName.set("kotlinx-serialization-${project.name}-${project.version}.jar")
        }

    }

}
