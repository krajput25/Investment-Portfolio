# Investment-Portfolio

DESCRIPTION OF THE PROGRAM:

This program exhibits a GUI where the user can perform various functions described below in the ePortfolio through an easy to use interface. 
Any error messages are displayed on the individual interfaces for each of the commands. The user begins by choosing which command he wants through the menu
at the top - 'Commands' and then enters the investment details accordingly. Any unacceptable value is rejected through exception handling and the user is 
given a message along with a chance to try again through the Reset button. The user can end the program both my closing the initial frame or by choosing 
quit command on the menu.

It is advised that the user maximizes the frame according to his/her convenience. 



FUNCTIONS:
Buy -
The user can use this option to purchase a new investment or purchase additional investments to add to an existing investment. Specify the symbol, name, quantity, and price to buy a new investment.
The hashmap is updated after a successful purchase.
Sell -
The user can use this option to sell an existing investment or a part of it. Specify the quantity or stocks or funds to be sold, symbol of the investment, and the price at which the investment is being sold. If the entire investment is sold, the record is removed from the system.
The hashmap is updated after a successful sell. The payment received is displayed.
Update -
The user can use this option if he wishes to update th prices of all existing investments.
Search -
Using this option, the user can look for an investment based on a singular name keyword that he specifies using a Hashmap. This program currently works for only single keyword search.
This is a faster approach as compared to the normal search function.
GetGain -
This option will help the user know the total gain of the portfolio at a point in time based on his existing investments. The user can see all individual gains as well.
Quit -
User can use this option to quit the program.

RUNNING THE PROGRAM:
Run the Portfolio.java file to run the GUI. Choose from the Commands menu to proceed. The user will be directed to the appropriate interface to perform functions on investments (either Stock or MutualFund)


TEST PLAN:
I tested each of the commands by creating various contentPanes and assigning only one to be visible at a time. I tried selecting all the commands one by one
and observed the change in interfaces. I tried entering incorrect values for the text fields or leave them empty and observed the appropriate error messages 
appear in the Messages text area at the bottom. Exception handling makes sure that any invalid user input is rejected but the Reset button gives the user to 
try again as many times he needs to. I tried exiting the GUI both by closing the initial frame or by selecting the quit option from the menu any time throughout 
the run of the program. Either way works. I tried buying, selling investments of both types - Stock and MutualFund. Succesful messages proved that both options 
of the Combo-box for investment type options work. I tried updating prices, observing the total Gain of the portfolio and the individual gains as well. The 
results observed were as expected. 
the search function was tested by entering single name keywords and the correct investment found was returned as expected. Which proves the correct functioning
of the hashmap.


FUTURE ENHANCEMENTS:
This program can undergo various future enhancements in the future both with regards to functionality and appeal. 
The search function in particular will undergo changes to add an option for the user to select the price range within which he wishes to search invsesments. 

