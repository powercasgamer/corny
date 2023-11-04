import net.kyori.indra.IndraPlugin
import net.kyori.indra.IndraPublishingPlugin
import net.kyori.indra.IndraCheckstylePlugin

plugins {
    id("net.kyori.indra")
    id("net.kyori.indra.publishing") apply false
    id("net.kyori.indra.checkstyle") apply false
    id("com.adarshr.test-logger")
}

group = "dev.mizule.corn"
description = "extremely opinionated mostly personal java utilities"

subprojects {
    apply<IndraPlugin>()
    apply<IndraPublishingPlugin>()
    apply<IndraCheckstylePlugin>()
    apply<com.adarshr.gradle.testlogger.TestLoggerPlugin>()

    repositories {
        mavenCentral()
        sonatype.ossSnapshots()
        sonatype.s01Snapshots()
    }

    dependencies {
        compileOnly(rootProject.libs.checker.qual)

        testImplementation(rootProject.libs.truth)

        testImplementation(rootProject.libs.junit.api)
        testImplementation(rootProject.libs.junit.engine)
    }

    indra {
        mitLicense()

        checkstyle("9.0")

        javaVersions {
            target(17)
            testWith(17)
        }

        github("powercasgamer", "corn") {
            ci(true)
            publishing(true)
        }

    publishSnapshotsTo("mizule", "https://repo.mizule.dev/snapshots")
    publishReleasesTo("mizule", "https://repo.mziule.dev/releases")

        configurePublications {
            pom {
                developers {
                    developer {
                        id.set("broccolai")
                        email.set("me@broccol.ai")
                    }
                }
            }
        }
    }

    testlogger {
        theme = com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA_PARALLEL
        showPassed = true
    }
}

tasks.withType<Jar> {
    onlyIf { false }
}
