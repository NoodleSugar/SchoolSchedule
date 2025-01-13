package com.noodle.schoolschedule.back.services

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.decorator.optional.NeverOptionalStrategy
import com.appmattus.kotlinfixture.decorator.optional.optionalStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.noodle.schoolschedule.back.assertions.assertMock
import com.noodle.schoolschedule.back.domain.entities.LessonEntity
import com.noodle.schoolschedule.back.domain.mappings.toResponse
import com.noodle.schoolschedule.back.repositories.LessonRepository
import com.noodle.schoolschedule.back.services.impl.LessonServiceImpl
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
	private val any = kotlinFixture {
		nullabilityStrategy(NeverNullStrategy)
		optionalStrategy(NeverOptionalStrategy)
		filter<Int> { filter { it >= 0 } }
	}

	@Test
	fun `When repository save() returns entity, create() should return response with entity content`() {
		val entity = any<LessonEntity>()
		`Make repository save() return`(entity)

		val response = service.create(any<LessonRequest>())

		assertThat(response).isEqualTo(entity.toResponse())
	}

	@Test
	fun `When repository findAll() returns empty list, readAll() should return empty list`() {
		`Make repository findAll() return`(listOf())

		val responseList = service.readAll()

		assertThat(responseList).isEmpty()
	}

	@Test
	fun `When repository findAll() returns entity list, readAll() should return list containing a response per entity`() {
		val entityList = any<List<LessonEntity>>()
		`Make repository findAll() return`(entityList)

		val response = service.readAll()

		assertThat(response).isEqualTo(entityList.map(LessonEntity::toResponse))
	}

	@Test
	fun `When repository findByIdOrNull() returns null, read() should return null`() {
		`Make repository findByIdOrNull() return`(null)

		val response = service.read(any<Int>())

		assertThat(response).isNull()
	}

	@Test
	fun `When repository findByIdOrNull() returns entity, read() should return response with entity content`() {
		val entity = any<LessonEntity>()
		`Make repository findByIdOrNull() return`(entity)

		val response = service.read(any<Int>())

		assertThat(response).isEqualTo(entity.toResponse())
	}

	@Test
	fun `When repository existsById() returns false, update() should throw IllegalStateException`() {
		`Make repository existsById() return`(false)

		assertThrows<IllegalStateException> { service.update(any<Int>(), any<LessonRequest>()) }
	}

	@Test
	fun `When repository existsById() returns true and save() returns entity, update() should return response with entity content`() {
		val entity = any<LessonEntity>()
		`Make repository existsById() return`(true)
		`Make repository save() return`(entity)

		val response = service.update(any<Int>(), any<LessonRequest>())

		assertThat(response).isEqualTo(entity.toResponse())
	}

	@Test
	fun `delete() should call repository deleteById() with given id`() {
		`Make repository delete() return nothing`()
		val id = any<Int>()

		service.delete(id)

		com.noodle.schoolschedule.back.assertions.assertMock(mockRepo).method("deleteById").wasCalledWith(id)
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