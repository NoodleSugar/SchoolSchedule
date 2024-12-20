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

/**
 * Fill database with demo data
 **/

INSERT INTO "lesson" VALUES
(nextval('lesson_id_seq'), 'Anglais',       'Niveau terminale'),
(nextval('lesson_id_seq'), 'Espagnol',      'Niveau terminale'),
(nextval('lesson_id_seq'), 'Allemand',      'Niveau terminale'),
(nextval('lesson_id_seq'), 'Français',      'Niveau terminale'),
(nextval('lesson_id_seq'), 'Philosophie',   'Niveau terminale : Personne ne sait'),
(nextval('lesson_id_seq'), 'Mathématiques', 'Niveau terminale :\n1. Suites et limites\n,2. Limites et continuité,\n3. Dérivabilité des fonctions,\n4. Fonction logarithme népérien\n5. Primitives et équations différentielles\n6. Convexité\n7. Statistiques à deux variables\n8. Lois de probabilités discrètes\n9. Lois de probabilités à densité'),
(nextval('lesson_id_seq'), 'Physique',      'Niveau terminale'),
(nextval('lesson_id_seq'), 'Physique TP',   'Niveau terminale'),
(nextval('lesson_id_seq'), 'SVT',           'Niveau terminale'),
(nextval('lesson_id_seq'), 'SVT TP',        'Niveau terminale'),
(nextval('lesson_id_seq'), 'Option : Science de l''ingénieur', 'Niveau terminale'),
(nextval('lesson_id_seq'), 'Option : Informatique',            'Niveau terminale');

INSERT INTO "teacher" VALUES
(nextval('teacher_id_seq'), 'Albus', 'DUMBLEDORE'),
(nextval('teacher_id_seq'), 'Severus', 'ROGUE'),
(nextval('teacher_id_seq'), 'Minerva', 'MCGONAGAL'),
(nextval('teacher_id_seq'), 'Remus', 'LUPIN'),
(nextval('teacher_id_seq'), 'Dolores', 'OMBRAGE'),
(nextval('teacher_id_seq'), 'Alastor', 'MAUGREY'),
(nextval('teacher_id_seq'), 'Renée', 'BIBINE'),
(nextval('teacher_id_seq'), 'Pomona', 'CHOURAVE'),
(nextval('teacher_id_seq'), 'Rubeus', 'HAGRID'),
(nextval('teacher_id_seq'), 'Gilderoy', 'LOCKHART');

INSERT INTO "teacher_lesson" VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(7, 8),
(8, 9),
(8, 10),
(9, 11),
(10, 12);

INSERT INTO "student" VALUES
(nextval('student_id_seq'), 'Noël', 'RODRIGUEZ'),
(nextval('student_id_seq'), 'Olivier', 'RODRIGUEZ'),
(nextval('student_id_seq'), 'Guillaume', 'YGOUF'),
(nextval('student_id_seq'), 'Lucie', 'DESIGAUD'),
(nextval('student_id_seq'), 'Lucie', 'ALLERY'),
(nextval('student_id_seq'), 'Léo', 'MULLETT'),
(nextval('student_id_seq'), 'Bartho', 'LADATA'),
(nextval('student_id_seq'), 'Kevin', 'MIGNON');

INSERT INTO "sgroup" VALUES
('TS1',     'Terminale scientifique 1'),
('TS2',     'Terminale scientifique 2'),
('TS1TP1',  'Terminale scientifique 1 Groupe TP 1'),
('TS1TP2',  'Terminale scientifique 1 Groupe TP 2'),
('TS2TP1',  'Terminale scientifique 2 Groupe TP 1'),
('TS2TP2',  'Terminale scientifique 2 Groupe TP 2'),
('TSOSI',   'Terminale scientifique : Option SI'),
('TSOI',    'Terminale scientifique : Option Informatique'),
('TLV1EN1', 'Terminale : Langue vivante 1: Anglais Groupe 1'),
('TLV1EN2', 'Terminale : Langue vivante 1: Anglais Groupe 2'),
('TLV2ES1', 'Terminale : Langue vivante 2: Espagnol Groupe 1'),
('TLV2ES2', 'Terminale : Langue vivante 2: Espagnol Groupe 2'),
('TLV2DE',  'Terminale : Langue vivante 2: Allemand');

INSERT INTO "student_group" VALUES
(1, 'TS1'),
(1, 'TS1TP1'),
(1, 'TSOSI'),
(1, 'TLV1EN1'),
(1, 'TLV2DE'),
(2, 'TS1'),
(2, 'TS1TP2'),
(2, 'TSOI'),
(2, 'TLV1EN1'),
(2, 'TLV2ES1');

INSERT INTO "schedule_rule" VALUES
(nextval('schedule_rule_id_seq'), '2024-09-02', '2024-12-20', 0, '08:00:00', '10:00:00', 6, 6, 'TS1', 11),
(nextval('schedule_rule_id_seq'), '2024-09-02', '2024-12-20', 0, '10:00:00', '12:00:00', 5, 5, 'TS1', 26);