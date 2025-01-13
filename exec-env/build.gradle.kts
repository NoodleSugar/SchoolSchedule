import com.avast.gradle.dockercompose.ComposeExtension

plugins {
	id("com.noodle.schoolschedule.exec-env-conventions")
}

configure<ComposeExtension> {
	createNested("ServerAppTestEnv").apply {
		useComposeFiles = listOf("${projectDir}/docker-compose.yml")
		startedServices = listOf("server-app")
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