DROP DATABASE IF EXISTS time_tracker;
CREATE DATABASE time_tracker DEFAULT CHARACTER SET utf8;

USE time_tracker;

-- Таблица пользователей (users)
DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id BIGINT NOT NULL AUTO_INCREMENT,
  username VARCHAR(64) NOT NULL,
  password VARCHAR(64) NOT NULL,
  authority VARCHAR(64) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (username)
);

INSERT INTO users (username, password, authority) VALUES 
('admin', '$2a$10$dpKmgQuFeyEecXdN0TJ5m.asy26TazCv6dUtXyrHjMLgrqK7fVOWe', 'ROLE_ADMIN'),
('manager', '$2a$10$dpKmgQuFeyEecXdN0TJ5m.asy26TazCv6dUtXyrHjMLgrqK7fVOWe', 'ROLE_MANAGER');

-- Таблица заданий (tasks) без ссылки на пользователей
DROP TABLE IF EXISTS tasks;
CREATE TABLE tasks (
  id BIGINT NOT NULL AUTO_INCREMENT,
  description VARCHAR(255) NOT NULL,       -- Описание задания
  assigned_date DATETIME NOT NULL,           -- Дата выдачи задания
  deadline DATETIME NOT NULL,                -- Дата дедлайна
  status VARCHAR(20) NOT NULL,               -- Статус задания (например, 'NEW', 'IN_PROGRESS', 'COMPLETED')
  PRIMARY KEY (id)
);

-- Вставляем 4 примера заданий
INSERT INTO tasks (description, assigned_date, deadline, status) VALUES 
('Подготовить отчет по продажам', NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 'NEW'),
('Провести собрание по проекту', NOW(), DATE_ADD(NOW(), INTERVAL 3 DAY), 'IN_PROGRESS'),
('Организовать обучение сотрудников', NOW(), DATE_ADD(NOW(), INTERVAL 14 DAY), 'NEW'),
('Обновить документацию проекта', NOW(), DATE_ADD(NOW(), INTERVAL 5 DAY), 'COMPLETED');

-- Таблица записей учёта рабочего времени (work_logs)
DROP TABLE IF EXISTS work_logs;
CREATE TABLE work_logs (
  id BIGINT NOT NULL AUTO_INCREMENT,
  task_id BIGINT NOT NULL,                    -- Ссылка на задание (FK на tasks)
  hours_worked DECIMAL(5,2) NOT NULL,          -- Количество отработанных часов
  log_date_time DATETIME NOT NULL,             -- Дата и время регистрации записи
  comment VARCHAR(255),                        -- Комментарий
  PRIMARY KEY (id),
  FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE
);

-- Вставляем 4 примера записей учета рабочего времени
INSERT INTO work_logs (task_id, hours_worked, log_date_time, comment) VALUES
(1, 4.50, NOW(), 'Начало работы над отчетом'),
(1, 3.00, DATE_ADD(NOW(), INTERVAL 1 HOUR), 'Доработка раздела продаж'),
(2, 2.50, NOW(), 'Подготовка материалов для собрания'),
(3, 5.00, NOW(), 'Проведение обучения по новым технологиям');

-- Таблица меток (tags)
DROP TABLE IF EXISTS tags;
CREATE TABLE tags (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,      -- Название метки
  PRIMARY KEY (id),
  UNIQUE KEY (name)
);

-- Вставляем 4 примера меток
INSERT INTO tags (name) VALUES 
('Срочно'),
('Важное'),
('Отложить'),
('Проверка');

-- Таблица связи заданий и меток (task_tags)
DROP TABLE IF EXISTS task_tags;
CREATE TABLE task_tags (
  task_id BIGINT NOT NULL,
  tag_id BIGINT NOT NULL,
  PRIMARY KEY (task_id, tag_id),
  FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
  FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE
);

-- Вставляем 4 примера связи
-- Пример: задание 1 имеет метки "Срочно" (id 1) и "Важное" (id 2)
INSERT INTO task_tags (task_id, tag_id) VALUES 
(1, 1), (1, 2),
(2, 2), (2, 3),
(3, 1), (3, 4),
(4, 3), (4, 4);
