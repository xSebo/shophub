# noinspection LossyEncodingForFile

INSERT INTO Users (User_First_Name, User_Last_Name, User_Email, User_Password, User_Profile_Picture, Two_Factor_Method_Id) VALUES ('randeep','ccovery','randeepccovery@email.com','$2a$10$YnDtWkRyd3WfYb5CDHBNx.yfuWPW7dOg86NteaEAyaEmaRywfwueK','imgs/defaultProfilePicture.jpg',2);
INSERT INTO Users (User_First_Name, User_Last_Name, User_Email, User_Password, User_Profile_Picture, Two_Factor_Method_Id) VALUES ('kaela','ra','kaelara@email.com','$2a$10$YnDtWkRyd3WfYb5CDHBNx.yfuWPW7dOg86NteaEAyaEmaRywfwueK','imgs/defaultProfilePicture.jpg',2);
INSERT INTO Users (User_First_Name, User_Last_Name, User_Email, User_Password, User_Profile_Picture, Two_Factor_Method_Id) VALUES ('kerra','oneypenny','kerraoneypenny@email.com','$2a$10$YnDtWkRyd3WfYb5CDHBNx.yfuWPW7dOg86NteaEAyaEmaRywfwueK','imgs/defaultProfilePicture.jpg',1);
INSERT INTO Users (User_First_Name, User_Last_Name, User_Email, User_Password, User_Profile_Picture, Two_Factor_Method_Id) VALUES ('morag','za','moragza@email.com','$2a$10$YnDtWkRyd3WfYb5CDHBNx.yfuWPW7dOg86NteaEAyaEmaRywfwueK','imgs/defaultProfilePicture.jpg',1);
INSERT INTO Users (User_First_Name, User_Last_Name, User_Email, User_Password, User_Profile_Picture, Two_Factor_Method_Id) VALUES ('waqas','ediego','waqasediego@email.com','$2a$10$YnDtWkRyd3WfYb5CDHBNx.yfuWPW7dOg86NteaEAyaEmaRywfwueK','imgs/defaultProfilePicture.jpg',1);
INSERT INTO Users (User_First_Name, User_Last_Name, User_Email, User_Password, User_Profile_Picture, Two_Factor_Method_Id) VALUES ('kobe','hamsi','kobehamsi@email.com','$2a$10$YnDtWkRyd3WfYb5CDHBNx.yfuWPW7dOg86NteaEAyaEmaRywfwueK','imgs/defaultProfilePicture.jpg',2);
INSERT INTO Users (User_First_Name, User_Last_Name, User_Email, User_Password, User_Profile_Picture, Two_Factor_Method_Id) VALUES ('mhairi','engblom','mhairiengblom@email.com','$2a$10$YnDtWkRyd3WfYb5CDHBNx.yfuWPW7dOg86NteaEAyaEmaRywfwueK','imgs/defaultProfilePicture.jpg',2);
INSERT INTO Users (User_First_Name, User_Last_Name, User_Email, User_Password, User_Profile_Picture, Two_Factor_Method_Id) VALUES ('deanne','aqib','deanneaqib@email.com','$2a$10$YnDtWkRyd3WfYb5CDHBNx.yfuWPW7dOg86NteaEAyaEmaRywfwueK','imgs/defaultProfilePicture.jpg',1);
INSERT INTO Users (User_First_Name, User_Last_Name, User_Email, User_Password, User_Profile_Picture, Two_Factor_Method_Id) VALUES ('kian','chesler','kianchesler@email.com','$2a$10$YnDtWkRyd3WfYb5CDHBNx.yfuWPW7dOg86NteaEAyaEmaRywfwueK','imgs/defaultProfilePicture.jpg',2);
INSERT INTO Users (User_First_Name, User_Last_Name, User_Email, User_Password, User_Profile_Picture, Two_Factor_Method_Id) VALUES ('freddie','lachy','freddielachy@email.com','$2a$10$YnDtWkRyd3WfYb5CDHBNx.yfuWPW7dOg86NteaEAyaEmaRywfwueK','imgs/defaultProfilePicture.jpg',2);
INSERT INTO User_Permissions (User_ID, Shop_ID, Admin_Type_Id) VALUES (1,1,3);

INSERT INTO Users (User_First_Name, User_Last_Name, User_Email, User_Password, User_Profile_Picture, Two_Factor_Method_Id) VALUES ('keeganjack','hieb','keeganjackhieb@email.com','$2a$10$YnDtWkRyd3WfYb5CDHBNx.yfuWPW7dOg86NteaEAyaEmaRywfwueK','imgs/defaultProfilePicture.jpg',2);
INSERT INTO Users (User_First_Name, User_Last_Name, User_Email, User_Password, User_Profile_Picture, Two_Factor_Method_Id) VALUES ('faren','krosschell','farenkrosschell@email.com','$2a$10$YnDtWkRyd3WfYb5CDHBNx.yfuWPW7dOg86NteaEAyaEmaRywfwueK','imgs/defaultProfilePicture.jpg',1);
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO User_Permissions (User_ID, Shop_ID, Admin_Type_Id) VALUES (3,2,2);
INSERT INTO User_Permissions (User_ID, Shop_ID, Admin_Type_Id) VALUES (12,3,2);
SET FOREIGN_KEY_CHECKS=1;

INSERT INTO Users (User_First_Name, User_Last_Name, User_Email, User_Password, User_Profile_Picture, Two_Factor_Method_Id) VALUES ('aimen','enifer','aimenenifer@email.com','$2a$10$YnDtWkRyd3WfYb5CDHBNx.yfuWPW7dOg86NteaEAyaEmaRywfwueK','imgs/defaultProfilePicture.jpg',1);
INSERT INTO User_Permissions (User_ID, Shop_ID, Admin_Type_Id) VALUES (13,1,3);



INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (26,'#c6ee4b','imgs/vibezStamp.jpg');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('10% off',3,2);
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('free drink',6,2);
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('3 for 1 deal',8,2);
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('£10 gift voucher',13,2);
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('crate of cans for £15',17,2);
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('free crate of cans',20,2);
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('free crate of limited edition cans',26,2);
INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (10,'#ff0000','stamp.png');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('2 for 1',8,3);
INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (10,'#ff0000','stamp.png');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('10% off',7,4);
INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (10,'#ff0000','stamp.png');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('£5 off',10,5);
INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (10,'#ff0000','stamp.png');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('2 for 1',6,6);
INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (10,'#ff0000','stamp.png');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('£5 off',9,7);
INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (10,'#ff0000','stamp.png');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('2 for 1',8,8);
INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (10,'#ff0000','stamp.png');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('10% off',5,9);
INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (10,'#ff0000','stamp.png');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('2 for 1',6,10);
INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (10,'#ff0000','stamp.png');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('2 for 1',8,11);
INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (10,'#ff0000','stamp.png');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('£5 off',3,12);
INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (10,'#ff0000','stamp.png');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('5% off',3,13);
INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (10,'#ff0000','stamp.png');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('2 for 1',8,14);
INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (10,'#ff0000','stamp.png');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('10% off',9,15);
INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (10,'#ff0000','stamp.png');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('10% off',3,16);
INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (10,'#ff0000','stamp.png');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('2 for 1',9,17);
INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (10,'#ff0000','stamp.png');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('10% off',8,18);
INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (10,'#ff0000','stamp.png');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('10% off',8,19);
INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (10,'#ff0000','stamp.png');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('10% off',7,20);
INSERT INTO Stamp_Boards (Stamp_Board_Size, Stamp_Board_Colour, Stamp_Board_Icon) VALUES (10,'#ff0000','stamp.png');
INSERT INTO Rewards (Reward_Name, Reward_Stamp_Location, Stamp_Board_Id) VALUES ('5% off',3,21);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('Vibez Drinks','We are not just your ordinary soft drinks company. Vibez Drinks is the best sugar free, plant based soft drink alternative for everybody. Providing you with nutritional goodness that creates positive vibes and energy.','https://https://www.vibezdrinks.co.uk/','21722','ge georgia','imgs/vibezLogo.png','imgs/vibezdrinkbanner.png',1,2,2);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('Hudson Group','','https://hudsongroup.com','21722','ge georgia','imgs/HudsongroupLogo.png','imgs/Xmas-Logo-1_410x.jpg',1,2,2);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('dogsdogsdogs','','https://www.dogsdogsdogs.co.uk','27121','ir iran islamic republic of','shopPic.png','imgs/shopBanner.png',0,3,4);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('Sauer, Hoppe and Hartmann','','sauerhoppeandhartmann.com','12603','kp korea democratic peoples republic of','shopPic.png','shopBanner.png',1,4,2);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('Flatley LLC','','flatleyllc.com','34968','tn tunisia','shopPic.png','shopBanner.png',1,5,6);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('Johnson Inc','','johnsoninc.com','31198','gs south georgia and the south sandwich islands','shopPic.png','shopBanner.png',1,6,4);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('Hills, Corkery and Maggio','','hillscorkeryandmaggio.com','16424','om oman','shopPic.png','shopBanner.png',0,7,7);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('Dickinson Group','','dickinsongroup.com','26474','ga gabon','shopPic.png','shopBanner.png',1,8,6);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('Balistreri LLC','','balistrerillc.com','38659','es spain','shopPic.png','shopBanner.png',1,9,2);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('Gaylord, Ankunding and Stanton','','gaylordankundingandstanton.com','35281','ba bosnia and herzegovina','shopPic.png','shopBanner.png',1,10,7);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('Hilpert Group','','hilpertgroup.com','3304','lv latvia','shopPic.png','shopBanner.png',0,11,7);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('Kling - Bauch','','klingbauch.com','35570','re reunion','shopPic.png','shopBanner.png',1,12,4);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('Larkin - King','','larkinking.com','7492','dk denmark','shopPic.png','shopBanner.png',1,13,4);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('Muller, Ullrich and Kunze','','mullerullrichandkunze.com','6456','gw guineabissau','shopPic.png','shopBanner.png',0,14,6);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('Friesen - Armstrong','','friesenarmstrong.com','29922','mw malawi','shopPic.png','shopBanner.png',0,15,3);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('McDermott Inc','','mcdermottinc.com','15319','gm gambia','shopPic.png','shopBanner.png',0,16,4);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('Moen - Raynor','','moenraynor.com','9864','kg kyrgyzstan','shopPic.png','shopBanner.png',1,17,3);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('Hilpert - Berge','','hilpertberge.com','11447','ar argentina','shopPic.png','shopBanner.png',0,18,3);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('Padberg Inc','','padberginc.com','15997','kr korea republic of','shopPic.png','shopBanner.png',1,19,2);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('Senger - Ryan','','sengerryan.com','25887','iq iraq','shopPic.png','shopBanner.png',0,20,3);
INSERT INTO Shops (Shop_Name, Shop_Description, Shop_Website, Shop_Earnings, Shop_Countries, Shop_Image, Shop_Banner, Shop_Active, Stamp_Board_Id, Category_Id) VALUES ('Fay - Koepp','','faykoepp.com','13482','gf french guiana','shopPic.png','shopBanner.png',1,21,2);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (1,7);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (2,9);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (2,17);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (2,10);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (3,19);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (3,9);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (3,5);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (3,16);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (3,6);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (3,3);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (3,10);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (3,14);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (3,17);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (3,15);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (3,2);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (3,8);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (3,1);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (3,7);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (3,18);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (4,17);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (4,13);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (4,2);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (4,16);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (4,12);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (4,14);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (4,1);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (4,9);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (4,11);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (4,19);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (4,3);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (5,10);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (6,10);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (6,6);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (6,11);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (6,7);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (6,13);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (6,16);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (6,14);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (7,18);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (7,12);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (7,3);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (7,7);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (7,1);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (7,19);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (7,2);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (7,5);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (7,16);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (8,13);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (8,1);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (8,8);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (8,11);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (8,16);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (9,2);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (9,14);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (9,10);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (9,12);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (9,17);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (9,13);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (9,5);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (9,9);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (9,6);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (9,16);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (9,18);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (9,4);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (9,15);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (9,1);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (9,3);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (9,11);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (9,7);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (10,8);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (10,16);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (10,3);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (11,6);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (11,16);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (11,13);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (11,1);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (11,2);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (11,11);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (11,19);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (11,5);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (11,10);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (11,9);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (11,3);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (11,14);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (11,17);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (11,8);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (11,12);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (11,18);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (12,16);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (12,18);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (12,11);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (12,19);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (12,13);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (12,14);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (12,17);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (12,12);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (12,1);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (12,10);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (12,6);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (12,3);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (12,5);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (12,15);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (12,7);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (12,4);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (12,2);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (12,9);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (13,13);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (13,14);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (13,3);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (13,17);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (13,10);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (13,4);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (13,11);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (13,18);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (13,7);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (13,19);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (13,9);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (13,1);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (13,6);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (13,16);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (13,8);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (14,3);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (14,17);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (14,5);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (14,13);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (14,11);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (14,14);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (15,2);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (15,10);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (15,6);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (15,11);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (15,9);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (15,7);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (15,4);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (15,17);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (15,19);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (15,15);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (15,8);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (15,5);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (16,19);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (16,2);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (16,3);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (16,4);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (16,15);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (16,17);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (16,9);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (16,6);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (16,1);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (16,16);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (17,16);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (17,12);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (17,7);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (17,3);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (17,6);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (17,1);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (17,5);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (17,13);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (18,10);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (18,7);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (18,1);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (18,13);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (18,2);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (18,18);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (19,5);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (19,8);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (19,1);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (19,14);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (19,2);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (19,19);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (19,7);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (19,15);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (20,18);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (20,7);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (20,6);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (20,14);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (20,12);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (20,1);
INSERT INTO Shop_Tag_Links (Shop_Id, Tag_Id) VALUES (20,3);
