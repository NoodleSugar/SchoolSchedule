package com.noodle.school_schedule.domain.mappings

import com.noodle.school_schedule.domain.entities.LessonEntity
import com.noodle.school_schedule.openapi.models.LessonRequest
import com.noodle.school_schedule.openapi.models.LessonResponse
import com.noodle.school_schedule.uri.UriFactory

fun LessonRequest.toEntity() = LessonEntity(
	name = name,
	description = description ?: "",
)

fun LessonEntity.toResponse() = LessonResponse(
	id = id!!,
	name = name,
	description = description,
)