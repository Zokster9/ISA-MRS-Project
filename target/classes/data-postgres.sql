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

insert into users (email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values ('matijazaric9@gmail.com', 'matija123','Matija', 'Zaric', 4, '0657842312', false, false, 0, 0);
insert into users (email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values ('sakal.teodor@gmail.com', 'teodor123', 'Teodor', 'Sakal Franciskovic', 1, '0636742312', false, false, 0, 0);
insert into users (email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values ('lordje@gmail.com', 'djordje123', 'Djordje', 'Vajagic', 2, '0617647812', false, false, 0, 0);
insert into users (email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values ('zoki@gmail.com', 'zoran123', 'Zoran', 'Bukorac', 3, '0607846912', true, false, 0, 0);
insert into users (email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values ('klijent@gmail.com', 'klijent123', 'Klijent', 'Klijentic', 2, '0667342312', true, false, 0, 0);

insert into registration_reasoning (privileged_user, registration_reasoning) values (1, 'I want to be retreat owner');
insert into registration_reasoning (privileged_user, registration_reasoning) values (2, 'I want to be ship owner');
insert into registration_reasoning (privileged_user, registration_reasoning) values (3, 'I want to be fishing instructor owner');

insert into retreat_owners (id) values (1);

insert into ship_owners (id) values (2);

insert into fishing_instructors (id) values (3);

insert into admins (id) values (4);

insert into clients (id, penalty_points, is_penalized) values (5, 0, false);

insert into services (name, description, price, owner_id, address_id, is_deleted) values ('Brvnara na Dunavu', 'Lepo mesto pored reke za kvalitetan odmor. Pogodno za zurke, proslave i drizenja sa prijateljima.', 50, 1, 3, false);
insert into my_pictures (id, pictures) values (1, 'pic1.jpg');
insert into my_pictures (id, pictures) values (1, 'pic2.jpg');
insert into my_pictures (id, pictures) values (1, 'pic3.jpg');
insert into my_pictures (id, pictures) values (1, 'pic4.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (1, 'No blabla.');

insert into services (name, description, price, owner_id, address_id, is_deleted) values ('Nojeva barka', 'Brod za ljude sa dubokim dzepom.', 500, 2, 1, false);
insert into my_pictures (id, pictures) values (2, 'pic3.jpg');
insert into my_pictures (id, pictures) values (2, 'pic4.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (2, 'Nooooooo noooooooo.');
insert into my_rules_of_conduct (id, rules_of_conduct) values (2, 'No L l LlLlL .');

insert into services (name, description, price, owner_id, address_id, is_deleted) values ('Instrukcije pecanja', 'Naucite da pecate. Strpljiv i veoma prijatan instruktor', 30, 3, 3, false);
insert into my_pictures (id, pictures) values (3, 'pic5.jpg');
insert into my_pictures (id, pictures) values (3, 'pic6.jpg');
insert into my_rules_of_conduct (id, rules_of_conduct) values (3, 'Isnt allowed: smoking.');
insert into my_rules_of_conduct (id, rules_of_conduct) values (3, 'No noisy music.');

insert into adventures (id, owner_id, instructor_biography, max_num_of_people, reservation_cancellation_conditions) values (3, 3, 'They call me Lordje the greatest.', 3, 'Just text me.');
insert into my_fishing_equipment_adventures (id, fishing_equipment) values (3, 'Hooks');
insert into my_fishing_equipment_adventures (id, fishing_equipment) values (3, 'Nets');
insert into my_fishing_equipment_adventures (id, fishing_equipment) values (3, 'Gaffs');

insert into ships (id, owner_id, type, length, engine_num, engine_power, capacity, max_speed, reservation_cancellation_conditions) values (2, 2, 'Brod', 55, 'A123B5', 200, 100, '50 km/h', 'Prilikom otkaza rezervacije, vlasniku se uplacuje 5% cene');
insert into my_navigation_equipment (id, navigation_equipment) values (2, 'GPS');
insert into my_navigation_equipment (id, navigation_equipment) values (2, 'Other type of GPS');
insert into my_fishing_equipment_ships (id, fishing_equipment) values (2, 'Hooks');
insert into my_fishing_equipment_ships (id, fishing_equipment) values (2, 'Lines');

insert into retreats (id, owner_id, num_of_beds, num_of_rooms) values (1, 1, 4, 2);
insert into retreat_tags (retreat_id, tag_id) values (1, 1);
insert into retreat_tags (retreat_id, tag_id) values (1, 6);
insert into retreat_tags (retreat_id, tag_id) values (1, 4);

insert into subscriptions (client_id, subscription_id) values (5, 1);
insert into subscriptions (client_id, subscription_id) values (5, 2);

insert into ratings (service_rating, user_rating) values (5, 5);

--insert into reservations (id, from_date, to_date, price, status, rating_id, service_id, client_id) values (1, 1623484800000, 1623657600000, 1500, 1, 1, 2, 2);