CREATE TABLE Comments (
                             id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
                             post_id INT NOT NULL,
                             user_id INT NOT NULL,
                             content VARCHAR(MAX),
                             FOREIGN KEY (post_id) REFERENCES Posts(id),
                             FOREIGN KEY (user_id) REFERENCES Users(id)
);