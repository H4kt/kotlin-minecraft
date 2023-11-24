val serializationVersion: String by project

version = serializationVersion

dependencies {

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion") {
        exclude(group = "org.jetbrains.kotlin")
        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-serialization-core-jvm")
    }

}
