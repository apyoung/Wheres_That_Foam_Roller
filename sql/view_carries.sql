SELECT 
    item.`name` AS 'Item Name',
    store.`name` AS 'Store Name',
    carries.`Condition`,
    carries.InventoryLevel AS 'In Stock',
    carries.InventoryOnOrder AS 'On Order',
    carries.Price,
    carries.LastStockedDate AS 'Last Order Date'
FROM
    carries
        JOIN
    item ON item.Upc = carries.UPC
        JOIN
    store ON store.storeID = carries.storeID
WHERE
    store.`name` = 'Price Group'
        AND item.`name` = 'consequatur'