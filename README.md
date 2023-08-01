# Tool_Rental
___
## Pulling and Running Application
Should be able to clone this repository. Project is set up to run on Main start.
### How project is set up
Main is set to pull three files from resources.

input-transactions.txt serves as a repository of transactions.

price-index-lookup.txt serves as a lookup table for product prices, similar to a table lookup in a database.

tool-inventory.txt serves as a lookup table for product offerings.

See each file for their formatting. If formatting other than what is entered in each file is present, then the project will generate exceptions.
### Startup
On startup, list of transactions will be read from input-transactions. Modifications to this file will generate different transaction results.
Each transaction will be read to a transaction object. Each transaction will create a ChargePeriod object, where an operation will execute to determine the type of each day of the rental period. Each day will be run against the price index to determine which days that transaction will associate the price to. 

Transaction is written such that an object can be created without the use of a startup file. I included a startup file for easy execution.

### Result 
Transaction generates a list of Rental Agreements. The main class reads over the list and prints out each rental agreement.

Example of output: based on transaction input of "JAKD 9/3/15 6 0%"

Tool :  
Jackhammer  
JAKD  
DeWalt  
Number of Days Renting : 6  
Checkout Date : 2015-09-03  
Return Date : 2015-09-08  
Daily Rental Fee : $2.99  
Charge Days : 3  
Pre-discount Charge : $8.97  
Discount : 0%  
Discount Amount : $0.00  
Final Charge Amount : $8.97  

