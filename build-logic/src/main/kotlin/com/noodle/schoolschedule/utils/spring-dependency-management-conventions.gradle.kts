package com.noodle.schoolschedule.utils

import org.gradle.accessors.dm.LibrariesForLibs

plugins {
	id("com.noodle.schoolschedule.utils.kotlin-conventions")
	id("io.spring.dependency-management")
}

val Project.libs
	get() = the<LibrariesForLibs>()

dependencyManagement {
	imports {
		mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
	}
}