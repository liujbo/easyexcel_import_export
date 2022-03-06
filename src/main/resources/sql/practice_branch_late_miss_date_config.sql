create table branch_late_miss_date_config
(
    id                 bigint auto_increment comment '主键'
        primary key,
    branch_no          varchar(256) null comment '报送分行机构号',
    branch_name        varchar(256) null comment '报送分行名称',
    belong_branch_no   varchar(256) null comment '所属一级分行机构号',
    belong_branch_name varchar(256) null comment '所属一级分行名称',
    frequency          char(2)      null comment '频度',
    batch              char(2)      null comment '批次',
    report_date        datetime     null comment '报送时间',
    report_num         int          null comment '报表张数'
)
    charset = utf8;

INSERT INTO practice.branch_late_miss_date_config (id, branch_no, branch_name, belong_branch_no, belong_branch_name, frequency, batch, report_date, report_num) VALUES (1, '000002', '西安市分行', '000020', '陕西省分行', '2', '4', '2022-06-06 08:00:00', 30);
INSERT INTO practice.branch_late_miss_date_config (id, branch_no, branch_name, belong_branch_no, belong_branch_name, frequency, batch, report_date, report_num) VALUES (2, '000002', '西安市分行', '000020', '陕西省分行', '2', '5', '2022-09-12 13:00:00', 12);
INSERT INTO practice.branch_late_miss_date_config (id, branch_no, branch_name, belong_branch_no, belong_branch_name, frequency, batch, report_date, report_num) VALUES (3, '000002', '西安市分行', '000020', '陕西省分行', '2', '6', '2022-09-15 13:00:00', 24);
INSERT INTO practice.branch_late_miss_date_config (id, branch_no, branch_name, belong_branch_no, belong_branch_name, frequency, batch, report_date, report_num) VALUES (4, '000002', '西安市分行', '000020', '陕西省分行', '2', '6', '2022-09-16 13:00:00', 25);