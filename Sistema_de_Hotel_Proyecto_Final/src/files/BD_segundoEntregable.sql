-- Backup desde Upstash Redis
CREATE TABLE IF NOT EXISTS redis_data (clave TEXT, valor TEXT);
INSERT INTO redis_data (clave, valor) VALUES ('cuenta[0]', 'davidcamacho@gmail.com, obsidiana');
INSERT INTO redis_data (clave, valor) VALUES ('cuenta[1]', 'danidash@gmail.com, elgranluis');
INSERT INTO redis_data (clave, valor) VALUES ('cuenta[2]', 'diegocareagacel@gmail.com, Colores');
INSERT INTO redis_data (clave, valor) VALUES ('cuenta[3]', '1, 2');
