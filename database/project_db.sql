CREATE TABLE `restaurant` (
  `id` bigint(20) NOT NULL,
  `name` varchar(250) NOT NULL,
  `location` varchar(250) DEFAULT NULL,
  `type_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `restaurant` VALUES (1,'Imli','Bangalore','VEG'),(2,'Paradise','Hyderabad','NON_VEG');

CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL,
  `restaurant_id` bigint(20) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `menu_FK` (`restaurant_id`),
  CONSTRAINT `menu_FK` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `menu` VALUES (1,1,''),(2,2,'');

CREATE TABLE `menu_item` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `type_name` varchar(100) DEFAULT NULL,
  `group_name` varchar(100) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `menu_item_FK` (`menu_id`),
  CONSTRAINT `menu_item_FK` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `menu_item` VALUES (1,'Amritsari Dal','Green gram and bengal gram with spices','VEG','MAIN_COURSE',270.00,1),(2,'Samosa Masala','Deep fried savory pastry with dressing of chickpeas salad','VEG','STARTER',120.00,1),(3,'Phirni','Creamy dessert of ground rice','VEG','DESSERT',120.00,1),(4,'Nizami Chicken Biryani','Freshly cooked kacchi dum ki biryany','NON_VEG','MAIN_COURSE',319.00,2),(5,'Mutton Seekh Kebab','Minced goat meet mixed with spices and cooked on gridle','NON_VEG','STARTER',365.00,2),(6,'Kurbaani Ka Meetha','Sweet dessert made from dried apricots and sugar enriched with saffron strands and rose water  ','VEG','DESSERT',160.00,2);

CREATE TABLE `order` (
  `id` bigint(20) NOT NULL,
  `restaurant_id` bigint(20) NOT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_FK` (`restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `menu_item_id` bigint(20) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_item_FK` (`menu_item_id`),
  KEY `order_item_FK_1` (`order_id`),
  CONSTRAINT `order_item_FK` FOREIGN KEY (`menu_item_id`) REFERENCES `menu_item` (`id`),
  CONSTRAINT `order_item_FK_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;