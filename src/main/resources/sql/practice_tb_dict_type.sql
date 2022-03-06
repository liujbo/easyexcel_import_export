create table tb_dict_type
(
    id        bigint auto_increment comment '主键'
        primary key,
    type_no   varchar(20)  null comment '类型编号',
    type_name varchar(128) null comment '类型名称'
)
    charset = utf8;

INSERT INTO practice.tb_dict_type (id, type_no, type_name) VALUES (1, '1001', '频度');
INSERT INTO practice.tb_dict_type (id, type_no, type_name) VALUES (2, '1002', '批次');