package com.noodle.school_schedule.back.services.impl

import com.noodle.school_schedule.back.repositories.LessonRepository
import com.noodle.school_schedule.back.domain.entities.LessonEntity
import com.noodle.school_schedule.back.domain.mappings.toEntity
import com.noodle.school_schedule.back.domain.mappings.toResponse
import com.noodle.school_schedule.back.services.LessonService
import com.noodle.school_schedule.openapi.models.LessonRequest
import com.noodle.school_schedule.openapi.models.LessonResponse
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