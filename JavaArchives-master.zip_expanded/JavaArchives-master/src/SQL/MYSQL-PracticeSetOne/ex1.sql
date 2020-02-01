DELETE FROM tbl_publisher WHERE publisherId > 0;
-- How many copies of the book titled The Lost Tribe are owned by the library branch whose name is "Sharpstown"?

SELECT
    noOfCopies
FROM
    tbl_book_copies
WHERE
    branchId = (
        SELECT
        branchId
    FROM
        tbl_library_branch
    WHERE
            branchName = "Sharptown")
    AND bookId = (
        SELECT bookId
    FROM tbl_book
    WHERE title = "The Lost Tribe"
    );

