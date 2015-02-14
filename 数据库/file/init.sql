spool ./log.log;
drop user king cascade;
create user king identified by king;
grant connect,resource to king;
conn king/king;
@@create.sql;
@@insert.sql;
spool off;