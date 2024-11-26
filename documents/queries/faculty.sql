-- Insert Users for CS Department
INSERT INTO User (userID, password, name, email, phoneNumber, userType) VALUES
('11', 'pass', 'Dr. John Smith', 'john.smith.cs@example.com', '123-456-7890', 'Faculty'),
('12', 'pass', 'Dr. Emily Johnson', 'emily.johnson.cs@example.com', '123-456-7891', 'Faculty'),
('13', 'pass', 'Dr. Michael Brown', 'michael.brown.cs@example.com', '123-456-7892', 'Faculty'),
('14', 'pass', 'Mr. David Williams', 'david.williams.cs@example.com', '123-456-7893', 'Faculty'),
('15', 'pass', 'Dr. Sarah Jones', 'sarah.jones.cs@example.com', '123-456-7894', 'Faculty'),
('16', 'pass', 'Dr. Robert Miller', 'robert.miller.cs@example.com', '123-456-7895', 'Faculty'),
('17', 'pass', 'Dr. Laura Wilson', 'laura.wilson.cs@example.com', '123-456-7896', 'Faculty'),
('18', 'pass', 'Mr. James Moore', 'james.moore.cs@example.com', '123-456-7897', 'Faculty'),
('19', 'pass', 'Dr. William Taylor', 'william.taylor.cs@example.com', '123-456-7898', 'Faculty'),
('20', 'pass', 'Dr. Linda Anderson', 'linda.anderson.cs@example.com', '123-456-7899', 'Faculty');

-- Insert Users for SE Department
INSERT INTO User (userID, password, name, email, phoneNumber, userType) VALUES
('21', 'pass', 'Dr. Alan Thomas', 'alan.thomas.se@example.com', '234-567-8901', 'Faculty'),
('22', 'pass', 'Dr. Nancy Clark', 'nancy.clark.se@example.com', '234-567-8902', 'Faculty'),
('23', 'pass', 'Dr. Patrick Lewis', 'patrick.lewis.se@example.com', '234-567-8903', 'Faculty'),
('24', 'pass', 'Mr. Carol Hall', 'carol.hall.se@example.com', '234-567-8904', 'Faculty'),
('25', 'pass', 'Dr. Thomas Young', 'thomas.young.se@example.com', '234-567-8905', 'Faculty'),
('26', 'pass', 'Dr. Margaret Walker', 'margaret.walker.se@example.com', '234-567-8906', 'Faculty'),
('27', 'pass', 'Dr. Steven Wright', 'steven.wright.se@example.com', '234-567-8907', 'Faculty'),
('28', 'pass', 'Mr. Alice King', 'alice.king.se@example.com', '234-567-8908', 'Faculty'),
('29', 'pass', 'Dr. Christopher Lee', 'christopher.lee.se@example.com', '234-567-8909', 'Faculty'),
('30', 'pass', 'Dr. Sophia Harris', 'sophia.harris.se@example.com', '234-567-8910', 'Faculty');

-- Insert Users for AI Department
INSERT INTO User (userID, password, name, email, phoneNumber, userType) VALUES
('31', 'pass', 'Dr. James Carter', 'james.carter.ai@example.com', '345-678-9011', 'Faculty'),
('32', 'pass', 'Dr. Olivia Mitchell', 'olivia.mitchell.ai@example.com', '345-678-9012', 'Faculty'),
('33', 'pass', 'Dr. Daniel Perez', 'daniel.perez.ai@example.com', '345-678-9013', 'Faculty'),
('34', 'pass', 'Mr. Sophia Scott', 'sophia.scott.ai@example.com', '345-678-9014', 'Faculty'),
('35', 'pass', 'Dr. Benjamin Adams', 'benjamin.adams.ai@example.com', '345-678-9015', 'Faculty'),
('36', 'pass', 'Dr. Rachel Green', 'rachel.green.ai@example.com', '345-678-9016', 'Faculty'),
('37', 'pass', 'Dr. Michael Evans', 'michael.evans.ai@example.com', '345-678-9017', 'Faculty'),
('38', 'pass', 'Mr. Emily Carter', 'emily.carter.ai@example.com', '345-678-9018', 'Faculty'),
('39', 'pass', 'Dr. John Foster', 'john.foster.ai@example.com', '345-678-9019', 'Faculty'),
('40', 'pass', 'Dr. Elizabeth King', 'elizabeth.king.ai@example.com', '345-678-9020', 'Faculty');

-- Faculty Members for CS Department
INSERT INTO Faculty (userID, department, designation, joinDate) VALUES
('11', 'CS', 'Professor', '2010-08-15'),
('12', 'CS', 'Associate Professor', '2012-07-20'),
('13', 'CS', 'Assistant Professor', '2014-05-10'),
('14', 'CS', 'Lecturer', '2016-09-01'),
('15', 'CS', 'Professor', '2008-03-12'),
('16', 'CS', 'Associate Professor', '2011-11-23'),
('17', 'CS', 'Assistant Professor', '2015-06-18'),
('18', 'CS', 'Lecturer', '2017-02-25'),
('19', 'CS', 'Professor', '2005-07-19'),
('20', 'CS', 'Assistant Professor', '2019-01-30');

-- Faculty Members for SE Department
INSERT INTO Faculty (userID, department, designation, joinDate) VALUES
('21', 'SE', 'Professor', '2009-10-11'),
('22', 'SE', 'Associate Professor', '2013-06-24'),
('23', 'SE', 'Assistant Professor', '2016-01-14'),
('24', 'SE', 'Lecturer', '2018-04-05'),
('25', 'SE', 'Professor', '2007-12-17'),
('26', 'SE', 'Associate Professor', '2012-02-28'),
('27', 'SE', 'Assistant Professor', '2014-08-09'),
('28', 'SE', 'Lecturer', '2017-03-19'),
('29', 'SE', 'Professor', '2006-05-30'),
('30', 'SE', 'Assistant Professor', '2020-07-11');

-- Faculty Members for AI Department
INSERT INTO Faculty (userID, department, designation, joinDate) VALUES
('31', 'AI', 'Professor', '2008-11-10'),
('32', 'AI', 'Associate Professor', '2011-03-22'),
('33', 'AI', 'Assistant Professor', '2014-07-04'),
('34', 'AI', 'Lecturer', '2016-05-14'),
('35', 'AI', 'Professor', '2007-09-13'),
('36', 'AI', 'Associate Professor', '2010-06-18'),
('37', 'AI', 'Assistant Professor', '2013-01-26'),
('38', 'AI', 'Lecturer', '2015-11-05'),
('39', 'AI', 'Professor', '2006-08-25'),
('40', 'AI', 'Assistant Professor', '2018-09-09');

-- Assign Courses for CS Department
-- Faculty 11
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('11', 'CS101'), ('11', 'CS102'), ('11', 'CS103'), ('11', 'CS104');
-- Faculty 12
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('12', 'CS105'), ('12', 'CS201'), ('12', 'CS202'), ('12', 'CS203');
-- Faculty 13
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('13', 'CS204'), ('13', 'CS205'), ('13', 'CS301'), ('13', 'CS302');
-- Faculty 14
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('14', 'CS303'), ('14', 'CS304'), ('14', 'CS305'), ('14', 'CS401');
-- Faculty 15
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('15', 'CS402'), ('15', 'CS403'), ('15', 'CS404'), ('15', 'CS405');
-- Faculty 16
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('16', 'CS501'), ('16', 'CS502'), ('16', 'CS503'), ('16', 'CS504');
-- Faculty 17
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('17', 'CS505'), ('17', 'CS601'), ('17', 'CS602'), ('17', 'CS603');
-- Faculty 18
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('18', 'CS604'), ('18', 'CS605'), ('18', 'CS701'), ('18', 'CS702');
-- Faculty 19
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('19', 'CS703'), ('19', 'CS704'), ('19', 'CS705'), ('19', 'CS801');
-- Faculty 20
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('20', 'CS802'), ('20', 'CS803'), ('20', 'CS804'), ('20', 'CS805');

-- Assign Courses for SE Department
-- Faculty 21
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('21', 'SE101'), ('21', 'SE102'), ('21', 'SE103'), ('21', 'SE104');
-- Faculty 22
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('22', 'SE105'), ('22', 'SE201'), ('22', 'SE202'), ('22', 'SE203');
-- Faculty 23
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('23', 'SE204'), ('23', 'SE205'), ('23', 'SE301'), ('23', 'SE302');
-- Faculty 24
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('24', 'SE303'), ('24', 'SE304'), ('24', 'SE305'), ('24', 'SE401');
-- Faculty 25
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('25', 'SE402'), ('25', 'SE403'), ('25', 'SE404'), ('25', 'SE405');
-- Faculty 26
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('26', 'SE501'), ('26', 'SE502'), ('26', 'SE503'), ('26', 'SE504');
-- Faculty 27
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('27', 'SE505'), ('27', 'SE601'), ('27', 'SE602'), ('27', 'SE603');
-- Faculty 28
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('28', 'SE604'), ('28', 'SE605'), ('28', 'SE701'), ('28', 'SE702');
-- Faculty 29
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('29', 'SE703'), ('29', 'SE704'), ('29', 'SE705'), ('29', 'SE801');
-- Faculty 30
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('30', 'SE802'), ('30', 'SE803'), ('30', 'SE804'), ('30', 'SE805');

-- Assign Courses for AI Department
-- Faculty 31
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('31', 'AI101'), ('31', 'AI102'), ('31', 'AI103'), ('31', 'AI104');
-- Faculty 32
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('32', 'AI105'), ('32', 'AI201'), ('32', 'AI202'), ('32', 'AI203');
-- Faculty 33
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('33', 'AI204'), ('33', 'AI205'), ('33', 'AI301'), ('33', 'AI302');
-- Faculty 34
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('34', 'AI303'), ('34', 'AI304'), ('34', 'AI305'), ('34', 'AI401');
-- Faculty 35
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('35', 'AI402'), ('35', 'AI403'), ('35', 'AI404'), ('35', 'AI405');
-- Faculty 36
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('36', 'AI501'), ('36', 'AI502'), ('36', 'AI503'), ('36', 'AI504');
-- Faculty 37
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('37', 'AI505'), ('37', 'AI601'), ('37', 'AI602'), ('37', 'AI603');
-- Faculty 38
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('38', 'AI604'), ('38', 'AI605'), ('38', 'AI701'), ('38', 'AI702');
-- Faculty 39
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('39', 'AI703'), ('39', 'AI704'), ('39', 'AI705'), ('39', 'AI801');
-- Faculty 40
INSERT INTO FacultyCourse (facultyID, courseID) VALUES ('40', 'AI802'), ('40', 'AI803'), ('40', 'AI804'), ('40', 'AI805');