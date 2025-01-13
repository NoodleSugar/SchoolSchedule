package com.noodle.schoolschedule

import org.gradle.accessors.dm.LibrariesForLibs
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val Project.libs
	get() = the<LibrariesForLibs>()

plugins {
	id("com.noodle.schoolschedule.utils.common-conventions")
	id("com.noodle.schoolschedule.utils.kotlin-conventions")
	id("com.noodle.schoolschedule.utils.openapi-generator-conventions")
	id("com.noodle.schoolschedule.utils.spring-dependency-management-conventions")
}

group = "$group.openapi"

dependencies {
	implementation(libs.springboot.starter.web)
	implementation(libs.springboot.starter.validation)
	implementation(libs.swagger.annotations)
	implementation(libs.swagger.models)
	implementation(libs.jackson)
}

openApiGenerate {
	groupId = "$group"
	packageName = "$group"
	apiPackage = "$group.controllers"
	modelPackage = "$group.models"
	apiNameSuffix = "Controller"
}

tasks.withType<KotlinCompile> {
	dependsOn("openApiGenerate")
}