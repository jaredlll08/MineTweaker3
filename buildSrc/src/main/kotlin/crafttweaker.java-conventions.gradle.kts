import com.blamejared.crafttweaker.gradle.Dependencies
import com.blamejared.crafttweaker.gradle.Properties
import com.blamejared.crafttweaker.gradle.Versions
import com.blamejared.gradle.mod.utils.GMUtils
import org.gradle.jvm.tasks.Jar
import java.nio.charset.StandardCharsets

plugins {
    base
    `java-library`
    idea
    `maven-publish`
}

base.archivesName.set("${Properties.MOD_NAME}-${project.name.lowercase()}-${Versions.MINECRAFT}")
version = GMUtils.updatingVersion(Versions.MOD)
group = Properties.GROUP

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_21.majorVersion))
    withSourcesJar()
    withJavadocJar()
}

@Suppress("UnstableApiUsage")
repositories {
    mavenCentral()
    maven("https://maven.blamejared.com/") {
        name = "BlameJared"
        content {
            includeGroupAndSubgroups("com.blamejared")
            includeGroupAndSubgroups("mezz.jei")
            includeGroupAndSubgroups("com.faux")
            includeGroupAndSubgroups("org.openzen")
        }
    }
    maven("https://repo.spongepowered.org/repository/maven-public/") {
        name = "Sponge"
        content {
            includeGroupAndSubgroups("org.spongepowered")
        }
    }
    maven("https://maven.parchmentmc.org/") {
        name = "ParchmentMC"
        content {
            includeGroupAndSubgroups("org.parchmentmc")
        }
    }
    maven("https://maven.shedaniel.me/") {
        name = "REI"
        content {
            includeGroupAndSubgroups("me.shedaniel")
            includeGroupAndSubgroups("dev.architectury")
        }
    }
}

setOf("apiElements", "runtimeElements", "sourcesElements", "javadocElements").forEach { it: String ->
    configurations.getByName(it).outgoing {
        capability("$group:${base.archivesName.get()}:$version")
        capability("$group:${Properties.MOD_ID}-${project.name}-${Versions.MINECRAFT}:$version")
        capability("$group:${Properties.MOD_ID}:$version")
    }
    publishing.publications {
        if (this is MavenPublication) {
            this.suppressPomMetadataWarningsFor(it)
        }
    }
}

tasks {
    named<JavaCompile>("compileJava").configure {
        options.compilerArgs.add("-Acrafttweaker.processor.document.output_directory=${project.rootProject.file(Properties.DOCS_OUTPUT_DIR)}")
        options.compilerArgs.add("-Acrafttweaker.processor.document.multi_source=true")
        options.encoding = StandardCharsets.UTF_8.toString()
        options.release.set(Versions.MOD_JAVA.toInt())
        Dependencies.ZENCODE.forEach {
            source(project(it).sourceSets.getByName("main").allSource)
        }
    }
    named<Javadoc>("javadoc").configure {
        options {
            encoding = StandardCharsets.UTF_8.toString()
            // Javadoc defines this specifically as StandardJavadocDocletOptions
            // but only has a getter for MinimalJavadocOptions, but let's just make sure to be safe
            if (this is StandardJavadocDocletOptions) {
                tags("docParam", "docEvent", "docShortDescription", "docObtention")
                addStringOption("Xdoclint:none", "-quiet")
            }
        }
        Dependencies.ZENCODE.forEach {
            source(project(it).sourceSets.getByName("main").allJava)
        }
    }
    named<Jar>("sourcesJar").configure {
        Dependencies.ZENCODE.forEach {
            from(project(it).sourceSets.getByName("main").allSource)
        }
    }
    named<ProcessResources>("processResources").configure {
        dependsOn(":StdLibs:zipItUp")
        from(project.files(project.evaluationDependsOn(":StdLibs").tasks.getByName("zipItUp").outputs))

        val properties = mapOf(
                "version" to project.version,
                "MOD" to Versions.MOD,
                "JAVA" to Versions.MOD_JAVA,
                "MINECRAFT" to Versions.MINECRAFT,
                "FABRIC_LOADER" to Versions.FABRIC_LOADER,
                "FABRIC" to Versions.FABRIC,
                "FORGE" to Versions.FORGE,
                "FORGE_LOADER" to Versions.FORGE_LOADER,
                "NEO_FORGE" to Versions.NEO_FORGE,
                "NEO_FORGE_LOADER" to Versions.NEO_FORGE_LOADER,
                "GROUP" to Properties.GROUP,
                "NAME" to Properties.MOD_NAME,
                "AUTHOR" to Properties.MOD_AUTHOR,
                "MOD_ID" to Properties.MOD_ID,
                "AVATAR" to Properties.MOD_AVATAR,
                "CURSE_PROJECT_ID" to Properties.CURSE_PROJECT_ID,
                "CURSE_HOMEPAGE_LINK" to Properties.CURSE_HOMEPAGE_LINK,
                "MODRINTH_PROJECT_ID" to Properties.MODRINTH_PROJECT_ID,
                "GIT_REPO" to Properties.GIT_REPO,
                "DESCRIPTION" to Properties.DESCRIPTION,
                "ITEM_ICON" to Properties.ITEM_ICON,
        )
        inputs.properties(properties)
        filesMatching(setOf("fabric.mod.json", "META-INF/mods.toml", "META-INF/neoforge.mods.toml", "pack.mcmeta")) {
            expand(properties)
        }
    }
    named<Jar>("jar").configure {
        from(project.rootProject.file("LICENSE"))
        manifest {
            attributes["Specification-Title"] = Properties.MOD_NAME
            attributes["Specification-Vendor"] = Properties.SIMPLE_AUTHOR
            attributes["Specification-Version"] = archiveVersion
            attributes["Implementation-Title"] = project.name
            attributes["Implementation-Version"] = archiveVersion
            attributes["Implementation-Vendor"] = Properties.SIMPLE_AUTHOR
            attributes["Built-On-Java"] = "${System.getProperty("java.vm.version")} (${System.getProperty("java.vm.vendor")})"
            attributes["Build-On-Minecraft"] = Versions.MINECRAFT
        }
    }
}

@Suppress("UnstableApiUsage")
configurations {
    val library = register("library")
    val lor = register("localOnlyRuntime")
    getByName("implementation") {
        extendsFrom(library.get())
    }
    getByName("runtimeClasspath").extendsFrom(lor.get())
    // fabric loader 0.15 adds mixinextras which causes a crash due to us pulling in other ASM versions from ZC
//    all {
//        resolutionStrategy {
//            force("org.ow2.asm:asm:9.6")
//            force("org.ow2.asm:asm-commons:9.6")
//        }
//    }
}

dependencies {
    annotationProcessor("com.blamejared.crafttweaker:Crafttweaker_Annotation_Processors:${Versions.CRAFTTWEAKER_ANNOTATION_PROCESSOR}")

    Dependencies.ZENCODE.forEach {
        implementation(project(it))
    }
}

publishing {
    publications {
        register<MavenPublication>("mavenJava") {
            artifactId = base.archivesName.get()
            from(components.getByName("java"))
        }
    }
    repositories {
        maven(System.getenv("local_maven_url") ?: "file://${project.projectDir}/repo")
    }
}

idea {
    module {
        excludeDirs.addAll(setOf(project.file("run"), project.file("runs"), project.file("run_server"), project.file("run_client"), project.file("run_game_test")))
    }
}