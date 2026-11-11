COPY electricity_price (date, kwh, kr_total, min_kr_total)
FROM '/csv/energy_consumtions.csv'
WITH(FORMAT csv, HEADER true)