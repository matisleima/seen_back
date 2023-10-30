CREATE TABLE public.location (
                                 id SERIAL NOT NULL PRIMARY KEY,
                                 description VARCHAR(255) NOT NULL,
                                 status CHAR(3) NOT NULL,
                                 latitude DOUBLE NOT NULL CHECK (latitude BETWEEN -90 AND 90),
                                 longitude DOUBLE NOT NULL CHECK (longitude BETWEEN -180 AND 180)
);
