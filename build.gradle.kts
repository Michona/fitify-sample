plugins {
    id(Plugins.ksp) version "1.8.10-1.0.9" apply false
}
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(ProjectDependencies.androidGradlePlugin)
        classpath(ProjectDependencies.kotlinGradlePlugin)
    }
}

task<Exec>("ktlintCheck") {
    commandLine("ktlint")
}

task<Exec>("ktlintFormat") {
    commandLine("ktlint", "-F")
}

task("clean") {
    delete(rootProject.buildDir)
}
