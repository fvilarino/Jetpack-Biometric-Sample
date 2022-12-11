buildscript {
    val compose_compiler_version by extra("1.3.2")
    val hilt_version by extra("2.44.1")
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hilt_version")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
