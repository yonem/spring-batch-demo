CREATE TABLE `books` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(40) NOT NULL COMMENT '書籍名',
  `isbn` varchar(255) NOT NULL COMMENT 'ISBN',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日付',
  PRIMARY KEY (`id`),
  UNIQUE KEY `isbn` (`isbn`)
) COMMENT='書籍';
