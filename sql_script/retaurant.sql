CREATE TABLE `Category` (
                            `id` INT AUTO_INCREMENT UNIQUE,
                            `Name` VARCHAR(255),
                            PRIMARY KEY(`id`)
);

CREATE TABLE `User` (
                        `id` INT NOT NULL AUTO_INCREMENT UNIQUE,
                        `FullName` VARCHAR(255) NOT NULL,
                        `Birthday` DATETIME,
                        `Email` VARCHAR(255) NOT NULL,
                        `Phone` VARCHAR(255),
                        `Password` VARCHAR(255) NOT NULL,
                        `Role` VARCHAR(255) NOT NULL,
                        `Address` VARCHAR(255),
                        `ResetToken` VARCHAR(255),
                        `ResetTokenExpiry` DATETIME,
                        PRIMARY KEY(`id`)
);

CREATE TABLE `Wishlist` (
                            `id` INT NOT NULL AUTO_INCREMENT UNIQUE,
                            `UserId` INT NOT NULL,
                            `FoodId` INT NOT NULL,
                            PRIMARY KEY(`id`)
);

CREATE TABLE `Feedback` (
                            `id` INT NOT NULL AUTO_INCREMENT UNIQUE,
                            `Name` VARCHAR(255) NOT NULL,
                            `Email` VARCHAR(255) NOT NULL,
                            `Phone` VARCHAR(255) NOT NULL,
                            `Message` VARCHAR(255) NOT NULL,
                            PRIMARY KEY(`id`)
);

CREATE TABLE `Food` (
                        `id` INT NOT NULL AUTO_INCREMENT UNIQUE,
                        `CategoryId` INT,
                        `Name` VARCHAR(255) NOT NULL,
                        `Image` VARCHAR(255) NOT NULL,
                        `Description` VARCHAR(255),
                        `Quantity` INT,
                        `Price` DECIMAL NOT NULL,
                        PRIMARY KEY(`id`)
);

CREATE TABLE `Review` (
                          `id` INT NOT NULL AUTO_INCREMENT UNIQUE,
                          `UserId` INT NOT NULL,
                          `FoodId` INT NOT NULL,
                          `Rating` INT NOT NULL,
                          `Message` VARCHAR(255) NOT NULL,
                          PRIMARY KEY(`id`)
);

CREATE TABLE `Orders` (
                          `id` INT NOT NULL AUTO_INCREMENT UNIQUE,
                          `UserId` INT NOT NULL,
                          `OrderCode` VARCHAR(255) NOT NULL,
                          `Total` DECIMAL,
                          `IsPaid` INT,
                          `Status` INT,
                          PRIMARY KEY(`id`)
);

CREATE TABLE `OrderDetail` (
                               `id` INT NOT NULL AUTO_INCREMENT UNIQUE,
                               `OrderId` INT NOT NULL,
                               `FoodId` INT NOT NULL,
                               `Quantity` INT NOT NULL,
                               `UnitPrice` DECIMAL NOT NULL,
                               `Discount` DECIMAL,
                               PRIMARY KEY(`id`)
);

CREATE TABLE `Menu` (
                        `id` INT NOT NULL AUTO_INCREMENT UNIQUE,
                        `Name` VARCHAR(255) NOT NULL,
                        `Description` VARCHAR(255) NOT NULL,
                        PRIMARY KEY(`id`)
);

CREATE TABLE `MenuFood` (
                            `id` INT NOT NULL AUTO_INCREMENT UNIQUE,
                            `MenuId` INT NOT NULL,
                            `FoodId` INT NOT NULL,
                            PRIMARY KEY(`id`)
);

CREATE TABLE `OrderTable` (
                              `id` INT NOT NULL AUTO_INCREMENT UNIQUE,
                              `MenuId` INT,
                              `Name` VARCHAR(255),
                              `NumberOfPerson` INT NOT NULL,
                              `Email` VARCHAR(255) NOT NULL,
                              `Phone` VARCHAR(255) NOT NULL,
                              `Status` INT NOT NULL,
                              `Time` TIME NOT NULL,
                              `Date` DATE NOT NULL,
                              PRIMARY KEY(`id`)
);

ALTER TABLE `Orders`
    ADD CONSTRAINT `FK_Orders_UserId`
        FOREIGN KEY (`UserId`) REFERENCES `User`(`id`);

ALTER TABLE `OrderDetail`
    ADD CONSTRAINT `FK_OrderDetail_OrderId`
        FOREIGN KEY (`OrderId`) REFERENCES `Orders`(`id`);

ALTER TABLE `OrderDetail`
    ADD CONSTRAINT `FK_OrderDetail_FoodId`
        FOREIGN KEY (`FoodId`) REFERENCES `Food`(`id`);

ALTER TABLE `MenuFood`
    ADD CONSTRAINT `FK_MenuFood_MenuId`
        FOREIGN KEY (`MenuId`) REFERENCES `Menu`(`id`);

ALTER TABLE `Food`
    ADD CONSTRAINT `FK_Food_CategoryId`
        FOREIGN KEY (`CategoryId`) REFERENCES `Category`(`id`);

ALTER TABLE `Wishlist`
    ADD CONSTRAINT `FK_Wishlist_FoodId`
        FOREIGN KEY (`FoodId`) REFERENCES `Food`(`id`);

ALTER TABLE `Wishlist`
    ADD CONSTRAINT `FK_Wishlist_UserId`
        FOREIGN KEY (`UserId`) REFERENCES `User`(`id`);

ALTER TABLE `Review`
    ADD CONSTRAINT `FK_Review_UserId`
        FOREIGN KEY (`UserId`) REFERENCES `User`(`id`);

ALTER TABLE `Review`
    ADD CONSTRAINT `FK_Review_FoodId`
        FOREIGN KEY (`FoodId`) REFERENCES `Food`(`id`);

ALTER TABLE `OrderTable`
    ADD CONSTRAINT `FK_OrderTable_MenuId`
        FOREIGN KEY (`MenuId`) REFERENCES `Menu`(`id`);

ALTER TABLE `MenuFood`
    ADD CONSTRAINT `FK_MenuFood_FoodId`
        FOREIGN KEY (`FoodId`) REFERENCES `Food`(`id`);


