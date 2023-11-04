repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    api(projects.cornMinecraftSpigot)

    compileOnly(libs.paper.api)
    compileOnly(libs.guava)
}
