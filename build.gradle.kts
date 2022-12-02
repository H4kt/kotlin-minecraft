subprojects {

    repositories {

        mavenCentral()

        maven {
            name = "PaperMC"
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }

    }

    tasks.withType<ProcessResources>() {
        filter {
            it.replace("{version}", project.version.toString())
        }
    }

}