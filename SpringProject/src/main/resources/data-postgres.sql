insert into tags (name, description) values ('WiFi', 'WiFi access');
insert into tags (name, description) values ('Garage', 'Parking spot for your car');
insert into tags (name, description) values ('Heating', 'You can heat up the room if you feel cold');
insert into tags (name, description) values ('Kitchen', 'You can prepare food');
insert into tags (name, description) values ('Shower', 'You can wash yourself');
insert into tags (name, description) values ('Barbeque', 'Barbeque with your friends');
insert into tags (name, description) values ('Drinking water', 'You do not need to bring your own water');
insert into tags (name, description) values ('Bathroom', 'Fully equipped bathroom');

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
insert into users (email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values ('matijazaric9@gmail.com', '$2a$10$6zhtvn7zujmExlDo2pDwUODVq09LpFBuBL62g6Bsd2lOJjnuctrKC','Matija', 'Zaric', 4, '+381657842312', false, false, 0, 0);
insert into users (email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values ('sakal.teodor@gmail.com', '$2a$10$5bqbvqKlyaN8wBVdvl7UeefSJ8ja584oeYMbhK9FQgcjWK2laRxRO', 'Teodor', 'Sakal Franciskovic', 1, '+381636742312', false, false, 0, 0);
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

insert into services (name, description, price, owner_id, address_id, is_deleted) values ('Brvnara na Dunavu', 'Lepo mesto pored reke za kvalitetan odmor. Pogodno za zurke, proslave i drizenja sa prijateljima.', 50, 1, 3, false);
insert into my_pictures (id, pictures) values (1, 'pic1.jpg');
insert into my_pictures (id, pictures) values (1, 'pic2.jpg');
insert into my_pictures (id, pictures) values (1, 'pic3.jpg');
insert into my_pictures (id, pictures) values (1, 'pic4.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (1, 'Inside smoking disallowed');
insert into my_rules_of_conduct (id, rules_of_conduct) values (1, 'Pet friendly');

insert into services (name, description, price, owner_id, address_id, is_deleted) values ('Nojeva barka', 'Brod za ljude sa dubokim dzepom.', 500, 2, 1, false);
insert into my_pictures (id, pictures) values (2, 'ship1.jpg');
insert into my_pictures (id, pictures) values (2, 'ship2.jpg');
insert into my_pictures (id, pictures) values (2, 'ship3.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (2, 'Smoking disallowed');
insert into my_rules_of_conduct (id, rules_of_conduct) values (2, 'Weapons disallowed');

insert into services (name, description, price, owner_id, address_id, is_deleted) values ('Instrukcije pecanja', 'Naucite da pecate. Strpljiv i veoma prijatan instruktor', 30, 3, 3, false);
insert into my_pictures (id, pictures) values (3, 'adventure1.jpg');
insert into my_pictures (id, pictures) values (3, 'adventure2.jpg');
insert into my_pictures (id, pictures) values (3, 'adventure3.jpg');
insert into my_pictures (id, pictures) values (3, 'adventure4.png');
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
insert into retreat_tags (retreat_id, tag_id) values (1, 1);
insert into retreat_tags (retreat_id, tag_id) values (1, 6);
insert into retreat_tags (retreat_id, tag_id) values (1, 4);

insert into subscriptions (client_id, subscription_id) values (5, 1);
insert into subscriptions (client_id, subscription_id) values (5, 2);

insert into ratings (service_rating, user_rating) values (5, 5);

--insert into reservations (id, from_date, to_date, price, status, rating_id, service_id, client_id) values (1, 1623484800000, 1623657600000, 1500, 1, 1, 2, 2);