package com.noodle.schoolschedule.back.controllers

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.decorator.optional.NeverOptionalStrategy
import com.appmattus.kotlinfixture.decorator.optional.optionalStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.noodle.schoolschedule.back.services.LessonService
import com.noodle.schoolschedule.back.url.UrlFactory
import com.noodle.schoolschedule.openapi.models.LessonRequest
import com.noodle.schoolschedule.openapi.models.LessonResponse
import io.mockk.every
import org.junit.jupiter.api.Nested
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*
import kotlin.test.BeforeTest
import kotlin.test.Test

@WebMvcTest(LessonController::class)
class LessonControllerTests(@Autowired private val mockMvc: MockMvc) {
	@MockkBean
	private lateinit var service: LessonService
	private val objectMapper = jacksonObjectMapper()

	private val any = kotlinFixture {
		nullabilityStrategy(NeverNullStrategy)
		optionalStrategy(NeverOptionalStrategy)
		filter<Int> { filter { it >= 0 } }
	}

	@Test
	fun `WHEN POST empty object THEN controller response status should be '400 Bad Request'`() {
		val result = `POST empty object`()

		result.andExpect {
			status { isBadRequest() }
		}
	}

	@Nested
	inner class `GIVEN service create() returns 'response'`() {
		private val response = any<LessonResponse>()

		@BeforeTest
		fun `Make service create() return 'response'`() {
			every { service.create(any()) } returns response
		}

		@Test
		fun `WHEN POST lesson THEN controller response status should be '201 Created'`() {
			val result = `POST lesson`()

			result.andExpect {
				status { isCreated() }
			}
		}

		@Test
		fun `WHEN POST lesson THEN controller response content should contain 'response'`() {
			val result = `POST lesson`()

			result.andExpect {
				content { json(objectMapper.writeValueAsString(response)) }
			}
		}
	}

	@Nested
	inner class `GIVEN service readAll() returns 'empty list'`() {
		@BeforeTest
		fun `Make service readAll() return 'empty list'`() {
			every { service.readAll() } returns listOf<LessonResponse>()
		}

		@Test
		fun `WHEN GET lessons THEN controller response status should be '200 OK'`() {
			val result = `GET lessons`()

			result.andExpect {
				status { isOk() }
			}
		}

		@Test
		fun `WHEN GET lessons THEN controller response content should be 'empty list'`() {
			val result = `GET lessons`()

			result.andExpect {
				content { json(objectMapper.writeValueAsString(listOf<LessonResponse>())) }
			}
		}
	}

	@Nested
	inner class `GIVEN service readAll() returns 'response list'`() {
		private val responseList = any<List<LessonResponse>>()

		@BeforeTest
		fun `Make service readAll() return 'response list'`() {
			every { service.readAll() } returns responseList
		}

		@Test
		fun `WHEN GET lessons THEN controller response status should be '200 OK'`() {
			val result = `GET lessons`()

			result.andExpect {
				status { isOk() }
			}
		}

		@Test
		fun `WHEN GET lessons THEN controller response content should contain 'response list'`() {
			val result = `GET lessons`()

			result.andExpect {
				content { json(objectMapper.writeValueAsString(responseList)) }
			}
		}
	}

	@Nested
	inner class `GIVEN service read() returns 'null'`() {
		@BeforeTest
		fun `Make service read() return a response`() {
			every { service.read(any()) } returns null
		}

		@Test
		fun `WHEN GET lesson with id THEN controller response status should be '404 Not Found'`() {
			val result = `GET lesson with id`()

			result.andExpect {
				status { isNotFound() }
			}
		}
	}

	@Nested
	inner class `GIVEN service read() returns 'response'`() {
		private val response = any<LessonResponse>()

		@BeforeTest
		fun `Make service read() return a response`() {
			every { service.read(any()) } returns response
		}

		@Test
		fun `WHEN GET lesson with id THEN controller response status should be '200 OK'`() {
			val result = `GET lesson with id`()

			result.andExpect {
				status { isOk() }
			}
		}

		@Test
		fun `WHEN GET lesson with id THEN controller response content should contain 'response'`() {
			val result = `GET lesson with id`()

			result.andExpect {
				content { json(objectMapper.writeValueAsString(response)) }
			}
		}
	}

	@Test
	fun `WHEN PUT empty object THEN controller response status should be '400 Bad Request'`() {
		val result = `PUT empty object`()

		result.andExpect {
			status { isBadRequest() }
		}
	}

	@Nested
	inner class `GIVEN service update() throws 'IllegalStateException'`() {
		@BeforeTest
		fun `Make service update() return a response`() {
			every { service.update(any(), any()) } throws IllegalStateException()
		}

		@Test
		fun `WHEN PUT lesson THEN controller response status should be '400 Not found'`() {
			val result = `PUT lesson`()

			result.andExpect {
				status { isNotFound() }
			}
		}
	}

	@Nested
	inner class `GIVEN service update() returns 'response'`() {
		private val response = any<LessonResponse>()

		@BeforeTest
		fun `Make service update() return a response`() {
			every { service.update(any(), any()) } returns response
		}

		@Test
		fun `WHEN PUT lesson THEN controller response status should be '200 OK'`() {
			val result = `PUT lesson`()

			result.andExpect {
				status { isOk() }
			}
		}

		@Test
		fun `WHEN PUT lesson with id THEN controller response content should contain 'response'`() {
			val result = `PUT lesson`()

			result.andExpect {
				content { json(objectMapper.writeValueAsString(response)) }
			}
		}
	}

	@Nested
	inner class `GIVEN service delete() returns nothing`() {
		@BeforeTest
		fun `Make service delete() return nothing`() {
			every { service.delete(any()) } answers {}
		}

		@Test
		fun `WHEN DELETE lesson THEN controller response status should be '204 No Content'`() {
			val result = `DELETE lesson`()

			result.andExpect {
				status { isNoContent() }
			}
		}
	}

	private fun `POST empty object`() =
		mockMvc.post(UrlFactory.lessonUri()) {
			contentType = MediaType.APPLICATION_JSON
			accept = MediaType.APPLICATION_JSON
			content = objectMapper.writeValueAsString {}
		}

	private fun `POST lesson`() =
		mockMvc.post(UrlFactory.lessonUri()) {
			contentType = MediaType.APPLICATION_JSON
			accept = MediaType.APPLICATION_JSON
			content = objectMapper.writeValueAsString(any<LessonRequest>())
		}

	private fun `GET lessons`() =
		mockMvc.get(UrlFactory.lessonUri()) {
			accept = MediaType.APPLICATION_JSON
		}

	private fun `GET lesson with id`() =
		mockMvc.get(UrlFactory.lessonUri(any<Int>())) {
			accept = MediaType.APPLICATION_JSON
		}

	private fun `PUT empty object`() =
		mockMvc.put(UrlFactory.lessonUri(any<Int>())) {
			contentType = MediaType.APPLICATION_JSON
			accept = MediaType.APPLICATION_JSON
			content = objectMapper.writeValueAsString {}
		}

	private fun `PUT lesson`() =
		mockMvc.put(UrlFactory.lessonUri(any<Int>())) {
			contentType = MediaType.APPLICATION_JSON
			accept = MediaType.APPLICATION_JSON
			content = objectMapper.writeValueAsString(any<LessonRequest>())
		}

	private fun `DELETE lesson`() =
		mockMvc.delete(UrlFactory.lessonUri(any<Int>()))
}