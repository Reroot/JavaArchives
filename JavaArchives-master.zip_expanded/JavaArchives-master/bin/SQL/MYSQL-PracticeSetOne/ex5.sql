-- For each library branch, retrieve the branch name and the total number of books loaned out from that branch.

SELECT tbl_library_branch.branchname,
    Count(tbl_book_loans.branchid)
FROM tbl_library_branch
    JOIN tbl_book_loans
    ON tbl_book_loans.branchid = tbl_library_branch.branchid
GROUP  BY tbl_library_branch.branchname; 