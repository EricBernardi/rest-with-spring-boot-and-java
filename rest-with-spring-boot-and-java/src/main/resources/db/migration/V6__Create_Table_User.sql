  CREATE TABLE IF NOT EXISTS `user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `username` varchar(80) NOT NULL,
    `password` varchar(80) NOT NULL,
    PRIMARY KEY (`id`)
  );