group = "com.noodle.school_schedule.back"
version = "0.0.1-SNAPSHOT"

plugins {
	kotlin("jvm")
	kotlin("plugin.spring")
	id("org.springframework.boot")
	id("io.spring.dependency-management")
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(project(":server-openapi"))
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")
	runtimeOnly("org.postgresql:postgresql")

	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(module = "mockito-core")
		exclude(module = "mockito-junit-jupiter")
	}
	testImplementation("com.ninja-squad:springmockk:4.0.2")
	testImplementation("com.appmattus.fixture:fixture:1.2.0")
	testRuntimeOnly("com.h2database:h2:2.2.220")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
	jvmArgs("-XX:+EnableDynamicAgentLoading")
}

tasks.withType<ProcessResources> {
	expand(project.properties)
}

tasks.jar {
	dependsOn(tasks.bootJar)
	enabled = false
}