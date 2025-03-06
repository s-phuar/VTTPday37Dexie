
use rsvp;

CREATE TABLE `posts` (
  `post_id` VARCHAR(8) NOT NULL,
  `comments` MEDIUMTEXT NULL,
  `picture` MEDIUMBLOB NULL,
  PRIMARY KEY (`post_id`));
  
  
  select * from posts;
  
drop table cities;

CREATE TABLE `cities` (
  `code` VARCHAR(5) NOT NULL,
  `city_name` VARCHAR(100) NULL,
  PRIMARY KEY (`code`));
  
INSERT INTO `cities` (`code`, `city_name`) VALUES ('KL', 'Kuala Lumpur');
INSERT INTO `cities` (`code`, `city_name`) VALUES ('LN', 'London');
INSERT INTO `cities` (`code`, `city_name`) VALUES ('MN', 'Manila');
INSERT INTO `cities` (`code`, `city_name`) VALUES ('SG', 'Singapore');
INSERT INTO `cities` (`code`, `city_name`) VALUES ('BJ', 'Beijing');
INSERT INTO `cities` (`code`, `city_name`) VALUES ('AK', 'Auckland');
INSERT INTO `cities` (`code`, `city_name`) VALUES ('PR', 'Paris');
INSERT INTO `cities` (`code`, `city_name`) VALUES ('SM', 'Sumatera');

select * from cities;  
  
  
  
  
  
  
  