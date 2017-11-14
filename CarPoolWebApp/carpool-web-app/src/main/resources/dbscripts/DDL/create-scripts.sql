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
