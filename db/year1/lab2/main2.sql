-- #1
SELECT human."��������", ved."����"
FROM "�_����" as human
         INNER JOIN "�_���������" as ved ON human."��" = ved."����_��"
WHERE human."�������" < '���������'
  AND ved."��" > 1490007 AND ved."��" < 1457443;

-- #2
SELECT human."��", learn."���", stud."��"
FROM "�_����" as human
         INNER JOIN "�_��������" learn on human."��" = learn."����_��"
         INNER JOIN "�_�������" stud on human."��" = stud."����_��"
WHERE human."���" = '�������'
  AND learn."����_��" = 113409;

-- #3
SELECT count(human."���" IS NULL) > 0 as has_person_without_tax_id
FROM "�_����" as human
         JOIN "�_�������" stud ON human."��" = stud."����_��"
WHERE stud."������" = '3102';

-- #4
SELECT human."��������" as middlename, count(human."��") as count
FROM "�_����" as human
         JOIN "�_�������" stud ON human."��" = stud."����_��"
         JOIN "�_�����" plan on stud."����_��" = plan."��"
         JOIN "�_������" otd on plan."���_��" = otd."��"
WHERE otd."��������_���" = '����'
  AND human."��������" <> '.'
GROUP BY human."��������"
HAVING count(human."��") > 10
ORDER BY count DESC;

-- #5
WITH min_years as
         (SELECT min(trunc(date_part('year', age(human."����_��������")))) as min
          FROM "�_����" as human
                   INNER JOIN "�_�������" stud
                              ON human."��" = stud."����_��"
          WHERE stud."������" = '1100'
          LIMIT 1)
SELECT stud."������"                                             as study_group,
       trunc(avg(date_part('year', age(human."����_��������")))) as average_age_years
FROM "�_����" as human
         JOIN "�_�������" stud ON human."��" = stud."����_��"
GROUP BY study_group
HAVING
    trunc(avg(
        date_part('year', age(human."����_��������"))
    )) = (SELECT min FROM min_years);

-- #6
SELECT stud."������",
       human."��",
       human."�������",
       human."���",
       human."��������",
       stud."�_�����_�" as protocol
FROM "�_����" human
         JOIN "�_�������" stud on human."��" = stud."����_��"
WHERE stud."����_��" IN
      (SELECT "��"
       FROM "�_�����"
       WHERE "��_��" = (SELECT "��"
                        FROM "�_�����_��������"
                        WHERE "������������" = '�����'))
  AND stud."�������" = '������'
  AND stud."�����" < '2012-09-01';

-- #7
SELECT "��", "�������", "����_��������"
FROM "�_����"
WHERE "�������" IN (SELECT "�������"
                    FROM "�_����"
                    GROUP BY "�������"
                    HAVING count(DISTINCT "����_��������") > 1)
ORDER BY "�������"
