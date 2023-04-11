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