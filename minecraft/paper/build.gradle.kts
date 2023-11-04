repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    api(projects.cornMinecraftSpigot)

    compileOnly(libs.paper.api)
    compileOnly("com.google.guava", "guava", "32.1.3-jre")
}
