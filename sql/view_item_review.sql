SELECT 
    review.*
FROM
    review,
    storereview
WHERE
    review.reviewid = storereview.ReviewID
