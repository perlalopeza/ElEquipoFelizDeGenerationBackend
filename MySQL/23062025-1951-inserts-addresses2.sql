-- Agregar 5 direcciones a user_addresses
INSERT INTO user_addresses (street_and_number, neighborhood, zip_code, town, state, users_id)
VALUES
('Av. Constituyentes 1000', 'Lomas Altas', '11950', 'Ciudad de México', 'CDMX', 1),
('Calle 16 de Septiembre #89', 'Centro', '44100', 'Guadalajara', 'Jalisco', 1),
('Prolongación Paseo de Montejo 456', 'Itzimná', '97100', 'Mérida', 'Yucatán', 2),
('Av. Benito Juárez 234', 'Centro', '64000', 'Monterrey', 'Nuevo León', 2),
('Blvd. Manuel Ávila Camacho 765', 'San Rafael', '11000', 'Naucalpan', 'Estado de México', 1);
SELECT * FROM user_addresses;