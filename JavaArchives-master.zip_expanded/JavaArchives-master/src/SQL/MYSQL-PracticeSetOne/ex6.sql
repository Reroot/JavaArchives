-- Retrieve the names, addresses, and number of books checked out for all borrowers who have more than five books checked out.

SELECT tbl_borrower.NAME,
    tbl_borrower.address,
    Count(tbl_book_loans.cardno) AS books
FROM tbl_borrower
    LEFT JOIN tbl_book_loans
    ON tbl_book_loans.cardno = tbl_borrower.cardno
GROUP  BY tbl_borrower.cardno; 