CREATE TABLE `m_workers` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) NOT NULL COMMENT '作業者名',
  `is_valid` int NOT NULL COMMENT '有効フラグ',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日付',
  PRIMARY KEY (`id`)
) COMMENT='作業者';
