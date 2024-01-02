CREATE TABLE `conditions` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(40) NOT NULL COMMENT 'ラベル名',
  `condison` varchar(4000) COMMENT '検索条件',
  PRIMARY KEY (`id`)
) COMMENT='検索条件';
