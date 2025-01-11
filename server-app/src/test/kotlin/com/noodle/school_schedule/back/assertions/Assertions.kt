package com.noodle.school_schedule.back.assertions

import io.mockk.verify

fun <T : Any> assertMock(mock: T) = MockAssert(mock)

class MockAssert<T : Any>(private val mock: T) {
	fun method(method: String) = MockMethod(method)

	inner class MockMethod constructor(val method: String) {
		fun wasCalledWith(vararg args: Any?) = verify { mock invoke method withArguments args.toList() }
	}
}