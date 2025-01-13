package com.noodle.schoolschedule.back.domain.mappings

import com.noodle.schoolschedule.back.domain.entities.LessonEntity
import com.noodle.schoolschedule.openapi.models.LessonRequest
import com.noodle.schoolschedule.openapi.models.LessonResponse

fun LessonRequest.toEntity() = LessonEntity(
	name = name,
	description = description ?: "",
)

fun LessonEntity.toResponse() = LessonResponse(
	id = id!!,
	name = name,
	description = description.ifEmpty { null },
)