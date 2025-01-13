package com.noodle.schoolschedule.utils

plugins {
	id("com.noodle.schoolschedule.utils.common-conventions")
	id("org.openapi.generator")
}

openApiValidate {
	inputSpec = "${rootProject.ext.get("openapiSpecPath")}"
}

openApiGenerate {
	inputSpec = "${rootProject.ext.get("openapiSpecPath")}"
}

tasks.openApiGenerate {
	dependsOn(tasks.openApiValidate)
}