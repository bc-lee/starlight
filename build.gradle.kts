import org.jetbrains.changelog.Changelog
import org.jetbrains.intellij.platform.gradle.tasks.GenerateLexerTask
import org.jetbrains.intellij.platform.gradle.TestFrameworkType

val versionDetails: groovy.lang.Closure<com.palantir.gradle.gitversion.VersionDetails> by extra

plugins {
    id("java")
    alias(libs.plugins.changelog)
    alias(libs.plugins.gitVersion)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.intelliJPlatform)
    alias(libs.plugins.intelliJPlatformGrammarKit)
}

group = providers.gradleProperty("pluginGroup").get()
val basePluginVersion = providers.gradleProperty("pluginVersion").get()
val gitDetails = versionDetails()
val semVerTagPattern = Regex("""\d+\.\d+\.\d+""")
version = if (gitDetails.isCleanTag && semVerTagPattern.matches(gitDetails.version)) {
    gitDetails.version
} else {
    "$basePluginVersion-dev.${gitDetails.gitHash}"
}

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()

    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    testImplementation(libs.junit)
    testImplementation(libs.opentest4j)

    intellijPlatform {
        intellijIdea(providers.gradleProperty("platformVersion"))
        grammarKit()
        jflex()

        bundledPlugins(
            providers.gradleProperty("platformBundledPlugins").map {
                it.split(',').map(String::trim).filter(String::isNotEmpty)
            },
        )

        plugins(
            providers.gradleProperty("platformPlugins").map {
                it.split(',').map(String::trim).filter(String::isNotEmpty)
            },
        )

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

intellijPlatform {
    pluginConfiguration {
        name = providers.gradleProperty("pluginName")
        version = project.provider { project.version.toString() }
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
