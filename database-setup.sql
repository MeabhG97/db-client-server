DROP DATABASE IF EXISTS music;
CREATE DATABASE music;

USE music;

CREATE TABLE artists (
  artist_id INT NOT NULL AUTO_INCREMENT,
  artist_name VARCHAR(50) NOT NULL,
  formed_date DATE,
  origin VARCHAR(50),
  members VARCHAR(255),
  PRIMARY KEY(artist_id)
);

CREATE TABLE albums (
  album_id INT NOT NULL AUTO_INCREMENT,
  album_name VARCHAR(50) NOT NULL,
  artist_id INT,
  release_date DATE,
  producer VARCHAR(50),
  PRIMARY KEY(album_id),
  FOREIGN KEY(artist_id) REFERENCES artists (artist_id)
);

CREATE TABLE songs (
  song_id INT NOT NULL AUTO_INCREMENT,
  song_name VARCHAR(50) NOT NULL,
  album_id INT,
  length TIME,
  PRIMARY KEY(song_id),
  FOREIGN KEY(album_id) REFERENCES albums(album_id)
);

INSERT INTO artists (artist_name, formed_date, origin, members)
VALUES 
  ('Nightwish', '1996-07-06', 'Kitee,Finland', 'Tuomas Holopainen,Emppu Vuorinen,Jukka Nevalainen,Tarja Turunen,Marco Hietala,Floor Jansen'),
  ('Beast in Black', '2015-12-08', 'Helsinki,Finland', 'Anton Kabanen,Yannis Papadopoulos,Kasperi Heikkinen,Mate Molnar,Atte Palokangas'),
  ('Apocalyptica', '1993-09-17', 'Helsinki,Finland', 'Eicca Toppinen,Perttu Kivilaakso,Paavo Lötjönen,Mikko Sirén');

INSERT INTO albums (album_name, artist_id, release_date, producer)
VALUES 
  ('Angels Fall First', 1, '1997-11-01', 'Tuomas Holopainen'),
  ('Oceanborn', 1, '1998-12-07', 'Tero Kinnunen'),
  ('Century Child', 1, '2002-04-22', 'Tero Kinnunen,Nightwish'),
  ('Dark Passion Play', 1, '2007-09-26', 'Tuomas Holopainen,Tero Kinnunen'),
  ('Endless Forms Most Beautiful', 1, '2015-03-27', 'Tuomas Holopainen,Tero Kinnunen'),
  ('Berserker', 2, '2019-02-08', 'Anton Kabanen,Janne Björkroth'),
  ('From Hell with Love', 2, '2019-02-08', 'Anton Kabanen,Janne Björkroth'),
  ('Amorphis Plays Metallica by Four Cellos', 3, '1996-12-11', 'Apocalyptica'),
  ('Inquisition Symphony', 3, '1998-09-22', 'Otto Donner,Hiili Hiilesmaa'),
  ('Cult', 3, '2000-10-02', 'Apocalyptica'),
  ('Reflections', 3, '2003-10-15', 'Marti Frederiksen,Jacob Hellner'),
  ('Worlds Collide', 3, '2007-09-14', 'Jacoby Shaddix,Johnny Andrews');

INSERT INTO songs (song_name, album_id, length)
VALUES 
  ('Elvenpath', 1, '4:38'),
  ('Beauty and the Beast', 1, '6:24'),
  ('Stargazers', 2, '4:28'),
  ('Sleeping Sun', 2, '4:01'),
  ('Bless the Child', 3, '6:12'),
  ('Ever Dream', 3, '4:43'),
  ('Amaranth', 4, '3:51'),
  ('The Poet and the Pendulum', 4, '13:55'),
  ('Shudder Before the Beautiful', 5, '6:29'),
  ('Endless Forms Most Beautiful', 5, '5:07'),
  ('Cry Out for a Hero', 6, '4:03'),
  ('Sweet True Lies', 6, '3:29'),
  ('From Hell with Love', 7, '3:58'),
  ('Die by the Blade', 7, '3:14'),
  ('Enter Sandman', 8, '3:41'),
  ('Refuse / Resist', 8, '3:12'),
  ('Hall of the Mountain King', 9, '3:33'),
  ('Path', 9, '3:09'),
  ('Hope', 10, '4:38'),
  ('Quutamo', 10, '3:27'),
  ('Bittersweet', 11, '4:58'),
  ('I Don''t Care', 11, '3:57');
