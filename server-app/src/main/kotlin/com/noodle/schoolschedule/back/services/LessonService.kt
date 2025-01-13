package com.noodle.schoolschedule.back.services

import com.noodle.schoolschedule.openapi.models.LessonRequest
import com.noodle.schoolschedule.openapi.models.LessonResponse

interface LessonService {
	fun create(request: LessonRequest): LessonResponse

	fun readAll(): List<LessonResponse>

	fun read(id: Int): LessonResponse?

	fun update(id: Int, request: LessonRequest): LessonResponse

	fun delete(id: Int)
}