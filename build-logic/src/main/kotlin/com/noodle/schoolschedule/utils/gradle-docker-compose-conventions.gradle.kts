package com.noodle.schoolschedule.utils

import com.avast.gradle.dockercompose.ComposeExtension

plugins {
	id("com.avast.gradle.docker-compose")
}

dockerCompose {
	useDockerComposeV2 = true
}

configure<ComposeExtension> {
	includeDependencies = true
}