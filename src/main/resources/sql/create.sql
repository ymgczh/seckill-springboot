-- 数据库初始化脚本
-- 创建数据库
create database seckill;

-- 使用数据库
use seckill;

-- 创建表
-- 秒杀库存表
create table seckill(
  `seckill_id` bigint not null AUTO_INCREMENT COMMENT '商品库存ID',
  `name` varchar(120) not null COMMENT '商品名称',
  `number` int not null COMMENT '库存数量',
  `start_time` datetime not null COMMENT '开始时间',
  `end_time` datetime not null COMMENT '结束时间',
  `create_time` timestamp not null default current_timestamp COMMENT '创建时间',
  primary key (seckill_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 comment='秒杀库存表';

-- 初始化数据
insert into seckill (name, number, start_time, end_time)
values
       ('1000秒杀iPhone6',100,'2019-9-11 00:00:00','2019-9-12 00:00:00'),
('2000秒杀iPhonexMax',200,'2019-9-11 00:00:00','2019-9-12 00:00:00'),
('3000秒杀魅族',300,'2019-9-11 00:00:00','2019-9-12 00:00:00'),
('200秒杀华为',10000,'2019-9-11 00:00:00','2019-9-12 00:00:00');

select * from seckill;

-- 秒杀成功明细表
-- 用户登录认证相关信息
create table success_killed(
  `seckill_id` bigint not null comment '秒杀商品ID',
  `user_phone` bigint not null comment '手机号',
  `status` tinyint not null default -1 comment '状态 -1 无效，0 成功 ，1 已付款，2 已发货',
  `create_time` timestamp not null default current_timestamp COMMENT '创建时间',
  primary key (seckill_id,user_phone),
  key idx_create_time(create_time)

) ENGINE=InnoDB default charset=utf8 comment='秒杀成功明细表';

-- 连接mysql控制台
-- mysql -uroot -proot