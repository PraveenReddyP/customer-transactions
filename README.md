# Getting Started

## Application setup
1. To run this spring boot application, need java 17 or above and postgres database in the network.
2. Create a database with name "customertransactions"
3. Configure the jdbc connection in  application.properties file. Please edit below 3 properties.

   spring.datasource.url=jdbc:postgresql://localhost:5432/customertransactions

   spring.datasource.username=postgres

   spring.datasource.password=postgres

4. Create table called "transaction" with below query

   CREATE TABLE transaction (
   transactionid VARCHAR (50) PRIMARY KEY,
   customerid VARCHAR (20),
   amount numeric (13,2),
   transactiondate TIMESTAMP
   );

## Rest Endpoints implemented
### 1. Add Transaction
#### Description: Create a new transaction
#### End point: {POST} http://localhost:8080/transactions/addTransaction
#### Sample request:
{

"transactionid":"7",

"customerid":"parveen",

"amount":111,

"transactiondate": "2024-03-05T09:45:00.897"

}

### 2. update Transaction
#### Description: Update an existing transaction with transaction ID
#### End point: {POST} http://localhost:8080/transactions/updateTransaction/{tranasctionID}
#### Sample request:
{

"transactionid":"5",

"customerid":"parveen",

"amount":152,

"transactiondate": "2024-03-06T09:45:00.897"

}

### 3. Get Reward
#### Description: Get last 3 month and Total reward points of a customer
#### End point: {GET} http://localhost:8080/transactions/getrewards/{customerID}
#### Sample Response:
{

"TOTAL": 380.0,

"MARCH": 226.0,

"APRIL": 154.0

}
## Unit tests
#### Wrote unit test cases for rewards calculation logic. Rewards calculation logic implemented in service layer of the application













