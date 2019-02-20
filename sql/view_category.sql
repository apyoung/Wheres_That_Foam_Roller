SELECT DISTINCT
    `Name`, item.`Condition`, `MadeIn`, item.`UPC`
FROM
    item,
    itemcategory
WHERE
    item.UPC = itemcategory.UPC
        AND itemcategory.Category = 'Sports'
        # The Sports category would be changed to whatever the current selected
        # category is
        AND item.`MadeIn` = 'Ghana'
        AND item.`Name` = 'VOLUPTATE';