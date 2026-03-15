import org.jetbrains.changelog.Changelog
import org.jetbrains.grammarkit.tasks.GenerateLexerTask
import org.jetbrains.intellij.platform.gradle.TestFrameworkType

plugins {
    id("java") // Java support
    id("org.jetbrains.grammarkit") version "2023.3.0.2"
    alias(libs.plugins.changelog) // Gradle Changelog Plugin
    alias(libs.plugins.kotlin) // Kotlin support
    alias(libs.plugins.intelliJPlatform) // IntelliJ Platform Gradle Plugin
}

group = providers.gradleProperty("pluginGroup").get()
version = providers.gradleProperty("pluginVersion").get()

// Set the JVM language level used to build the project.
kotlin {
    jvmToolchain(21)
}

// Configure project's dependencies
repositories {
    mavenCentral()

    // IntelliJ Platform Gradle Plugin Repositories Extension - read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin-repositories-extension.html
    intellijPlatform {
        defaultRepositories()
    }
}

// Dependencies are managed with Gradle version catalog - read more: https://docs.gradle.org/current/userguide/version_catalogs.html
dependencies {
    testImplementation(libs.junit)
    testImplementation(libs.opentest4j)

    // IntelliJ Platform Gradle Plugin Dependencies Extension - read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin-dependencies-extension.html
    intellijPlatform {
        intellijIdea(providers.gradleProperty("platformVersion"))

        // Plugin Dependencies. Uses `platformBundledPlugins` property from the gradle.properties file for bundled IntelliJ Platform plugins.
        bundledPlugins(
            providers.gradleProperty("platformBundledPlugins").map {
                it.split(',').map(String::trim).filter(String::isNotEmpty)
            },
        )

        // Plugin Dependencies. Uses `platformPlugins` property from the gradle.properties file for plugin from JetBrains Marketplace.
        plugins(
            providers.gradleProperty("platformPlugins").map {
                it.split(',').map(String::trim).filter(String::isNotEmpty)
            },
        )

        // Module Dependencies. Uses `platformBundledModules` property from the gradle.properties file for bundled IntelliJ Platform modules.
        bundledModules(
            providers.gradleProperty("platformBundledModules").map {
                it.split(',').map(String::trim).filter(String::isNotEmpty)
            },
        )

        testFramework(TestFrameworkType.Platform)
    }
}

val generateStarlightLexer by tasks.registering(GenerateLexerTask::class) {
    sourceFile.set(file("src/main/kotlin/io/github/bclee/starlight/lexer/Starlark.flex"))
    targetOutputDir = file("build/generated/grammarkit/lexer/io/github/bclee/starlight/lexer")
    purgeOldFiles = true
}

sourceSets {
    main {
        java.srcDir("build/generated/grammarkit/lexer")
    }
}

// Configure IntelliJ Platform Gradle Plugin - read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin-extension.html
intellijPlatform {
    pluginConfiguration {
        name = providers.gradleProperty("pluginName")
        version = providers.gradleProperty("pluginVersion")
        description = "Starlark language support for Bazel files: syntax highlighting, line comments, and parser errors."
        val changelog = project.changelog // local variable for configuration cache compatibility
        changeNotes.set(providers.gradleProperty("pluginVersion").map { pluginVersion ->
            with(changelog) {
                renderItem(
                    (getOrNull(pluginVersion) ?: getUnreleased())
                        .withHeader(false)
                        .withEmptySections(false),
                    Changelog.OutputType.HTML,
                )
            }
        })

        ideaVersion {
            sinceBuild = providers.gradleProperty("pluginSinceBuild")
        }
    }

    pluginVerification {
        ides {
            recommended()
        }
    }
}

changelog {
    groups.empty()
}

tasks {
    named("compileKotlin") {
        dependsOn(generateStarlightLexer)
    }
    named("compileJava") {
        dependsOn(generateStarlightLexer)
    }

    wrapper {
        gradleVersion = providers.gradleProperty("gradleVersion").get()
    }

    publishPlugin {
        dependsOn(patchChangelog)
    }
}
