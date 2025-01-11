plugins {
	`kotlin-dsl`
	`kotlin-dsl-precompiled-script-plugins`
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(
		"org.jetbrains.kotlin.jvm",
		"org.jetbrains.kotlin.jvm.gradle.plugin",
		"2.1.0"
	)
	implementation(
		"org.jetbrains.kotlin.plugin.spring",
		"org.jetbrains.kotlin.plugin.spring.gradle.plugin",
		"2.1.0"
	)
	implementation(
		"org.springframework.boot",
		"org.springframework.boot.gradle.plugin",
		"3.4.1"
	)
	implementation(
		"org.springframework.boot",
		"org.springframework.boot.gradle.plugin",
		"3.4.1"
	)
	implementation(
		"io.spring.dependency-management",
		"io.spring.dependency-management.gradle.plugin",
		"1.1.7"
	)
	implementation(
		"org.openapitools",
		"openapi-generator-gradle-plugin",
		"7.10.0"
	)
	implementation(
		"com.avast.gradle.docker-compose",
		"com.avast.gradle.docker-compose.gradle.plugin",
		"0.17.12"
	)
}