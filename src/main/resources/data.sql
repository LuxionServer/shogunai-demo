insert into books (title, author, published_year)
select title, author, published_year
from (values
    ('The Pragmatic Programmer', 'Andrew Hunt, David Thomas', 1999),
    ('Refactoring', 'Martin Fowler', 1999),
    ('Domain-Driven Design', 'Eric Evans', 2003),
    ('Working Effectively with Legacy Code', 'Michael Feathers', 2004),
    ('Clean Code', 'Robert C. Martin', 2008),
    ('Growing Object-Oriented Software, Guided by Tests', 'Steve Freeman, Nat Pryce', 2009),
    ('Continuous Delivery', 'Jez Humble, David Farley', 2010),
    ('Designing Data-Intensive Applications', 'Martin Kleppmann', 2017),
    ('Accelerate', 'Nicole Forsgren, Jez Humble, Gene Kim', 2018),
    ('A Philosophy of Software Design', 'John Ousterhout', 2018),
    ('Team Topologies', 'Matthew Skelton, Manuel Pais', 2019),
    ('Software Engineering at Google', 'Titus Winters, Tom Manshreck, Hyrum Wright', 2020),
    ('The Mythical Man-Month', 'Frederick P. Brooks Jr.', 1975),
    ('Structure and Interpretation of Computer Programs', 'Harold Abelson, Gerald Jay Sussman', 1985),
    ('Design Patterns', 'Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides', 1994),
    ('Patterns of Enterprise Application Architecture', 'Martin Fowler', 2002),
    ('Release It!', 'Michael T. Nygard', 2007),
    ('Building Microservices', 'Sam Newman', 2015),
    ('Site Reliability Engineering', 'Betsy Beyer, Chris Jones, Jennifer Petoff, Niall Richard Murphy', 2016),
    ('Fundamentals of Software Architecture', 'Mark Richards, Neal Ford', 2020)
) as seed(title, author, published_year)
where not exists (select 1 from books);
