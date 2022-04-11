insert into addresses (id, country, city, street) values (1, 'Srbija', 'Subotica', 'Brace Radica 21');
insert into addresses (id, country, city, street) values (2, 'Srbija', 'Novi Sad', 'Sekspirova 4');
insert into addresses (id, country, city, street) values (3, 'Srbija', 'Sremska Kamenica', 'Dunavska 1');
insert into addresses (id, country, city, street) values (4, 'Srbija', 'Loznica', 'Drinska 84');

insert into tags (id, name, description) values (1, 'WiFi', 'WiFi access');
insert into tags (id, name, description) values (2, 'Garage', 'Parking spot for your car');
insert into tags (id, name, description) values (3, 'Heating', 'You cna heat up the room if you feel cold');
insert into tags (id, name, description) values (4, 'Kitchen', 'You can prepare food');
insert into tags (id, name, description) values (5, 'Shower', 'You can wash yourself');
insert into tags (id, name, description) values (6, 'Barbeque', 'Barbeque with your friends');
insert into tags (id, name, description) values (7, 'Drinking water', 'You do not need to bring your own water');
insert into tags (id, name, description) values (8, 'Bathroom', 'Fully equipped bathroom');

insert into users (id, email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values (1, 'matijazaric9@gmail.com', 'Matija', 'Zaric', 4, '0657842312', true, false, 0, 0);
insert into users (id, email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values (2, 'sakal.teodor@gmail.com', 'Teodor', 'Sakal Franciskovic', 1, '0636742312', true, false, 0, 0);
insert into users (id, email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values (3, 'lordje@gmail.com', 'Djordje', 'Vajagic', 2, '0617647812', true, false, 0, 0);
insert into users (id, email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values (4, 'zoki@gmail.com', 'Zoran', 'Bukorac', 3, '0607846912', true, false, 0, 0);
insert into users (id, email, password, name, surname, address_id, phone_number, is_active, is_deleted, loyalty_points, loyalty_status) values (5, 'klijent@gmail.com', 'Klijent', 'Klijentic', 2, '0667342312', true, false, 0, 0);

insert into services (id, name, description, price, owner_id, address_id, is_deleted) values (1, 'Brvnara na Dunavu', 'Lepo mesto pored reke za kvalitetan odmor. Pogodno za zurke, proslave i drizenja sa prijateljima.', 50, 1, 3, false);
insert into services (id, name, description, price, owner_id, address_id, is_deleted) values (2, 'Nojeva barka', 'Brod za ljude sa dubokim dzepom.', 500, 2, 1, false);
insert into services (id, name, description, price, owner_id, address_id, is_deleted) values (3, 'Instrukcije pecanja', 'Naucite da pecate. Strpljiv i veoma prijatan instruktor', 30, 3, 3, false);

insert into subscriptions (client_id, subscription_id) values (5, 2);

insert into ships (id, owner_id, type, length, engine_num, engine_power, capacity, max_speed, reservation_cancellation_conditions) values (2, 2, 'Brod', 55, 'A123B5', 200, 100, '50 km/h', 'Prilikom otkaza rezervacije, vlasniku se uplacuje 5% cene');

insert into ship_owners (id) values (2);

insert into rating (id, service_rating, user_rating) values (1, 5, 5);

insert into reservations (id, from_date, to_date, price, status, rating_id, service_id, client_id) values (1, 1623484800000, 1623657600000, 1500, 1, 1, 2, 2);


