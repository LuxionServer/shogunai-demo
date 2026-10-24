create table if not exists books (
    id bigint generated always as identity primary key,
    title text not null,
    author text not null,
    published_year int not null
);
