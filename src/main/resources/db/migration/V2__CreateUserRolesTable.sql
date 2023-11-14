CREATE TABLE user_roles
(
    id INT IDENTITY(1,1) NOT NULL,
    role_name NVARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
)