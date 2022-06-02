select 
	c_name,
	c_custkey,
	o_orderkey,
	o_orderdate,
	o_totalprice,
	sum(l_quantity)
from
	customer,
	orders,
	lineitem
where
	c_custkey = o_custkey
	and o_orderkey = l_orderkey
group by
	c_name,
	c_custkey,
	o_orderkey,
	o_orderdate,
	o_totalprice
order by
	o_totalprice desc,
	o_orderdate;


SELECT
     SUM(l_extendedprice* (1 - l_discount)) AS revenue
FROM
     lineitem,
     part
WHERE
     (
		p_partkey = l_partkey
		AND p_brand = 'Brand#12'
		AND l_quantity >= 1 AND l_quantity <= 1 + 10
        AND p_size > 1 and p_size < 5
		AND l_shipinstruct = 'DELIVER IN PERSON'
     )
     OR
     (
		p_partkey = l_partkey
		AND p_brand = 'Brand#23'
		AND l_quantity >= 10 AND l_quantity <= 10 + 10
		AND l_shipinstruct = 'DELIVER IN PERSON'
     )
     OR
     (
		p_partkey = l_partkey
		AND p_brand = 'Brand#34'
		AND l_quantity >= 20 AND l_quantity <= 20 + 10
		AND l_shipinstruct = 'DELIVER IN PERSON'
     );


SELECT
 	s_name,
     s_address
FROM
     supplier,
     nation
WHERE
     s_nationkey = n_nationkey
     AND n_name = 'CANADA'
ORDER BY
     s_name;


SELECT n_name, SUM(l_extendedprice * (1 - l_discount)) AS revenue
  FROM customer, orders, lineitem, supplier, nation, region
 WHERE c_custkey = o_custkey
   AND l_orderkey = o_orderkey
   AND l_suppkey = s_suppkey
   AND c_nationkey = s_nationkey
   AND s_nationkey = n_nationkey
   AND n_regionkey = r_regionkey
   AND r_name = 'ASIA'
   AND o_orderdate >= '1994-01-01'
   AND o_orderdate < '1995-01-01'
GROUP BY n_name;


SELECT o_orderpriority, COUNT(*) AS order_count
  FROM orders
 WHERE o_orderdate >= '1993-07-01'
   AND o_orderdate < '1993-07-01'
GROUP BY o_orderpriority
ORDER BY o_orderpriority;


SELECT
     SUM(l_extendedprice * l_discount) AS revenue
FROM
     lineitem
WHERE
     l_shipdate >= '1994-01-01'
     AND l_shipdate < '1995-01-01'
     AND l_discount > 0.05 and l_discount < 0.070001
     AND l_quantity < 24;


-- SELECT
--      supp_nation,
--      cust_nation,
--      l_year,
--      SUM(volume) AS revenue
-- FROM
--      (
-- 		SELECT
-- 			n1.n_name AS supp_nation,
-- 			n2.n_name AS cust_nation,
-- 			YEAR(l_shipdate) AS l_year,
-- 			l_extendedprice * (1 - l_discount) AS volume
-- 		FROM
-- 			supplier,
-- 			lineitem,
-- 			orders,
-- 			customer,
-- 			nation n1,
-- 			nation n2
-- 		WHERE
-- 			s_suppkey = l_suppkey
-- 			AND o_orderkey = l_orderkey
-- 			AND c_custkey = o_custkey
-- 			AND s_nationkey = n1.n_nationkey
-- 			AND c_nationkey = n2.n_nationkey
-- 			AND (
-- 				(n1.n_name = 'FRANCE' AND n2.n_name = 'GERMANY')
-- 				OR (n1.n_name = 'GERMANY' AND n2.n_name = 'FRANCE')
-- 			)
-- 			AND l_shipdate > '1995-01-01' AND l_shipdate < '1996-12-31'
--      ) 
-- GROUP BY
--      supp_nation,
--      cust_nation,
--      l_year
-- ORDER BY
--      supp_nation,
--      cust_nation,
--      l_year;


SELECT 
     l_orderkey,
     SUM(l_extendedprice * (1 - l_discount)) AS revenue,
     o_orderdate,
     o_shippriority
 FROM  customer, orders, lineitem
WHERE c_mktsegment = 'BUILDING'
  AND c_custkey = o_custkey
  AND l_orderkey = o_orderkey
  AND o_orderdate < '1995-03-15'
  AND l_shipdate > '1995-03-15'
GROUP BY l_orderkey, o_orderdate, o_shippriority
ORDER BY o_orderdate;


SELECT 
     s_acctbal,
     s_name,
     n_name,
     p_partkey,
     p_mfgr,
     s_address,
     s_phone,
     s_comment
FROM part, supplier, partsupp, nation, region
WHERE
     p_partkey = ps_partkey
     AND s_suppkey = ps_suppkey
     AND p_size = 15
     AND p_type LIKE '%BRASS'
     AND s_nationkey = n_nationkey
     AND n_regionkey = r_regionkey
     AND r_name = 'EUROPE'
ORDER BY s_acctbal DESC, n_name, s_name, p_partkey;


select
       l_returnflag,
       l_linestatus,
       sum(l_quantity) as sum_qty,
       sum(l_extendedprice) as sum_base_price,
       sum(l_extendedprice * (1-l_discount)) as sum_disc_price,
       sum(l_extendedprice * (1-l_discount) * (1+l_tax)) as sum_charge,
       avg(l_quantity) as avg_qty,
       avg(l_extendedprice) as avg_price,
       avg(l_discount) as avg_disc,
       count(*) as count_order
 from
       lineitem
 where
       l_shipdate <= '1998-12-01'
 group by
       l_returnflag,
       l_linestatus
 order by
       l_returnflag,
       l_linestatus;


-- SELECT
--      nation,
--      o_year,
--      SUM(amount) AS sum_profit
-- FROM
--      (
-- 		SELECT
-- 			n_name AS nation,
-- 			YEAR(o_orderdate) AS o_year,
-- 			l_extendedprice * (1 - l_discount) - ps_supplycost * l_quantity AS amount
-- 		FROM
-- 			part,
-- 			supplier,
-- 			lineitem,
-- 			partsupp,
-- 			orders,
-- 			nation
-- 		WHERE
-- 			s_suppkey = l_suppkey
-- 			AND ps_suppkey = l_suppkey
-- 			AND ps_partkey = l_partkey
-- 			AND p_partkey = l_partkey
-- 			AND o_orderkey = l_orderkey
-- 			AND s_nationkey = n_nationkey
-- 			AND p_name LIKE '%green%'
--      )
-- GROUP BY
--      nation,
--      o_year
-- ORDER BY
--      nation,
--      o_year DESC;


-- SELECT
--      o_year,
--      SUM(CASE
-- 		WHEN nation = 'BRAZIL' THEN volume
-- 		ELSE 0
--      END) / SUM(volume) AS mkt_share
-- FROM
--      (
-- 		SELECT
-- 			YEAR(o_orderdate) AS o_year,
-- 			l_extendedprice * (1 - l_discount) AS volume,
-- 			n2.n_name AS nation
-- 		FROM
-- 			part,
-- 			supplier,
-- 			lineitem,
-- 			orders,
-- 			customer,
-- 			nation n1,
-- 			nation n2,
-- 			region
-- 		WHERE
-- 			p_partkey = l_partkey
-- 			AND s_suppkey = l_suppkey
-- 			AND l_orderkey = o_orderkey
-- 			AND o_custkey = c_custkey
-- 			AND c_nationkey = n1.n_nationkey
-- 			AND n1.n_regionkey = r_regionkey
-- 			AND r_name = 'AMERICA'
-- 			AND s_nationkey = n2.n_nationkey
-- 			AND o_orderdate BETWEEN CAST ('1995-01-01' AS DATE) AND CAST ('1996-12-31' AS DATE)
-- 			AND p_type = 'ECONOMY ANODIZED STEEL'
--      ) 
-- GROUP BY
--      o_year
-- ORDER BY
--      o_year;


SELECT
     ps_partkey,
     SUM(ps_supplycost * ps_availqty) AS value
FROM
     partsupp,
     supplier,
     nation
WHERE
     ps_suppkey = s_suppkey
     AND s_nationkey = n_nationkey
     AND n_name = 'GERMANY'
GROUP BY
     ps_partkey;


SELECT 
     c_custkey,
     c_name,
     SUM(l_extendedprice * (1 - l_discount)) AS revenue,
     c_acctbal,
     n_name,
     c_address,
     c_phone,
     c_comment
FROM
     customer,
     orders,
     lineitem,
     nation
WHERE
     c_custkey = o_custkey
     AND l_orderkey = o_orderkey
     AND o_orderdate >= '1993-10-01'
     AND o_orderdate < '1994-03-01'
     AND l_returnflag = 'R'
     AND c_nationkey = n_nationkey
GROUP BY
     c_custkey,
     c_name,
     c_acctbal,
     c_phone,
     n_name,
     c_address,
     c_comment;


SELECT
     l_shipmode
FROM
     orders,
     lineitem
WHERE
     o_orderkey = l_orderkey
     AND l_commitdate < l_receiptdate
     AND l_shipdate < l_commitdate
     AND l_receiptdate >= '1994-01-01'
     AND l_receiptdate < '1995-01-01'
GROUP BY
     l_shipmode
ORDER BY
     l_shipmode;


SELECT
     SUM(l_extendedprice) / 7.0 AS avg_yearly
FROM
     lineitem,
     part
WHERE
     p_partkey = l_partkey
     AND p_brand = 'Brand#23'
     AND p_container = 'MED BOX';


SELECT 
     p_brand,
     p_type,
     p_size,
     COUNT(ps_suppkey) AS supplier_cnt
FROM
     partsupp,
     part
WHERE
     p_partkey = ps_partkey
GROUP BY
     p_brand,
     p_type,
     p_size
ORDER BY
     p_brand,
     p_type,
     p_size;


