INSERT INTO `sys_table` ( `name`, label, module_id, order_id, remark )
VALUES
	( 'test', '测试', 1, 0, "这是一张测试表" );
	
	
	--
INSERT INTO `sys_column` ( `name`, label, visible, deleted, sys_table_id )
VALUES
	('${name}','${label}',1,0,1),
	('${name}','${label}',1,0,1);
	
	
drop  table test1