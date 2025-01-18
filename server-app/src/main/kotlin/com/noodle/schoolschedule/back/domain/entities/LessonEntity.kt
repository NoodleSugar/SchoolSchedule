package com.noodle.schoolschedule.back.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "`lesson`")
data class LessonEntity(
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "`lesson_id_seq`")
	@SequenceGenerator(name = "`lesson_id_seq`", allocationSize = 1)
	@Column(name = "`id`") val id: Int? = null,

	@Column(name = "`name`") val name: String,
	@Column(name = "`description`") val description: String,
)