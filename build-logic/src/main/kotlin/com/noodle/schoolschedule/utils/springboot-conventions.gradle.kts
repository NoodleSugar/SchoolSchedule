package com.noodle.schoolschedule.utils

import org.gradle.accessors.dm.LibrariesForLibs

val Project.libs
	get() = the<LibrariesForLibs>()

plugins {
	id("com.noodle.schoolschedule.utils.kotlin-conventions")
	id("com.noodle.schoolschedule.utils.spring-dependency-management-conventions")
	kotlin("plugin.spring")
	kotlin("plugin.jpa")
	id("org.springframework.boot")
}

dependencies {
	implementation(libs.springboot.starter.web)
	implementation(libs.springboot.starter.jpa)
	implementation(libs.springboot.starter.actuator)
	implementation(libs.logback.classic)
	implementation(libs.logback.core)
	implementation(libs.spring.data.jpa)
	implementation(libs.jackson)
	implementation(libs.flyway.core)
	implementation(libs.flyway.postgresql)
	testImplementation(libs.springboot.starter.test) {
		exclude(module = "mockito-core")
		exclude(module = "mockito-junit-jupiter")
	}
}

tasks.withType<ProcessResources> {
	expand(project.properties)
}