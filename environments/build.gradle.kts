import com.avast.gradle.dockercompose.ComposeExtension
import com.avast.gradle.dockercompose.tasks.ComposeBuild

plugins {
	id("gradle-docker-compose.conventions")
}

dependencies {
	implementation(project(":server-app"))
}

dockerCompose {
	useDockerComposeV2.set(true)
}

configure<ComposeExtension> {
	includeDependencies.set(true)

	createNested("${rootProject.name}TestEnv").apply {
		useComposeFiles.set(listOf("${projectDir}/docker-compose.yml"))
		startedServices.set(listOf("server-app", "adminer"))
		environment.putAll(
			mapOf(
				"SPRING_PROFILE" to "test",
				"DB_NAME" to properties["TEST_DB_NAME"],
				"DB_USER" to properties["TEST_DB_USER"],
				"DB_PASS" to properties["TEST_DB_PASS"],
			)
		)
	}
}

tasks.withType<ComposeBuild> {
	dependsOn(tasks.build)
}