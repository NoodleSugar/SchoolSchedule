package com.noodle.school_schedule.back.domain.mappings

import com.noodle.school_schedule.back.domain.entities.LessonEntity
import com.noodle.school_schedule.openapi.models.LessonRequest
import com.noodle.school_schedule.openapi.models.LessonResponse

fun LessonRequest.toEntity() = LessonEntity(
	name = name,
	description = description ?: "",
)

fun LessonEntity.toResponse() = LessonResponse(
	id = id!!,
	name = name,
	description = description.ifEmpty { null },
)