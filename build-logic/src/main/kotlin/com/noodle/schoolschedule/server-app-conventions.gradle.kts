package com.noodle.schoolschedule

import org.gradle.accessors.dm.LibrariesForLibs

val Project.libs
	get() = the<LibrariesForLibs>()

plugins {
	id("com.noodle.schoolschedule.utils.common-conventions")
	id("com.noodle.schoolschedule.utils.springboot-conventions")
	id("com.bmuschko.docker-spring-boot-application")
}

group = "$group.back"

dependencies {
	implementation(project(":server-openapi"))
	runtimeOnly(libs.db.postgresql)
	testRuntimeOnly(libs.db.h2)
}