import com.blamejared.crafttweaker.gradle.Properties
import com.blamejared.crafttweaker.gradle.Versions
import com.blamejared.gradle.mod.utils.GMUtils
import net.darkhax.curseforgegradle.Constants
import net.darkhax.curseforgegradle.TaskPublishCurseForge

plugins {
    id("crafttweaker.modloader-conventions")
    id("net.neoforged.moddev") version ("0.1.94")
}

neoForge {
    version = Versions.NEO_FORGE
    accessTransformers.add(project(":common").file("src/main/resources/META-INF/accesstransformer.cfg").absolutePath)
    runs {
        configureEach {
            environment("crafttweaker.logger.forward_to_latest_log", "true")
            environment("crafttweaker.scripts.directory", rootProject.file("dev_scripts").absolutePath)
            systemProperty("forge.enabledGameTestNamespaces", Properties.MOD_ID)
        }
        register("client") {
            client()
            gameDirectory.set(file("runs/client"))

        }
        register("server") {
            server()
        }
    }
    mods {
        register("crafttweaker") {
            sourceSet(sourceSets.main.get())
        }
    }
}

dependencies {
//    localOnlyRuntime("dev.architectury:architectury-neoforge:12.1.3")
//    localOnlyRuntime("me.shedaniel:RoughlyEnoughItems-neoforge:${Versions.REI}")
    localOnlyRuntime("mezz.jei:jei-${Versions.MINECRAFT}-neoforge:${Versions.JEI}")
}

tasks.create<TaskPublishCurseForge>("publishCurseForge") {
    dependsOn(tasks.jar)
    apiToken = GMUtils.locateProperty(project, "curseforgeApiToken") ?: 0

    val mainFile = upload(Properties.CURSE_PROJECT_ID, tasks.jar.get().archiveFile)
    mainFile.changelogType = "markdown"
    mainFile.changelog = GMUtils.smallChangelog(project, Properties.GIT_REPO)
    mainFile.releaseType = Constants.RELEASE_TYPE_RELEASE
    mainFile.addJavaVersion("Java ${Versions.MOD_JAVA}")
    mainFile.addGameVersion(Versions.MINECRAFT)
    mainFile.addModLoader("NeoForge")
    doLast {
        project.ext.set("curse_file_url", "${Properties.CURSE_HOMEPAGE_LINK}/files/${mainFile.curseFileId}")
    }
}

modrinth {
    token.set(GMUtils.locateProperty(project, "modrinth_token"))
    projectId.set(Properties.MODRINTH_PROJECT_ID)
    changelog.set(GMUtils.smallChangelog(project, Properties.GIT_REPO))
    versionName.set("NeoForge-${Versions.MINECRAFT}-$version")
    versionType.set("release")

    loaders.add("neoforge")
    gameVersions.set(listOf(Versions.MINECRAFT))
    uploadFile.set(tasks.jar.get())
}
tasks.modrinth.get().dependsOn(tasks.jar)