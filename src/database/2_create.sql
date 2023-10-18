CREATE TABLE location (
                          id serial NOT NULL PRIMARY KEY,
                          description varchar(255) NOT NULL,
                          status varchar(3) NOT NULL,
                          latitude double precision NOT NULL CHECK (latitude BETWEEN -90 AND 90),
                          longitude double precision NOT NULL CHECK (longitude BETWEEN -180 AND 180)
);
