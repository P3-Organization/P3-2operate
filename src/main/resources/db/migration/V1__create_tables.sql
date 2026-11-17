CREATE TABLE electricity_consumption (
    date TIMESTAMP PRIMARY KEY,
    kwh DOUBLE PRECISION NOT NULL,
    kr_total DOUBLE PRECISION NOT NULL,
    min_kr_total DOUBLE PRECISION NOT NULL
);

CREATE TABLE temp_rooms (
    time TIMESTAMP NOT NULL,
    humidity DOUBLE PRECISION NOT NULL,
    hoa_humidity DOUBLE PRECISION,
    temperature DOUBLE PRECISION,
    hoa_temperature DOUBLE PRECISION,
    signal_temperature TEXT
);


CREATE TABLE rooms (
    room_id INT NOT NULL,
    room_name TEXT NOT NULL,
    time TIMESTAMP NOT NULL,
    humidity DOUBLE PRECISION NOT NULL,
    hoa_humidity DOUBLE PRECISION,
    temperature DOUBLE PRECISION NOT NULL ,
    hoa_temperature DOUBLE PRECISION,
    signal_temperature DOUBLE PRECISION
);