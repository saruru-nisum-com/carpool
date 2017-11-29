
CREATE TABLE UserRole (
  roleId int,
  role text ,
  createdDate timestamp ,
  PRIMARY KEY (roleId)
) ;

INSERT INTO UserRole (roleid,role,createdDate) VALUES (1,'Admin', '2017-11-13');
INSERT INTO UserRole (roleid,role,createdDate) VALUES (2,'User', '2017-11-13');

CREATE TABLE User (
  userId int PRIMARY KEY,
  emailid text  ,
  username text ,
  loginDate timestamp ,
  activeStatus text ,
  roleId int ,
 notifications text,
 profilename text,
 image blob,
 createdate timestamp
 

);

Create index carpool.emailIndex on User(emailId);

CREATE TABLE cp_vehicletypes (
   id int, 
   vehicletype text,
   noofseats int,
  PRIMARY KEY(id)
);

insert into cp_vehicletypes (id, vehicletype, noofseats) values (1, 'Two Wheeler',1);
insert into cp_vehicletypes (id, vehicletype, noofseats) values (2, 'Four Wheeler',3);


CREATE TABLE cp_userregistration (
   registrationid int, 
   userid text,
   vehicletype list<int>,
   location text,
   latitude text,
   longitude text,
   nearby text,
   mobile text,
   emailnotification boolean,
   isrider int,
   createddate timestamp,
   modifieddate timestamp,
PRIMARY KEY(registrationid)
);

CREATE TABLE cp_carpooldetails (
   id int PRIMARY KEY,
   parentid int,
   userid text,
   vehicletype int,
   noofseats int,
   fromdate text,
   todate text,
   fromtime text,
   totime text,
   status int,
   createddate timestamp,
   modifieddate timestamp
);

------ Version II ----------

drop table cp_carpoolriderdetails;
CREATE TABLE cp_carpoolriderdetails (
   id int PRIMARY KEY,
   cpid int,
   emailid text,
   status int,
   reason int,
   notifyme boolean,
   rewards double,
   createddate timestamp,
   modifieddate timestamp
    
);
alter table cp_userregistration add  gender int;
alter table cp_userregistration drop userid;
alter table cp_userregistration add emailid text;
alter table cp_carpooldetails drop userid;
alter table cp_carpooldetails add emailid text;
alter table cp_carpooldetails add rewards double;

------ Version III ----------

alter table cp_carpoolriderdetails drop notifyme;

CREATE TABLE cp_carpoolridernotifications (
   id int PRIMARY KEY,
   cpid int,
   emailid text,
   notified boolean
   );


alter table cp_carpooldetails add location text;
alter table cp_carpoolriderdetails add location text;


 
