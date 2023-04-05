-- #1
SELECT human."ÎÒ×ÅÑÒÂÎ", ved."ÄÀÒÀ"
FROM "Í_ËŞÄÈ" as human
         INNER JOIN "Í_ÂÅÄÎÌÎÑÒÈ" as ved ON human."ÈÄ" = ved."×ËÂÊ_ÈÄ"
WHERE human."ÔÀÌÈËÈß" < 'Àôàíàñüåâ'
  AND ved."ÈÄ" > 1490007 AND ved."ÈÄ" < 1457443;

-- #2
SELECT human."ÈÄ", learn."ÍÇÊ", stud."ÈÄ"
FROM "Í_ËŞÄÈ" as human
         INNER JOIN "Í_ÎÁÓ×ÅÍÈß" learn on human."ÈÄ" = learn."×ËÂÊ_ÈÄ"
         INNER JOIN "Í_Ó×ÅÍÈÊÈ" stud on human."ÈÄ" = stud."×ËÂÊ_ÈÄ"
WHERE human."ÈÌß" = 'ßğîñëàâ'
  AND learn."×ËÂÊ_ÈÄ" = 113409;

-- #3
SELECT count(human."ÈÍÍ" IS NULL) > 0 as has_person_without_tax_id
FROM "Í_ËŞÄÈ" as human
         JOIN "Í_Ó×ÅÍÈÊÈ" stud ON human."ÈÄ" = stud."×ËÂÊ_ÈÄ"
WHERE stud."ÃĞÓÏÏÀ" = '3102';

-- #4
SELECT human."ÎÒ×ÅÑÒÂÎ" as middlename, count(human."ÈÄ") as count
FROM "Í_ËŞÄÈ" as human
         JOIN "Í_Ó×ÅÍÈÊÈ" stud ON human."ÈÄ" = stud."×ËÂÊ_ÈÄ"
         JOIN "Í_ÏËÀÍÛ" plan on stud."ÏËÀÍ_ÈÄ" = plan."ÈÄ"
         JOIN "Í_ÎÒÄÅËÛ" otd on plan."ÎÒÄ_ÈÄ" = otd."ÈÄ"
WHERE otd."ÊÎĞÎÒÊÎÅ_ÈÌß" = 'ÊÒèÓ'
  AND human."ÎÒ×ÅÑÒÂÎ" <> '.'
GROUP BY human."ÎÒ×ÅÑÒÂÎ"
HAVING count(human."ÈÄ") > 10
ORDER BY count DESC;

-- #5
WITH min_years as
         (SELECT min(trunc(date_part('year', age(human."ÄÀÒÀ_ĞÎÆÄÅÍÈß")))) as min
          FROM "Í_ËŞÄÈ" as human
                   INNER JOIN "Í_Ó×ÅÍÈÊÈ" stud
                              ON human."ÈÄ" = stud."×ËÂÊ_ÈÄ"
          WHERE stud."ÃĞÓÏÏÀ" = '1100'
          LIMIT 1)
SELECT stud."ÃĞÓÏÏÀ"                                             as study_group,
       trunc(avg(date_part('year', age(human."ÄÀÒÀ_ĞÎÆÄÅÍÈß")))) as average_age_years
FROM "Í_ËŞÄÈ" as human
         JOIN "Í_Ó×ÅÍÈÊÈ" stud ON human."ÈÄ" = stud."×ËÂÊ_ÈÄ"
GROUP BY study_group
HAVING
    trunc(avg(
        date_part('year', age(human."ÄÀÒÀ_ĞÎÆÄÅÍÈß"))
    )) = (SELECT min FROM min_years);

-- #6
SELECT stud."ÃĞÓÏÏÀ",
       human."ÈÄ",
       human."ÔÀÌÈËÈß",
       human."ÈÌß",
       human."ÎÒ×ÅÑÒÂÎ",
       stud."Â_ÑÂßÇÈ_Ñ" as protocol
FROM "Í_ËŞÄÈ" human
         JOIN "Í_Ó×ÅÍÈÊÈ" stud on human."ÈÄ" = stud."×ËÂÊ_ÈÄ"
WHERE stud."ÏËÀÍ_ÈÄ" IN
      (SELECT "ÈÄ"
       FROM "Í_ÏËÀÍÛ"
       WHERE "ÔÎ_ÈÄ" = (SELECT "ÈÄ"
                        FROM "Í_ÔÎĞÌÛ_ÎÁÓ×ÅÍÈß"
                        WHERE "ÍÀÈÌÅÍÎÂÀÍÈÅ" = 'Î÷íàÿ'))
  AND stud."ÏĞÈÇÍÀÊ" = 'îò÷èñë'
  AND stud."ÊÎÍÅÖ" < '2012-09-01';

-- #7
SELECT "ÈÄ", "ÔÀÌÈËÈß", "ÄÀÒÀ_ĞÎÆÄÅÍÈß"
FROM "Í_ËŞÄÈ"
WHERE "ÔÀÌÈËÈß" IN (SELECT "ÔÀÌÈËÈß"
                    FROM "Í_ËŞÄÈ"
                    GROUP BY "ÔÀÌÈËÈß"
                    HAVING count(DISTINCT "ÄÀÒÀ_ĞÎÆÄÅÍÈß") > 1)
ORDER BY "ÔÀÌÈËÈß"
