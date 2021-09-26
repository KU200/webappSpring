-- size for int / timestamp is not really needed
-- PRIMARY KEY could be set directly on column name
-- PRIMARY KEY is already unique, no need for UNIQUE keyword
-- engine / character set are optional, for now we don't really need them

-- Auto generated ids is okay stuff, but for initialization scripts its better to set them manually, 
-- so it would be more predictable when foreign keys would be used
INSERT INTO 
	campaigns (id, name,status,start_date,end_date)
VALUES
	 (1, 'sony cristmas', 0, 210228, 210401 ),
	 (2, 'sony black friday', 1, 210201, 220101 ),
	 (3, 'panasonic lucky week', 1, 210201, 220201 ),
	 (4, 'aiwa black friday', 1, 210201, 220201 ),
	 (5, 'asus end of the world', 0, 210228, 210401 ),
	 (6, 'sven', 1, 210201, 220101 ),
	 (7, 'sven black friday', 3, 210101, 210201 ),
	 (8, 'edifier happy holidays', 2, 200101, 240101 ),
	 (9, 'samsung black friday', 3, 201201, 210201 ),
	 (10, 'lg from dusk till dawn', 0, 210301, 220101 ),
	 (11, 'lg black friday', 1, 210201, 220201 ),
	 (12, 'apple tim cook is god', 1, 210201, 220401 ),
	 (13, 'apple red friday', 1, 210201, 220101);

-- Same as for first query:
-- size for int / timestamp is not really needed
-- PRIMARY KEY could be set directly on column name
-- PRIMARY KEY is already unique, no need for UNIQUE keyword
-- engine / character set are optional, for now we don't really need them

-- Same as for first query:
-- Auto generated ids is okay stuff, but for initialization scripts its better to set them manually, 
-- so it would be more predictable when foreign keys would be used
-- Also, your foreign key (campaign_id) is NOT_NULL, so you need to set it too.
INSERT INTO 
	ads (id, campaign_id, name, status /*,platforms*/, asset_url)
VALUES
	(1, 1, 'sony christmas ad', 2, 'https://www.youtube.com/sony_ad '),
	(2, 2, 'sony BF ad1', 1,'https://www.youtube.com/sony_christmas '),
	(3, 2, 'sony BF ad2', 2,'https://www.youtube.com/sony_christmas '),
	(4, 2, 'sony BF ad3', 2,'https://www.youtube.com/sony_christmas '),
	(5, 3, 'panasonic lucky week ad1', 1, 'https://www.youtube.com/panasonic_ad ' ),
	(6, 3, 'panasonic lucky week ad2', 1, 'https://www.youtube.com/panasonic_ad ' ),
	(7, 3, 'panasonic lucky week ad3', 1, 'https://www.youtube.com/panasonic_ad ' ),
	(8, 4, 'aiwa BF ad', 1, 'https://www.youtube.com/aiwa_ad '),
	(9, 5, 'asus EotW ad1', 0, 'https://www.youtube.com/asus_christmas '),
	(10, 5, 'asus EotW ad2', 0, 'https://www.youtube.com/asus_christmas '),
	(11, 7, 'sven BF ad1', 3, 'https://www.youtube.com/sven_birth '),
	(12, 7, 'sven BF ad2', 3, 'https://www.youtube.com/sven_birth '),
	(13, 8, 'edifier HH ad', 2, 'https://www.youtube.com/edifier_ad '),
	(14, 9, 'samsung BF ad1', 3, 'https://www.youtube.com/samsung_birth ' ),
	(15, 9, 'samsung BF ad2', 3, 'https://www.youtube.com/samsung_birth ' ),
	(16, 9, 'samsung BF ad3', 3, 'https://www.youtube.com/samsung_birth ' ),
	(17, 9, 'samsung BF ad4', 3, 'https://www.youtube.com/samsung_birth ' ),
	(18, 9, 'samsung BF ad5', 3, 'https://www.youtube.com/samsung_birth ' ),
	(19, 10, 'lg fdtd ad1', 0, 'https://www.youtube.com/lg_ad'),
	(20, 10, 'lg fdtd ad2', 0, 'https://www.youtube.com/lg_ad'),
	(21, 10, 'lg fdtd ad3', 0, 'https://www.youtube.com/lg_ad'),
	(22, 10, 'lg fdtd ad4', 1, 'https://www.youtube.com/lg_newyear ' );



#
# CREATE TABLE platforms (
#   id int NOT NULL PRIMARY KEY,
#   name varchar(45) NOT NULL);
#
# INSERT INTO
# 	platforms (id, name)
# VALUES
# 	(0, 'web'),
# 	(1, 'android'),
# 	(2, 'ios');

# CREATE TABLE ads_platforms (
#   ad_id int NOT NULL,
#   platform_id int NOT NULL,
#   FOREIGN KEY (ad_id) REFERENCES ads(id),
#   FOREIGN KEY (platform_id) REFERENCES platforms(id)
# );

# INSERT INTO
# 	ads_platforms (ad_id, platform_id)
# VALUES
# 	(1, 0),
# 	(1, 1),
# 	(2, 2),
# 	(3, 0),
# 	(3, 1),
# 	(3, 2),
# 	(4, 0),
# 	(4, 2),
# 	(5, 0),
# 	(6, 0),
# 	(6, 1),
# 	(6, 2),
# 	(7, 1),
# 	(8, 0),
# 	(9, 0),
# 	(9, 2),
# 	(10, 0),
# 	(10, 1),
# 	(11, 2),
# 	(12, 2),
# 	(13, 0),
# 	(13, 1),
# 	(13, 2),
# 	(14, 0),
# 	(14, 1),
# 	(15, 0),
# 	(16, 0),
# 	(16, 2),
# 	(17, 0),
# 	(17, 1),
# 	(18, 0),
# 	(19, 2),
# 	(20, 0),
# 	(20, 1),
# 	(21, 0),
# 	(22, 1);