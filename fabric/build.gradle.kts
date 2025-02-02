import com.blamejared.crafttweaker.gradle.Dependencies
import com.blamejared.crafttweaker.gradle.Properties
import com.blamejared.crafttweaker.gradle.Versions
import com.blamejared.gradle.mod.utils.GMUtils
import net.darkhax.curseforgegradle.TaskPublishCurseForge
import net.darkhax.curseforgegradle.Constants as CFG_Constants

plugins {
    id("crafttweaker.modloader-conventions")
    id("fabric-loom")
}

dependencies {
    minecraft("com.mojang:minecraft:${Versions.MINECRAFT}")
    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${Versions.PARCHMENT_MINECRAFT}:${Versions.PARCHMENT}@zip")
    })
    implementation("org.jetbrains:annotations:23.0.0")
    modImplementation("net.fabricmc:fabric-loader:${Versions.FABRIC_LOADER}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${Versions.FABRIC}")

    modLocalRuntime("me.shedaniel:RoughlyEnoughItems-fabric:${Versions.REI}") {
        exclude("net.fabricmc", "fabric-loader")
    }

    implementation("org.reflections:reflections:${Versions.REFLECTIONS}")?.let { include(it) }
    implementation("org.javassist:javassist:${Versions.JAVA_ASSIST}")?.let { include(it) } // required for reflections

    modImplementation("com.faux.fauxcustomentitydata:FauxCustomEntityData-fabric-${Versions.MINECRAFT}:${Versions.FAUX_CUSTOM_ENTITY_DATA}")
}

loom {
    accessWidenerPath.set(project(":common").file("src/main/resources/${Properties.MOD_ID}.accesswidener"))
    @Suppress("UnstableApiUsage")
    mixin {
        this.defaultRefmapName.set("${Properties.MOD_ID}.refmap.json")
        useLegacyMixinAp = false
    }
    mods {
        register("crafttweaker") {
            sourceSet(sourceSets.main.get())
            Dependencies.ZENCODE.forEach {
                sourceSet(project(it).sourceSets.main.get())
                sourceSet(project(it).sourceSets.test.get())
            }
        }
    }
    runs {
        named("client") {
            client()
            configName = "Fabric Client"
            ideConfigGenerated(true)
            runDir("run")
            programArg("--username=Dev")
            environmentVariable("crafttweaker.logger.forward_to_latest_log", true)
            environmentVariable("crafttweaker.scripts.directory", rootProject.file("dev_scripts"))
        }
        named("server") {
            server()
            configName = "Fabric Server"
            ideConfigGenerated(true)
            runDir("run_server")
            environmentVariable("crafttweaker.logger.forward_to_latest_log", true)
            environmentVariable("crafttweaker.scripts.directory", rootProject.file("dev_scripts"))
        }
    }
}

tasks.create<TaskPublishCurseForge>("publishCurseForge") {
    dependsOn(tasks.remapJar)
    apiToken = System.getenv("curseforgeApiToken") ?: 0

    val mainFile = upload(Properties.CURSE_PROJECT_ID, tasks.remapJar.get().archiveFile)
    mainFile.changelogType = "markdown"
    mainFile.changelog = GMUtils.smallChangelog(project, Properties.GIT_REPO)
    mainFile.releaseType = CFG_Constants.RELEASE_TYPE_RELEASE
    mainFile.addGameVersion(Versions.MINECRAFT)
    mainFile.addJavaVersion("Java ${Versions.MOD_JAVA}")
    mainFile.addRequirement("faux-custom-entity-data")
    mainFile.addRequirement("fabric-api")

    doLast {
        project.ext.set("curse_file_url", "${Properties.CURSE_HOMEPAGE_LINK}/files/${mainFile.curseFileId}")
    }
}

modrinth {
    token.set(GMUtils.locateProperty(project, "modrinth_token"))
    projectId.set(Properties.MODRINTH_PROJECT_ID)
    changelog.set(GMUtils.smallChangelog(project, Properties.GIT_REPO))
    versionName.set("Fabric-${Versions.MINECRAFT}-$version")
    versionType.set("release")
    uploadFile.set(tasks.remapJar.get())
    dependencies {
        required.project("faux-custom-entity-data")
        required.project("fabric-api")
    }
}
tasks.modrinth.get().dependsOn(tasks.remapJar)