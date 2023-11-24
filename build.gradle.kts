import org.jetbrains.kotlin.gradle.tasks.UsesKotlinJavaToolchain

plugins {
    kotlin("jvm") apply false
}

subprojects {

    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public")
    }

    val service = project.extensions.getByType<JavaToolchainService>()

    val customJavaCompiler = service.compilerFor {
        languageVersion.set(JavaLanguageVersion.of(17))
    }

    tasks.withType<JavaCompile> {
        javaCompiler.set(customJavaCompiler)
    }

    tasks.register<Copy>("deploy") {
        dependsOn("shadowJar")
        from(tasks["shadowJar"].outputs.files)
        into("../build/libs")
    }

}
