plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version("8.1.1")
}

group = "me.dave"
version = "1.0-BETA"

repositories {
    mavenCentral()
    mavenLocal()
    maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
    maven { url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/") }
    maven { url = uri("https://repo.opencollab.dev/main/") }
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.20-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.4")
    compileOnly("org.geysermc.floodgate:api:2.0-SNAPSHOT")
    implementation("com.github.CoolDCB:ChatColorHandler:v2.1.5")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    shadowJar {
        relocate("me.dave.chatcolorhandler", "me.dave.configurableplaceholders.libraries.chatcolor")

        val folder = System.getenv("pluginFolder_1-20")
        if (folder != null) destinationDirectory.set(file(folder))
        archiveFileName.set("${project.name}-${project.version}.jar")
    }

    processResources{
        expand(project.properties)

        inputs.property("version", rootProject.version)
        filesMatching("plugin.yml") {
            expand("version" to rootProject.version)
        }
    }
}