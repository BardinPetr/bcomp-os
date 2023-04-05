SELECT count(human."ИНН" IS NULL) > 0 as has_person_without_tax_id
FROM "Н_ЛЮДИ" as human
WHERE human."ИД" IN (SELECT "ЧЛВК_ИД" FROM "Н_УЧЕНИКИ" WHERE "ГРУППА" = '3102');
