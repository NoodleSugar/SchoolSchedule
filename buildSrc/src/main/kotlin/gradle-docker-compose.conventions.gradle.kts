import gradle.kotlin.dsl.accessors._1bea848f001a408cf5a3cf44f23a7422.dependencyManagement
import gradle.kotlin.dsl.accessors._1bea848f001a408cf5a3cf44f23a7422.implementation

group = "com.noodle.school_schedule"
version = "0.0.1-SNAPSHOT"

plugins {
	kotlin("jvm")
	kotlin("plugin.spring")
	id("io.spring.dependency-management")
	id("com.avast.gradle.docker-compose")
}

repositories {
	mavenCentral()
}

dependencyManagement {
	imports {
		mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
	}
}

dependencies {
	implementation(
		"org.springframework.boot",
		"org.springframework.boot.gradle.plugin",
		"3.4.1"
	)
}