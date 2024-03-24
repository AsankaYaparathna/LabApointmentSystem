

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

INSERT INTO patients (FirstName,LastName,DateOfBirth,Gender,ContactNumber,Email,Address,UserName,Password) VALUES
	 ('John','Doe','1990-01-01','Male','1234567890','john.doe@example.com','123 Main St','johndoe','ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb'),
	 ('Bob','Harizon','1996-02-11','Male','0756019387','bob@gmail.com','1040, Himbutuwa Barawardhana Oya.','bob','ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb'),
	 ('Asanka','Yaparathna','1996-10-21','Male','0756019387','asankayaparathna50@gmail.com','1040, Himbutuwa Barawardhana Oya.','asankay','ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb'),
	 ('Thilanka','Madushan','2000-03-28','Male','0761847718','tmyaparathna@gmail.com','1040, Himbutuwa Barawardhana Oya.','thilanka','ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb');


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

INSERT INTO tests (TestName,Description,Price) VALUES
	 ('Full Blood Count','This is full blod count',5000.00),
	 ('Test 01','Test 01',450.00),
	 ('Test 02','Test 02',1000.00);


CREATE TABLE Technicians (
    TechnicianID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    ContactNumber VARCHAR(20),
    Email VARCHAR(255),
    UserName VARCHAR(255),
    Password VARCHAR(255)
);

INSERT INTO technicians (FirstName,LastName,ContactNumber,Email,UserName,Password) VALUES
	 ('John','Doe','123-456-7890','john.doe@example.com','johnd','ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb'),
	 ('Jane','Smith','987-654-3210','jane.smith@example.com','janes','ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb');

CREATE TABLE Doctors (
    DoctorID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    ContactNumber VARCHAR(20),
    Email VARCHAR(255)
);

INSERT INTO doctors (FirstName,LastName,ContactNumber,Email) VALUES
	 ('John','Smith','0726819387','johns@gmail.com'),
	 ('Doctor','01','0756019387','doc1@gmail.com'),
	 ('Doctor','02','0756019387','doc2@gmail.com');


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

INSERT INTO appointments (PatientID,TestID,TechnicianID,DoctorID,AppointmentDateTime) VALUES
	 (2,1,0,5,'2024-03-17 17:00:00'),
	 (3,1,0,1,'2024-04-01 08:00:00'),
	 (1,5,0,1,'2024-04-05 15:00:00'),
	 (2,1,0,1,'2024-04-10 14:00:00');


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
    AppointmentID INT,
    ReportDetails TEXT,
    FOREIGN KEY (AppointmentID) REFERENCES Appointments(AppointmentID)
);


INSERT INTO reports (AppointmentID,ReportDetails) VALUES
	 (1,'<p style="text-align: right;"><strong>03/24/2024</strong></p>
<p style="text-align: right;"><strong>Report for Full Blood Count</strong></p>
<table style="border-collapse: collapse; width: 99.9828%; height: 72px;" border="1"><colgroup><col style="width: 4.21614%;"><col style="width: 10.1532%;"><col style="width: 29.8439%;"><col style="width: 55.7696%;"></colgroup>
<tbody>
<tr style="height: 36px;">
<td>#</td>
<td>Type</td>
<td>Value</td>
<td>Description</td>
</tr>
<tr style="height: 36px;">
<td>01</td>
<td>A+</td>
<td>0.5gsm</td>
<td>This is test description of full blood count</td>
</tr>
</tbody>
</table>
<p><em><strong>Updated</strong></em></p>');







