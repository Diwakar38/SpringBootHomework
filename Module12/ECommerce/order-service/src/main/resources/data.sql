INSERT INTO orders (order_status, total_price)
VALUES
    ('PENDING', 69999.00),
    ('CONFIRMED', 129998.00),
    ('SHIPPED', 74999.00),
    ('DELIVERED', 154998.00),
    ('CANCELLED', 39999.00),
    ('CONFIRMED', 99999.00),
    ('PENDING', 58999.00),
    ('DELIVERED', 149999.00),
    ('SHIPPED', 83998.00),
    ('CONFIRMED', 129999.00);

INSERT INTO order_item (product_id, quantity, order_id)
VALUES
    (1, 1, 1),
    (2, 1, 2),
    (3, 1, 2),
    (4, 1, 3),
    (5, 1, 4),
    (6, 1, 4),
    (7, 1, 5),
    (8, 1, 6),
    (9, 1, 7),
    (10, 2, 8),
    (11, 1, 8),
    (12, 1, 9),
    (13, 1, 9),
    (14, 1, 10),
    (15, 1, 10);