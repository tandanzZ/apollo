insert into ``.App select * from `apolloconfigfat`.App where IsDeleted = 0;
insert into ``.AppNamespace  select * from `apolloconfigfat`.AppNamespace  where IsDeleted = 0;
insert into ``.Cluster  select * from `apolloconfigfat`.Cluster  where IsDeleted = 0;
insert into ``.Namespace  select * from `apolloconfigfat`.Namespace  where IsDeleted = 0;
