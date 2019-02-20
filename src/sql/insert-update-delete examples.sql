INSERT into item
VALUES (99067192, 'new', 42, 'France', 'Pancakes');

INSERT into carries
VALUES (13416487, 'New', 10170, 9, 99.19, 123, '2019-02-18');

UPDATE store 
SET 
    store.phone = 4805551234
WHERE
    store.storeID = 12023;

UPDATE carries 
SET 
    carries.InventoryLevel = 50
WHERE
    carries.storeID = 12023
        AND carries.UPC = 14718436
        AND carries.Condition = 'New';

DELETE FROM itemreview 
WHERE
    reviewID = 17727674;
DELETE FROM review 
WHERE
    reviewID = 17727674;