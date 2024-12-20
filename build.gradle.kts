plugins {
	val kotlinVersion = "2.0.20"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	kotlin("plugin.jpa") version kotlinVersion
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.openapi.generator") version "7.10.0"
}
group = "com.noodle"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

kotlin {
	jvmToolchain(21)
	compilerOptions {
		freeCompilerArgs.add("-Xjsr305=strict")
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("io.swagger.core.v3:swagger-annotations:2.2.27")
	implementation("io.swagger.core.v3:swagger-models:2.2.27")
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
	testRuntimeOnly("com.h2database:h2:2.2.220")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
	jvmArgs("-XX:+EnableDynamicAgentLoading")
}

////////////////////////
// OpenApi generation //
////////////////////////

val apiDir = "$rootDir/openapi/api.yaml"
val apiOutputDir = layout.buildDirectory.dir("generated")

tasks.withType<GradleBuild> {
	dependsOn("openApiGenerate")
}

tasks.named("openApiGenerate") {
	dependsOn("openApiValidate")
}

openApiValidate {
	inputSpec.set(apiDir)
}

openApiGenerate {
	inputSpec.set(apiDir)
	outputDir.set(apiOutputDir.get().toString())
	generatorName.set("kotlin-spring")

	packageName.set("${group}.school_schedule.openapi")
	apiPackage.set("${packageName.get()}.controllers")
	modelPackage.set("${packageName.get()}.models")

	configOptions.putAll(
		mapOf(
			"gradleBuildFile" to "false",
			"useSpringBoot3" to "true",
			"basePackage" to packageName.get(),

			"useTags" to "true",
			"interfaceOnly" to "true",
			"requestMappingMode" to "api_interface",
			"skipDefaultInterface" to "true",
		)
	)

	typeMappings.putAll(
		mapOf(
			"string+time" to "java.time.LocalTime",
			"string+date" to "java.time.LocalDate",
			"string+uri-reference" to "java.net.URI",
		)
	)
	importMappings.putAll(
		mapOf(
			"string+time" to "java.time.LocalTime",
			"string+date" to "java.time.LocalDate",
			"string+uri-reference" to "java.net.URI",
		)
	)
}

sourceSets {
	main {
		kotlin {
			srcDir(apiOutputDir)
		}
	}
}