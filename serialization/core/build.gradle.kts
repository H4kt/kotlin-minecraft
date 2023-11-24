val serializationVersion: String by project

version = serializationVersion

dependencies {

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core-jvm:$serializationVersion") {
        exclude(group = "org.jetbrains.kotlin")
    }

}
