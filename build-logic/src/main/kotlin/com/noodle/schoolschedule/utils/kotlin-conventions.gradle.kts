package com.noodle.schoolschedule.utils

import org.gradle.accessors.dm.LibrariesForLibs

val Project.libs
	get() = the<LibrariesForLibs>()

plugins {
	kotlin("jvm")
}

kotlin {
	jvmToolchain(21)
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(libs.kotlin.reflect)
	implementation(libs.kotlin.stdlib)
	testImplementation(libs.test.junit)
	testImplementation(libs.test.kotlin.fixture)
	testImplementation(libs.test.spring.mockk)
	testRuntimeOnly(libs.test.junit.launcher)
}

tasks.withType<Test> {
	useJUnitPlatform()
	jvmArgs("-XX:+EnableDynamicAgentLoading")
}