SELECT * FROM mydb.cart_items;
DESCRIBE cart_items;
INSERT INTO cart_items (products_id, shopping_cart_id, quantity, price_at_purchase)
VALUES
(1, 1, 1, 2000),
(2, 2, 1, 2775),
(3, 3, 2, 3200),
(1, 1, 3, 6000),
(2, 2, 3, 8325);