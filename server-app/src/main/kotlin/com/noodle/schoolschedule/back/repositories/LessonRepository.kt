package com.noodle.schoolschedule.back.repositories

import com.noodle.schoolschedule.back.domain.entities.LessonEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LessonRepository : JpaRepository<LessonEntity, Int?>