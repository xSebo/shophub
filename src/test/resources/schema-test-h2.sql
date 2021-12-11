SET MODE MYSQL;
SET IGNORECASE=TRUE;

-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- DROP SCHEMA IF EXISTS `mydb` CASCADE;
-- CREATE SCHEMA IF NOT EXISTS `mydb`;
-- USE `mydb`;
DROP ALL OBJECTS;


CREATE TABLE SPRING_SESSION (
                                PRIMARY_ID CHAR(36) NOT NULL,
                                SESSION_ID CHAR(36) NOT NULL,
                                CREATION_TIME BIGINT NOT NULL,
                                LAST_ACCESS_TIME BIGINT NOT NULL,
                                MAX_INACTIVE_INTERVAL INT NOT NULL,
                                EXPIRY_TIME BIGINT NOT NULL,
                                PRINCIPAL_NAME VARCHAR(100),
                                CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
                                           SESSION_PRIMARY_ID CHAR(36) NOT NULL,
                                           ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
                                           ATTRIBUTE_BYTES LONGVARBINARY NOT NULL,
                                           CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
                                           CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);



-- -----------------------------------------------------
-- Table `mydb`.`Two_Factor_Methods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Two_Factor_Methods` (
                                                    `Two_Factor_Method_Id` INT NOT NULL,
                                                    `Two_Factor_Method_Name` VARCHAR(45) NOT NULL,
                                                    PRIMARY KEY (`Two_Factor_Method_Id`))
    ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `mydb`.`Admin_Types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Admin_Types` (
                                                    `Admin_Type_Id` INT NOT NULL,
                                                    `Admin_Type_Name` VARCHAR(45) NOT NULL,
                                                    PRIMARY KEY (`Admin_Type_Id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Users` (
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
                                                      REFERENCES `Two_Factor_Methods` (`Two_Factor_Method_Id`)
                                                      ON DELETE NO ACTION
                                                      ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tags` (
                                             `Tag_Id` INT NOT NULL AUTO_INCREMENT,
                                             `Tag_Name` VARCHAR(45) NOT NULL,
                                             PRIMARY KEY (`Tag_Id`))
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`User_Favourite_Tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `User_Favourite_Tags` (
    `User_Favourite_Tag_Id` INT NOT NULL AUTO_INCREMENT,
    `User_Id` INT NOT NULL,
    `Tag_Id` INT NOT NULL,
    PRIMARY KEY (`User_Favourite_Tag_Id`, `User_Id`, `Tag_Id`),
    CONSTRAINT `fk_User_Favourite_Tags_Tags1`
        FOREIGN KEY (`Tag_Id`)
            REFERENCES `Tags` (`Tag_Id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_User_Favourite_Tags_Users1`
        FOREIGN KEY (`User_Id`)
            REFERENCES `Users` (`User_Id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Stamp_Boards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Stamp_Boards` (
                                                     `Stamp_Board_Id` INT NOT NULL AUTO_INCREMENT,
                                                     `Stamp_Board_Size` INT NOT NULL,
                                                     `Stamp_Board_Colour` VARCHAR(7),
                                                     `Stamp_Board_Icon` varchar(255),
                                                     PRIMARY KEY (`Stamp_Board_Id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Rewards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Rewards` (
                                                `Reward_Id` INT NOT NULL AUTO_INCREMENT,
                                                `Reward_Name` VARCHAR(45) NOT NULL,
                                                `Reward_Stamp_Location` INT NULL,
                                                `Stamp_Board_Id` INT NOT NULL,
                                                PRIMARY KEY (`Reward_Id`, `Stamp_Board_Id`),
                                                CONSTRAINT `fk_Rewards_Stamp_Boards1`
                                                    FOREIGN KEY (`Stamp_Board_Id`)
                                                        REFERENCES `Stamp_Boards` (`Stamp_Board_Id`)
                                                        ON DELETE NO ACTION
                                                        ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Categories` (
    `Category_Id` INT NOT NULL AUTO_INCREMENT,
    `Category_Name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`Category_Id`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Shops`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Shops` (
                                              `Shop_Id` INT NOT NULL AUTO_INCREMENT,
                                              `Shop_Name` VARCHAR(45) NOT NULL,
                                              `Shop_Description` VARCHAR(250) NOT NULL,
                                              `Shop_Website` VARCHAR(45) NOT NULL,
                                              `Shop_Earnings` INT NOT NULL,
                                              `Shop_Countries` VARCHAR(150) NOT NULL,
                                              `Shop_Image` VARCHAR(150),
                                              `Shop_Banner` VARCHAR(150),
                                              `Shop_Active` TINYINT NOT NULL,
                                              `Stamp_Board_Id` INT NOT NULL,
                                              `Category_Id` INT NOT NULL,
                                              PRIMARY KEY (`Shop_Id`, `Stamp_Board_Id`, `Category_Id`),
                                                CONSTRAINT `fk_Shops_Stamp_Boards1`
                                                  FOREIGN KEY (`Stamp_Board_Id`)
                                                    REFERENCES `Stamp_Boards` (`Stamp_Board_Id`)
                                                    ON DELETE NO ACTION
                                                    ON UPDATE NO ACTION,
                                                CONSTRAINT `fk_Shops_Categories1`
                                                  FOREIGN KEY (`Category_Id`)
                                                    REFERENCES `Categories` (`Category_Id`)
                                                    ON DELETE NO ACTION
                                                    ON UPDATE NO ACTION )
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Shop_Tag_Links`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Shop_Tag_Links` (
                                                       `Shop_Tag_Link_Id` INT NOT NULL AUTO_INCREMENT,
                                                       `Shop_Id` INT NOT NULL,
                                                       `Tag_Id` INT NOT NULL,
                                                       PRIMARY KEY (`Shop_Tag_Link_Id`, `Shop_Id`, `Tag_Id`),
                                                       CONSTRAINT `fk_Shop_Tag_Links_Shops1`
                                                           FOREIGN KEY (`Shop_Id`)
                                                               REFERENCES `Shops` (`Shop_Id`)
                                                               ON DELETE NO ACTION
                                                               ON UPDATE NO ACTION,
                                                       CONSTRAINT `fk_Shop_Tag_Links_Tags1`
                                                           FOREIGN KEY (`Tag_Id`)
                                                               REFERENCES `Tags` (`Tag_Id`)
                                                               ON DELETE NO ACTION
                                                               ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User_Permissions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `User_Permissions` (
                                                         `User_Permission_Id` INT NOT NULL AUTO_INCREMENT,
                                                         `User_Id` INT NOT NULL,
                                                         `Shop_Id` INT,
                                                         `Admin_Type_Id` INT NOT NULL,
                                                         PRIMARY KEY (`User_Permission_Id`, `User_Id`, `Shop_Id`, `Admin_Type_Id`),
                                                         CONSTRAINT `fk_User_Permissions_Users1`
                                                             FOREIGN KEY (`User_Id`)
                                                                 REFERENCES `Users` (`User_Id`)
                                                                 ON DELETE NO ACTION
                                                                 ON UPDATE NO ACTION,
                                                         CONSTRAINT `fk_User_Permissions_Shops1`
                                                             FOREIGN KEY (`Shop_Id`)
                                                                 REFERENCES `Shops` (`Shop_Id`)
                                                                 ON DELETE NO ACTION
                                                                 ON UPDATE NO ACTION,
                                                         CONSTRAINT `fk_User_Permissions_Admin_Types1`
                                                             FOREIGN KEY (`Admin_Type_Id`)
                                                                 REFERENCES `Admin_Types` (`Admin_Type_Id`)
                                                                 ON DELETE NO ACTION
                                                                 ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User_Shop_Links`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `User_Shop_Links` (
                                                        `User_Shop_Link_Id` INT NOT NULL AUTO_INCREMENT,
                                                        `Shop_Id` INT NOT NULL,
                                                        `User_Id` INT NOT NULL,
                                                        PRIMARY KEY (`User_Shop_Link_Id`, `Shop_Id`, `User_Id`),
                                                        CONSTRAINT `fk_User_Shop_Links_Shops1`
                                                            FOREIGN KEY (`Shop_Id`)
                                                                REFERENCES `Shops` (`Shop_Id`)
                                                                ON DELETE NO ACTION
                                                                ON UPDATE NO ACTION,
                                                        CONSTRAINT `fk_User_Shop_Links_Users1`
                                                            FOREIGN KEY (`User_Id`)
                                                                REFERENCES `Users` (`User_Id`)
                                                                ON DELETE NO ACTION
                                                                ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User_Stamp_Links`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `User_Stamp_Boards` (
                                                          `User_Stamp_Board_Id` INT NOT NULL AUTO_INCREMENT,
                                                          `User_Id` INT NOT NULL,
                                                          `Stamp_Board_Id` INT NOT NULL,
                                                          `User_Stamp_Position` INT NOT NULL,
                                                          PRIMARY KEY (`User_Stamp_Board_Id`, `User_Id`, `Stamp_Board_Id`),
                                                          CONSTRAINT `fk_User_Stamp_Links_Users1`
                                                              FOREIGN KEY (`User_Id`)
                                                                  REFERENCES `Users` (`User_Id`)
                                                                  ON DELETE NO ACTION
                                                                  ON UPDATE NO ACTION,
                                                          CONSTRAINT `fk_User_Stamp_Links_StampBoards1`
                                                              FOREIGN KEY (`Stamp_Board_Id`)
                                                                  REFERENCES `Stamp_Boards` (`Stamp_Board_Id`)
                                                                  ON DELETE NO ACTION
                                                                  ON UPDATE NO ACTION)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Socials`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Socials` (
                                              `Social_Id` INT NOT NULL AUTO_INCREMENT,
                                              `Shop_Id` INT NOT NULL,
                                              `Social_Platform` VARCHAR(45),
                                              `Social_Name` VARCHAR(45),
                                              PRIMARY KEY (`Social_Id`, `Shop_Id`),
											  CONSTRAINT `fk_Socials1`
													FOREIGN KEY (`Shop_Id`)
														REFERENCES `Shops` (`Shop_Id`)
																ON DELETE NO ACTION
																ON UPDATE NO ACTION)
                                                
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Events` (
    `Event_Id` INT NOT NULL AUTO_INCREMENT,
    `Event_Name` VARCHAR(45) NOT NULL,
    PRIMARY KEY(`Event_Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Logs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Logs` (
    `Log_Id` INT NOT NULL AUTO_INCREMENT,
    `Event_Id` INT NOT NULL,
    `User_Id` INT NOT NULL,
    `Log_Details` VARCHAR(150) NOT NULL,
    `Log_Date_Time` DATETIME NOT NULL,
    `Log_Super_Admin` TINYINT NOT NULL,
    PRIMARY KEY(`Log_Id`, `Event_Id`, `User_Id`),
    CONSTRAINT `fk_Events1`
        FOREIGN KEY (`Event_Id`)
            REFERENCES `Events` (`Event_Id`)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION,
    CONSTRAINT `fk_Users1`
        FOREIGN KEY (`User_Id`)
            REFERENCES `Users` (`User_Id`)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
    )
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

INSERT INTO Tags (Tag_Name) VALUES ('football');
INSERT INTO Tags (Tag_Name) VALUES ('fashion');
INSERT INTO Tags (Tag_Name) VALUES ('electronics');
INSERT INTO Tags (Tag_Name) VALUES ('coffee');
INSERT INTO Tags (Tag_Name) VALUES ('art');
INSERT INTO Tags (Tag_Name) VALUES ('pets');
INSERT INTO Tags (Tag_Name) VALUES ('clothes');
INSERT INTO Tags (Tag_Name) VALUES ('designer');
INSERT INTO Tags (Tag_Name) VALUES ('groceries');
INSERT INTO Tags (Tag_Name) VALUES ('cars');
INSERT INTO Tags (Tag_Name) VALUES ('hiking');
INSERT INTO Tags (Tag_Name) VALUES ('cooking');
INSERT INTO Tags (Tag_Name) VALUES ('furniture');
INSERT INTO Tags (Tag_Name) VALUES ('gaming');
INSERT INTO Tags (Tag_Name) VALUES ('travelling');
INSERT INTO Tags (Tag_Name) VALUES ('beauty');
INSERT INTO Tags (Tag_Name) VALUES ('eco-friendly');
INSERT INTO Tags (Tag_Name) VALUES ('decorations');
INSERT INTO Tags (Tag_Name) VALUES ('photography');

INSERT INTO Events (Event_Name) VALUES ('New User');
INSERT INTO Events (Event_Name) VALUES ('Failed Login');
INSERT INTO Events (Event_Name) VALUES ('Successful Login');
INSERT INTO Events (Event_Name) VALUES ('User Details Changed');
INSERT INTO Events (Event_Name) VALUES ('User Removed');
INSERT INTO Events (Event_Name) VALUES ('New Shop');
INSERT INTO Events (Event_Name) VALUES ('Deleted Shop');
INSERT INTO Events (Event_Name) VALUES ('New Favourite Business');
INSERT INTO Events (Event_Name) VALUES ('New Shop User');
INSERT INTO Events (Event_Name) VALUES ('Shop Details Changed');
INSERT INTO Events (Event_Name) VALUES ('Shop Activity Toggled');
INSERT INTO Events (Event_Name) VALUES ('Image Inserted');
INSERT INTO Events (Event_Name) VALUES ('New Stamp Board');
INSERT INTO Events (Event_Name) VALUES ('New Reward');
INSERT INTO Events (Event_Name) VALUES ('New Tag');
INSERT INTO Events (Event_Name) VALUES ('New Shop Tag Link');
INSERT INTO Events (Event_Name) VALUES ('New Social');
INSERT INTO Events (Event_Name) VALUES ('New User Permission');
INSERT INTO Events (Event_Name) VALUES ('Stamp Board Updated');
INSERT INTO Events (Event_Name) VALUES ('Reward Deleted');
INSERT INTO Events (Event_Name) VALUES ('Shop Updated');
INSERT INTO Events (Event_Name) VALUES ('UserShopLink Deleted');
INSERT INTO Events (Event_Name) VALUES ('UserShopLink Inserted');
INSERT INTO Events (Event_Name) VALUES ('ShopWebsite Updated');
INSERT INTO Events (Event_Name) VALUES ('UserStampBoard Updated');
INSERT INTO Events (Event_Name) VALUES ('UserStampBoard Inserted');
INSERT INTO Events (Event_Name) VALUES ('UserFavouriteTag Inserted');
INSERT INTO Events (Event_Name) VALUES ('Social Updated');