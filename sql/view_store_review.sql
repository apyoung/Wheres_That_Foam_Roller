SELECT 
    review.*
FROM
    storereview,
    review
WHERE
    storereview.storeid = 10170
        AND storereview.reviewid = review.reviewid