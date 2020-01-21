CREATE TABLE siteusers (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    twitter_name VARCHAR(100) NOT NULL,
    spotify_mame VARCHAR(100) NOT NULL,
    api_three_name VARCHAR(100) NOT NULL,
    api_four_name VARCHAR(100) NOT NULL,
    api_five_name VARCHAR(100) NOT NULL,
    friends_list VARCHAR(300) NOT NULL,
    user_password VARCHAR(100) NOT NULL
);
