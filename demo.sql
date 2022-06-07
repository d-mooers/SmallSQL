DROP TABLE nation;

CREATE TABLE region (
    r_regionkey integer  NOT NULL,
    r_name      char(25) NOT NULL,
    r_comment   varchar(152),
    PRIMARY KEY (r_regionkey)
);

CREATE TABLE nation (
    n_nationkey integer  NOT NULL,
    n_name      char(25) NOT NULL,
    n_regionkey integer  NOT NULL,
    n_comment   varchar(152),
    PRIMARY KEY (n_nationkey),
    FOREIGN KEY (n_regionkey) REFERENCES region (r_regionkey)
);



INSERT INTO region (r_regionkey, r_name, r_comment) VALUES (0, 'AFRICA', 'lar deposits. blithely final packages cajole. regular waters are final requests. regular accounts are according to ');
INSERT INTO region (r_regionkey, r_name, r_comment) VALUES (1, 'AMERICA', 'hs use ironic, even requests. s');
INSERT INTO region (r_regionkey, r_name, r_comment) VALUES (2, 'ASIA', 'ges. thinly even pinto beans ca');
INSERT INTO region (r_regionkey, r_name, r_comment) VALUES (3, 'EUROPE', 'ly final courts cajole furiously final excuse');
INSERT INTO region (r_regionkey, r_name, r_comment) VALUES (4, 'MIDDLE EAST', 'uickly special accounts cajole carefully blithely close requests. carefully final asymptotes haggle furiousl');
INSERT INTO nation (n_nationkey, n_name, n_regionkey, n_comment) VALUES (0, 'ALGERIA', 0, ' haggle. carefully final deposits detect slyly agai');
INSERT INTO nation (n_nationkey, n_name, n_regionkey, n_comment) VALUES (1, 'ARGENTINA', 1, 'al foxes promise slyly according to the regular accounts. bold requests alon');
INSERT INTO nation (n_nationkey, n_name, n_regionkey, n_comment) VALUES (2, 'BRAZIL', 1, 'y alongside of the pending deposits. carefully special packages are about the ironic forges. slyly special ');
INSERT INTO nation (n_nationkey, n_name, n_regionkey, n_comment) VALUES (3, 'CANADA', 1, 'eas hang ironic, silent packages. slyly regular packages are furiously over the tithes. fluffily bold');
INSERT INTO nation (n_nationkey, n_name, n_regionkey, n_comment) VALUES (4, 'EGYPT', 4, 'y above the carefully unusual theodolites. final dugouts are quickly across the furiously regular d');
INSERT INTO nation (n_nationkey, n_name, n_regionkey, n_comment) VALUES (5, 'ETHIOPIA', 0, 'ven packages wake quickly. regu');
INSERT INTO nation (n_nationkey, n_name, n_regionkey, n_comment) VALUES (6, 'FRANCE', 3, 'refully final requests. regular, ironi');
INSERT INTO nation (n_nationkey, n_name, n_regionkey, n_comment) VALUES (7, 'GERMANY', 3, 'l platelets. regular accounts x-ray: unusual, regular acco');
INSERT INTO nation (n_nationkey, n_name, n_regionkey, n_comment) VALUES (8, 'INDIA', 2, 'ss excuses cajole slyly across the packages. deposits print aroun');
INSERT INTO nation (n_nationkey, n_name, n_regionkey, n_comment) VALUES (9, 'INDONESIA', 2, ' slyly express asymptotes. regular deposits haggle slyly. carefully ironic hockey players sleep blithely. carefull');
INSERT INTO nation (n_nationkey, n_name, n_regionkey, n_comment) VALUES (10, 'IRAN', 4, 'efully alongside of the slyly final dependencies. ');
INSERT INTO nation (n_nationkey, n_name, n_regionkey, n_comment) VALUES (11, 'IRAQ', 4, 'nic deposits boost atop the quickly final requests? quickly regula');
INSERT INTO nation (n_nationkey, n_name, n_regionkey, n_comment) VALUES (12, 'JAPAN', 2, 'ously. final, express gifts cajole a');
CLEAR MONITORING
START MONITORING

INSERT INTO nation (n_nationkey, n_name, n_regionkey, n_comment) VALUES (13, 'JORDAN', 4, 'ic deposits are blithely about the carefully regular pa');
INSERT INTO nation (n_nationkey, n_name, n_regionkey, n_comment) VALUES (14, 'KENYA', 0, ' pending excuses haggle furiously deposits. pending, express pinto beans wake fluffily past t');
INSERT INTO nation (n_nationkey, n_name, n_regionkey, n_comment) VALUES (15, 'MOROCCO', 0, 'rns. blithely bold courts among the closely regular packages use furiously bold platelets?');

SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_name = 'AFRICA';
SELECT * from nation where n_regionkey = 0;
SELECT * from nation where n_regionkey = 1;
SELECT * from nation where n_regionkey = 2;
SELECT * from nation where n_regionkey = 4;
SELECT * from nation where n_regionkey = 5;
SELECT * from nation where n_regionkey = 0;
SELECT * from nation where n_regionkey = 0;


REC_INDEX BASIC;
REC_INDEX ADVANCED;
REC_INDEX REL_FREQ;
q