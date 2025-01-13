plugins {
	id("com.noodle.schoolschedule.server-app-conventions")
}

docker {
	springBootApplication {
		baseImage.set("amazoncorretto:21")
		images.set(listOf("server-app"))
		ports.set(listOf(8080))
	}
}