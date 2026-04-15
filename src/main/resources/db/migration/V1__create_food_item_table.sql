--V1: Migration to add table of foods
CREATE TABLE tb_food_item (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    category VARCHAR(100),
    quantity INT,
    expiration_date DATE
);