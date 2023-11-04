repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly(libs.spigot.api)
    compileOnly(libs.guava)
    compileOnly(libs.annotations)
}
