insert into event_categories (name) values ("Concert"), ("Theatre"), ("Sport"), ("Exhibitions and Museums"),
("Cinema"), ("International"), ("Other");

insert into event_subcategories (name, category_id) values
("Pop & Rock", 1), ("Metal", 1), ("Jazz", 1), ("Festival", 1), ("Other", 1),
("Musical and variety", 2), ("Prose", 2), ("Cabaret", 2), ("Lyric theatre", 2), ("Classical and modern ballet", 2), ("Classic music", 2), ("Other", 2),
("Soccer", 3), ("Motoring", 3), ("Motorcycling", 3), ("Volleyball", 3), ("Rugby", 3), ("Tennis", 3), ("Boxing", 3), ("Other", 3),
("Art and history exhibitions", 4), ("Museums and archaeological sites", 4),  ("Guided tours", 4),
("Film", 5),
("Summary", 6),
("Circus", 7), ("Exhibitions", 7), ("Theme parks", 7), ("Other", 7);

insert into locations (citta) values  ("Ancona"), ("Bari"), ("Bergamo"), ("Bologna"), ("Caserta"),
("Catania"), ("Ferrara"), ("Firenze"), ("Forlì"), ("Genova"), ("Lucca"), ("Milano"), ("Misano"), ("Monza"),
("Napoli"), ("Padova"), ("Palermo"), ("Parma"), ("Pescara"), ("Piacenza"), ("Reggio Calabria"),
("Reggio Emilia"), ("Roma"), ("Siena"), ("Torino"), ("Trento"), ("Trieste"), ("Udine"),
("Varese"), ("Venezia"), ("Verona");

INSERT INTO event (name, date, imagePath, subcategory_id, location_id) VALUES
("Rock Concert", '2024-08-15', 'path/to/rock_concert.jpg', 1, 12),
("Jazz Night", '2024-07-20', 'path/to/jazz_night.jpg', 3, 22),
("Soccer Match", '2024-09-10', 'path/to/soccer_match.jpg', 13, 25),
("Art Exhibition", '2024-10-05', 'path/to/art_exhibition.jpg', 18, 8),
("Film Premiere", '2024-11-01', 'path/to/film_premiere.jpg', 21, 18);

