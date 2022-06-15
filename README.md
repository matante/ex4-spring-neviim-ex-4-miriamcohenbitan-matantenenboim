[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7370503&assignment_repo_type=AssignmentRepo)
# ex4 - Miriam Cohen Bitan and Matan Tenenboim
<h1>Author(s)</h1>
<ol>
<li>Miriam Cohen Bitan, miriamco@edu.hac.ac.il</li>
<li>Matan Tenenboim, matante@edu.hac.ac.il</li>
</ol>

<h1>Documentation</h1>
<p>
In this project we built a Sweet Store website.

The users are able to browse the website, add Sweets to their cart.

The users then can proceed to check their cart before committing the checkout, add more items, add more quantity to an item in the list, or remove an item (or remove all at once).

To make the purchase, the user need to log in.

Available users:
user1, user2, user3.
password for any of them is "user"

If the purchase failed (more sweets in the cart than in the store, or if sum to pay is 0), the users will be notified and will not be able to proceed.

After a successful purchase, the payment information is added to the admin panel.
 
To log in to admin, use "http://localhost:8080/admin" with username "admin" and password "admin".

A user must log off before admin can log in (in the same browsing session). you can do it by using "http://localhost:8080/logout".

The program counts and prints the number of active sessions.

The form in the payment page is not active, and there just for the entertainment (maybe we will make it active in the future ☻)
, which means one can click the payment button without inserting any information, and if inserted, the information goes nowhere
This applies also for the Coupon code (we disabled the option to insert and click on Coupon)
</p>
<h1>Tests and input files</h1>
<p> We added "ex4.sql" file in the main folder of this repo

We added JavaDoc folder in the main folder of this repo
</p>
<h1>Assumptions</h1>
<p>
We made a store of Sweets rather than Books (approved by Solange)

As written above, we added 3 users to get the bonus.

In the cart, if the users want to update the amount of an item manually, they can insert the number to the text box, and click on the refresh icon next to it.

</p>
