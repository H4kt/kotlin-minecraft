version = "0.55.0"

dependencies {
    implementation("com.charleskorn.kaml:kaml:$version") {
        exclude("org.jetbrains.kotlin")
        exclude("org.jetbrains.kotlinx")
    }
}
