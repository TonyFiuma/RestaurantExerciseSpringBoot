Pilotes Order Management Application
This is a Spring based application that allows you to manage the orders of the "pilotes", a Majorcan recipe consisting
of a meatball stew.

Operations
The following operations are implemented in the application:

Create a pilotes order, choosing between 5, 10 or 15 pilotes.
Update a pilotes order. During the 5 minutes following the creation of the order, it will be allowed to update the order
data. After that time, it will not be possible to modify any data of the order because Miquel will be occupied cooking
the pilotes.
Search orders by customer data. Allow partial searches: e.g., all orders of customers whose name contains an "a" in
their name.
Data Model
The data model used in the application contains the following data:

Basic Client Info
First and last name
Telephone number
Basic Order Info
Order number (server-side generated)
Delivery address
Number of pilotes
Order total (server-side generated, considering 1.33 € as the price for a single pilotes)