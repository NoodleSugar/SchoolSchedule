package com.noodle.schoolschedule

plugins {
	id("com.noodle.schoolschedule.utils.common-conventions")
}

tasks.create<Delete>("clean") {
	group = "portman"
	doFirst { delete("$projectDir/tmp") }
}