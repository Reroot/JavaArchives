-- Retrieve the names of all borrowers who do not have any books checked out .

SELECT
    *
FROM
    tbl_borrower
WHERE
    cardno NOT IN (
        SELECT
    cardno
FROM
    tbl_book_loans
WHERE
            datein IS NULL);

