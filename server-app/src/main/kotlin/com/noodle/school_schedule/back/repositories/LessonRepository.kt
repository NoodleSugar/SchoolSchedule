package com.noodle.school_schedule.back.repositories

import com.noodle.school_schedule.back.domain.entities.LessonEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LessonRepository : JpaRepository<LessonEntity, Int?>