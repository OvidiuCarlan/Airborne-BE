CREATE TABLE Posts(
      id INT PRIMARY KEY,
      userId INT,
      content NVARCHAR(MAX),
      image VARBINARY(MAX),
      FOREIGN KEY (userId) REFERENCES Users(id)
);