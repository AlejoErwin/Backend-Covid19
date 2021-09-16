drop database covid_19_DB;
create database covid_19_DB;
USE covid_19_DB;

CREATE TABLE City (
    id_city int NOT NULL AUTO_INCREMENT,
    city varchar(100) NOT NULL,
    longitude numeric(15,10) NOT NULL,
    latitude numeric(15,10) NOT NULL,
    status int NOT NULL,
    CONSTRAINT City_pk PRIMARY KEY (id_city)
);

-- Table: City_covid_data
CREATE TABLE City_covid_data (
    id_city_covid_data int NOT NULL,
    id_city int NOT NULL,
    id_covid_data int NOT NULL,
    status int NOT NULL,
    CONSTRAINT City_covid_data_pk PRIMARY KEY (id_city_covid_data)
);

-- Table: Country
CREATE TABLE Country (
    id_country int NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    longitude float NOT NULL,
    latitude float NOT NULL,
    status int NOT NULL,
    tx_id int NOT NULL,
    tx_date datetime NOT NULL,
    tx_host varchar(100) NOT NULL,
    tx_update datetime NOT NULL,
    CONSTRAINT Country_pk PRIMARY KEY (id_country)
);

-- Table: Country_covid_data
CREATE TABLE Country_covid_data (
    id_city_covid_data int NOT NULL,
    status int NOT NULL,
    tx_id int NOT NULL,
    tx_date datetime NOT NULL,
    tx_host varchar(100) NOT NULL,
    tx_update datetime NOT NULL,
    id_country int NOT NULL,
    id_covid_data int NOT NULL,
    CONSTRAINT Country_covid_data_pk PRIMARY KEY (id_city_covid_data)
);

-- Table: Covid_data
CREATE TABLE Covid_data (
    id_covid_data int NOT NULL AUTO_INCREMENT,
    id_page_url int NOT NULL,
    death_cases int NOT NULL,
    confirmed_cases int NOT NULL,
    vaccinated int NOT NULL,
    cumulative_cases int NOT NULL,
    recuperated int NOT NULL,
    date datetime NOT NULL,
    status int NOT NULL,
    CONSTRAINT Covid_data_pk PRIMARY KEY (id_covid_data)
);

-- Table: Drugstore
CREATE TABLE Drugstore (
    id_drugstore int NOT NULL,
    id_city int NOT NULL,
    name varchar(50) NOT NULL,
    lon float NOT NULL,
    lat float NOT NULL,
    status int NOT NULL,
    CONSTRAINT Drugstore_pk PRIMARY KEY (id_drugstore)
);

-- Table: Hospital
CREATE TABLE Hospital (
    id_hospital int NOT NULL,
    id_city int NOT NULL,
    name varchar(50) NOT NULL,
    lon float NOT NULL,
    lat float NOT NULL,
    status int NOT NULL,
    CONSTRAINT Hospital_pk PRIMARY KEY (id_hospital)
);

-- Table: Municipality
CREATE TABLE Municipality (
    id_municipality int NOT NULL AUTO_INCREMENT,
    id_city int NOT NULL,
    municipality varchar(100) NOT NULL,
    longitude float NOT NULL,
    latitude float NOT NULL,
    status int NOT NULL,
    CONSTRAINT Municipality_pk PRIMARY KEY (id_municipality)
);

-- Table: Municipality_covid_data
CREATE TABLE Municipality_covid_data (
    id_municipality_covid_data int NOT NULL,
    id_municipality int NOT NULL,
    id_covid_data int NOT NULL,
    status int NOT NULL,
    CONSTRAINT Municipality_covid_data_pk PRIMARY KEY (id_municipality_covid_data)
);

-- Table: Page_url
CREATE TABLE Page_url (
    id_page_url int NOT NULL AUTO_INCREMENT,
    url varchar(300) NOT NULL,
    status int NOT NULL,
    CONSTRAINT Page_url_pk PRIMARY KEY (id_page_url)
);

-- Table: User
CREATE TABLE User (
    id_user int NOT NULL AUTO_INCREMENT,
    user_name varchar(100) NOT NULL,
    last_name varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    password varchar(60) NOT NULL,
    status int NOT NULL,
    CONSTRAINT id_user PRIMARY KEY (id_user)
);

-- foreign keys
-- Reference: Country_covid_data_Country (table: Country_covid_data)
ALTER TABLE Country_covid_data ADD CONSTRAINT Country_covid_data_Country FOREIGN KEY Country_covid_data_Country (id_country)
    REFERENCES Country (id_country);

-- Reference: Country_covid_data_Covid_data (table: Country_covid_data)
ALTER TABLE Country_covid_data ADD CONSTRAINT Country_covid_data_Covid_data FOREIGN KEY Country_covid_data_Covid_data (id_covid_data)
    REFERENCES Covid_data (id_covid_data);

-- Reference: Covid_data_Page_url (table: Covid_data)
ALTER TABLE Covid_data ADD CONSTRAINT Covid_data_Page_url FOREIGN KEY Covid_data_Page_url (id_page_url)
    REFERENCES Page_url (id_page_url);

-- Reference: Drugstore_City (table: Drugstore)
ALTER TABLE Drugstore ADD CONSTRAINT Drugstore_City FOREIGN KEY Drugstore_City (id_city)
    REFERENCES City (id_city);

-- Reference: Hospital_City (table: Hospital)
ALTER TABLE Hospital ADD CONSTRAINT Hospital_City FOREIGN KEY Hospital_City (id_city)
    REFERENCES City (id_city);

-- Reference: Municipality_City (table: Municipality)
ALTER TABLE Municipality ADD CONSTRAINT Municipality_City FOREIGN KEY Municipality_City (id_city)
    REFERENCES City (id_city);

-- Reference: Table_11_Covid_data (table: Municipality_covid_data)
ALTER TABLE Municipality_covid_data ADD CONSTRAINT Table_11_Covid_data FOREIGN KEY Table_11_Covid_data (id_covid_data)
    REFERENCES Covid_data (id_covid_data);

-- Reference: Table_11_Municipality (table: Municipality_covid_data)
ALTER TABLE Municipality_covid_data ADD CONSTRAINT Table_11_Municipality FOREIGN KEY Table_11_Municipality (id_municipality)
    REFERENCES Municipality (id_municipality);

-- Reference: Table_25_City (table: City_covid_data)
ALTER TABLE City_covid_data ADD CONSTRAINT Table_25_City FOREIGN KEY Table_25_City (id_covid_data)
    REFERENCES City (id_city);

-- Reference: Table_25_Covid_data (table: City_covid_data)
ALTER TABLE City_covid_data ADD CONSTRAINT Table_25_Covid_data FOREIGN KEY Table_25_Covid_data (id_city)
    REFERENCES Covid_data (id_covid_data);

-- End of file.
