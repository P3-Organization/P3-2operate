/*
This file takes each csv into a temp file, then copies that into the actual room table.
This is because there are no IDs in the CSV, so wee need to insert a room ID for each csv.
We also convert the signal_temperature to a double, by recplacing , with .
After each table be Truncate to not insert the same data twice.

All of this is neccesarry so each CSV has a different room ID.

 */

 --Fjordstue
COPY temp_rooms (time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
    FROM '/csv/RoomCSV/Fjordstue.csv'
    WITH(FORMAT csv, HEADER true);

INSERT INTO rooms(room_id, room_name, time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
SELECT 1, 'Fjordstue', time, humidity, hoa_humidity, temperature, hoa_temperature, REPLACE("signal_temperature", ',', '.')::DOUBLE PRECISION
FROM temp_rooms;

TRUNCATE TABLE temp_rooms;

--Hall nord
COPY temp_rooms (time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
    FROM '/csv/RoomCSV/HallNord.csv'
    WITH(FORMAT csv, HEADER true);

INSERT INTO rooms(room_id, room_name, time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
SELECT 2, 'Hall nord', time, humidity, hoa_humidity, temperature, hoa_temperature, REPLACE("signal_temperature", ',', '.')::DOUBLE PRECISION
FROM temp_rooms;

TRUNCATE TABLE temp_rooms;

--Hall Syd
COPY temp_rooms (time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
    FROM '/csv/RoomCSV/HallSyd.csv'
    WITH(FORMAT csv, HEADER true);

INSERT INTO rooms(room_id, room_name, time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
SELECT 3, 'Hall syd', time, humidity, hoa_humidity, temperature, hoa_temperature, REPLACE("signal_temperature", ',', '.')::DOUBLE PRECISION
FROM temp_rooms;

TRUNCATE TABLE temp_rooms;

--Restaurant nord
COPY temp_rooms (time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
    FROM '/csv/RoomCSV/RestarantNord.csv'
    WITH(FORMAT csv, HEADER true);

INSERT INTO rooms(room_id, room_name, time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
SELECT 4, 'Restaurant nord', time, humidity, hoa_humidity, temperature, hoa_temperature, REPLACE("signal_temperature", ',', '.')::DOUBLE PRECISION
FROM temp_rooms;

TRUNCATE TABLE temp_rooms;

--Restaurant syd
COPY temp_rooms (time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
    FROM '/csv/RoomCSV/RestarantSyd.csv'
    WITH(FORMAT csv, HEADER true);

INSERT INTO rooms(room_id, room_name,time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
SELECT 5, 'Restaurant syd', time, humidity, hoa_humidity, temperature, hoa_temperature, REPLACE("signal_temperature", ',', '.')::DOUBLE PRECISION
FROM temp_rooms;

TRUNCATE TABLE temp_rooms;

--Room 49
COPY temp_rooms (time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
    FROM '/csv/RoomCSV/Room49.csv'
    WITH(FORMAT csv, HEADER true);

INSERT INTO rooms(room_id, room_name,time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
SELECT 6, 'Room 49', time, humidity, hoa_humidity, temperature, hoa_temperature, REPLACE("signal_temperature", ',', '.')::DOUBLE PRECISION
FROM temp_rooms;

TRUNCATE TABLE temp_rooms;

--Room 50
COPY temp_rooms (time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
    FROM '/csv/RoomCSV/Room50.csv'
    WITH(FORMAT csv, HEADER true);

INSERT INTO rooms(room_id, room_name,time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
SELECT 7, 'Room 50', time, humidity, hoa_humidity, temperature, hoa_temperature, REPLACE("signal_temperature", ',', '.')::DOUBLE PRECISION
FROM temp_rooms;

TRUNCATE TABLE temp_rooms;

--Room 61
COPY temp_rooms (time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
    FROM '/csv/RoomCSV/Room61.csv'
    WITH(FORMAT csv, HEADER true);

INSERT INTO rooms(room_id, room_name,time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
SELECT 8, 'Room 61', time, humidity, hoa_humidity, temperature, hoa_temperature, REPLACE("signal_temperature", ',', '.')::DOUBLE PRECISION
FROM temp_rooms;

TRUNCATE TABLE temp_rooms;

--Room 62
COPY temp_rooms (time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
    FROM '/csv/RoomCSV/Room62.csv'
    WITH(FORMAT csv, HEADER true);

INSERT INTO rooms(room_id, room_name,time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
SELECT 9,'Room 62', time, humidity, hoa_humidity, temperature, hoa_temperature, REPLACE("signal_temperature", ',', '.')::DOUBLE PRECISION
FROM temp_rooms;

TRUNCATE TABLE temp_rooms;

--Room 63
COPY temp_rooms (time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
    FROM '/csv/RoomCSV/Room63.csv'
    WITH(FORMAT csv, HEADER true);

INSERT INTO rooms(room_id, room_name,time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
SELECT 10, 'Room 63',time, humidity, hoa_humidity, temperature, hoa_temperature, REPLACE("signal_temperature", ',', '.')::DOUBLE PRECISION
FROM temp_rooms;

TRUNCATE TABLE temp_rooms;

--Room 64
COPY temp_rooms (time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
    FROM '/csv/RoomCSV/Room64.csv'
    WITH(FORMAT csv, HEADER true);

INSERT INTO rooms(room_id, room_name,time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
SELECT 11, 'Room 64', time, humidity, hoa_humidity, temperature, hoa_temperature, REPLACE("signal_temperature", ',', '.')::DOUBLE PRECISION
FROM temp_rooms;

TRUNCATE TABLE temp_rooms;

--StortKøkken
COPY temp_rooms (time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
    FROM '/csv/RoomCSV/StortKøkken.csv'
    WITH(FORMAT csv, HEADER true);

INSERT INTO rooms(room_id, room_name,time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
SELECT 12, 'Stort køkken',time, humidity, hoa_humidity, temperature, hoa_temperature, REPLACE("signal_temperature", ',', '.')::DOUBLE PRECISION
FROM temp_rooms;

TRUNCATE TABLE temp_rooms;

--Sydfløj
COPY temp_rooms (time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
    FROM '/csv/RoomCSV/Sydfløj.csv'
    WITH(FORMAT csv, HEADER true);

INSERT INTO rooms(room_id, room_name,time, humidity, hoa_humidity, temperature, hoa_temperature, signal_temperature)
SELECT 13, 'Sydfløj', time, humidity, hoa_humidity, temperature, hoa_temperature, REPLACE("signal_temperature", ',', '.')::DOUBLE PRECISION
FROM temp_rooms;

TRUNCATE TABLE temp_rooms;
