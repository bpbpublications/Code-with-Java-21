CREATE TABLE weather_by_station_by_month (
    station_id TEXT,
    month_bucket INT,
    reading_timestamp TIMESTAMP,
    reading_icon TEXT,
    station_coordinates_lat FLOAT,
    station_coordinates_lon FLOAT,
    temperature_c FLOAT,
    dewpoint_c FLOAT,
    wind_direction_deg INT,
    wind_speed_kmh FLOAT,
    wind_gust_kmh FLOAT,
    visibility_m INT,
    precipitation_last_hour FLOAT,
    precipitation_last_3_hours FLOAT,
    precipitation_last_6_hours FLOAT,
    relative_humidity_c FLOAT,
    wind_chill_c FLOAT,
    head_index_c FLOAT,
    cloud_cover MAP<INT,TEXT>,
    PRIMARY KEY ((station_id,month_bucket),reading_timestamp);
) WITH CLUSTERING ORDER BY (reading_timestamp DESC);

