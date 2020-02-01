-- For each book that is loaned out from the "Sharpstown" branch and whose DueDateis today, retrieve the book title, the borrower's name, and the borrower's address

SELECT
    tbl_book.title,
    tbl_borrower.name,
    tbl_borrower.address
FROM
    tbl_book_loans
    JOIN
    tbl_book
    ON tbl_book.bookId = tbl_book_loans.bookId
    JOIN
    tbl_borrower
    ON tbl_borrower.cardNo = tbl_book_loans.cardNo
WHERE
    branchId IN
    (
        SELECT
        branchId
    FROM
        tbl_library_branch
    WHERE
            branchName = "Sharptown"
    )
    AND dueDate = CURDATE() ;
