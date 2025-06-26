SELECT * FROM mydb.shopping_cart;
DESCRIBE shopping_cart;
INSERT INTO shopping_cart (subtotal, shipment, total, users_id)
VALUES  
  (2000, 50, 2050, 3), 
  (2755, 50, 2805, 3), 
  (1600, 50, 1650, 3), 
  (197.56, 50, 247.56, 2), 
  (0, 50, 50, 1);