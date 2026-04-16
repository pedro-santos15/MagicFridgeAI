--V1: Migration to add table of foods
CREATE TABLE food_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category ENUM('CEREAL', 'VEGETABLE', 'FRUIT', 'DAIRY', 'PROTEIN') NOT NULL,
    quantity INT NOT NULL DEFAULT 0,
    expiration_date DATE
);