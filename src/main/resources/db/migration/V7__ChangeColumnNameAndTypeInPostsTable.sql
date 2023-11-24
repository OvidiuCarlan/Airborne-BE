EXEC sp_rename 'Posts.userId', 'user_id', 'COLUMN';

ALTER TABLE Posts
ADD new_image TEXT;

ALTER TABLE Posts
DROP COLUMN image;

EXEC sp_rename 'Posts.new_image', 'image', 'COLUMN';
