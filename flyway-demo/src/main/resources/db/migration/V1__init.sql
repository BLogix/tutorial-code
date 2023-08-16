create table personal_best_apps_and_games_of_2019(
    id  int auto_increment primary key,
    name varchar(40),
    maker varchar(40),
    rating int
);

insert into personal_best_apps_and_games_of_2019
    (name, maker)
values
    ('TikTok', 'TikTok Inc.'),
    ('Maki', 'Sunshine Apps'),
    ('Discord', 'Discord Inc.'),
    ('Teams', 'Microsoft Corporation'),
    ('Hearthstone', 'Blizzard Entertainment, Inc.'),
    ('Bible', 'Life.Church'),
    ('Brave Browser', 'Brave Software'),
    ('Meetup', 'Meetup'),
    ('Acorns', 'Acorns'),
    ('Nextdoor', 'Nextdoor.com');