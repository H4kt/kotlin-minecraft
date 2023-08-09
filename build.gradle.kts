import org.jetbrains.kotlin.gradle.tasks.UsesKotlinJavaToolchain

plugins {
    kotlin("jvm") apply false
}

subprojects {

    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {

        mavenCentral()

        maven {
            name = "PaperMC"
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }

    }

    val service = project.extensions.getByType<JavaToolchainService>()

    val customJavaLauncher = service.launcherFor {
        languageVersion.set(JavaLanguageVersion.of(11))
    }

    tasks.withType<UsesKotlinJavaToolchain> {
        kotlinJavaToolchain.toolchain.use(customJavaLauncher)
    }

    tasks.register<Copy>("deploy") {
        dependsOn("shadowJar")
        from(tasks.get("shadowJar").outputs.files)
        into("../build/libs")
    }

    tasks.withType<ProcessResources> {
        filter {
            it.replace("{version}", project.version.toString())
        }
    }

}