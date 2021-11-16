SET MODE MYSQL;
SET IGNORECASE=TRUE;

-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb`;
CREATE SCHEMA IF NOT EXISTS `mydb`;
USE `mydb`;

-- -----------------------------------------------------
-- Table `mydb`.`TwoFactorMethods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Two_Factor_Methods` (
                                                           `Two_Factor_Method_Id` INT NOT NULL,
                                                           `Two_Factor_Method_Name` VARCHAR(45) NOT NULL,
                                                           PRIMARY KEY (`Two_Factor_Method_Id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Shops`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Shops` (
                                              `Shop_Id` INT NOT NULL AUTO_INCREMENT,
                                              `Shop_Name` VARCHAR(45) NOT NULL,
                                              `Shop_Website` VARCHAR(45) NOT NULL,
                                              `Shop_Earnings` INT NOT NULL,
                                              `Shop_Countries` VARCHAR(150) NOT NULL,
                                              `Shop_Active` TINYINT NOT NULL,
                                              PRIMARY KEY (`Shop_Id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`AdminTypes`
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
-- Table `mydb`.`UserPermissions`
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
-- Table `mydb`.`Tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Tags` (
                                             `Tag_Id` INT NOT NULL AUTO_INCREMENT,
                                             `Tag_Name` VARCHAR(45) NOT NULL,
                                             PRIMARY KEY (`Tag_Id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ShopTagLinks`
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
-- Table `mydb`.`StampBoards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Stamp_Boards` (
                                                     `Stamp_Board_Id` INT NOT NULL AUTO_INCREMENT,
                                                     `Stamp_Board_Size` INT NOT NULL,
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
-- Table `mydb`.`RewardShopLinks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Reward_Shop_Links` (
                                                          `Reward_Shop_Link_ID` INT NOT NULL AUTO_INCREMENT,
                                                          `Shop_Id` INT NOT NULL,
                                                          `Reward_Id` INT NOT NULL,
                                                          PRIMARY KEY (`Reward_Shop_Link_ID`, `Shop_Id`, `Reward_Id`),
                                                          CONSTRAINT `fk_Reward_Shop_Links_Shops1`
                                                              FOREIGN KEY (`Shop_Id`)
                                                                  REFERENCES `mydb`.`Shops` (`Shop_Id`)
                                                                  ON DELETE NO ACTION
                                                                  ON UPDATE NO ACTION,
                                                          CONSTRAINT `fk_Reward_Shop_Links_Rewards1`
                                                              FOREIGN KEY (`Reward_Id`)
                                                                  REFERENCES `mydb`.`Rewards` (`Reward_Id`)
                                                                  ON DELETE NO ACTION
                                                                  ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`UserShopLinks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`User_Shop_Links` (
                                                        `User_Shop_Link_Id` INT NOT NULL AUTO_INCREMENT,
                                                        `Shop_Id` INT NOT NULL,
                                                        `Users_User_Id` INT NOT NULL,
                                                        PRIMARY KEY (`User_Shop_Link_Id`, `Shop_Id`, `Users_User_Id`),
                                                        CONSTRAINT `fk_User_Shop_Links_Shops1`
                                                            FOREIGN KEY (`Shop_Id`)
                                                                REFERENCES `mydb`.`Shops` (`Shop_Id`)
                                                                ON DELETE NO ACTION
                                                                ON UPDATE NO ACTION,
                                                        CONSTRAINT `fk_User_Shop_Links_Users1`
                                                            FOREIGN KEY (`Users_User_Id`)
                                                                REFERENCES `mydb`.`Users` (`User_Id`)
                                                                ON DELETE NO ACTION
                                                                ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`UserStampLinks`
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

INSERT INTO Shops (Shop_Name, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Active) VALUES ('','',0,'',0);

INSERT INTO Admin_Types (Admin_Type_Id, Admin_Type_Name) VALUES (1,'User');
INSERT INTO Admin_Types (Admin_Type_Id, Admin_Type_Name) VALUES (2,'Business Admin');
INSERT INTO Admin_Types (Admin_Type_Id, Admin_Type_Name) VALUES (3,'Super Admin');