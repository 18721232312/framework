create (ALBK_SPACE:SPACE{ code:'ALBK_SPACE', type:'SPACE' , title:'albk运维空间',accessKey:'ALBK',projectCode:'1',description:'BK博客的运维空间'} )
create (DEV_ENV_01:ENV{ code:'DEV_ENV_01', type:'DEV' , title:'开发环境'})
create (DEV_ENV_PATH:DEPLOY_PATH{ code:'DEV_ENV_PATH', type:'DEPLOY_PATH' , title:'部署路径'})
create (DEV001:MACHINE{ code:'DEV001', type:'MACHINE' , title:'DEV001',ip:'192.168.1.1',status:'running',system:'centos7'} )
create (DEV002:MACHINE{ code:'DEV002', type:'MACHINE' , title:'DEV002',ip:'192.168.1.2',status:'running',system:'centos7'} )
create (DEV003:MACHINE{ code:'DEV003', type:'MACHINE' , title:'DEV003',ip:'192.168.1.3',status:'running',system:'centos7'} )
create (TEST_ENV_01:ENV{ code:'TEST_ENV_01', type:'TEST' , title:'测试环境'})
create (TEST001:MACHINE{ code:'TEST001', type:'MACHINE' , title:'TEST001',ip:'192.168.1.10',status:'shutdown',system:'centos7'} )
create (TEST002:MACHINE{ code:'TEST002', type:'MACHINE' , title:'TEST002',ip:'192.168.1.11',status:'running',system:'centos7'} )
create (TEST003:MACHINE{ code:'TEST003', type:'MACHINE' , title:'TEST003',ip:'192.168.1.13',status:'running',system:'centos7'} )
create (PRO_ENV_01:ENV{ code:'PRO_ENV_01', type:'PRO' , title:'生产环境'})
create (PRO001:MACHINE{ code:'PRO001', type:'MACHINE' , title:'PRO001',ip:'192.168.1.20',status:'shutdown',system:'centos7'} )
create (PRO002:MACHINE{ code:'PRO002', type:'MACHINE' , title:'PRO002',ip:'192.168.1.21',status:'running',system:'centos7'} )
create (PRO003:MACHINE{ code:'PRO003', type:'MACHINE' , title:'PRO003',ip:'192.168.1.23',status:'running',system:'centos7'} )
create (DEV001_NGINX:APP{ code:'DEV001_NGINX', type:'NGINX' , title:'NGINX',version:'2.3',domain:'www.albk.tech',appType:'BASE'} )
create (DEV001_MYSQL:APP{ code:'DEV001_MYSQL', type:'MYSQL' , title:'MYSQL',version:'5.7', role:'master',appType:'BASE'} )
create (DEV001_APP_UC:APP{ code:'DEV001_APP_UC', type:'UC' , title:'用户中心（UC)',version:'1.2',lastupdatetime:'2019-06-17 10:20:00',owner:'zhangsan',appType:'BIZ'} )
create (DEV002_APP_ORDER:APP{ code:'DEV002_APP_ORDER', type:'ORDER' , title:'订单服务（ORDER）',version:'1.2',lastupdatetime:'2019-06-17 10:20:00',owner:'zhangsan',appType:'BIZ'} )
create (DEV002_APP_REDIS:APP{ code:'DEV002_APP_REDIS', type:'REDIS' , title:'REDIS',version:'3.5',appType:'BASE'} )
create (DEV003_APP_PC_WEB:APP{ code:'DEV003_APP_PC_WEB', type:'PC_WEB' , title:'网站 静态资源',version:'1.2',lastupdatetime:'2019-06-17 10:20:00',owner:'zhangsan',appType:'BIZ'} )
create (DEV003_APP_PUBLISH:APP{ code:'DEV003_APP_PUBLISH', type:'PUBLISH' , title:'发布服务',version:'1.2',lastupdatetime:'2019-06-17 10:20:00',owner:'zhangsan',appType:'BIZ'} )
create (DEV003_APP_SHOPPINGCART:APP{ code:'DEV003_APP_SHOPPINGCARD', type:'SHOPPINGCART' , title:'购物车应用',version:'1.2',lastupdatetime:'2019-06-17 10:20:00',owner:'zhangsan',appType:'BIZ'} )
create (DEV003_APP_PROMOTION:APP{ code:'DEV003_APP_PROMOTION', type:'PROMOTION' , title:'促销应用',version:'1.2',lastupdatetime:'2019-06-17 10:20:00',owner:'zhangsan',appType:'BIZ'} )
create
 ( ALBK_SPACE)- [:include] -> (DEV_ENV_01),
 ( ALBK_SPACE)- [:include] -> (TEST_ENV_01),
 ( ALBK_SPACE)- [:include] -> (PRO_ENV_01),
 ( DEV_ENV_01)- [:path] -> (DEV_ENV_PATH),
 ( DEV_ENV_01)- [:has] -> (DEV001),
 ( DEV_ENV_01)- [:has] -> (DEV002),
 ( DEV_ENV_01)- [:has] -> (DEV003),
 ( DEV001)- [:setup] -> (DEV001_NGINX),
 ( DEV001)- [:setup] -> (DEV001_MYSQL),
 ( DEV001)- [:setup] -> (DEV001_APP_UC),
 ( DEV002)- [:setup] -> (DEV002_APP_ORDER),
 ( DEV002)- [:setup] -> (DEV002_APP_REDIS),
 ( DEV003)- [:setup] -> (DEV003_APP_PC_WEB),
 ( DEV003)- [:setup] -> (DEV003_APP_PUBLISH),
 ( DEV003)- [:setup] -> (DEV003_APP_SHOPPINGCART),
 ( DEV003)- [:setup] -> (DEV003_APP_PROMOTION),
 ( TEST_ENV_01)- [:has] -> (TEST001),
 ( TEST_ENV_01)- [:has] -> (TEST002),
 ( TEST_ENV_01)- [:has] -> (TEST003),
 ( PRO_ENV_01)- [:has] -> (PRO001),
 ( PRO_ENV_01)- [:has] -> (PRO002),
 ( PRO_ENV_01)- [:has] -> (PRO003),
(DEV_ENV_01) -[:access {type:'WAL'}] ->(TEST_ENV_01),
( TEST_ENV_01) -[:access {type:'WAL'}] ->(PRO_ENV_01)