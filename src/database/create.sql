CREATE TABLE location (
                          id serial NOT NULL,
                          description varchar(255) NOT NULL,
                          coordinates double precision[] NOT NULL CHECK (array_length(coordinates, 1) = 2),
                          CONSTRAINT location_pk PRIMARY KEY (id),
                          CONSTRAINT chk_coordinates CHECK (coordinates[1] BETWEEN -90 AND 90 AND coordinates[2] BETWEEN -180 AND 180)
);
