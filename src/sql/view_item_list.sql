SELECT DISTINCT
    item.`Name`, item.`Condition`, item.MadeIn, item.UPC
FROM
    item,
    itemcategory
WHERE
    item.UPC = itemcategory.UPC
        AND item.`Condition` = itemcategory.`Condition`
        AND itemcategory.`Category` = 'Sports'
        AND item.`Condition` = 'New'
        AND item.`MadeIn` = 'Ghana'
        AND `Name` = 'voluptate'
        AND item.`UPC` = '12908337';