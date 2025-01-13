package com.noodle.schoolschedule.back.services.impl

import com.noodle.schoolschedule.back.repositories.LessonRepository
import com.noodle.schoolschedule.back.domain.entities.LessonEntity
import com.noodle.schoolschedule.back.domain.mappings.toEntity
import com.noodle.schoolschedule.back.domain.mappings.toResponse
import com.noodle.schoolschedule.back.services.LessonService
import com.noodle.schoolschedule.openapi.models.LessonRequest
import com.noodle.schoolschedule.openapi.models.LessonResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LessonServiceImpl(private val repository: LessonRepository) : LessonService {
	override fun create(request: LessonRequest) =
		repository
			.save(request.toEntity())
			.toResponse()

	override fun readAll(): List<LessonResponse> =
		repository
			.findAll()
			.map(LessonEntity::toResponse)

	override fun read(id: Int): LessonResponse? =
		repository
			.findByIdOrNull(id)
			?.toResponse()

	@Transactional
	override fun update(id: Int, request: LessonRequest): LessonResponse {
		check(repository.existsById(id))

		val updated = request.toEntity().copy(id = id)

		return repository
			.save(updated)
			.toResponse()
	}

	override fun delete(id: Int) = repository.deleteById(id)
}