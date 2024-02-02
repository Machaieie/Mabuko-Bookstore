
INSERT INTO publisher (location, name, phone) VALUES 
('Maputo', 'Tech Books Ltd.', '+258841234567'),   -- Vodacom
('Beira', 'Code Masters Publishing', '+258821234567'),   -- Mcel
('Nampula', 'Innovation Press', '+258841234567'),   -- Vodacom
('Maputo', 'Programming World', '+258821234567'),   -- Mcel
('Chimoio', 'Algorithmic Solutions', '+258841234567'),   -- Vodacom
('Matola', 'Web Development Co.', '+258841234567'),   -- Vodacom
('Pemba', 'Future Code Publishers', '+258821234567'),   -- Mcel
('Quelimane', 'Inventive Minds', '+258841234567'),   -- Vodacom
('Tete', 'Software Innovators', '+258821234567'),   -- Mcel
('Maxixe', 'Digital Creations', '+258841234567'),   -- Vodacom
('Lichinga', 'Byte Crafters', '+258821234567'),   -- Mcel
('Xai-Xai', 'Creative Coders', '+258841234567'),   -- Vodacom
('Inhambane', 'Tech Wizards', '+258821234567'),   -- Mcel
('Cuamba', 'Code Fusion', '+258841234567'),   -- Vodacom
('Manica', 'Infinite Loop Publishers', '+258841234567'),   -- Vodacom
('Gurue', 'Web Wizards', '+258821234567'),   -- Mcel
('Chokwe', 'Epic Code Ventures', '+258841234567'),   -- Vodacom
('Lumbo', 'Algorithm Architects', '+258821234567'),   -- Mcel
('Nacala', 'Digital Dreams', '+258841234567'),   -- Vodacom
('Angoche', 'Future Tech Books', '+258821234567');   -- Mcel

INSERT INTO author (name, biography, nationality) VALUES 
('Robert C. Martin', 'Clean Code, The Clean Coder', 'American'),
('Steve McConnell', 'Code Complete', 'American'),
('Dave Thomas, Andy Hunt', 'The Pragmatic Programmer', 'American'),
('Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides, Grady Booch', 'Design Patterns: Elements of Reusable Object-Oriented Software', 'Various'),
('Martin Fowler', 'Refactoring: Improving the Design of Existing Code', 'British'),
('Douglas Crockford', 'JavaScript: The Good Parts', 'American'),
('Marijn Haverbeke', 'Eloquent JavaScript: A Modern Introduction to Programming', 'Dutch'),
('Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra', 'Head First Design Patterns', 'Various'),
('Eric Matthes', 'Python Crash Course: A Hands-On, Project-Based Introduction to Programming', 'American'),
('Joshua Bloch', 'Effective Java', 'American'),
('Jon Bentley', 'Programming Pearls', 'American'),
('Robert Sedgewick, Kevin Wayne', 'Algorithms', 'American'),
('Frederick P. Brooks Jr.', 'The Mythical Man-Month: Essays on Software Engineering', 'American'),
('Kent Beck', 'Test-Driven Development: By Example', 'American'),
('Robert C. Martin', 'Clean Architecture: A Craftsmans Guide to Software Structure and Design', 'American'),
('Kyle Simpson', 'You Dont Know JS', 'American'),
('Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein', 'Introduction to Algorithms', 'Various'),
('Luciano Ramalho', 'Fluent Python: Clear, Concise, and Effective Programming', 'Brazilian'),
('Eric Evans', 'Domain-Driven Design: Tackling Complexity in the Heart of Software', 'American'),
('Martin Odersky, Lex Spoon, Bill Venners', 'Programming in Scala', 'Various');


INSERT INTO books (edition, gender, price, publisher_date, title, author_id, publisher_id) VALUES 
(1, 'Programming', 29.99, '2022-01-15', 'Clean Code', 1, 1),
(2, 'Programming', 39.99, '2022-02-20', 'Code Complete', 2, 2),
(1, 'Programming', 24.99, '2022-03-25', 'The Pragmatic Programmer', 3, 3),
(3, 'Programming', 49.99, '2022-04-30', 'Design Patterns', 4, 4),
(2, 'Programming', 34.99, '2022-05-15', 'Refactoring', 5, 5),
(1, 'JavaScript', 19.99, '2022-06-20', 'JavaScript: The Good Parts', 6, 6),
(1, 'JavaScript', 29.99, '2022-07-25', 'Eloquent JavaScript', 7, 7),
(2, 'Programming', 44.99, '2022-08-30', 'Head First Design Patterns', 8, 8),
(1, 'Python', 27.99, '2022-09-15', 'Python Crash Course', 9, 9),
(3, 'Java', 59.99, '2022-10-20', 'Effective Java', 10, 10),
(2, 'Programming', 32.99, '2022-11-25', 'Programming Pearls', 11, 11),
(1, 'Algorithms', 39.99, '2022-12-30', 'Algorithms', 12, 12),
(2, 'Programming', 45.99, '2023-01-15', 'The Mythical Man-Month', 13, 13),
(1, 'Programming', 29.99, '2023-02-20', 'Test-Driven Development', 14, 14),
(1, 'Software Engineering', 39.99, '2023-03-25', 'Clean Architecture', 1, 1),
(2, 'JavaScript', 24.99, '2023-04-30', 'You Dont Know JS', 15, 6),
(1, 'Algorithms', 34.99, '2023-05-15', 'Introduction to Algorithms', 16, 16),
(2, 'Python', 42.99, '2023-06-20', 'Fluent Python', 17, 17),
(1, 'Software Design', 29.99, '2023-07-25', 'Domain-Driven Design', 18, 18),
(3, 'Scala', 49.99, '2023-08-30', 'Programming in Scala', 19, 19),
(1, 'Programming', 27.99, '2023-09-15', 'Book Example', 20, 20);