plugins {
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.com.google.dagger.dagger)
    kapt(libs.com.google.dagger.dagger.compiler)
}
