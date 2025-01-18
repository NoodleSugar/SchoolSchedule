package com.noodle.schoolschedule.back.repositories;

import com.noodle.schoolschedule.back.domain.entities.LessonEntity
import com.noodle.schoolschedule.back.util.AnyValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull
import kotlin.test.BeforeTest
import kotlin.test.Test

@DataJpaTest
class LessonReporitoryTests @Autowired constructor(
	private val repository: LessonRepository,
	private val entityManager: TestEntityManager,
) {
	@Test
	fun `WHEN save() new entity with null id THEN a non-null id is given to this entity`() {
		val new = AnyValue.of<LessonEntity>().copy(id = null)

		repository.save(new)

		assertThat(new.id).isNotNull()
	}

	@Test
	fun `WHEN save() new entity with null id THEN repository entity is created`() {
		val new = AnyValue.of<LessonEntity>().copy(id = null)

		repository.save(new)

		val retrieved = entityManager.find(LessonEntity::class.java, new.id)
		assertThat(new).isEqualTo(retrieved)
	}

	@Test
	fun `WHEN findByIdOrNull() with non-existing id THEN null is returned`() {
		val entity = repository.findByIdOrNull(AnyValue.of<Int>())

		assertThat(entity).isNull()
	}

	@Test
	fun `WHEN existsById() with non-existing id THEN false is returned`() {
		val result = repository.existsById(AnyValue.of<Int>())

		assertThat(result).isFalse()
	}

	@Test
	fun `WHEN deleteById() with non-existing id THEN does not throw`() {
		assertDoesNotThrow { repository.deleteById(AnyValue.of<Int>()) }
	}

	@Nested
	inner class `GIVEN repository contains an entity` {
		private val entity = AnyValue.of<LessonEntity>().copy(id = null)
		private var id: Int = 0

		@BeforeTest
		fun `Make repository contain an entity`() {
			repository.save(entity)
			id = entity.id!!
		}

		@Test
		fun `WHEN save() entity with existing id THEN repository entity is updated`() {
			val new = AnyValue.of<LessonEntity>().copy(id = id)

			repository.save(new)

			val retrieved = entityManager.find(LessonEntity::class.java, new.id)
			assertThat(new).isEqualTo(retrieved)
		}

		@Test
		fun `WHEN findByIdOrNull() with existing id THEN non-null entity is returned`() {
			val entity = repository.findByIdOrNull(id)

			assertThat(entity).isNotNull()
		}

		@Test
		fun `WHEN findByIdOrNull() with existing id THEN repository entity is returned`() {
			val entity = repository.findByIdOrNull(id)

			val retrieved = entityManager.find(LessonEntity::class.java, id)
			assertThat(entity).isEqualTo(retrieved)
		}

		@Test
		fun `WHEN existsById() with existing id THEN true is returned`() {
			val result = repository.existsById(id)

			assertThat(result).isTrue()
		}

		@Test
		fun `WHEN deleteById() with existing id THEN repository entity is removed`() {
			repository.deleteById(id)

			val retrieved = entityManager.find(LessonEntity::class.java, id)
			assertThat(retrieved).isNull()
		}
	}

	@Nested
	inner class `GIVEN repository contains list of entities` {
		val entityList = AnyValue.of<List<LessonEntity>>().map { it.copy(id = null) }

		@BeforeTest
		fun `Make repository contain list of entities`() {
			repository.saveAll(entityList)
		}

		@Test
		fun `WHEN findAll() THEN return list of every entity`() {
			val entities = repository.findAll()

			assertThat(entities).isEqualTo(entityList)
		}
	}
}
