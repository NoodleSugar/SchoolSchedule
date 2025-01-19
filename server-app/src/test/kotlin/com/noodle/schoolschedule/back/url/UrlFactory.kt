package com.noodle.schoolschedule.back.url

import org.springframework.web.util.DefaultUriBuilderFactory

class UrlFactory {
	companion object {
		private const val BASE_URI = "http://localhost:8080/v1"
		private val builderFactory = DefaultUriBuilderFactory(BASE_URI)

		private val lessonUriBuilder = builderFactory.uriString("/lessons")
		private val lessonUriIdBuilder = builderFactory.uriString("/lessons/{id}")

		fun lessonUri() = lessonUriBuilder.build()
		fun lessonUri(id: Int) = lessonUriIdBuilder.build(id)
	}
}