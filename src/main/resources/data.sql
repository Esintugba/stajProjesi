INSERT INTO USERS VALUES('user1','{bcrypt}$2a$10$meu4fv15TXkUi28sl0b54.6HULCKAOxex1W/lKangx.3FqRVPzGEW',TRUE);
INSERT INTO USERS VALUES('user2','{bcrypt}$2a$10$NMxOnwKzcxYwlk/.voK5WeIIwCbYUSTY.aC1lKcRPh8mluq8xXX8i',TRUE);
INSERT INTO USERS VALUES('user3','{bcrypt}$2a$10$KAv4y7cs7fCN/.VOA8TK1e7BVj9Q4mUM1.a47.32WOJ1zJfZakU7C',TRUE);

INSERT INTO AUTHORITIES VALUES('user1','ROLE_USER');
INSERT INTO AUTHORITIES VALUES('user2','ROLE_USER');
INSERT INTO AUTHORITIES VALUES('user2','ROLE_EDITOR');
INSERT INTO AUTHORITIES VALUES('user3','ROLE_USER');
INSERT INTO AUTHORITIES VALUES('user3','ROLE_EDITOR');
INSERT INTO AUTHORITIES VALUES('user3','ROLE_ADMIN');

INSERT INTO t_company(id,company_code,company_name,address,e_mail) VALUES (1,1234,'Arzum','Bursa','arzum@gmail.com');
INSERT INTO t_company(id,company_code,company_name,address,e_mail) VALUES (2,2345,'Samsung','İstanbul','samsung@gmail.com');
INSERT INTO t_company(id,company_code,company_name,address,e_mail) VALUES (3,3456,'Xiaomi','İstanbul','xiaomi@gmail.com');
INSERT INTO t_company(id,company_code,company_name,address,e_mail) VALUES (4,4567,'Philips','İstanbul','philips@gmail.com');
INSERT INTO t_company(id,company_code,company_name,address,e_mail) VALUES (5,5678,'Karaca','Ankara','karaca@gmail.com');
INSERT INTO t_company(id,company_code,company_name,address,e_mail) VALUES (6,6789,'Apple','Kocaeli','apple@gmail.com');
INSERT INTO t_company(id,company_code,company_name,address,e_mail) VALUES (7,7890,'Casper','İstanbul','casper@gmail.com');
INSERT INTO t_company(id,company_code,company_name,address,e_mail) VALUES (8,1239,'Vestel','Ankara','vestel@gmail.com');
INSERT INTO t_company(id,company_code,company_name,address,e_mail) VALUES (9,2349,'Arçelik','İstanbul','arçelik@gmail.com');
INSERT INTO t_company(id,company_code,company_name,address,e_mail) VALUES (10,3459,'Beko','İstanbul','beko@gmail.com');
INSERT INTO t_company(id,company_code,company_name,address,e_mail) VALUES (11,4569,'TCL','İstanbul','tcl@gmail.com');
INSERT INTO t_company(id,company_code,company_name,address,e_mail) VALUES (12,5679,'Lenovo','İstanbul','lenovo@gmail.com');
INSERT INTO t_company(id,company_code,company_name,address,e_mail) VALUES (13,6788,'Acer','Ankara','acer@gmail.com');
INSERT INTO t_company(id,company_code,company_name,address,e_mail) VALUES (14,7899,'Oppo','Kocaeli','oppo@gmail.com');
INSERT INTO t_company(id,company_code,company_name,address,e_mail) VALUES (15,1028,'Dyson','İstanbul','dyson@gmail.com');


INSERT INTO t_product(id,product_code,product_name,categories,explanation,price,company_id) VALUES (1,999,'Arzum Mixset Elektrikli El Mikseri','Mutfak Ürünleri','Kremaları çırpmak ve hamurları karıştırmak için kullanılan el mikseri',2500,1);
INSERT INTO t_product(id,product_code,product_name,categories,explanation,price,company_id) VALUES (2,888,'Samsung Galaxy S21 Ultra','Cep Telefonları','Samsung un amiral gemisi telefonu, gelişmiş kameralar ve yüksek performans sunar',6780,2);
INSERT INTO t_product(id,product_code,product_name,categories,explanation,price,company_id) VALUES (3,777,'Xiaomi Mi 11','Cep Telefonları','Xiaominin güçlü özelliklere sahip akıllı telefon modeli',4500,3);
INSERT INTO t_product(id,product_code,product_name,categories,explanation,price,company_id) VALUES (4,666,'Philips PowerPro Expert Elektrikli Süpürge','Ev Aletleri','Yüksek emiş gücüne sahip etkili bir elektrikli süpürge',4899,4);
INSERT INTO t_product(id,product_code,product_name,categories,explanation,price,company_id) VALUES (5,555,'Karaca Multiblender Seti','Mutfak Ürünleri',' Çeşitli kesme ve karıştırma işlemleri için kullanılan bir mutfak robotu',7550,5);
INSERT INTO t_product(id,product_code,product_name,categories,explanation,price,company_id) VALUES (6,444,'MacBook Pro','Bilgisayar ve Teknoloji Ürünleri','Apple ın güçlü performansa sahip, yüksek çözünürlüklü Retina ekranlı dizüstü bilgisayar serisi',63995,6);
INSERT INTO t_product(id,product_code,product_name,categories,explanation,price,company_id) VALUES (7,333,'Casper Excalibur G900','Bilgisayar ve Teknoloji Ürünleri','Casper ın oyuncular için tasarlanmış güçlü dizüstü bilgisayarı',18500,7);
INSERT INTO t_product(id,product_code,product_name,categories,explanation,price,company_id) VALUES (8,222,'Vestel Venus 4K Uydu Alıcılı Smart TV','Televizyon ve Ses Sistemleri','Vestel in akıllı televizyon serisi yüksek çözünürlük ve akıllı özellikler sunar',20000,8);
INSERT INTO t_product(id,product_code,product_name,categories,explanation,price,company_id) VALUES (9,111,'Arçelik Akıllı Buzdolabı','Akıllı Cihazlar','İnternet erişimi ve akıllı özelliklerle donatılmış bir buzdolabı modeli',22080,9);
INSERT INTO t_product(id,product_code,product_name,categories,explanation,price,company_id) VALUES (10,123,'Beko Akıllı Çamaşır Makinesi','Akıllı Cihazlar',' Uygulama üzerinden uzaktan kontrol edilebilen akıllı çamaşır makinesi',16010,10);
INSERT INTO t_product(id,product_code,product_name,categories,explanation,price,company_id) VALUES (11,234,'TCL 55C815 QLED TV','Bilgisayar ve Teknoloji Ürünleri','Yüksek renk doygunluğu ve QLED teknolojisi ile öne çıkan TCL televizyon modeli',14999,11);
INSERT INTO t_product(id,product_code,product_name,categories,explanation,price,company_id) VALUES (12,345,'Lenovo ThinkPad X1 Carbon','Bilgisayar ve Teknoloji Ürünleri','Lenovo nun iş dünyası odaklı hafif ve dayanıklı dizüstü bilgisayarı',53790,12);
INSERT INTO t_product(id,product_code,product_name,categories,explanation,price,company_id) VALUES (13,456,'Acer Predator Helios 300','Bilgisayar ve Teknoloji Ürünleri','Oyun odaklı performansıyla öne çıkan Acer dizüstü bilgisayar',76160,13);
INSERT INTO t_product(id,product_code,product_name,categories,explanation,price,company_id) VALUES (14,567,'Oppo Find X3 Pro','Cep Telefonları','Oppo nun yüksek çözünürlüklü kameralara sahip premium telefonu',7056,14);
INSERT INTO t_product(id,product_code,product_name,categories,explanation,price,company_id) VALUES (15,678,'Dyson Elektrikli Süpürge','Ev Aletleri','Kablosuz ve yüksek performanslı bir süpürge modeli',19980,15);

INSERT INTO t_customer(id,tc,customer_number,first_name,last_name,gender,e_mail) VALUES (1,11111111110,111,'Ali','Güç','Erkek','aligüc@gmail.com');
INSERT INTO t_customer(id,tc,customer_number,first_name,last_name,gender,e_mail) VALUES (2,22222222220,222,'Bekir','Sağ','Erkek','bekirsag@gmail.com');
INSERT INTO t_customer(id,tc,customer_number,first_name,last_name,gender,e_mail) VALUES (3,33333333330,333,'Esma','Kul','Kadın','esmakul@gmail.com');
INSERT INTO t_customer(id,tc,customer_number,first_name,last_name,gender,e_mail) VALUES (4,44444444440,444,'Zeynep','Ferit','Kadın','zeynepferit@gmail.com');
INSERT INTO t_customer(id,tc,customer_number,first_name,last_name,gender,e_mail) VALUES (5,55555555550,555,'Beşir','Dal','Erkek','besirdal@gmail.com');
INSERT INTO t_customer(id,tc,customer_number,first_name,last_name,gender,e_mail) VALUES (6,66666666660,666,'Eda','Rize','Kadın','edarize@gmail.com');
INSERT INTO t_customer(id,tc,customer_number,first_name,last_name,gender,e_mail) VALUES (7,77777777770,777,'Hadi','Duru','Erkek','hadiduru@gmail.com');
INSERT INTO t_customer(id,tc,customer_number,first_name,last_name,gender,e_mail) VALUES (8,88888888880,888,'Pınar','Mus','Kadın','pinarmus@gmail.com');
INSERT INTO t_customer(id,tc,customer_number,first_name,last_name,gender,e_mail) VALUES (9,99999999990,999,'Çiğdem','Su','Kadın','cigdemsu@gmail.com');
INSERT INTO t_customer(id,tc,customer_number,first_name,last_name,gender,e_mail) VALUES (10,1111111112,112,'Aslı','Zor','Kadın','aslizor@gmail.com');
INSERT INTO t_customer(id,tc,customer_number,first_name,last_name,gender,e_mail) VALUES (11,2222222223,223,'Murat','Eski','Erkek','murateski@gmail.com');
INSERT INTO t_customer(id,tc,customer_number,first_name,last_name,gender,e_mail) VALUES (12,3333333334,334,'Davut','Saz','Erkek','davutsaz@gmail.com');
INSERT INTO t_customer(id,tc,customer_number,first_name,last_name,gender,e_mail) VALUES (13,4444444445,445,'Kadir','Mutlu','Erkek','kadirmutlu@gmail.com');
INSERT INTO t_customer(id,tc,customer_number,first_name,last_name,gender,e_mail) VALUES (14,5555555556,556,'Ebru','Demir','Kadın','ebrudemir@gmail.com');
INSERT INTO t_customer(id,tc,customer_number,first_name,last_name,gender,e_mail) VALUES (15,6666666667,667,'Eray','Kara','Erkek','eraykara@gmail.com');





























