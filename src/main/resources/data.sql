-- dodawanie rekordów do bazy
insert into Course(id, name) values(10001, 'Testowo');
insert into Course(id, name) values(10002, 'Testowo2');
insert into Course(id, name) values(10003, 'Testowo3');
insert into Course(id, name) values(10004, 'Testowo4');
insert into Course(id, name) values(10005, 'Testowo5');
insert into passport(id, number) values(40001, 'A1234');
insert into passport(id, number) values(40002, 'B4567');
insert into passport(id, number) values(40003, 'C7890');
insert into student(id, name, passport_id) values(20001,'Jan', 40001);
insert into student(id, name, passport_id) values(20002,'Beata', 40002);
insert into student(id, name, passport_id) values(20003, 'Zofia', 40003);
