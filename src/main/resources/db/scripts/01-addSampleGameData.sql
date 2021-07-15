INSERT INTO games (id, short_url, title, description)
VALUES
(nextval('hibernate_sequence'), 'alpha_centauri', 'Alpha Centauri', '4X game by the Civilization creator'),
(nextval('hibernate_sequence'), 'killing_time', 'Killing Time', 'Interesting game, but not so good'),
(nextval('hibernate_sequence'), 'shadow_warrior','Shadow Warrior', 'Shadow is my place'),
(nextval('hibernate_sequence'), 'quake','Quake', 'It is quake goddamn it!'),
(nextval('hibernate_sequence'), 'starcraft','Starcraft', 'RTS from blizzard'),
(nextval('hibernate_sequence'), 'redalert','Red alert', 'Legendary RTS');