package com.noodle.schoolschedule.utils

plugins {
	id("com.noodle.schoolschedule.utils.kotlin-conventions")
	id("io.spring.dependency-management")
}

dependencyManagement {
	imports {
		mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
	}
}