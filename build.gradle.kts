import com.diffplug.gradle.spotless.FormatExtension
import com.diffplug.gradle.spotless.SpotlessExtension
import net.kyori.indra.IndraCheckstylePlugin
import net.kyori.indra.IndraPlugin
import net.kyori.indra.IndraPublishingPlugin
import net.kyori.indra.licenser.spotless.HeaderFormat
import net.kyori.indra.licenser.spotless.IndraSpotlessLicenserExtension
import net.kyori.indra.licenser.spotless.IndraSpotlessLicenserPlugin
import java.util.*

plugins {
    id("net.kyori.indra")
    id("net.kyori.indra.publishing") apply false
    id("net.kyori.indra.checkstyle") apply false
    id("net.kyori.indra.licenser.spotless") apply false
    id("com.adarshr.test-logger")
}

group = "dev.mizule.corn"
description = "extremely opinionated mostly personal java utilities"

subprojects {
    apply<IndraPlugin>()
    apply<IndraPublishingPlugin>()
    apply<IndraCheckstylePlugin>()
    apply<IndraSpotlessLicenserPlugin>()
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

    configure<IndraSpotlessLicenserExtension> {
        headerFormat(HeaderFormat.starSlash())
        licenseHeaderFile(rootProject.projectDir.resolve("HEADER"))

        val currentYear = Calendar.getInstance().apply {
            time = Date()
        }.get(Calendar.YEAR)
        val createdYear = 2020
        val year = if (createdYear == currentYear) createdYear.toString() else "$createdYear-$currentYear"

        property("name", providers.gradleProperty("projectName").getOrElse("corn"))
        property("year", year)
        property("description", project.description ?: "A Project")
        property("author", "broccolai")
    }

    configure<SpotlessExtension> {
        fun FormatExtension.applyCommon() {
            trimTrailingWhitespace()
            endWithNewline()
            encoding("UTF-8")
            toggleOffOn()
        }
        java {
            importOrderFile(rootProject.file(".spotless/mizule.importorder"))
            removeUnusedImports()
            formatAnnotations()
            applyCommon()
            target("*/src/*/java/**/*.java")
            target("*/src/*/templates/**/*.java")
        }
        kotlinGradle {
            applyCommon()
        }
    }

    indra {
        lgpl3OnlyLicense() // or later?

        checkstyle("9.0")

        javaVersions {
            target(17)
            testWith(17, 21)
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
                    developer {
                        id.set("powercas_gamer")
                        email.set("cas@mizule.dev")
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
