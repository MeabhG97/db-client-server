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

