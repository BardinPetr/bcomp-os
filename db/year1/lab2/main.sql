-- #1
SELECT human."ОТЧЕСТВО", ved."ДАТА"
FROM "Н_ЛЮДИ" as human
         INNER JOIN "Н_ВЕДОМОСТИ" as ved ON human."ИД" = ved."ЧЛВК_ИД"
WHERE human."ФАМИЛИЯ" < 'Афанасьев'
  AND ved."ИД" > 1490007 AND ved."ИД" < 1457443;

-- #2
SELECT human."ИД", learn."НЗК", stud."ИД"
FROM "Н_ЛЮДИ" as human
         INNER JOIN "Н_ОБУЧЕНИЯ" learn on human."ИД" = learn."ЧЛВК_ИД"
         INNER JOIN "Н_УЧЕНИКИ" stud on human."ИД" = stud."ЧЛВК_ИД"
WHERE human."ИМЯ" = 'Ярослав'
  AND learn."ЧЛВК_ИД" = 113409;

-- #3
SELECT count(human."ИНН" IS NULL) > 0 as has_person_without_tax_id
FROM "Н_ЛЮДИ" as human
         JOIN "Н_УЧЕНИКИ" stud ON human."ИД" = stud."ЧЛВК_ИД"
WHERE stud."ГРУППА" = '3102';

-- #4
SELECT human."ОТЧЕСТВО" as middlename, count(human."ИД") as count
FROM "Н_ЛЮДИ" as human
         JOIN "Н_УЧЕНИКИ" stud ON human."ИД" = stud."ЧЛВК_ИД"
         JOIN "Н_ПЛАНЫ" plan on stud."ПЛАН_ИД" = plan."ИД"
         JOIN "Н_ОТДЕЛЫ" otd on plan."ОТД_ИД" = otd."ИД"
WHERE otd."КОРОТКОЕ_ИМЯ" = 'КТиУ'
  AND human."ОТЧЕСТВО" <> '.'
GROUP BY human."ОТЧЕСТВО"
HAVING count(human."ИД") > 10
ORDER BY count DESC;

-- #5
WITH min_years as
         (SELECT min(trunc(date_part('year', age(human."ДАТА_РОЖДЕНИЯ")))) as min
          FROM "Н_ЛЮДИ" as human
                   INNER JOIN "Н_УЧЕНИКИ" stud
                              ON human."ИД" = stud."ЧЛВК_ИД"
          WHERE stud."ГРУППА" = '1100'
          LIMIT 1)
SELECT stud."ГРУППА"                                             as study_group,
       trunc(avg(date_part('year', age(human."ДАТА_РОЖДЕНИЯ")))) as average_age_years
FROM "Н_ЛЮДИ" as human
         JOIN "Н_УЧЕНИКИ" stud ON human."ИД" = stud."ЧЛВК_ИД"
GROUP BY study_group
HAVING
    trunc(avg(
        date_part('year', age(human."ДАТА_РОЖДЕНИЯ"))
    )) = (SELECT min FROM min_years);

-- #6
SELECT stud."ГРУППА",
       human."ИД",
       human."ФАМИЛИЯ",
       human."ИМЯ",
       human."ОТЧЕСТВО",
       stud."В_СВЯЗИ_С" as protocol
FROM "Н_ЛЮДИ" human
         JOIN "Н_УЧЕНИКИ" stud on human."ИД" = stud."ЧЛВК_ИД"
WHERE stud."ПЛАН_ИД" IN
      (SELECT "ИД"
       FROM "Н_ПЛАНЫ"
       WHERE "ФО_ИД" = (SELECT "ИД"
                        FROM "Н_ФОРМЫ_ОБУЧЕНИЯ"
                        WHERE "НАИМЕНОВАНИЕ" = 'Очная'))
  AND stud."ПРИЗНАК" = 'отчисл'
  AND stud."КОНЕЦ" < '2012-09-01';

-- #7
SELECT "ИД", "ФАМИЛИЯ", "ДАТА_РОЖДЕНИЯ"
FROM "Н_ЛЮДИ"
WHERE "ФАМИЛИЯ" IN (SELECT "ФАМИЛИЯ"
                    FROM "Н_ЛЮДИ"
                    GROUP BY "ФАМИЛИЯ"
                    HAVING count(DISTINCT "ДАТА_РОЖДЕНИЯ") > 1)
ORDER BY "ФАМИЛИЯ"
