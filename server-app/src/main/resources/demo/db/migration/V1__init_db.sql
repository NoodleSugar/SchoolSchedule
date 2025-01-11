DROP TYPE IF EXISTS DAY_OW;
CREATE TYPE DAY_OW AS ENUM ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday');

DROP SEQUENCE IF EXISTS "lesson_id_seq";
DROP TABLE    IF EXISTS "lesson";
CREATE SEQUENCE "lesson_id_seq" START WITH 1;
CREATE TABLE "lesson"
(
    "id"          INT           NOT NULL,
    "name"        VARCHAR(256)  NOT NULL,
    "description" VARCHAR(1024) NOT NULL,
    CONSTRAINT "lesson_pkey" PRIMARY KEY ("id")
);

DROP SEQUENCE IF EXISTS "teacher_id_seq";
DROP TABLE    IF EXISTS "teacher";
CREATE SEQUENCE "teacher_id_seq" START WITH 1;
CREATE TABLE "teacher"
(
    "id"        INT          NOT NULL,
    "name"      VARCHAR(256) NOT NULL,
    "last_name" VARCHAR(256) NOT NULL,
    CONSTRAINT "teacher-pkey" PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "teacher_lesson";
CREATE TABLE "teacher_lesson"
(
    "teacher_id" INT NOT NULL,
    "lesson_id"  INT NOT NULL,
    CONSTRAINT "teacher_lesson-pkey" PRIMARY KEY ("teacher_id", "lesson_id")
);

DROP SEQUENCE IF EXISTS "student_id_seq";
DROP TABLE    IF EXISTS "student";
CREATE SEQUENCE "student_id_seq" START WITH 1;
CREATE TABLE "student"
(
    "id"        INT          NOT NULL,
    "name"      VARCHAR(256) NOT NULL,
    "last_name" VARCHAR(256) NOT NULL,
    CONSTRAINT "student-pkey" PRIMARY KEY ("id")
);

DROP TABLE    IF EXISTS "sgroup";
CREATE TABLE "sgroup"
(
    "id"   VARCHAR(64)  NOT NULL,
    "name" VARCHAR(256) NOT NULL,
    CONSTRAINT "sgroup-pkey" PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "student_group";
CREATE TABLE "student_group"
(
    "student_id" INT         NOT NULL,
    "group_id"   VARCHAR(64) NOT NULL,
    CONSTRAINT "student_lesson-pkey"         PRIMARY KEY ("student_id", "group_id"),
    CONSTRAINT "student_lesson-fkey_student" FOREIGN KEY ("student_id")  REFERENCES "student" ("id"),
    CONSTRAINT "student_lesson-fkey_group"   FOREIGN KEY ("group_id")    REFERENCES "sgroup" ("id")
);

DROP SEQUENCE IF EXISTS "schedule_rule_id_seq";
DROP TABLE    IF EXISTS "schedule_rule";
CREATE SEQUENCE "schedule_rule_id_seq" START WITH 1;
CREATE TABLE "schedule_rule"
(
    "id"         INT         NOT NULL,
    "start_date" DATE        NOT NULL,
    "end_date"   DATE        NOT NULL,
    "day"        INT         NOT NULL,
    "start_time" TIME        NOT NULL,
    "end_time"   TIME        NOT NULL,
    "lesson_id"  INT         NOT NULL,
    "teacher_id" INT         NOT NULL,
    "group_id"   VARCHAR(64) NOT NULL,
    "room"       INT         NOT NULL,
    CONSTRAINT "schedule_rule_pkey"         PRIMARY KEY ("id"),
    CONSTRAINT "schedule_rule_fkey_lesson"  FOREIGN KEY ("lesson_id")  REFERENCES "lesson" ("id"),
    CONSTRAINT "schedule_rule_fkey_teacher" FOREIGN KEY ("teacher_id") REFERENCES "teacher" ("id"),
    CONSTRAINT "schedule_rule_fkey_group"   FOREIGN KEY ("group_id")   REFERENCES "sgroup" ("id")
);