CREATE TABLE side_missions(
id INTEGER PRIMARY KEY,
   category  VARCHAR(5) NOT NULL
  ,hero      VARCHAR(13)
  ,name      VARCHAR(26) NOT NULL
  ,reward    VARCHAR(38)
  ,expansion VARCHAR(39) NOT NULL
  ,expansion_id INTEGER
);
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','Fenn Signis','Brushfire','Veteran Prowess','Core Set');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','Diala Passil','Temptation','Shu Yen''s Lightsaber','Core Set');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','Jyn Odan','High Moon','Peacemaker','Core Set');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','Gaarkhan','Indebted','Life Debt','Core Set');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','Mak Eshka''rey','Loose Cannon','Shadow Suit','Core Set');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','Gideon Argus','Friends of Old','Fearless Leader','Core Set');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'Sorry About the Mess','Han Solo (Scoundrel)','Core Set');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'Homecoming','Luke Skywalker (Hero of the Rebellion)','Core Set');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'The Spice Job','Chewbacca (Loyal Wookiee)','Core Set');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'Target of Opportunity','Rebel Saboteur','Core Set');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Grey',NULL,'Viper''s Den','Rebel Recon','Core Set');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Grey',NULL,'A Simple Task','Adrenal Implant','Core Set');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Grey',NULL,'Generous Donations','Credits','Core Set');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Grey',NULL,'Luxury Cruise','Allied Operations','Core Set');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Grey',NULL,'Sympathy for the Rebellion','The Ways of the Force','Core Set');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'Open to Interpretation',NULL,'R2-D2 and C-3PO Ally Pack');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Grey',NULL,'Celebration','Intimidation','Chewbacca Ally Pack');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Grey',NULL,'Imperial Entanglements','Quickdraw Holster','Han Solo Ally Pack');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'Armed and Operational','Rebel Saboteur','Rebel Saboteurs Ally Pack');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'Brace for Impact','Rebel Trooper','Rebel Troopers Ally Pack');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','Biv Bodhrik','Past Life Enemies','Hunt Them Down','Twin Shadows');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','Saska Teft','Shady Dealings','Tech Goggles','Twin Shadows');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'Hunted Down','Han Solo','Twin Shadows');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Grey',NULL,'Canyon Run','Counterparts','Twin Shadows');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'Brute Force','Wookiee Warrior','Wookiee Warriors Ally Pack');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'Precious Cargo','Alliance Smuggler','Alliance Smuggler Ally Pack');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','Verena Talos','Know Your Enemy','Iron Hand','Return to Hoth');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','Loku Kanoloa','Constant Vigilance','Battle Vision','Return to Hoth');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','MHD-19','Preventative Measures','Systems Upgrade','Return to Hoth');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'Communication Breakdown','Leia Organa','Leia Organa Ally Pack');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'Snowcrash','Echo Base Trooper','Echo Base Troopers Ally Pack');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'Reclamation','Lando Calrissian','The Bespin Gambit');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','Davith Elso','Reclassified','Radiant Holocron','The Bespin Gambit');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','Murne Rin','Panic in the Streets','Cam Droid','The Bespin Gambit');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Grey',NULL,'Freedom Fighters','Lobot''s Favor','The Bespin Gambit');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Grey',NULL,'Cloud City''s Secret','Under the Radar','The Bespin Gambit');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Grey',NULL,'Hostile Takeover','Relief Effort','The Bespin Gambit');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Grey',NULL,'Into the Unknown','Cautious Approach','ISB Infiltrators Villain Pack');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Grey',NULL,'Paying Debts','Lady Luck','Lando Calrissian Ally Pack');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'Deadly Transmission','Obi-Wan Kenobi','Obi-Wan Kenobi Ally Pack');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Grey',NULL,'Perilous Hunt',NULL,'Jabba''s Realm');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','Onar Koma','Extortion','Haymaker','Jabba''s Realm');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','Vinto Hreeda','A Hero''s Welcome','Desperado','Jabba''s Realm');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','Shyla Varad','Born from Death','Mandalorian Heritage','Jabba''s Realm');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'Strike Force Xesh','Alliance Ranger','Alliance Rangers Ally Pack');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'A Light in the Darkness','Luke Skywalker (Jedi Knight)','Luke Skywalker Jedi Knight Ally Pack');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Grey',NULL,'Phantom Extraction','C1-10P and Hera Syndulla','Hera Syndulla and C1-10P Ally Pack');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','Ko-Tun Feralo','Extraction','Alliance Efficiency','Heart of the Empire');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','Drokatta','Unfinished Business','Wookiee Roar','Heart of the Empire');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','Jarrod Kelvin','Test of Metal','X-8 Upgrade','Heart of the Empire');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Grey',NULL,'Civil Unrest','Populist Support','Heart of the Empire');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'Home Invasion','Ahsoka Tano','Ahsoka Tano Ally Pack');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','Tress Hacnua','Duel on Devaron','Wholeness','Tyrants of Lothal');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Red','CT-1701','Sands of Seelos','Bullseye!','Tyrants of Lothal');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'Race on Ryloth','Any 1 Spectre Ally','Tyrants of Lothal');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'Sands of Seelos','Any 1 Spectre Ally','Tyrants of Lothal');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Grey',NULL,'Call to Action','Nova Cell Leader','Tyrants of Lothal');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Grey',NULL,'The Final Order','Credits','Tyrants of Lothal');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'Executive Overreach','Any 1 Spectre Ally','Ezra Bridger and Kanan Jarrus Ally Pack');
INSERT INTO side_missions(category,hero,name,reward,expansion) VALUES ('Green',NULL,'Intimidation Factor','Any 1 Spectre Ally','Sabine Wren and Zeb Orrelios Ally Pack');
