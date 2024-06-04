import java.io.FileFilter

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        exclusiveContent {
            forRepository {
                maven("https://maven.fabricmc.net") {
                    name = "Fabric"
                }
            }
            filter {
                includeGroup("net.fabricmc")
                includeGroup("fabric-loom")
            }
        }
        maven("https://maven.neoforged.net/releases")
        // Add the maven repository for the ModDevGradle plugin.
        maven("https://prmaven.neoforged.net/ModDevGradle/pr1") {
            content {
                includeModule("net.neoforged.moddev", "net.neoforged.moddev.gradle.plugin")
                includeModule("net.neoforged.moddev.junit", "net.neoforged.moddev.junit.gradle.plugin")
                includeModule("net.neoforged", "moddev-gradle")
            }
        }
        exclusiveContent {
            forRepository {
                maven("https://repo.spongepowered.org/repository/maven-public") {
                    name = "Sponge"
                }
            }
            filter {
                includeGroupAndSubgroups("org.spongepowered")
            }
        }
        exclusiveContent {
            forRepository {
                maven("https://maven.minecraftforge.net") {
                    name = "Forge"
                }
            }
            filter {
                includeGroup("net.minecraftforge")
            }
        }
        exclusiveContent {
            forRepository {
                maven("https://maven.parchmentmc.org") {
                    name = "ParchmentMC"
                }
            }
            filter {
                includeGroup("org.parchmentmc.data")
            }
        }

        exclusiveContent {
            forRepository {
                maven("https://maven.blamejared.com") {
                    name = "BlameJared"
                }
            }
            filter {
                includeGroupAndSubgroups("com.blamejared")
                includeGroupAndSubgroups("net.darkhax")
            }
        }

    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "CraftTweaker"
val excludedProjects = setOf(
        "buildSrc",
        "ModuleDeserializer",
        "ModuleSerializationShared",
        "ModuleSerializer",
        "JavaSource",
        "JavaSourceCompiler",
        "IDE",
        "Constructor",
        "DrawableGui",
        "DrawableGuiIconConverter",
        "CompilerShared"
)

include("ZenCode")
collectSubProjects("ZenCode")

include("common")
include("fabric")
//include("forge")
include("neoforge")

if (file("CraftTweaker-Annotation-Processors").exists()) {
    includeBuild("CraftTweaker-Annotation-Processors") {
        dependencySubstitution {
            substitute(module("com.blamejared.crafttweaker:Crafttweaker_Annotation_Processors")).using(project(":"))
        }
    }
}

fun collectSubProjects(folder: String) {

    file(folder).listFiles(FileFilter {

        if (!it.isDirectory || excludedProjects.contains(it.name)) {
            return@FileFilter false
        }

        return@FileFilter File(it, "build.gradle").isFile || File(it, "build.gradle.kts").isFile
    })?.forEach {
        include(":${it.name}")
        project(":${it.name}").projectDir = File("./$folder/" + it.name)
    }
}
