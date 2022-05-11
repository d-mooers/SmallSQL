


/**
Create the tables
**/

CREATE TABLE Dept(
    id int,
    name varchar(20),
    PRIMARY KEY (id)
);

CREATE TABLE Employee(
    id int,
    name varchar(20),
    age int,
    dept int,
    PRIMARY KEY (id),
    FOREIGN KEY (dept) REFERENCES Dept(id)
);


/**
Insert records
**/

INSERT INTO Dept (id, name) VALUES (10, 'CS');
INSERT INTO Employee (id, name, age, dept) VALUES (1, 'bob', 34, 10);
INSERT INTO Employee (id, name, age, dept) VALUES (2, 'alice', 22, 10);
INSERT INTO Employee (id, name, age, dept) VALUES (3, 'tom', 40, 10);



/**
Update records
**/
UPDATE Employee
SET age = 10
WHERE name = 'bob';

/**
Select statement
**/
SELECT e.name, d.name
FROM Employee AS e JOIN Dept as d ON e.dept = d.id
WHERE e.name = 'bob';

SELECT dept, AVG(age)
FROM Employee
GROUP BY Employee.dept;


/**
Delete Statement
**/
DELETE FROM Employee WHERE name = 'tom'; 

/**
Cleanup
**/
DROP TABLE Employee;
DROP TABLE Dept;
