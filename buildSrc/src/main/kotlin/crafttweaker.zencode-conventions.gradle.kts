import com.blamejared.crafttweaker.gradle.Properties
import com.blamejared.crafttweaker.gradle.Versions
import gradle.kotlin.dsl.accessors._8347e3f88f0262002bff02c22a2fad22.base
import gradle.kotlin.dsl.accessors._8347e3f88f0262002bff02c22a2fad22.java
import java.nio.charset.StandardCharsets

plugins {
    base
    `java-library`
}

base.archivesName.set("${Properties.MOD_NAME}-${project.name.lowercase()}-${Versions.MINECRAFT}")
group = "org.openzen.zencode"
version = Versions.ZENCODE

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(Versions.ZENCODE_JAVA))
    withSourcesJar()
}

repositories {
    mavenCentral()
}

tasks {
    withType<JavaCompile>().configureEach {
        options.encoding = StandardCharsets.UTF_8.toString()
    }
}
