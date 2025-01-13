package com.noodle.schoolschedule

import com.avast.gradle.dockercompose.tasks.ComposeBuild

plugins {
	id("com.noodle.schoolschedule.utils.common-conventions")
	id("com.noodle.schoolschedule.utils.gradle-docker-compose-conventions")
}

tasks.withType<ComposeBuild>() {
	dependsOn(":server-app:dockerBuildImage")
}