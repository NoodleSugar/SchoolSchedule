package com.noodle.schoolschedule.back.services

import com.noodle.schoolschedule.back.assertions.assertMock
import com.noodle.schoolschedule.back.domain.entities.LessonEntity
import com.noodle.schoolschedule.back.domain.mappings.toResponse
import com.noodle.schoolschedule.back.repositories.LessonRepository
import com.noodle.schoolschedule.back.services.impl.LessonServiceImpl
import com.noodle.schoolschedule.back.util.AnyValue
import com.noodle.schoolschedule.openapi.models.LessonRequest
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.springframework.data.repository.findByIdOrNull
import kotlin.test.Test

class LessonServiceTests {
	private val mockRepo = mockk<LessonRepository>()
	private val service = LessonServiceImpl(mockRepo)

	@Test
	fun `If repository save() returns entity, create() should return response with entity content`() {
		val entity = AnyValue.of<LessonEntity>()
		`Make repository save() return`(entity)

		val response = service.create(AnyValue.of<LessonRequest>())

		assertThat(response).isEqualTo(entity.toResponse())
	}

	@Test
	fun `If repository findAll() returns empty list, readAll() should return empty list`() {
		`Make repository findAll() return`(listOf())

		val responseList = service.readAll()

		assertThat(responseList).isEmpty()
	}

	@Test
	fun `If repository findAll() returns entity list, readAll() should return list containing a response per entity`() {
		val entityList = AnyValue.of<List<LessonEntity>>()
		`Make repository findAll() return`(entityList)

		val response = service.readAll()

		assertThat(response).isEqualTo(entityList.map(LessonEntity::toResponse))
	}

	@Test
	fun `If repository findByIdOrNull() returns null, read() should return null`() {
		`Make repository findByIdOrNull() return`(null)

		val response = service.read(AnyValue.of<Int>())

		assertThat(response).isNull()
	}

	@Test
	fun `If repository findByIdOrNull() returns entity, read() should return response with entity content`() {
		val entity = AnyValue.of<LessonEntity>()
		`Make repository findByIdOrNull() return`(entity)

		val response = service.read(AnyValue.of<Int>())

		assertThat(response).isEqualTo(entity.toResponse())
	}

	@Test
	fun `If repository existsById() returns false, update() should throw IllegalStateException`() {
		`Make repository existsById() return`(false)

		assertThrows<IllegalStateException> { service.update(AnyValue.of<Int>(), AnyValue.of<LessonRequest>()) }
	}

	@Test
	fun `If repository existsById() returns true and save() returns entity, update() should return response with entity content`() {
		val entity = AnyValue.of<LessonEntity>()
		`Make repository existsById() return`(true)
		`Make repository save() return`(entity)

		val response = service.update(AnyValue.of<Int>(), AnyValue.of<LessonRequest>())

		assertThat(response).isEqualTo(entity.toResponse())
	}

	@Test
	fun `delete() should call repository deleteById() with given id`() {
		`Make repository delete() return nothing`()
		val id = AnyValue.of<Int>()

		service.delete(id)

		assertMock(mockRepo).method("deleteById").wasCalledWith(id)
	}

	private fun `Make repository save() return`(entity: LessonEntity) =
		every { mockRepo.save(any()) } returns entity

	private fun `Make repository findAll() return`(entityList: List<LessonEntity>) =
		every { mockRepo.findAll() } returns entityList

	private fun `Make repository findByIdOrNull() return`(entity: LessonEntity?) =
		every { mockRepo.findByIdOrNull(any()) } returns entity

	private fun `Make repository existsById() return`(exists: Boolean) =
		every { mockRepo.existsById(any()) } returns exists

	private fun `Make repository delete() return nothing`() =
		every { mockRepo.deleteById(any()) } answers {}
}