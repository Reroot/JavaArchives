-- For each book authored (or co-authored) by "Stephen King", retrieve the title and the number of copies owned by the library branch whose name is "Central".

SELECT
    tbl_book.title AS title,
    tbl_book_copies.noOfCopies AS copies
FROM
    tbl_book
    JOIN
    tbl_book_copies
    ON tbl_book.bookId = tbl_book_copies.bookId
    JOIN
    tbl_library_branch
    ON tbl_library_branch.branchId = tbl_Book_copies.branchId
WHERE
	tbl_library_branch.branchName = "Central"
    AND title = "Stephen King";