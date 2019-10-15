BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "tbl_purchase" (
	"purchase_id"	INTEGER NOT NULL,
	"customer_id"	INTEGER,
	"purchase_date"	TEXT,
	"purchase_quantity"	INTEGER,
	"purchase_price"	REAL,
	"purchase_tax"	REAL,
	"purchase_total"	INTEGER,
	"product_id"	INTEGER,
	PRIMARY KEY("purchase_id")
);
CREATE TABLE IF NOT EXISTS "tbl_customer" (
	"customer_id"	INTEGER NOT NULL,
	"customer_name"	VARCHAR(255),
	"customer_address"	VARCHAR(255),
	"customer_phone"	INTEGER,
	"customer_payment_info"	VARCHAR(255),
	PRIMARY KEY("customer_id")
);
CREATE TABLE IF NOT EXISTS "tbl_product" (
	"product_id"	INTEGER NOT NULL,
	"product_name"	varchar(255),
	"product_price"	varchar(255),
	"product_quantity"	INTEGER,
	PRIMARY KEY("product_id")
);
INSERT INTO "tbl_purchase" ("purchase_id","customer_id","purchase_date","purchase_quantity","purchase_price","purchase_tax","purchase_total","product_id") VALUES (1,1,'2019-09-10 13:16:09.963',1,1299.99,91.0,1390.99,2),
 (2,1,'2019-09-10 13:16:09.963',1,499.99,35.0,534.99,4),
 (3,2,'2019-09-10 13:16:09.963',2,1449.98,101.5,1551.48,1),
 (4,3,'2019-09-10 13:16:09.963',3,3299.97,231.0,3530.97,1),
 (5,1,'2019-09-10 13:16:09.963',1,799.99,56.0,855.99,3),
 (6,2,'2019-09-10 13:16:09.963',2,1149.98,80.5,1230.48,5),
 (7,3,'2019-09-10 13:16:09.963',1,1299.99,91.0,1390.99,4),
 (8,4,'2019-09-10 13:16:09.963',4,4799.96,336.0,5135.96,3),
 (9,1,'2019-09-10 13:16:09.963',1,799.99,56.0,855.99,3),
 (10,4,'2019-09-10 13:16:09.963',1,649.99,45.5,695.49,1),
 (11,5,'Tue Oct 15 13:35:53 CDT 2019',3,2399.97,55.9993,2455.9693,2);
INSERT INTO "tbl_customer" ("customer_id","customer_name","customer_address","customer_phone","customer_payment_info") VALUES (1,'John Doe','123 Main Street, Auburn, AL, USA',5556667878,'Credit Card'),
 (2,'Adam Smith','1600 Pennsylvania Ave., Washington, D.C, USA',1234567890,'Debit Card'),
 (3,'Alan Jones','567 Somewhere Rd., Columbus, GA, USA',7637789000,'Credit Card'),
 (4,'Sandra Miller','145 Principle Blvd., Montgomery, AL, USA',2055558899,'Credit Card'),
 (5,'Rick Young','3455 Summerdale St., Auburn, AL, USA',6767829999,'Debit Card'),
 (6,'Bill Johnson','413 Ableton Dr., Atlanta, GA, USA',7638559999,'Debit Card');
INSERT INTO "tbl_product" ("product_id","product_name","product_price","product_quantity") VALUES (1,'Google Pixel 2','699.99',100),
 (2,'Google Pixel 3','799.99',100),
 (3,'Google Pixel 4','899.99',100),
 (4,'Google Pixel 5','999.99',100),
 (5,'Google Pixel 6','1099.99',100),
 (6,'Samsung Galaxy 7','599.99',35);
COMMIT;
