rootProject.name = "SchoolSchedule"

pluginManagement {
	includeBuild("build-logic")
}

include(
	"server-openapi",
	"server-app",
	"exec-env",
	"portman",
)
