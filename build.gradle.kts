subprojects {

    repositories {

        mavenCentral()

        maven {
            name = "PaperMC"
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }

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