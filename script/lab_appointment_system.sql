

create database lab_appointment_system

use lab_appointment_system


CREATE TABLE Patients (
    PatientID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    DateOfBirth DATE,
    Gender ENUM('Male', 'Female', 'Other'),
    ContactNumber VARCHAR(20),
    Email VARCHAR(255),
    Address VARCHAR(255),
    UserName VARCHAR(255),
    Password VARCHAR(255)
);

CREATE TABLE Admin (
    Address VARCHAR(255),
    UserName VARCHAR(255),
    Password VARCHAR(255)
);

CREATE TABLE Tests (
    TestID INT PRIMARY KEY AUTO_INCREMENT,
    TestName VARCHAR(255),
    Description TEXT,
    Price DECIMAL(10, 2)
);

CREATE TABLE Technicians (
    TechnicianID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    ContactNumber VARCHAR(20),
    Email VARCHAR(255),
    UserName VARCHAR(255),
    Password VARCHAR(255)
);

CREATE TABLE Doctors (
    DoctorID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    ContactNumber VARCHAR(20),
    Email VARCHAR(255)
);

-- CREATE TABLE Appointments (
--     AppointmentID INT PRIMARY KEY AUTO_INCREMENT,
--     PatientID INT,
--     TestID INT,
--     TechnicianID INT,
--     DoctorID INT,
--     AppointmentDateTime DATETIME,
--     FOREIGN KEY (PatientID) REFERENCES Patients(PatientID),
--     FOREIGN KEY (TestID) REFERENCES Tests(TestID),
--     FOREIGN KEY (TechnicianID) REFERENCES Technicians(TechnicianID),
--     FOREIGN KEY (DoctorID) REFERENCES Doctors(DoctorID)
-- );

CREATE TABLE Appointments (
    AppointmentID INT PRIMARY KEY AUTO_INCREMENT,
    PatientID INT,
    TestID INT,
    TechnicianID INT,
    DoctorID INT,
    AppointmentDateTime DATETIME,
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID),
    FOREIGN KEY (TestID) REFERENCES Tests(TestID),
    FOREIGN KEY (DoctorID) REFERENCES Doctors(DoctorID)
);

CREATE TABLE Payments (
    PaymentID INT PRIMARY KEY AUTO_INCREMENT,
    PatientID INT,
    Amount DECIMAL(10, 2),
    PaymentDateTime DATETIME,
    PaymentMethod VARCHAR(255),
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID)
);

CREATE TABLE Reports (
    ReportID INT PRIMARY KEY AUTO_INCREMENT,
    PatientID INT,
    TestID INT,
    ReportDate DATETIME,
    ReportFile BLOB,
    ReportDetails TEXT,
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID),
    FOREIGN KEY (TestID) REFERENCES Tests(TestID)
);









