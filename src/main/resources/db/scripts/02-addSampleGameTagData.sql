INSERT INTO game_tags(uuid, key, value, game_id)
VALUES
    (gen_random_uuid(), 'genre', '4X', (SELECT uuid FROM games where short_url = 'alpha_centauri')),
    (gen_random_uuid(), 'artistic', 'Sci-Fi', (SELECT uuid FROM games where short_url = 'alpha_centauri')),
    (gen_random_uuid(), 'extra', 'Bestseller', (SELECT uuid FROM games where short_url = 'alpha_centauri')),
    (gen_random_uuid(), 'extra', 'Game Of The Year', (SELECT uuid FROM games where short_url = 'alpha_centauri')),
    (gen_random_uuid(), 'genre', 'FPS', (SELECT uuid FROM games where short_url = 'killing_time')),
    (gen_random_uuid(), 'artistic', 'Interwar', (SELECT uuid FROM games where short_url = 'killing_time')),
    (gen_random_uuid(), 'genre', 'FPS', (SELECT uuid FROM games where short_url = 'shadow_warrior')),
    (gen_random_uuid(), 'artistic', 'Contemporary', (SELECT uuid FROM games where short_url = 'shadow_warrior')),
    (gen_random_uuid(), 'genre', 'FPS', (SELECT uuid FROM games where short_url = 'quake')),
    (gen_random_uuid(), 'artistic', 'Cultist', (SELECT uuid FROM games where short_url = 'quake')),
    (gen_random_uuid(), 'extra', 'Bestseller', (SELECT uuid FROM games where short_url = 'quake')),
    (gen_random_uuid(), 'extra', 'Revolutionary', (SELECT uuid FROM games where short_url = 'quake')),
    (gen_random_uuid(), 'genre', 'RTS', (SELECT uuid FROM games where short_url = 'starcraft')),
    (gen_random_uuid(), 'artistic', 'Sci-Fi', (SELECT uuid FROM games where short_url = 'starcraft')),
    (gen_random_uuid(), 'extra', 'Bestseller', (SELECT uuid FROM games where short_url = 'starcraft')),
    (gen_random_uuid(), 'genre', 'RTS', (SELECT uuid FROM games where short_url = 'redalert')),
    (gen_random_uuid(), 'artistic', 'Alternative', (SELECT uuid FROM games where short_url = 'redalert')),
    (gen_random_uuid(), 'extra', 'Bestseller', (SELECT uuid FROM games where short_url = 'redalert')),
    (gen_random_uuid(), 'extra', 'Game Of The Year', (SELECT uuid FROM games where short_url = 'redalert'))