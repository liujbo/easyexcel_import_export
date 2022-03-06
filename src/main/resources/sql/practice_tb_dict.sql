create table tb_dict
(
    id        bigint auto_increment comment '主键'
        primary key,
    dict_no   varchar(20)  null comment '码表编号',
    dict_name varchar(128) null comment '码表名称',
    type_no   varchar(20)  null comment '类型编号'
)
    charset = utf8;

INSERT INTO practice.tb_dict (id, dict_no, dict_name, type_no) VALUES (1, '1', '月', '1001');
INSERT INTO practice.tb_dict (id, dict_no, dict_name, type_no) VALUES (2, '2', '季', '1001');
INSERT INTO practice.tb_dict (id, dict_no, dict_name, type_no) VALUES (3, '3', '半年', '1001');
INSERT INTO practice.tb_dict (id, dict_no, dict_name, type_no) VALUES (4, '1', '一批次', '1002');
INSERT INTO practice.tb_dict (id, dict_no, dict_name, type_no) VALUES (5, '2', '二批次', '1002');
INSERT INTO practice.tb_dict (id, dict_no, dict_name, type_no) VALUES (6, '3', '三批次', '1002');
INSERT INTO practice.tb_dict (id, dict_no, dict_name, type_no) VALUES (7, '4', '四批次', '1002');
INSERT INTO practice.tb_dict (id, dict_no, dict_name, type_no) VALUES (8, '5', '五批次', '1002');
INSERT INTO practice.tb_dict (id, dict_no, dict_name, type_no) VALUES (9, '6', '六批次', '1002');