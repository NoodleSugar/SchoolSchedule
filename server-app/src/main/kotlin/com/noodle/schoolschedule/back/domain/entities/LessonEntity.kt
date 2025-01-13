package com.noodle.schoolschedule.back.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "lesson")
data class LessonEntity(
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_id_seq")
	@SequenceGenerator(name = "lesson_id_seq", allocationSize = 1)
	val id: Int? = null,

	val name: String,
	val description: String,
)