# ReceiptProcessorFetchChallenge

### How to Run the application

1. Open the command line in the folder ReceiptProcessorFetchChallenge
2. Run the command `mvnw spring-boot:run`
3. After the server starts to run, in Postman or any other method you need to POST an Https request use the URL http://localhost:8080/receipts/process
4. The response from the application should return the uuid of the receipt
5. Create another Https request and post an empty body to http://localhost:8080/receipts/{uuid}/points
6. The response will return a JSON with the points.
