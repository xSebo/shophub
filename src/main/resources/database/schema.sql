-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8;
USE `mydb`;

-- -----------------------------------------------------
-- Table `mydb`.`TwoFactorMethods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TwoFactorMethods` (
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
CREATE TABLE IF NOT EXISTS `mydb`.`AdminTypes` (
    `Admin_Type_Id` INT NOT NULL AUTO_INCREMENT,
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
    INDEX `fk_Users_TwoFactorMethods_idx` (`Two_Factor_Method_Id` ASC) VISIBLE,
        CONSTRAINT `fk_Users_TwoFactorMethods`
            FOREIGN KEY (`Two_Factor_Method_Id`)
                REFERENCES `mydb`.`TwoFactorMethods` (`Two_Factor_Method_Id`)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`UserPermissions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`UserPermissions` (
    `User_Permission_Id` INT NOT NULL AUTO_INCREMENT,
    `User_Id` INT NOT NULL,
    `Shop_Id` INT NOT NULL,
    `Admin_Type_Id` INT NOT NULL,
    PRIMARY KEY (`User_Permission_Id`, `User_Id`, `Shop_Id`, `Admin_Type_Id`),
    INDEX `fk_UserPermissions_Users1_idx` (`User_Id` ASC) VISIBLE,
    INDEX `fk_UserPermissions_Shops1_idx` (`Shop_Id` ASC) VISIBLE,
    INDEX `fk_UserPermissions_AdminTypes1_idx` (`Admin_Type_Id` ASC) VISIBLE,
    CONSTRAINT `fk_UserPermissions_Users1`
        FOREIGN KEY (`User_Id`)
            REFERENCES `mydb`.`Users` (`User_Id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_UserPermissions_Shops1`
        FOREIGN KEY (`Shop_Id`)
            REFERENCES `mydb`.`Shops` (`Shop_Id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_UserPermissions_AdminTypes1`
        FOREIGN KEY (`Admin_Type_Id`)
            REFERENCES `mydb`.`AdminTypes` (`Admin_Type_Id`)
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
CREATE TABLE IF NOT EXISTS `mydb`.`ShopTagLinks` (
    `Shop_Tag_Link_Id` INT NOT NULL AUTO_INCREMENT,
    `Shop_Id` INT NOT NULL,
    `Tag_Id` INT NOT NULL,
    PRIMARY KEY (`Shop_Tag_Link_Id`, `Shop_Id`, `Tag_Id`),
    INDEX `fk_ShopTagLinks_Shops1_idx` (`Shop_Id` ASC) VISIBLE,
    INDEX `fk_ShopTagLinks_Tags1_idx` (`Tag_Id` ASC) VISIBLE,
    CONSTRAINT `fk_ShopTagLinks_Shops1`
        FOREIGN KEY (`Shop_Id`)
            REFERENCES `mydb`.`Shops` (`Shop_Id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_ShopTagLinks_Tags1`
        FOREIGN KEY (`Tag_Id`)
            REFERENCES `mydb`.`Tags` (`Tag_Id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`StampBoards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`StampBoards` (
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
    INDEX `fk_Rewards_StampBoards1_idx` (`Stamp_Board_Id` ASC) VISIBLE,
    CONSTRAINT `fk_Rewards_StampBoards1`
        FOREIGN KEY (`Stamp_Board_Id`)
            REFERENCES `mydb`.`StampBoards` (`Stamp_Board_Id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`RewardShopLinks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`RewardShopLinks` (
    `Reward_Shop_Link_ID` INT NOT NULL AUTO_INCREMENT,
    `Shop_Id` INT NOT NULL,
    `Reward_Id` INT NOT NULL,
    PRIMARY KEY (`Reward_Shop_Link_ID`, `Shop_Id`, `Reward_Id`),
    INDEX `fk_RewardShopLinks_Shops1_idx` (`Shop_Id` ASC) VISIBLE,
    INDEX `fk_RewardShopLinks_Rewards1_idx` (`Reward_Id` ASC) VISIBLE,
    CONSTRAINT `fk_RewardShopLinks_Shops1`
        FOREIGN KEY (`Shop_Id`)
            REFERENCES `mydb`.`Shops` (`Shop_Id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_RewardShopLinks_Rewards1`
        FOREIGN KEY (`Reward_Id`)
            REFERENCES `mydb`.`Rewards` (`Reward_Id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`UserShopLinks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`UserShopLinks` (
    `User_Shop_Link_Id` INT NOT NULL AUTO_INCREMENT,
    `Shop_Id` INT NOT NULL,
    `Users_User_Id` INT NOT NULL,
    PRIMARY KEY (`User_Shop_Link_Id`, `Shop_Id`, `Users_User_Id`),
    INDEX `fk_UserShopLinks_Shops1_idx` (`Shop_Id` ASC) VISIBLE,
    INDEX `fk_UserShopLinks_Users1_idx` (`Users_User_Id` ASC) VISIBLE,
    CONSTRAINT `fk_UserShopLinks_Shops1`
        FOREIGN KEY (`Shop_Id`)
            REFERENCES `mydb`.`Shops` (`Shop_Id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_UserShopLinks_Users1`
        FOREIGN KEY (`Users_User_Id`)
            REFERENCES `mydb`.`Users` (`User_Id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`UserStampLinks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`UserStampLinks` (
    `User_Stamp_Link_Id` INT NOT NULL AUTO_INCREMENT,
    `User_Id` INT NOT NULL,
    `Stamp_Board_Id` INT NOT NULL,
    `User_Stamp_Position` INT NOT NULL,
    PRIMARY KEY (`User_Stamp_Link_Id`, `User_Id`, `Stamp_Board_Id`),
    INDEX `fk_UserStampLinks_Users1_idx` (`User_Id` ASC) VISIBLE,
    INDEX `fk_UserStampLinks_StampBoards1_idx` (`Stamp_Board_Id` ASC) VISIBLE,
    CONSTRAINT `fk_UserStampLinks_Users1`
        FOREIGN KEY (`User_Id`)
            REFERENCES `mydb`.`Users` (`User_Id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_UserStampLinks_StampBoards1`
        FOREIGN KEY (`Stamp_Board_Id`)
            REFERENCES `mydb`.`StampBoards` (`Stamp_Board_Id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION)
    ENGINE = InnoDB;