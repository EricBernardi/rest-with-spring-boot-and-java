  CREATE TABLE IF NOT EXISTS `phone` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `number` varchar(20) NOT NULL,
    `type` varchar(10) NOT NULL,
    `person_id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`)
  );