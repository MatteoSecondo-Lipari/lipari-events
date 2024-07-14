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

insert into locations (city, address, max_numbered_seats, max_seats) values 
("Ancona", "Via prova", 500, 3000), ("Bari", "Via prova", 500, 3000), ("Bergamo", "Via prova", 500, 3000),
("Bologna", "Via prova", 500, 3000), ("Caserta", "Via prova", 500, 3000), ("Catania", "Via prova", 500, 3000),
("Ferrara", "Via prova", 500, 3000), ("Firenze", "Via prova", 500, 3000), ("Forlì", "Via prova", 500, 3000),
("Genova", "Via prova", 500, 3000), ("Lucca", "Via prova", 500, 3000), ("Milano", "Via prova", 500, 3000),
("Misano", "Via prova", 500, 3000), ("Monza", "Via prova", 500, 3000), ("Napoli", "Via prova", 500, 3000),
("Padova", "Via prova", 500, 3000), ("Palermo", "Via prova", 500, 3000), ("Parma", "Via prova", 500, 3000),
("Pescara", "Via prova", 500, 3000), ("Piacenza", "Via prova", 500, 3000), ("Reggio Calabria", "Via prova", 500, 3000),
("Reggio Emilia", "Via prova", 500, 3000), ("Roma", "Via prova", 500, 3000), ("Siena", "Via prova", 500, 3000),
("Torino", "Via prova", 500, 3000), ("Trento", "Via prova", 500, 3000), ("Trieste", "Via prova", 500, 3000),
("Udine", "Via prova", 500, 3000),
("Varese", "Via prova", 500, 3000), ("Venezia", "Via prova", 500, 3000), ("Verona", "Via prova", 500, 3000);

INSERT INTO events (name, date, image_Path, subcategory_id, location_id, numbered_ticket_price, ticket_price) VALUES
("Rock Concert", '2024-08-15', 'path/to/rock_concert.jpg', 1, 12, 35, 30),
("Jazz Night", '2024-07-20', 'path/to/jazz_night.jpg', 3, 22, 35, 30),
("Soccer Match", '2024-09-10', 'path/to/soccer_match.jpg', 13, 25, 35, 30),
("Art Exhibition", '2024-10-05', 'path/to/art_exhibition.jpg', 18, 8, 35, 30),
("Film Premiere", '2024-11-01', 'path/to/film_premiere.jpg', 21, 18, 35, 30);

