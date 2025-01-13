plugins {
	id("com.noodle.schoolschedule.portman-conventions")
}

tasks.create<Exec>("test") {
	val specPath = rootProject.ext.get("openapiSpecPath")

	group = "portman"
	workingDir = projectDir

	executable("portman")
	args(
		"--local", specPath,
		"--baseUrl", "http://localhost:8080/v1",
		"--envFile", "./env-portman",
		"--output", "tmp/collection.postman.json",
		"--includeTests", true,
		"--syncPostman", false,
		"--runNewman", true,
		"--portmanConfigFile", "portman-config.yml",
	)
}

tasks.named("test") {
	dependsOn(":exec-env:ServerAppTestEnvComposeUp")
	finalizedBy(":exec-env:ServerAppTestEnvComposeDown")
}