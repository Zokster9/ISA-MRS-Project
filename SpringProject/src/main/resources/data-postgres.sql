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
insert into addresses (country, city, street) values ('Srbija', 'Apatin', 'Doze Djerdja 18');
insert into addresses (country, city, street) values ('Srbija', 'Apatin', 'Nova ulica 2');

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
insert into users (email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values ('nikola@gmail.com', '$2a$10$5bqbvqKlyaN8wBVdvl7UeefSJ8ja584oeYMbhK9FQgcjWK2laRxRO', 'Nikola', 'Damjanovic', 6, '+381667342111', true, false, 0, 0);
insert into users (email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values ('vladan@gmail.com', '$2a$10$5bqbvqKlyaN8wBVdvl7UeefSJ8ja584oeYMbhK9FQgcjWK2laRxRO', 'Vladan', 'Vladanovic', 5, '+381667001122', true, false, 0, 0);
insert into users (email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values ('jovana@gmail.com', '$2a$10$5bqbvqKlyaN8wBVdvl7UeefSJ8ja584oeYMbhK9FQgcjWK2laRxRO', 'Jovana', 'Jovanovic', 1, '+381667340000', true, false, 0, 0);
insert into users (email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values ('lazar@gmail.com', '$2a$10$5bqbvqKlyaN8wBVdvl7UeefSJ8ja584oeYMbhK9FQgcjWK2laRxRO', 'Lazar', 'Lazarevic', 1, '+381667342110', true, false, 0, 0);
insert into users (email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values ('slavko@gmail.com', '$2a$10$5bqbvqKlyaN8wBVdvl7UeefSJ8ja584oeYMbhK9FQgcjWK2laRxRO', 'Slavko', 'Slavkovic', 1, '+381667349999', true, false, 0, 0);

insert into admins(admin_id, is_password_changed) values (4, true);
insert into admins(admin_id, is_password_changed) values (7, true);

insert into registration_reasoning (privileged_user, registration_reasoning, is_answered) values (1, 'I want to be retreat owner', true);
insert into registration_reasoning (privileged_user, registration_reasoning, is_answered) values (2, 'I want to be ship owner', true);
insert into registration_reasoning (privileged_user, registration_reasoning, is_answered) values (3, 'I want to be fishing instructor owner', true);
insert into registration_reasoning (privileged_user, registration_reasoning, is_answered) values (8, 'I want to earn some money', true);
insert into registration_reasoning (privileged_user, registration_reasoning, is_answered) values (9, 'I wanna get into business side of things.', true);
insert into registration_reasoning (privileged_user, registration_reasoning, is_answered) values (10, 'I want to expand my business through this site.', true);

insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (2, 2);
insert into user_role (user_id, role_id) values (3, 3);
insert into user_role (user_id, role_id) values (4, 6);
insert into user_role (user_id, role_id) values (5, 4);
insert into user_role (user_id, role_id) values (6, 4);
insert into user_role (user_id, role_id) values (7, 5);
insert into user_role (user_id, role_id) values (8, 2);
insert into user_role (user_id, role_id) values (9, 3);
insert into user_role (user_id, role_id) values (10, 1);

insert into clients (id, penalty_points, is_penalized) values (5, 0, false);
insert into clients (id, penalty_points, is_penalized) values (6, 0, false);

insert into services (name, description, price, owner_id, address_id, is_deleted, num_of_reservations) values ('Cottage near Danube', 'Fine place near lake, excellent for rest.', 50, 1, 3, false, 3);
insert into my_pictures (id, pictures) values (1, 'pic1.jpg');
insert into my_pictures (id, pictures) values (1, 'pic2.jpg');
insert into my_pictures (id, pictures) values (1, 'pic3.jpg');
insert into my_pictures (id, pictures) values (1, 'pic4.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (1, 'Inside smoking forbidden.');
insert into my_rules_of_conduct (id, rules_of_conduct) values (1, 'Pet friendly');

insert into services (name, description, price, owner_id, address_id, is_deleted, num_of_reservations) values ('Flamingo boat', 'Expensive boat for rich people.', 500, 2, 1, false, 3);
insert into my_pictures (id, pictures) values (2, 'ship1.jpg');
insert into my_pictures (id, pictures) values (2, 'ship2.jpg');
insert into my_pictures (id, pictures) values (2, 'ship3.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (2, 'Smoking forbidden');
insert into my_rules_of_conduct (id, rules_of_conduct) values (2, 'Weapons forbidden');

insert into services (name, description, price, owner_id, address_id, is_deleted, num_of_reservations) values ('Fishing with experts', 'Learn to fish with one of the most famous instructors.', 30, 3, 3, false, 4);
insert into my_pictures (id, pictures) values (3, 'adventure1.jpg');
insert into my_pictures (id, pictures) values (3, 'adventure2.jpg');
insert into my_pictures (id, pictures) values (3, 'adventure3.jpg');
insert into my_pictures (id, pictures) values (3, 'adventure4.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (3, 'Must be properly dressed');
insert into my_rules_of_conduct (id, rules_of_conduct) values (3, 'Can''t be noisy');

insert into services (name, description, price, owner_id, address_id, is_deleted, num_of_reservations) values ('Cottage for partying', 'Cottage made for teenager parties.', 100, 1, 4, false, 2);
insert into my_pictures (id, pictures) values (4, 'pic1.jpg');
insert into my_pictures (id, pictures) values (4, 'pic2.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (4, 'No noise after 4am');

insert into services (name, description, price, owner_id, address_id, is_deleted, num_of_reservations) values ('Shipstappen', 'Ship made for Formula 1 Red Bull fans.', 1000, 2, 1, false, 2);
insert into my_pictures (id, pictures) values (5, 'ship3.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (5, 'Smoking forbidden');
insert into my_rules_of_conduct (id, rules_of_conduct) values (5, 'Alcohol friendly');

insert into services (name, description, price, owner_id, address_id, is_deleted, num_of_reservations) values ('Beginner fishing', 'Fishing course made primarily for kids.', 20, 3, 5, false, 2);
insert into my_pictures (id, pictures) values (6, 'adventure2.jpg');
insert into my_pictures (id, pictures) values (6, 'adventure4.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (6, 'Must be under 18');
insert into my_rules_of_conduct (id, rules_of_conduct) values (6, 'Can''t be noisy');

insert into services (name, description, price, owner_id, address_id, is_deleted, num_of_reservations) values ('Party boat', 'Boat made for summer parties.', 50, 8, 6, false, 2);
insert into my_pictures (id, pictures) values (7, 'ship1.jpg');
insert into my_pictures (id, pictures) values (7, 'ship2.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (7, 'Must be over 18');
insert into my_rules_of_conduct (id, rules_of_conduct) values (7, 'Allowed drinking');

insert into services (name, description, price, owner_id, address_id, is_deleted, num_of_reservations) values ('Marriage boat', 'Boat made exclusively for weddings.', 300, 8, 5, false, 2);
insert into my_pictures (id, pictures) values (8, 'ship2.jpg');
insert into my_pictures (id, pictures) values (8, 'ship3.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (8, 'No jumping allowed');

insert into services (name, description, price, owner_id, address_id, is_deleted, num_of_reservations) values ('Extreme fishing', 'Fishing course where limits are tested.', 100, 9, 3, false, 2);
insert into my_pictures (id, pictures) values (9, 'adventure3.jpg');
insert into my_pictures (id, pictures) values (9, 'adventure4.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (9, 'Must sign a contract for fishing at your own risk');
insert into my_rules_of_conduct (id, rules_of_conduct) values (9, 'Must be over 18');

insert into services (name, description, price, owner_id, address_id, is_deleted, num_of_reservations) values ('Fishing with barbecue', 'Fishing course after which barbecue is served.', 70, 9, 2, false, 2);
insert into my_pictures (id, pictures) values (10, 'adventure1.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (10, 'Must be over 18');

insert into services (name, description, price, owner_id, address_id, is_deleted, num_of_reservations) values ('Retreat for rest in mountains', 'Retreat primarily made for older people.', 100, 10, 1, false, 2);
insert into my_pictures (id, pictures) values (11, 'pic2.jpg');
insert into my_pictures (id, pictures) values (11, 'pic3.jpg');
insert into my_pictures (id, pictures) values (11, 'pic4.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (11, 'Smoking forbidden');

insert into services (name, description, price, owner_id, address_id, is_deleted, num_of_reservations) values ('Farm retreat', 'Retreat made for animal and farm lovers.', 30, 10, 2, false, 2);
insert into my_pictures (id, pictures) values (12, 'pic2.jpg');
insert into my_pictures (id, pictures) values (12, 'pic4.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (12, 'Smoking forbidden');

insert into adventures (id, instructor_biography, max_num_of_people, reservation_cancellation_conditions) values (3, 'They call me Lordje the greatest.', 3, 'Just text me.');
insert into my_fishing_equipment_adventures (id, fishing_equipment) values (3, 'Hooks');
insert into my_fishing_equipment_adventures (id, fishing_equipment) values (3, 'Nets');
insert into my_fishing_equipment_adventures (id, fishing_equipment) values (3, 'Gaffs');

insert into adventures (id, instructor_biography, max_num_of_people, reservation_cancellation_conditions) values (6, 'Expert in teaching kids.', 15, 'Please inform me in time.');
insert into my_fishing_equipment_adventures (id, fishing_equipment) values (6, 'Hooks');
insert into my_fishing_equipment_adventures (id, fishing_equipment) values (6, 'Nets');
insert into my_fishing_equipment_adventures (id, fishing_equipment) values (6, 'Gaffs');

insert into adventures (id, instructor_biography, max_num_of_people, reservation_cancellation_conditions) values (9, 'Famous for limit testing while fishing.', 2, 'Please inform me in time.');
insert into my_fishing_equipment_adventures (id, fishing_equipment) values (9, 'Hooks');
insert into my_fishing_equipment_adventures (id, fishing_equipment) values (9, 'Nets');

insert into adventures (id, instructor_biography, max_num_of_people, reservation_cancellation_conditions) values (10, 'Lovely fishing instructor.', 20, 'Please inform me 3 days prior.');
insert into my_fishing_equipment_adventures (id, fishing_equipment) values (10, 'Hooks');
insert into my_fishing_equipment_adventures (id, fishing_equipment) values (10, 'Nets');
insert into my_fishing_equipment_adventures (id, fishing_equipment) values (10, 'Gaffs');

insert into ships (id, type, length, engine_num, engine_power, capacity, max_speed, reservation_cancellation_conditions) values (2, 'Boat', 55, 'A123B5', 200, 100, '50 km/h', 'If unable to confirm your reservation, 5% of the original price will be taken.');
insert into my_navigation_equipment (id, navigation_equipment) values (2, 'Gyro Compass');
insert into my_navigation_equipment (id, navigation_equipment) values (2, 'Radar');
insert into my_navigation_equipment (id, navigation_equipment) values (2, 'Autopilot');
insert into my_fishing_equipment_ships (id, fishing_equipment) values (2, 'Hooks');
insert into my_fishing_equipment_ships (id, fishing_equipment) values (2, 'Lines');

insert into ships (id, type, length, engine_num, engine_power, capacity, max_speed, reservation_cancellation_conditions) values (5, 'Ship', 49, 'A124B5', 250, 50, '60 km/h', 'Please inform us at least 3 days prior to the cancellation of the reservation.');
insert into my_navigation_equipment (id, navigation_equipment) values (5, 'Gyro Compass');
insert into my_navigation_equipment (id, navigation_equipment) values (5, 'Radar');
insert into my_navigation_equipment (id, navigation_equipment) values (5, 'Autopilot');
insert into my_fishing_equipment_ships (id, fishing_equipment) values (5, 'Hooks');
insert into my_fishing_equipment_ships (id, fishing_equipment) values (5, 'Lines');

insert into ships (id, type, length, engine_num, engine_power, capacity, max_speed, reservation_cancellation_conditions) values (7, 'PartyBoat', 40, 'A125B5', 100, 75, '30 km/h', 'No conditions.');
insert into my_navigation_equipment (id, navigation_equipment) values (7, 'Radar');
insert into my_navigation_equipment (id, navigation_equipment) values (7, 'Autopilot');
insert into my_fishing_equipment_ships (id, fishing_equipment) values (7, 'Hooks');

insert into ships (id, type, length, engine_num, engine_power, capacity, max_speed, reservation_cancellation_conditions) values (8, 'WeddingBoat', 45, 'A126B5', 120, 100, '35 km/h', 'Inform us a week before.');
insert into my_navigation_equipment (id, navigation_equipment) values (8, 'Radar');
insert into my_navigation_equipment (id, navigation_equipment) values (8, 'Autopilot');
insert into my_navigation_equipment (id, navigation_equipment) values (8, 'Gyro Compass');
insert into my_fishing_equipment_ships (id, fishing_equipment) values (8, 'Hooks');
insert into my_fishing_equipment_ships (id, fishing_equipment) values (8, 'Lines');

insert into retreats (id, num_of_beds, num_of_rooms) values (1, 4, 2);
insert into retreats (id, num_of_beds, num_of_rooms) values (4, 10, 5);
insert into retreats (id, num_of_beds, num_of_rooms) values (11, 3, 2);
insert into retreats (id, num_of_beds, num_of_rooms) values (12, 2, 1);

insert into service_tags (service_id, tag_id) values (1, 1);
insert into service_tags (service_id, tag_id) values (1, 6);
insert into service_tags (service_id, tag_id) values (1, 4);

insert into service_tags (service_id, tag_id) values (2, 9);
insert into service_tags (service_id, tag_id) values (2, 12);

insert into service_tags (service_id, tag_id) values (3, 13);
insert into service_tags (service_id, tag_id) values (3, 14);
insert into service_tags (service_id, tag_id) values (3, 15);

insert into service_tags (service_id, tag_id) values (4, 1);
insert into service_tags (service_id, tag_id) values (4, 3);
insert into service_tags (service_id, tag_id) values (4, 4);
insert into service_tags (service_id, tag_id) values (4, 5);
insert into service_tags (service_id, tag_id) values (4, 6);

insert into service_tags (service_id, tag_id) values (5, 9);
insert into service_tags (service_id, tag_id) values (5, 10);
insert into service_tags (service_id, tag_id) values (5, 11);
insert into service_tags (service_id, tag_id) values (5, 12);

insert into service_tags (service_id, tag_id) values (6, 14);
insert into service_tags (service_id, tag_id) values (6, 15);
insert into service_tags (service_id, tag_id) values (6, 16);
insert into service_tags (service_id, tag_id) values (6, 18);

insert into service_tags (service_id, tag_id) values (7, 9);
insert into service_tags (service_id, tag_id) values (7, 10);

insert into service_tags (service_id, tag_id) values (8, 10);
insert into service_tags (service_id, tag_id) values (8, 12);

insert into service_tags (service_id, tag_id) values (9, 13);

insert into service_tags (service_id, tag_id) values (10, 13);
insert into service_tags (service_id, tag_id) values (10, 14);
insert into service_tags (service_id, tag_id) values (10, 15);
insert into service_tags (service_id, tag_id) values (10, 16);

insert into service_tags (service_id, tag_id) values (11, 3);
insert into service_tags (service_id, tag_id) values (11, 4);
insert into service_tags (service_id, tag_id) values (11, 5);
insert into service_tags (service_id, tag_id) values (11, 6);
insert into service_tags (service_id, tag_id) values (11, 7);
insert into service_tags (service_id, tag_id) values (11, 8);

insert into service_tags (service_id, tag_id) values (12, 4);
insert into service_tags (service_id, tag_id) values (12, 5);
insert into service_tags (service_id, tag_id) values (12, 6);
insert into service_tags (service_id, tag_id) values (12, 8);

insert into subscriptions (client_id, subscription_id) values (5, 1);
insert into subscriptions (client_id, subscription_id) values (5, 2);
insert into subscriptions (client_id, subscription_id) values (6, 10);
insert into subscriptions (client_id, subscription_id) values (6, 12);

insert into ratings (service_rating, privileged_user_rating) values (5, 5);
insert into ratings (service_rating, privileged_user_rating) values (1, 3);
insert into ratings (service_rating, privileged_user_rating) values (3, 4);
insert into ratings (service_rating, privileged_user_rating) values (4, 4);
insert into ratings (service_rating, privileged_user_rating) values (5, 4);
insert into ratings (service_rating, privileged_user_rating) values (4, 5);
insert into ratings (service_rating, privileged_user_rating) values (2, 5);
insert into ratings (service_rating, privileged_user_rating) values (3, 5);
insert into ratings (service_rating, privileged_user_rating) values (1, 5);
insert into ratings (service_rating, privileged_user_rating) values (4, 4);
insert into ratings (service_rating, privileged_user_rating) values (3, 4);
insert into ratings (service_rating, privileged_user_rating) values (2, 2);
insert into ratings (service_rating, privileged_user_rating) values (3, 3);

insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-05-13 00:00:00', '2022-05-15 00:00:00', '13:00', '13:00', 60, 1, 3, 5, 3);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-05-16 00:00:00', '2022-05-17 00:00:00', '13:00', '13:00', 30, 1, 3, 5, 3);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-06-06 00:00:00', '2022-06-21 00:00:00', '13:00', '13:00', 90, 1, 3, 5, 3);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-06-28 00:00:00', '2022-07-03 00:00:00', '13:00', '13:00', 90, 0, 3, 5, 3);

insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-05-13 00:00:00', '2022-05-15 00:00:00', '12:00', '10:00', 100, 1, 1, 5, 5);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-05-16 00:00:00', '2022-05-17 00:00:00', '12:00', '10:00', 50, 1, 1, 5, 8);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-05-19 00:00:00', '2022-05-24 00:00:00', '12:00', '10:00', 150, 1, 1, 5, 9);

insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-05-13 00:00:00', '2022-05-15 00:00:00', '12:00', '10:00', 1000, 1, 2, 5, 20);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-05-16 00:00:00', '2022-05-17 00:00:00', '12:00', '10:00', 500, 1, 2, 5, 22);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-05-20 00:00:00', '2022-05-23 00:00:00', '12:00', '10:00', 1500, 1, 2, 5, 23);

insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-06-27 00:00:00', '2022-07-01 00:00:00', '13:00', '13:00', 150, 0, 4, 6, 5);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-07-02 00:00:00', '2022-07-03 00:00:00', '14:00', '14:00', 200, 0, 4, 5, 3);

insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-06-26 00:00:00', '2022-07-03 00:00:00', '13:00', '13:00', 150, 0, 5, 6, 4);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-07-04 00:00:00', '2022-07-05 00:00:00', '14:00', '14:00', 270, 0, 5, 5, 4);

insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-06-20 00:00:00', '2022-06-27 00:00:00', '13:00', '13:00', 150, 1, 6, 6, 2);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-06-30 00:00:00', '2022-07-02 00:00:00', '14:00', '14:00', 200, 0, 6, 5, 2);

insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-06-29 00:00:00', '2022-06-30 00:00:00', '13:00', '13:00', 150, 0, 7, 6, 5);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-07-02 00:00:00', '2022-07-03 00:00:00', '14:00', '14:00', 200, 0, 7, 5, 3);

insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-06-25 00:00:00', '2022-06-26 00:00:00', '13:00', '13:00', 215, 0, 8, 6, 5);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-06-27 00:00:00', '2022-06-28 00:00:00', '14:00', '14:00', 215, 0, 8, 5, 3);

insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-06-27 00:00:00', '2022-06-29 00:00:00', '13:00', '13:00', 150, 0, 9, 6, 5);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-06-30 00:00:00', '2022-07-01 00:00:00', '14:00', '14:00', 200, 0, 9, 5, 3);

insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-06-12 00:00:00', '2022-06-16 00:00:00', '13:00', '13:00', 150, 1, 10, 6, 5);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-06-12 00:00:00', '2022-06-20 00:00:00', '14:00', '14:00', 200, 1, 10, 5, 3);

insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-06-10 00:00:00', '2022-06-15 00:00:00', '13:00', '13:00', 150, 1, 11, 6, 5);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-06-18 00:00:00', '2022-06-20 00:00:00', '14:00', '14:00', 200, 1, 11, 5, 3);

insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-06-15 00:00:00', '2022-06-20 00:00:00', '13:00', '13:00', 155, 1, 12, 6, 5);
insert into reservations (from_date, to_date, from_time, to_time, price, status, service_id, client_id, num_of_people) values ('2022-06-21 00:00:00', '2022-06-23 00:00:00', '14:00', '14:00', 205, 1, 12, 5, 3);

insert into service_availabilities (service_id, date_from, date_to, time_from, time_to) values ('1', '2022-05-01', '2022-09-01', '10:00', '22:00');
insert into service_availabilities (service_id, date_from, date_to, time_from, time_to) values ('2', '2022-05-01', '2022-09-01', '10:00', '22:00');
insert into service_availabilities (service_id, date_from, date_to, time_from, time_to) values ('3', '2022-05-01', '2022-09-01', '10:00', '22:00');
insert into service_availabilities (service_id, date_from, date_to, time_from, time_to) values ('4', '2022-05-01', '2022-09-01', '10:00', '22:00');
insert into service_availabilities (service_id, date_from, date_to, time_from, time_to) values ('5', '2022-05-01', '2022-09-01', '10:00', '22:00');
insert into service_availabilities (service_id, date_from, date_to, time_from, time_to) values ('6', '2022-05-01', '2022-09-01', '10:00', '22:00');
insert into service_availabilities (service_id, date_from, date_to, time_from, time_to) values ('7', '2022-05-01', '2022-09-01', '10:00', '22:00');
insert into service_availabilities (service_id, date_from, date_to, time_from, time_to) values ('8', '2022-05-01', '2022-09-01', '10:00', '22:00');
insert into service_availabilities (service_id, date_from, date_to, time_from, time_to) values ('9', '2022-05-01', '2022-09-01', '10:00', '22:00');
insert into service_availabilities (service_id, date_from, date_to, time_from, time_to) values ('10', '2022-05-01', '2022-09-01', '10:00', '22:00');
insert into service_availabilities (service_id, date_from, date_to, time_from, time_to) values ('11', '2022-05-01', '2022-09-01', '10:00', '22:00');
insert into service_availabilities (service_id, date_from, date_to, time_from, time_to) values ('12', '2022-05-01', '2022-09-01', '10:00', '22:00');

insert into actions (date_from, date_to, max_num_of_people, price, time_from, time_to, service_id) values ('2022-07-04', '2022-07-10', 10, 100, '10:00', '22:00', 1);
insert into actions (date_from, date_to, max_num_of_people, price, time_from, time_to, service_id) values ('2022-07-04', '2022-07-10', 10, 100, '10:00', '22:00', 2);
insert into actions (date_from, date_to, max_num_of_people, price, time_from, time_to, service_id) values ('2022-07-04', '2022-07-10', 10, 100, '10:00', '22:00', 3);
insert into actions (date_from, date_to, max_num_of_people, price, time_from, time_to, service_id) values ('2022-07-04', '2022-07-10', 10, 100, '10:00', '22:00', 4);
insert into actions (date_from, date_to, max_num_of_people, price, time_from, time_to, service_id) values ('2022-07-06', '2022-07-10', 10, 100, '10:00', '22:00', 5);
insert into actions (date_from, date_to, max_num_of_people, price, time_from, time_to, service_id) values ('2022-07-04', '2022-07-10', 10, 100, '10:00', '22:00', 6);
insert into actions (date_from, date_to, max_num_of_people, price, time_from, time_to, service_id) values ('2022-07-04', '2022-07-10', 10, 100, '10:00', '22:00', 7);
insert into actions (date_from, date_to, max_num_of_people, price, time_from, time_to, service_id) values ('2022-07-04', '2022-07-10', 10, 100, '10:00', '22:00', 8);
insert into actions (date_from, date_to, max_num_of_people, price, time_from, time_to, service_id) values ('2022-07-04', '2022-07-10', 10, 100, '10:00', '22:00', 9);
insert into actions (date_from, date_to, max_num_of_people, price, time_from, time_to, service_id) values ('2022-07-04', '2022-07-10', 10, 100, '10:00', '22:00', 10);
insert into actions (date_from, date_to, max_num_of_people, price, time_from, time_to, service_id) values ('2022-07-04', '2022-07-10', 10, 100, '10:00', '22:00', 11);
insert into actions (date_from, date_to, max_num_of_people, price, time_from, time_to, service_id) values ('2022-07-04', '2022-07-10', 10, 100, '10:00', '22:00', 12);

insert into action_tags (action_id, tag_id) values (1, 1);
insert into action_tags (action_id, tag_id) values (1, 4);
insert into action_tags (action_id, tag_id) values (1, 6);

insert into action_tags (action_id, tag_id) values (4, 1);
insert into action_tags (action_id, tag_id) values (4, 3);
insert into action_tags (action_id, tag_id) values (4, 5);

insert into action_tags (action_id, tag_id) values (11, 3);
insert into action_tags (action_id, tag_id) values (11, 4);
insert into action_tags (action_id, tag_id) values (11, 5);

insert into action_tags (action_id, tag_id) values (12, 4);
insert into action_tags (action_id, tag_id) values (12, 5);
insert into action_tags (action_id, tag_id) values (12, 6);

insert into action_tags (action_id, tag_id) values (3, 13);
insert into action_tags (action_id, tag_id) values (3, 14);

insert into action_tags (action_id, tag_id) values (6, 15);
insert into action_tags (action_id, tag_id) values (6, 16);

insert into action_tags (action_id, tag_id) values (9, 13);

insert into action_tags (action_id, tag_id) values (10, 13);
insert into action_tags (action_id, tag_id) values (10, 14);
insert into action_tags (action_id, tag_id) values (10, 15);

insert into action_tags (action_id, tag_id) values (2, 9);
insert into action_tags (action_id, tag_id) values (2, 12);
insert into action_tags (action_id, tag_id) values (5, 9);
insert into action_tags (action_id, tag_id) values (5, 10);
insert into action_tags (action_id, tag_id) values (7, 9);
insert into action_tags (action_id, tag_id) values (7, 10);
insert into action_tags (action_id, tag_id) values (8, 10);
insert into action_tags (action_id, tag_id) values (8, 12);

insert into complaints (complaint, response, is_answered, reservation_id) values ('Unstable boat Unstable boat Unstable boat Unstable boat Unstable boat', '', False, 9);
insert into complaints (complaint, response, is_answered, reservation_id) values ('Stinky bathroom', '', False, 6);
insert into complaints (complaint, response, is_answered, reservation_id) values ('No fish?', '', False, 3);

insert into revisions (revision, is_approved, is_answered, rating_id, reservation_id) values ('It was fine, liked it a lot.', False, False, 1, 3);
insert into revisions (revision, is_approved, is_answered, rating_id, reservation_id) values ('It stinks, it was really bad.', False, False, 2, 6);
insert into revisions (revision, is_approved, is_answered, rating_id, reservation_id) values ('We almost fell out of the boat. Fix it!', False, False, 3, 9);
insert into revisions (revision, is_approved, is_answered, rating_id, reservation_id) values ('Was dope.', False, False, 4, 12);
insert into revisions (revision, is_approved, is_answered, rating_id, reservation_id) values ('It was fine', False, False, 5, 14);
insert into revisions (revision, is_approved, is_answered, rating_id, reservation_id) values ('It was ok.', False, False, 6, 16);
insert into revisions (revision, is_approved, is_answered, rating_id, reservation_id) values ('It was a success.', False, False, 7, 18);
insert into revisions (revision, is_approved, is_answered, rating_id, reservation_id) values ('It could be better.', False, False, 8, 20);
insert into revisions (revision, is_approved, is_answered, rating_id, reservation_id) values ('It was fine i guess.', False, False, 9, 22);
insert into revisions (revision, is_approved, is_answered, rating_id, reservation_id) values ('It was smelly.', False, False, 10, 24);
insert into revisions (revision, is_approved, is_answered, rating_id, reservation_id) values ('It was really nice.', False, False, 11, 26);
insert into revisions (revision, is_approved, is_answered, rating_id, reservation_id) values ('It was good, nothing bad to say about it.', False, False, 12, 28);
insert into revisions (revision, is_approved, is_answered, rating_id, reservation_id) values ('It was good, nothing bad to say about it.', False, False, 13, 27);

insert into discounts(discount, from_date, to_date) values (0.15, '2022-05-12 00:00:00', '2100-01-01 00:00:00');

insert into loyalty_programs(client_points_per_reservation, privileged_points_per_reservation, silver_points_required, gold_points_required,
                           silver_client_bonus, gold_client_bonus, silver_privileged_user_bonus, gold_privileged_user_bonus, is_active)
                           values (1, 1, 10, 40, 0.1, 0.15, 0.05, 0.1, True);