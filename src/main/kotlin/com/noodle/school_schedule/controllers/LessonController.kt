package com.noodle.school_schedule.controllers

import com.noodle.school_schedule.openapi.controllers.LessonApi
import com.noodle.school_schedule.openapi.models.LessonRequest
import com.noodle.school_schedule.openapi.models.LessonResponse
import com.noodle.school_schedule.services.LessonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LessonController(private val service: LessonService) : LessonApi {
	override fun postLesson(lessonRequest: LessonRequest): ResponseEntity<LessonResponse> {
		return ResponseEntity(service.create(lessonRequest), HttpStatus.CREATED)
	}

	override fun getAllLessons() = ResponseEntity.ok(service.readAll())

	override fun getLesson(id: Int) =
		service.read(id)
			?.let { ResponseEntity.ok(it) }
			?: ResponseEntity.notFound().build()

	override fun putLesson(id: Int, lessonRequest: LessonRequest) =
		try {
			val updated = service.update(id, lessonRequest)
			ResponseEntity.ok(updated)
		} catch (ex: IllegalStateException) {
			ResponseEntity.notFound().build()
		}

	override fun deleteLesson(id: Int): ResponseEntity<Unit> {
		service.delete(id)
		return ResponseEntity.noContent().build()
	}
}