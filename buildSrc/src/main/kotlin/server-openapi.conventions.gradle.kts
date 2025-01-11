group = "com.noodle.school_schedule.openapi"
version = "0.0.1-SNAPSHOT"

plugins {
	kotlin("jvm")
	id("org.openapi.generator")
	id("io.spring.dependency-management")
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
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("io.swagger.core.v3:swagger-annotations:2.2.27")
	implementation("io.swagger.core.v3:swagger-models:2.2.27")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}