buildscript {
    val compose_compiler_version by extra("1.5.3")
    val hilt_version by extra("2.48")
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hilt_version")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
