SELECT 
    store.*, company.name AS 'Parent Company'
FROM
    store,
    company,
    owns
WHERE
    store.storeid = owns.storeid
        AND owns.companyname = company.name