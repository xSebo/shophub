-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb`;
CREATE SCHEMA IF NOT EXISTS `mydb`;
USE `mydb`;

-- -----------------------------------------------------
-- Table `mydb`.`Two_Factor_Methods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Two_Factor_Methods` (
                                                           `Two_Factor_Method_Id` INT NOT NULL,
                                                           `Two_Factor_Method_Name` VARCHAR(45) NOT NULL,
                                                           PRIMARY KEY (`Two_Factor_Method_Id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Admin_Types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Admin_Types` (
                                                    `Admin_Type_Id` INT NOT NULL,
                                                    `Admin_Type_Name` VARCHAR(45) NOT NULL,
                                                    PRIMARY KEY (`Admin_Type_Id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Users` (
                                              `User_Id` INT NOT NULL AUTO_INCREMENT,
                                              `User_First_Name` VARCHAR(45) NOT NULL,
                                              `User_Last_Name` VARCHAR(45) NOT NULL,
                                              `User_Email` VARCHAR(45) NOT NULL,
                                              `User_Password` VARCHAR(150) NOT NULL,
                                              `User_Profile_Picture` VARCHAR(45) NOT NULL,
                                              `Two_Factor_Method_Id` INT NOT NULL,
                                              `User_Reset_Code` VARCHAR(45) NULL,
                                              `User_Reset_Code_Expiry` DATETIME NULL,
                                              PRIMARY KEY (`User_Id`, `Two_Factor_Method_Id`),
                                              CONSTRAINT `fk_Users_Two_Factor_Methods`
                                                  FOREIGN KEY (`Two_Factor_Method_Id`)
                                                      REFERENCES `mydb`.`Two_Factor_Methods` (`Two_Factor_Method_Id`)
                                                      ON DELETE NO ACTION
                                                      ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Tags` (
                                             `Tag_Id` INT NOT NULL AUTO_INCREMENT,
                                             `Tag_Name` VARCHAR(45) NOT NULL,
                                             PRIMARY KEY (`Tag_Id`))
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`User_Favourite_Tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`User_Favourite_Tags` (
    `User_Favourite_Tag_Id` INT NOT NULL AUTO_INCREMENT,
    `User_Id` INT NOT NULL,
    `Tag_Id` INT NOT NULL,
    PRIMARY KEY (`User_Favourite_Tag_Id`, `User_Id`, `Tag_Id`),
    CONSTRAINT `fk_User_Favourite_Tags_Tags1`
        FOREIGN KEY (`Tag_Id`)
            REFERENCES `mydb`.`Tags` (`Tag_Id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_User_Favourite_Tags_Users1`
        FOREIGN KEY (`User_Id`)
            REFERENCES `mydb`.`Users` (`User_Id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Stamp_Boards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Stamp_Boards` (
                                                     `Stamp_Board_Id` INT NOT NULL AUTO_INCREMENT,
                                                     `Stamp_Board_Size` INT NOT NULL,
                                                     `Stamp_Board_Colour` VARCHAR(7),
                                                     `Stamp_Board_Icon` varchar(255),
                                                     PRIMARY KEY (`Stamp_Board_Id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Rewards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Rewards` (
                                                `Reward_Id` INT NOT NULL AUTO_INCREMENT,
                                                `Reward_Name` VARCHAR(45) NOT NULL,
                                                `Reward_Stamp_Location` INT NULL,
                                                `Stamp_Board_Id` INT NOT NULL,
                                                PRIMARY KEY (`Reward_Id`, `Stamp_Board_Id`),
                                                CONSTRAINT `fk_Rewards_Stamp_Boards1`
                                                    FOREIGN KEY (`Stamp_Board_Id`)
                                                        REFERENCES `mydb`.`Stamp_Boards` (`Stamp_Board_Id`)
                                                        ON DELETE NO ACTION
                                                        ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Categories` (
    `Category_Id` INT NOT NULL AUTO_INCREMENT,
    `Category_Name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`Category_Id`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Shops`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Shops` (
                                              `Shop_Id` INT NOT NULL AUTO_INCREMENT,
                                              `Shop_Name` VARCHAR(45) NOT NULL,
                                              `Shop_Description` VARCHAR(250) NOT NULL,
                                              `Shop_Website` VARCHAR(45) NOT NULL,
                                              `Shop_Earnings` INT NOT NULL,
                                              `Shop_Countries` VARCHAR(150) NOT NULL,
                                              `Shop_Image` VARCHAR(150),
                                              `Shop_Active` TINYINT NOT NULL,
                                              `Stamp_Board_Id` INT NOT NULL,
                                              `Category_Id` INT NOT NULL,
                                              PRIMARY KEY (`Shop_Id`, `Stamp_Board_Id`, `Category_Id`),
                                                CONSTRAINT `fk_Shops_Stamp_Boards1`
                                                  FOREIGN KEY (`Stamp_Board_Id`)
                                                    REFERENCES `mydb`.`Stamp_Boards` (`Stamp_Board_Id`)
                                                    ON DELETE NO ACTION
                                                    ON UPDATE NO ACTION,
                                                CONSTRAINT `fk_Shops_Categories1`
                                                  FOREIGN KEY (`Category_Id`)
                                                    REFERENCES `mydb`.`Categories` (`Category_Id`)
                                                    ON DELETE NO ACTION
                                                    ON UPDATE NO ACTION )
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Shop_Tag_Links`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Shop_Tag_Links` (
                                                       `Shop_Tag_Link_Id` INT NOT NULL AUTO_INCREMENT,
                                                       `Shop_Id` INT NOT NULL,
                                                       `Tag_Id` INT NOT NULL,
                                                       PRIMARY KEY (`Shop_Tag_Link_Id`, `Shop_Id`, `Tag_Id`),
                                                       CONSTRAINT `fk_Shop_Tag_Links_Shops1`
                                                           FOREIGN KEY (`Shop_Id`)
                                                               REFERENCES `mydb`.`Shops` (`Shop_Id`)
                                                               ON DELETE NO ACTION
                                                               ON UPDATE NO ACTION,
                                                       CONSTRAINT `fk_Shop_Tag_Links_Tags1`
                                                           FOREIGN KEY (`Tag_Id`)
                                                               REFERENCES `mydb`.`Tags` (`Tag_Id`)
                                                               ON DELETE NO ACTION
                                                               ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User_Permissions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`User_Permissions` (
                                                         `User_Permission_Id` INT NOT NULL AUTO_INCREMENT,
                                                         `User_Id` INT NOT NULL,
                                                         `Shop_Id` INT,
                                                         `Admin_Type_Id` INT NOT NULL,
                                                         PRIMARY KEY (`User_Permission_Id`, `User_Id`, `Shop_Id`, `Admin_Type_Id`),
                                                         CONSTRAINT `fk_User_Permissions_Users1`
                                                             FOREIGN KEY (`User_Id`)
                                                                 REFERENCES `mydb`.`Users` (`User_Id`)
                                                                 ON DELETE NO ACTION
                                                                 ON UPDATE NO ACTION,
                                                         CONSTRAINT `fk_User_Permissions_Shops1`
                                                             FOREIGN KEY (`Shop_Id`)
                                                                 REFERENCES `mydb`.`Shops` (`Shop_Id`)
                                                                 ON DELETE NO ACTION
                                                                 ON UPDATE NO ACTION,
                                                         CONSTRAINT `fk_User_Permissions_Admin_Types1`
                                                             FOREIGN KEY (`Admin_Type_Id`)
                                                                 REFERENCES `mydb`.`Admin_Types` (`Admin_Type_Id`)
                                                                 ON DELETE NO ACTION
                                                                 ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User_Shop_Links`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`User_Shop_Links` (
                                                        `User_Shop_Link_Id` INT NOT NULL AUTO_INCREMENT,
                                                        `Shop_Id` INT NOT NULL,
                                                        `User_Id` INT NOT NULL,
                                                        PRIMARY KEY (`User_Shop_Link_Id`, `Shop_Id`, `User_Id`),
                                                        CONSTRAINT `fk_User_Shop_Links_Shops1`
                                                            FOREIGN KEY (`Shop_Id`)
                                                                REFERENCES `mydb`.`Shops` (`Shop_Id`)
                                                                ON DELETE NO ACTION
                                                                ON UPDATE NO ACTION,
                                                        CONSTRAINT `fk_User_Shop_Links_Users1`
                                                            FOREIGN KEY (`User_Id`)
                                                                REFERENCES `mydb`.`Users` (`User_Id`)
                                                                ON DELETE NO ACTION
                                                                ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User_Stamp_Links`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`User_Stamp_Boards` (
                                                          `User_Stamp_Board_Id` INT NOT NULL AUTO_INCREMENT,
                                                          `User_Id` INT NOT NULL,
                                                          `Stamp_Board_Id` INT NOT NULL,
                                                          `User_Stamp_Position` INT NOT NULL,
                                                          PRIMARY KEY (`User_Stamp_Board_Id`, `User_Id`, `Stamp_Board_Id`),
                                                          CONSTRAINT `fk_User_Stamp_Links_Users1`
                                                              FOREIGN KEY (`User_Id`)
                                                                  REFERENCES `mydb`.`Users` (`User_Id`)
                                                                  ON DELETE NO ACTION
                                                                  ON UPDATE NO ACTION,
                                                          CONSTRAINT `fk_User_Stamp_Links_StampBoards1`
                                                              FOREIGN KEY (`Stamp_Board_Id`)
                                                                  REFERENCES `mydb`.`Stamp_Boards` (`Stamp_Board_Id`)
                                                                  ON DELETE NO ACTION
                                                                  ON UPDATE NO ACTION)
    ENGINE = InnoDB;
    

INSERT INTO two_factor_methods (`Two_Factor_Method_Id`, `Two_Factor_Method_Name`) VALUES (1, 'None');
INSERT INTO two_factor_methods (`Two_Factor_Method_Id`, `Two_Factor_Method_Name`) VALUES (2, 'GAuth');

INSERT INTO stamp_boards (Stamp_Board_Id, Stamp_Board_Size) VALUES (1, 0);

INSERT INTO Categories (Category_Id, Category_Name) VALUES (1, '');
INSERT INTO Categories (Category_Name) VALUES ('Hospitality');
INSERT INTO Categories (Category_Name) VALUES ('Retail');
INSERT INTO Categories (Category_Name) VALUES ('Supplier');
INSERT INTO Categories (Category_Name) VALUES ('Food And Drink');
INSERT INTO Categories (Category_Name) VALUES ('Animals');
INSERT INTO Categories (Category_Name) VALUES ('Alcohol');

INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('','','',0,'',0,1,1);

INSERT INTO Admin_Types (Admin_Type_Id, Admin_Type_Name) VALUES (1,'User');
INSERT INTO Admin_Types (Admin_Type_Id, Admin_Type_Name) VALUES (2,'Business Admin');
INSERT INTO Admin_Types (Admin_Type_Id, Admin_Type_Name) VALUES (3,'Super Admin');

INSERT INTO Tags (Tag_Name) VALUES ('Football');
INSERT INTO Tags (Tag_Name) VALUES ('Fashion');
INSERT INTO Tags (Tag_Name) VALUES ('Electronics');
INSERT INTO Tags (Tag_Name) VALUES ('Coffee');
INSERT INTO Tags (Tag_Name) VALUES ('Art');
INSERT INTO Tags (Tag_Name) VALUES ('Pets');
INSERT INTO Tags (Tag_Name) VALUES ('Clothes');
INSERT INTO Tags (Tag_Name) VALUES ('Designer');
INSERT INTO Tags (Tag_Name) VALUES ('Groceries');
INSERT INTO Tags (Tag_Name) VALUES ('Cars');
INSERT INTO Tags (Tag_Name) VALUES ('Hiking');
INSERT INTO Tags (Tag_Name) VALUES ('Cooking');
INSERT INTO Tags (Tag_Name) VALUES ('Furniture');
INSERT INTO Tags (Tag_Name) VALUES ('Gaming');
INSERT INTO Tags (Tag_Name) VALUES ('Travelling');
INSERT INTO Tags (Tag_Name) VALUES ('Beauty');
INSERT INTO Tags (Tag_Name) VALUES ('Eco-friendly');
INSERT INTO Tags (Tag_Name) VALUES ('Decorations');
INSERT INTO Tags (Tag_Name) VALUES ('Photography');
