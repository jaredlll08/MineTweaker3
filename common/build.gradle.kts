import com.blamejared.crafttweaker.gradle.Versions

plugins {
    id("crafttweaker.java-conventions")
    id("net.neoforged.moddev")
}

neoForge {
    neoFormVersion = Versions.NEO_FORM
    accessTransformers.add(project.file("src/main/resources/META-INF/accesstransformer.cfg").absolutePath)
    parchment {
        minecraftVersion = Versions.PARCHMENT_MINECRAFT
        mappingsVersion = Versions.PARCHMENT
    }
}

dependencies {
    compileOnly("org.spongepowered:mixin:${Versions.MIXIN}")
    compileOnly("io.github.llamalad7:mixinextras-common:0.3.5")
    annotationProcessor("io.github.llamalad7:mixinextras-common:0.3.5")
}

configurations {
    register("commonJava") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
    register("commonResources") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
}

artifacts {
    add("commonJava", sourceSets.main.get().java.sourceDirectories.singleFile)
    add("commonResources", sourceSets.main.get().resources.sourceDirectories.singleFile)
}