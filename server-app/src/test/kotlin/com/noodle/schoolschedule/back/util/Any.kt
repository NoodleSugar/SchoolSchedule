package com.noodle.schoolschedule.back.util

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.decorator.optional.NeverOptionalStrategy
import com.appmattus.kotlinfixture.decorator.optional.optionalStrategy
import com.appmattus.kotlinfixture.kotlinFixture

object AnyValue {
	val of = kotlinFixture {
		nullabilityStrategy(NeverNullStrategy)
		optionalStrategy(NeverOptionalStrategy)
		filter<Int> { filter { it > 0 } }
	}
}