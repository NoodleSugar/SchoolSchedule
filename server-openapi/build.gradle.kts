import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("server-openapi.conventions")
}

val apiInputDir = "${rootDir.path}/openapi/api.yaml"
val apiOutputDir = "${projectDir.path}/generated"

openApiValidate {
	inputSpec.set(apiInputDir)
}

openApiGenerate {
	inputSpec = apiInputDir
	outputDir = apiOutputDir
	generatorName = "kotlin-spring"

	groupId = "$group"
	packageName = "$group"
	apiPackage = "$group.controllers"
	modelPackage = "$group.models"
	apiNameSuffix = "Controller"

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

tasks.withType<KotlinCompile> {
	dependsOn("openApiGenerate")
}

tasks.openApiGenerate {
	dependsOn("openApiValidate")
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