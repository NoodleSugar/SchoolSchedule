import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("com.noodle.schoolschedule.server-openapi-conventions")
}

val apiOutputDir = "${projectDir.path}/generated"

openApiGenerate {
	outputDir = apiOutputDir
	generatorName = "kotlin-spring"

	configOptions.putAll(
		mapOf(
			"gradleBuildFile" to "false",
			"useSpringBoot3" to "true",
			"basePackage" to "$group",

			"interfaceOnly" to "true",
			"serviceInterface" to "true",
			"useTags" to "true",
			"requestMappingMode" to "api_interface",
			"skipDefaultInterface" to "true",
		)
	)

	typeMappings.putAll(
		mapOf(
			"string+time" to "java.time.LocalTime",
			"string+date" to "java.time.LocalDate",
		)
	)
	importMappings.putAll(
		mapOf(
			"string+time" to "java.time.LocalTime",
			"string+date" to "java.time.LocalDate",
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

tasks.clean {
	doFirst {
		delete(apiOutputDir)
	}
}