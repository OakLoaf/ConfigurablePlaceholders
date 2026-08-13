plugins {
    `java-library`
    id("com.gradleup.shadow") version("9.3.1")
    id("xyz.jpenilla.run-paper") version("3.0.2")
}

group = "org.lushplugins"
version = "2.0.1"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.papermc.io/repository/maven-public/") // Paper
    maven("https://repo.helpch.at/releases") // PlaceholderAPI
    maven("https://repo.opencollab.dev/main/") // Floodgate
    maven("https://repo.lushplugins.org/snapshots/") // ChatColorHandler
}

dependencies {
    // Dependencies
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.12.3")
    compileOnly("org.geysermc.floodgate:api:2.2.5-SNAPSHOT")

    // Libraries
    implementation("org.lushplugins.chatcolorhandler:paper:8.1.1")
    implementation("io.github.revxrsal:lamp.common:4.0.0-rc.18")
    implementation("io.github.revxrsal:lamp.bukkit:4.0.0-rc.18")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))

    registerFeature("optional") {
        usingSourceSet(sourceSets["main"])
    }

    withSourcesJar()
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.compilerArgs.add("-parameters")
    }

    shadowJar {
        relocate("org.lushplugins.chatcolorhandler", "org.lushplugins.configurableplaceholders.libraries.chatcolor")

        minimize()

        archiveFileName.set("${project.name}-${project.version}.jar")
    }

    processResources{
        filesMatching("plugin.yml") {
            expand(project.properties)
        }

        inputs.property("version", rootProject.version)
        filesMatching("plugin.yml") {
            expand("version" to rootProject.version)
        }
    }

    runServer {
        minecraftVersion("1.21.11")

        downloadPlugins {
            modrinth("viaversion", "5.7.1")
            modrinth("viabackwards", "5.7.1")
        }
    }
}