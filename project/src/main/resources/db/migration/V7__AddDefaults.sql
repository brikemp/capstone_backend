ALTER TABLE person
ADD spotify_name VARCHAR(100) DEFAULT 'Account Name not Provided';
ALTER TABLE person
ADD twitter_name VARCHAR(100) DEFAULT 'Account Name not Provided';
ALTER TABLE person RENAME COLUMN spotify_name_two TO pinterest_name;