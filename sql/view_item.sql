SELECT 
    item.*, itemcategory.Category
FROM
    item,
    itemcategory
WHERE
    item.upc = itemcategory.upc
        AND item.Condition = itemcategory.Condition
        AND item.upc = 12908337