package com.noodle.school_schedule.uri

import org.springframework.web.util.UriComponentsBuilder

interface UriFactory {
	companion object {
		const val URI_TEMPLATE = "/v1/{resource}/{id}"

		const val RESOURCE_LESSON = "lessons"

		fun buildUri(resource: String, id: String) = UriComponentsBuilder.fromPath(URI_TEMPLATE).build(resource, id)
		fun buildLessonUri(id: Int) = buildUri(RESOURCE_LESSON, id.toString())
	}
}