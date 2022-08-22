-- 查询某个库下的所有表
select * from information_schema.tables where table_schema='hhnail' order by TABLE_NAME



-- 查询某个表的所有字段
SELECT
    *
FROM
    information_schema.COLUMNS
WHERE 1=1
AND TABLE_SCHEMA = 'hhnail'
AND TABLE_NAME = 'user'
ORDER BY COLUMN_NAME