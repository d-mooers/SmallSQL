SELECT
     SUM(l_extendedprice* (1 - l_discount)) AS revenue
FROM
     lineitem
     JOIN part ON p_partkey = l_partkey
WHERE
     (
		p_brand = 'Brand#12'
		AND l_quantity >= 1 AND l_quantity <= 1 + 10
        AND p_size > 1 and p_size < 5
		AND l_shipinstruct = 'DELIVER IN PERSON'
     )
     OR
     (
		p_brand = 'Brand#23'
		AND l_quantity >= 10 AND l_quantity <= 10 + 10
		AND l_shipinstruct = 'DELIVER IN PERSON'
     )
     OR
     (
		p_brand = 'Brand#34'
		AND l_quantity >= 20 AND l_quantity <= 20 + 10
		AND l_shipinstruct = 'DELIVER IN PERSON'
     );
q