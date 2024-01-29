/* 12p
	PAYMENT 테이블에서 단일 거래의 AMOUNT 액수가 가장 많은 고객들의 CUSTOMER_ID를 추출하기 (단, CUSTOMER_ID의 값은 유일해야 한다)
*/
SELECT DISTINCT p.customer_id
FROM payment p
WHERE p.amount = (SELECT MAX(amount)
						FROM payment)
ORDER BY p.customer_id
;

/* 12p
	고객들에게 단체 이메일을 전송하고자 한다. 
	CUSTOMER 테이블에서 고객의 EMAIL 주소를 추출하고, 이메일 형식에 맞지 않는 이메일 주소는 제외한다. 
	(이메일 형식은 ‘@’가 존재, ‘@’로 시작하면 안되고, ‘@’로 끝나지 말아야 한다)
*/
SELECT c.email
FROM customer C
WHERE c.email LIKE '%@%'
AND 	c.email NOT LIKE '@%'
AND 	c.email NOT LIKE '%@'
;

/* 21p
	employee table 과 department table 을 full outer join
*/
SELECT e.employee_name
	  , d.department_name
FROM employees e
FULL OUTER JOIN departments d
ON (e.department_id = d.department_id)
;

/* 21p
	소속한 직원이 없는 부서만 출력
*/
SELECT e.employee_name
	  , d.department_name
FROM employees e
FULL OUTER JOIN departments d
ON (e.department_id = d.department_id)
WHERE e.employee_id IS NULL 
;

/* 22p
	소속한 부서가 없는 부서만 출력
*/
SELECT e.employee_name
	  , d.department_name
FROM employees e
FULL OUTER JOIN departments d
ON (e.department_id = d.department_id)
WHERE e.department_id IS NULL 
AND e.employee_id IS NOT NULL 
;
/* 32p
	RENTAL 테이블을 이용하여 연,연월,연월일, 전체 각각의 기준으로
	RENTAL_ID 기준 렌탈이 일어난 횟수를 출력 (전체 데이터를 기준으로 모든 행을 출력)
*/
SELECT EXTRACT(YEAR FROM r.rental_date) 		AS Y
	  , EXTRACT(MONTH FROM r.rental_date)	 	AS "M"
	  , EXTRACT(DAY FROM r.rental_date)	 		AS D
	  , COUNT(r.rental_id)							AS "COUNT"
FROM rental r
GROUP BY
	ROLLUP (
				EXTRACT(YEAR FROM r.rental_date) 	
		    , EXTRACT(MONTH FROM r.rental_date)	 	
		    , EXTRACT(DAY FROM r.rental_date)
			 )
ORDER BY y
;

/* 33P
	RENTAL과 CUSTOMER 테이블을 이용하여 
	현재까지 가장 많이 RENTAL을 한 고객의 고객ID, 렌탈순위, 누적렌탈회수, 이름을 출력
*/

SELECT r.customer_id
	  , row_number() over(ORDER BY COUNT(r.rental_id) DESC) AS rn
	  , COUNT(r.rental_id) AS retal_count
	  , c.first_name
	  , c.last_name
FROM rental r
INNER JOIN customer c
ON (r.customer_id = c.customer_id)
GROUP BY r.customer_id, c.customer_id
LIMIT 1
;





