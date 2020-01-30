Create a library management application on the Command Line which will follow the following protocol.

User is first presented the following options:

**MAIN:**

Welcome to the GCIT Library Management System. Which category of a user are you

1. Librarian
2. Administrator
3. Borrower

\&lt;take input\&gt;

Based on what the user selects, the following prompts will appear to fit his role:

**LIBRARIAN**

**LIB1:**

1. Enter Branch you manage
2. Quite to previous **(should take you menu MAIN)**

\&lt;take input\&gt;

For Option 1, Give a list of Library branches using the names or locations like this:

**LIB2:**

1. University Library, Boston
2. State Library, New York
3. Federal Library, Washington DC
4. County Library, McLean VA
5. Quit to previous **(should take you menu LIB1)**

\&lt;take input\&gt;

The user will only pick the number in the above list and you should figure out which branch he is referring.

Based on the selection, the next list would be:

**LIB3:**

1. Update the details of the Library
2. Add copies of Book to the Branch
3. Quit to previous **(should take you menu LIB2)**

\&lt;take input\&gt;b

**Option 1** should update **library_branch** table for the branch he had picked before. This should be like:

You have chosen to update the Branch with Branch Id: X and Branch Name: ABCD. **Enter &#39;quit&#39; at any prompt to cancel operation.**

Please enter new branch name or enter N/A for no change:

\&lt;take input\&gt;

Please enter new branch address or enter N/A for no change:

\&lt;take input\&gt;

Then update the values for this branch and say successfully updated. Then go back to menu **LIB3,** to start over again.

**Option 2** should give further options like this:

Pick the Book you want to add copies of, to your branch:

1. Lost Tribe by Sidney Sheldon
2. The Haunting by Stepehen King
3. Microtrends by Mark Penn
4. Quit to cancel operation

\&lt;take input\&gt;

Existing number of copies: N **(if none show zero)**

Enter new number of copies:

\&lt;take input\&gt;

Then you should update the book_copies table with the new values. Then take the user back to **LIB3.**

**BORROWER**

**Enter the your Card Number:**

**\&lt;take input\&gt;**

**Don&#39;t let borrower to proceed until he gives valid card number.**

**BORR1:**

1. Check out a book
2. Return a Book
3. Quit to Previous **(should take you menu MAIN)**

**Option 1** should show the following options

Pick the Branch you want to check out from:

1. University Library, Boston
2. State Library, New York
3. Federal Library, Washington DC
4. County Library, McLean VA
5. Quit to previous **(should take you menu BORR1)**

Pick the Book you want to check out **(make sure you only show books that have atleast one copy in BOOK_COPIES in the branch picked)**

1. Lost Tribe by Sidney Sheldon
2. The Haunting by Stepehen King
3. Microtrends by Mark Penn
4. Quit to cancel operation **(should take you menu BORR1)**

**Then add entry into book_loans, date out should be today&#39;s date, due date should be one week from today&#39;s date.**

**Implement the return book functionality the same way as above.**

**Also implement the following ADMINISTRATOR functions:**

1.  **Add/Update/Delete Book and Author**
2.  **Add/Update/Delete Publishers**
3.  **Add/Update/Delete Library Branches**
4.  **Add/Update/Delete Borrowers**
5.  **Over-ride Due Date for a Book Loan**
