SELECT 
    store.*, company.`Name` AS 'Company Name'
FROM
    store,
    company,
    owns
WHERE
    store.storeid = 10170
        AND store.StoreID = owns.StoreID
        AND company.`Name` = owns.CompanyName;