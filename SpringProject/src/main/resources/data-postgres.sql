insert into tags (name, description) values ('WiFi', 'retreat');
insert into tags (name, description) values ('Garage', 'retreat');
insert into tags (name, description) values ('Heating', 'retreat');
insert into tags (name, description) values ('Kitchen', 'retreat');
insert into tags (name, description) values ('Shower', 'retreat');
insert into tags (name, description) values ('Barbeque', 'retreat');
insert into tags (name, description) values ('Drinking water', 'retreat');
insert into tags (name, description) values ('Bathroom', 'retreat');
insert into tags (name, description) values ('WiFi', 'ship');
insert into tags (name, description) values ('Mini bar', 'ship');
insert into tags (name, description) values ('TV', 'ship');
insert into tags (name, description) values ('Jacuzzi', 'ship');
insert into tags (name, description) values ('Additional boat', 'adventure');
insert into tags (name, description) values ('Fish food', 'adventure');
insert into tags (name, description) values ('Fish preparing','adventure');
insert into tags (name, description) values ('Fish cleaning', 'adventure');
insert into tags (name, description) values ('Fishing license', 'adventure');
insert into tags (name, description) values ('Cabins', 'adventure');

insert into addresses (country, city, street) values ('Srbija', 'Subotica', 'Brace Radica 21');
insert into addresses (country, city, street) values ('Srbija', 'Novi Sad', 'Sekspirova 4');
insert into addresses (country, city, street) values ('Srbija', 'Sremska Kamenica', 'Dunavska 1');
insert into addresses (country, city, street) values ('Srbija', 'Loznica', 'Drinska 84');

insert into roles (name) values ('ROLE_retreatOwner');
insert into roles (name) values ('ROLE_shipOwner');
insert into roles (name) values ('ROLE_fishingInstructor');
insert into roles (name) values ('ROLE_client');
insert into roles (name) values ('ROLE_admin');
insert into roles (name) values ('ROLE_mainAdmin');
    
-- za matiju sifra matija123
-- ostalima je: sifra123
insert into users (email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values ('matijazaric9@gmail.com', '$2a$10$6zhtvn7zujmExlDo2pDwUODVq09LpFBuBL62g6Bsd2lOJjnuctrKC','Matija', 'Zaric', 4, '+381657842312', true, false, 0, 0);
insert into users (email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values ('sakal.teodor@gmail.com', '$2a$10$5bqbvqKlyaN8wBVdvl7UeefSJ8ja584oeYMbhK9FQgcjWK2laRxRO', 'Teodor', 'Sakal Franciskovic', 1, '+381636742312', true, false, 0, 0);
insert into users (email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values ('lordje@gmail.com', '$2a$10$5bqbvqKlyaN8wBVdvl7UeefSJ8ja584oeYMbhK9FQgcjWK2laRxRO', 'Djordje', 'Vajagic', 2, '+381617647812', true, false, 0, 0);
insert into users (email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values ('zoki@gmail.com', '$2a$10$5bqbvqKlyaN8wBVdvl7UeefSJ8ja584oeYMbhK9FQgcjWK2laRxRO', 'Zoran', 'Bukorac', 3, '+381607846912', true, false, 0, 0);
insert into users (email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values ('klijent@gmail.com', '$2a$10$5bqbvqKlyaN8wBVdvl7UeefSJ8ja584oeYMbhK9FQgcjWK2laRxRO', 'Klijent', 'Klijentic', 2, '+381667342312', true, false, 0, 0);

insert into admins(admin_id, is_password_changed) values (4, true);

insert into registration_reasoning (privileged_user, registration_reasoning, is_answered) values (1, 'I want to be retreat owner', true);
insert into registration_reasoning (privileged_user, registration_reasoning, is_answered) values (2, 'I want to be ship owner', false);
insert into registration_reasoning (privileged_user, registration_reasoning, is_answered) values (3, 'I want to be fishing instructor owner', false);

insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (2, 2);
insert into user_role (user_id, role_id) values (3, 3);
insert into user_role (user_id, role_id) values (4, 6);
insert into user_role (user_id, role_id) values (5, 4);

insert into clients (id, penalty_points, is_penalized) values (5, 0, false);

insert into services (name, description, price, owner_id, address_id, is_deleted, num_of_reservations) values ('Brvnara na Dunavu', 'Lepo mesto pored reke za kvalitetan odmor. Pogodno za zurke, proslave i druzenja sa prijateljima.', 50, 1, 3, false, 3);
insert into my_pictures (id, pictures) values (1, 'pic1.jpg');
insert into my_pictures (id, pictures) values (1, 'pic2.jpg');
insert into my_pictures (id, pictures) values (1, 'pic3.jpg');
insert into my_pictures (id, pictures) values (1, 'pic4.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (1, 'Inside smoking disallowed');
insert into my_rules_of_conduct (id, rules_of_conduct) values (1, 'Pet friendly');

insert into services (name, description, price, owner_id, address_id, is_deleted, num_of_reservations) values ('Nojeva barka', 'Brod za ljude sa dubokim dzepom.', 500, 2, 1, false, 3);
insert into my_pictures (id, pictures) values (2, 'ship1.jpg');
insert into my_pictures (id, pictures) values (2, 'ship2.jpg');
insert into my_pictures (id, pictures) values (2, 'ship3.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (2, 'Smoking disallowed');
insert into my_rules_of_conduct (id, rules_of_conduct) values (2, 'Weapons disallowed');

insert into services (name, description, price, owner_id, address_id, is_deleted, num_of_reservations) values ('Instrukcije pecanja', 'Naucite da pecate. Strpljiv i veoma prijatan instruktor', 30, 3, 3, false, 4);
insert into my_pictures (id, pictures) values (3, 'adventure1.jpg');
insert into my_pictures (id, pictures) values (3, 'adventure2.jpg');
insert into my_pictures (id, pictures) values (3, 'adventure3.jpg');
insert into my_pictures (id, pictures) values (3, 'adventure4.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (3, 'Must be properly dressed');
insert into my_rules_of_conduct (id, rules_of_conduct) values (3, 'Can''t be noisy');

insert into adventures (id, instructor_biography, max_num_of_people, reservation_cancellation_conditions) values (3, 'They call me Lordje the greatest.', 3, 'Just text me.');
insert into my_fishing_equipment_adventures (id, fishing_equipment) values (3, 'Hooks');
insert into my_fishing_equipment_adventures (id, fishing_equipment) values (3, 'Nets');
insert into my_fishing_equipment_adventures (id, fishing_equipment) values (3, 'Gaffs');

insert into ships (id, type, length, engine_num, engine_power, capacity, max_speed, reservation_cancellation_conditions) values (2, 'Brod', 55, 'A123B5', 200, 100, '50 km/h', 'Prilikom otkaza rezervacije, vlasniku se uplacuje 5% cene');
insert into my_navigation_equipment (id, navigation_equipment) values (2, 'Gyro Compass');
insert into my_navigation_equipment (id, navigation_equipment) values (2, 'Radar');
insert into my_navigation_equipment (id, navigation_equipment) values (2, 'Autopilot');
insert into my_fishing_equipment_ships (id, fishing_equipment) values (2, 'Hooks');
insert into my_fishing_equipment_ships (id, fishing_equipment) values (2, 'Lines');

insert into retreats (id, num_of_beds, num_of_rooms) values (1, 4, 2);

insert into service_tags (service_id, tag_id) values (1, 1);
insert into service_tags (service_id, tag_id) values (1, 6);
insert into service_tags (service_id, tag_id) values (1, 4);
insert into service_tags (service_id, tag_id) values (2, 9);
insert into service_tags (service_id, tag_id) values (2, 12);
insert into service_tags (service_id, tag_id) values (3, 13);
insert into service_tags (service_id, tag_id) values (3, 14);
insert into service_tags (service_id, tag_id) values (3, 15);

insert into subscriptions (client_id, subscription_id) values (5, 1);
insert into subscriptions (client_id, subscription_id) values (5, 2);

insert into ratings (service_rating, privileged_user_rating) values (5, 5);
insert into ratings (service_rating, privileged_user_rating) values (1, 3);
insert into ratings (service_rating, privileged_user_rating) values (2, 2);

insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-05-13 00:00:00', '2022-05-15 00:00:00', '13:00', '13:00', 60, 1, 3, 5, 3);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-05-16 00:00:00', '2022-05-17 00:00:00', '13:00', '13:00', 30, 1, 3, 5, 3);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-06-06 00:00:00', '2022-06-21 00:00:00', '13:00', '13:00', 90, 1, 3, 5, 3);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-06-28 00:00:00', '2022-07-04 00:00:00', '13:00', '13:00', 90, 0, 3, 5, 3);

insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-05-13 00:00:00', '2022-05-15 00:00:00', '12:00', '10:00', 100, 1, 1, 5, 5);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-05-16 00:00:00', '2022-05-17 00:00:00', '12:00', '10:00', 50, 1, 1, 5, 8);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-05-19 00:00:00', '2022-05-24 00:00:00', '12:00', '10:00', 150, 1, 1, 5, 9);

insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-05-13 00:00:00', '2022-05-15 00:00:00', '12:00', '10:00', 1000, 1, 2, 5, 20);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-05-16 00:00:00', '2022-05-17 00:00:00', '12:00', '10:00', 500, 1, 2, 5, 22);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-05-20 00:00:00', '2022-05-23 00:00:00', '12:00', '10:00', 1500, 1, 2, 5, 23);

insert into complaints (complaint, response, is_answered, reservation_id) values ('Unstable boat Unstable boat Unstable boat Unstable boat Unstable boat', '', False, 9);
insert into complaints (complaint, response, is_answered, reservation_id) values ('Stinky bathroom', '', False, 6);
insert into complaints (complaint, response, is_answered, reservation_id) values ('No fish?', '', False, 3);

insert into revisions (revision, is_approved, is_answered, rating_id, reservation_id) values ('It was fine, liked it a lot.', False, False, 1, 3);
insert into revisions (revision, is_approved, is_answered, rating_id, reservation_id) values ('It stinks, it was really bad.', False, False, 2, 6);
insert into revisions (revision, is_approved, is_answered, rating_id, reservation_id) values ('We almost fell out of the boat. Fix it!', False, False, 3, 9);

insert into discounts(discount, from_date, to_date) values (0.15, '2022-05-12 00:00:00', '2100-01-01 00:00:00');

insert into loyalty_programs(client_points_per_reservation, privileged_points_per_reservation, silver_points_required, gold_points_required,
                           silver_client_bonus, gold_client_bonus, silver_privileged_user_bonus, gold_privileged_user_bonus, is_active)
                           values (1, 1, 10, 40, 0.1, 0.15, 0.05, 0.1, True);