INSERT INTO items (id, name, description)
OVERRIDING SYSTEM VALUE
VALUES
(1, 'T-Shirt', 'Cotton t-shirt'),
(2, 'Hoodie', 'Hoodie')
ON CONFLICT (id) DO NOTHING;

INSERT INTO variants (id, category, name, is_active)
OVERRIDING SYSTEM VALUE
VALUES
(1, 'Size', 'L', TRUE),
(2, 'Size', 'XL', TRUE),
(3, 'Color', 'Black', TRUE),
(4, 'Color', 'White', TRUE),
(5, 'Tier', 'Epic', TRUE),
(6, 'Tier', 'Legendary', TRUE)
ON CONFLICT (id) DO NOTHING;
