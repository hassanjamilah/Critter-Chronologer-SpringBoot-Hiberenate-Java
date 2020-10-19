insert into activity (id , activity_Name) values (1,'PETTING');
insert into activity (id , activity_Name) values (2, 'WALKING');
insert into activity (id , activity_Name) values (3, 'FEEDING');
insert into activity (id , activity_Name) values (4, 'MEDICATING');
insert into activity (id , activity_Name) values (5 , 'SHAVING');

insert into week_days (id, day_name) values (6, 'Saturday');
insert into week_days (id, day_name) values (7, 'Sunday');
insert into week_days (id, day_name) values (1, 'Monday');
insert into week_days (id, day_name) values (2, 'Tuesday');
insert into week_days (id, day_name) values (3, 'Wednsday');
insert into week_days (id, day_name) values (4, 'Thursday');
insert into week_days (id, day_name) values (5, 'Friday');

-- TODO: Temp please remove tis
insert into customer (name, phone_number) values ('hassan13' , '132432423');
insert into customer (name, phone_number) values ('Maya' , '132432423');
insert into pet (name, type, owner_id_id) values ('my cat', 1, 1) ;
insert into pet (name, type, owner_id_id) values ('my cat 2', 2, 1) ;
insert into pet (name, type, owner_id_id) values ('my bird 2', 2, 2) ;

insert into employee (name) values ('employee 1');
insert into employee (name) values ('employee 2');

INSERT INTO employee_working_days (employee_id,   working_days_id) VALUES (1, 3);
INSERT INTO employee_working_days (employee_id,   working_days_id) VALUES (1, 5);
INSERT INTO employee_working_days (employee_id,   working_days_id) VALUES (1, 1);
INSERT INTO employee_working_days (employee_id,   working_days_id) VALUES (2, 7);
INSERT INTO employee_working_days (employee_id,   working_days_id) VALUES (2, 1);

INSERT INTO employee_skills (employee_id, skills_id) VALUES (1, 1);
INSERT INTO employee_skills (employee_id, skills_id) VALUES (1, 3);
INSERT INTO employee_skills (employee_id, skills_id) VALUES (2, 2);
INSERT INTO employee_skills (employee_id, skills_id) VALUES (2, 4);

INSERT INTO schedule(id, schedule_date) VALUES(1,'2020-10-16');
INSERT INTO schedule(id, schedule_date) VALUES(2,'2020-10-19');

INSERT INTO schedule_pets (schedule_id, pets_id) VALUES (1, 1);
INSERT INTO schedule_pets (schedule_id, pets_id) VALUES (2, 2);

INSERT INTO schedule_employees (schedule_id, employees_id) VALUES (2,2);
INSERT INTO schedule_employees (schedule_id, employees_id) VALUES (1,1);

INSERT INTO schedule_activities (schedule_id, activities_id) VALUES (1, 2);
INSERT INTO schedule_activities (schedule_id, activities_id) VALUES (2, 3);
INSERT INTO schedule_activities (schedule_id, activities_id) VALUES (1, 5);
INSERT INTO schedule_activities (schedule_id, activities_id) VALUES (1, 4);

