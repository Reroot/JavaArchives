--How many copies of the book titled The Lost Tribe are owned by each library branch?

SELECT
    tbl_library_branch.branchname,
    tbl_book_copies.noofcopies
FROM
    tbl_library_branch
    JOIN tbl_book_copies ON tbl_library_branch.branchid = tbl_book_copies.branchid
WHERE
    tbl_book_copies.bookid = (
        SELECT
    bookid
FROM
    tbl_book
WHERE
    title = "The lost tribe");

