plugins {
	`kotlin-dsl`
	`kotlin-dsl-precompiled-script-plugins`
}

repositories {
	gradlePluginPortal()
	mavenCentral()
}

dependencies {
	implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
	implementation(libs.kotlin.jvm.plugin)
	implementation(libs.kotlin.spring.plugin)
	implementation(libs.kotlin.jpa.plugin)
	implementation(libs.springboot.plugin)
	implementation(libs.spring.dependency.management.plugin)
	implementation(libs.openapi.generator.plugin)
	implementation(libs.docker.compose.plugin)
	implementation(libs.docker.plugin)
}