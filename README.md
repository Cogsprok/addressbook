addressbook
===========

Simple Address Book exercise, converting to use MySQL DB.

Original assignment called simply for an address book capable of entering business and personal contacts with specified information, displaying a list of contact names and types, selecting specific contact and displaying contact details. GUI was optional, I took the opportunity to learn basics of Swing, thus a very rough GUI. Data retention after exit was not originally part of the project, but what use is an address book that loses your contacts on exit?

To sharpen MySQL and JDBC knowledge, I decided to go back to this old project and make it at least basically functional as an address book with data retention. I also decided to try out TDD and work on JUnit test writing skills at the same time. Hopefully there will also be some serious refactoring and polishing of code as the project progresses. I'd like to think my development skills have improved a bit from the early days of this project, and that I'll recognize better ways to perform some of the functions within the project. 

-6/26/14:
 Basic implementation is finished, app basically functions. Current code requires a MySQL DB on localhost with DB addressbook and a single table CONTACTS. Columns ID INT NOT NULL AUTO_INCREMENT, VARCHAR colums FNAME, LNAME, ADDRESS, PHONE, EMAIL, TITLE, ORG. DATE column DOB, and CHAR(1) column TYPE. User adbook with pass adbook.
 
 Most of the unit tests were broken with final changes to code. I didn't go back and fix them as I was so close to working code and didn't feel like remocking data, etc. I also found that while a TDD approach was novel and sometimes useful for gathering my thoughts as to next step and how to approach a particular feature, it didn't always work great for testing with a DB, much less when I wanted to test user input through the GUI. Eventually I got tired of mocking up data sets and just wanted to write production code. I also found with some tests that when I couldn't immediately tell where a failure was, I ended up reverting to the same systematic placement of println messages to track down problems, same as I'd do if I weren't running JUnit tests along the way. 
 
Next step is to clean up the code. There's a lot of legacy code that is now not being used for anything, as well as the usual level of refactoring and tidying that can be done. I'll return soon to do the housekeeping. 
