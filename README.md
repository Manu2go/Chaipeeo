# Chaipeeo
An app for online selling of tea shop products (such as :- black coffee,tea,green tea,etc)

Developing an app named "Chaipeeo" for online selling of Tea shop products( like coffee,ice tea,green tea etc.). The app is almost complete. Only payment Gateway Integration is left.

## The activities in the app :-

## 1. MainActivity.class
The app starts with this activity. It is a splashscreen displaying hte company logo. It automatically redirects user to login activity (i.e. login1.class).
<div>
<img src="/Screenshots/13.png" alt="Drawing"  height="300" width="180" >
</div>

## 2. login1.class
This activity is used for user login .A user can login using his email-id and password. On successful login he is directed to order.class . 
<div>
<img src="/Screenshots/14.png" alt="Drawing"  height="300" width="180" >
</div>

## 3. order.class
This activity contains a list of all the products with their image and price. When a user clicks any list item, he is directed to another activity named 'mydialog.class'  where he could choose the list item's quantity . After choosing all the products and their quantity when the user clicks 'Submit' button, he is redirected to buyeraddress.class //put a screen shot of mydialog.class as well
<div>
<img src="/Screenshots/4.png" alt="Drawing"  height="300" width="180" >
</div>

## 4. buyeraddress.class
In this activity  the user mentions  name, address and contact-no of the buyer . After clicking on 'Proceed For Payment', user  is redirected to confirmation.class.
<div>
<img src="/Screenshots/6.png" alt="Drawing"  height="300" width="180" >
</div>

## 5. confirmarion.class
This activity displays your order summary before the final payment. After clicking 'Pay' button, you will be  redirected to payment gateway . 
<div>
<img src="/Screenshots/7.png" alt="Drawing"  height="300" width="180" >
</div>

## 6. settings.class
In This activity, a user can change his password as well as email_ID.
<div>
<img src="/Screenshots/17.png" alt="Drawing"  height="300" width="180" >
</div>

## 7. about.class
This activity will contain a brief informarion about the seller and his company  
<div>
<img src="/Screenshots/16.png" alt="Drawing"  height="300" width="180" >
</div>

## 8. contactus.class
This activity will display contact_details of the seller 
<div>
<img src="/Screenshots/15.png" alt="Drawing"  height="300" width="180" >
</div>

# Things still not completed:-
### 1. Payment gateway Integration:-
'PayU money' payment gateway integration partially completed.
<div>
<img src="/Screenshots/19.png" alt="Drawing"  height="300" width="180" >
</div>

## Note:- 
Sometimes, it may happen that a user might not be able to login or some other operations (requiring server calls) may result with an unsuccessful status, this might be possible due to poor functioning of servers.

Therefore, if a user encounters such problems frequently then he should follow the given steps:-

1. create a local server or choose any remote server (to store php files and to create a MySQL database)
2. download the zip file(containing all php files) from the link:-
  https://drive.google.com/open?id=0B8i_i9ZwP1yIblFMQWtCVE5rSTAand
  and put all the contents of the downloaded zip file in the htdocs(=or public_html) folder on his server
3. download the .sql file from the link:- https://drive.google.com/open?id=0B8i_i9ZwP1yITEh4LXpOZGYzOE0 
   and create a database from that file
4. add the details of his newly created database in dbconnect.php file present inside the htdocs(=or public_html) folder on his server
5. and then go to app->src->main->java->manan->chaii-->URLs.java 
	and change the URLs accordingly
