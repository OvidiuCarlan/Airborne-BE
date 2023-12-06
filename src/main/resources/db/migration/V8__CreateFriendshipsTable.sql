CREATE TABLE Friendships (
                             id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
                             sender_id INT NOT NULL,
                             recipient_id INT NOT NULL,
                             FOREIGN KEY (sender_id) REFERENCES Users(id),
                             FOREIGN KEY (recipient_id) REFERENCES Users(id)
);