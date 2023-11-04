repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly(libs.spigot.api)
    compileOnly("com.google.guava", "guava", "32.1.3-jre")
    compileOnly("org.jetbrains", "annotations", "24.0.1")
}
