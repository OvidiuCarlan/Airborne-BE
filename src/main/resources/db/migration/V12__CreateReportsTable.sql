CREATE TABLE Reports (
                         id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
                         reporter_id INT NOT NULL,
                         reported_id INT NOT NULL,
                         reason VARCHAR(MAX),
                         FOREIGN KEY (reporter_id) REFERENCES Users(id),
                         FOREIGN KEY (reported_id) REFERENCES Users(id),
);