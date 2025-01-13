package com.noodle.schoolschedule.back.assertions

import io.mockk.verify

fun <T : Any> assertMock(mock: T) = com.noodle.schoolschedule.back.assertions.MockAssert(mock)

class MockAssert<T : Any>(private val mock: T) {
	fun method(method: String) = MockMethod(method)

	inner class MockMethod constructor(val method: String) {
		fun wasCalledWith(vararg args: Any?) = verify { mock invoke method withArguments args.toList() }
	}
}