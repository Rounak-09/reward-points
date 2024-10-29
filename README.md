# Reward Points

A spring boot application project to calculate the reward points per customer per month and total from the given transactions.


## Getting Started

These instructions will give you a copy of the project up and running on
your local machine for development and testing purposes. See deployment
for notes on deploying the project on a live system.

### Prerequisites

Requirements for the software and other tools to build, test and push 
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org/)
- [MongoDB 7](https://www.mongodb.com/try/download/community)

### Installing

A step by step series of examples that tell you how to get a development environment running.

There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method from your IDE in the:

    rewardPoints/src/main/java/com/walmart/rewardPoints/RewardPointsApplication.java

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

Once the application has started, open below url in your web browser:

```shell
localhost:8080/swagger-ui/index.html
```

You should be able to see this screen:
<img width="953" alt="image" src="https://github.com/user-attachments/assets/a8bd90aa-7971-4a42-a61a-206fc1dfa65c">



## Running the tests

There are several ways to run the tests in a spring boot application. 
One way is to execute the test file from your IDE in the:

    rewardPoints/src/test/java/com/walmart/rewardPoints/RewardPointsApplicationTests.java

Alternatively you can right-click on the root folder and select Run All Tests in your IDE:

![image](https://github.com/user-attachments/assets/21d89c2e-f444-4a75-ab40-273477efbf32)


### Sample Tests

These tests are written as per the business logic to check if the application is able to satisfy all the business requirements.

Below code checks if the saveCustomerHandler() method in CustomerController class is working as expected:

    @Test
    void saveCustomerHandlerTest() {
        Customer customer = new Customer("001", "Rounak", "7978881737", "Bhubaneswar");

        when(customerDAO.save(customer)).thenReturn(customer);
        assertEquals(new ResponseEntity<>(customer, HttpStatus.CREATED), customerController.saveCustomerHandler(customer));
    }

Below code checks if the saveInvoice() method in CustomerService class is working as expected:

    @Test
    void saveInvoiceTest() {
        Invoice invoice = new Invoice("001", LocalDate.parse("2024-06-10"), "001", "Rounak",
                Stream.of(
                                new Item("001", "Chips", 1, 20.0, 20.0),
                                new Item("002", "Chocolate", 2, 50.0, 100.0))
                        .collect(Collectors.toList()), 120.00);

        when(customerDAO.existsById(invoice.getCustomerId())).thenReturn(true);
        when(invoiceDAO.existsById(invoice.getInvoiceNumber())).thenReturn(false);
        when(invoiceDAO.save(invoice)).thenReturn(invoice);
        assertEquals(invoice, invoiceService.saveInvoice(invoice));
    }

Below code checks if the getCustomerRewardPoints() method in RewardService class is working as expected:

    @Test
    void getCustomerRewardPointsTest() {
        List<Invoice> invoices = Stream.of(
                new Invoice("001", LocalDate.parse("2024-06-10"), "001", "Rounak", new ArrayList<>(), 120.00),
                new Invoice("002", LocalDate.parse("2024-06-12"), "001", "Rounak", new ArrayList<>(), 60.00),
                new Invoice("003", LocalDate.parse("2024-06-15"), "001", "Rounak", new ArrayList<>(), 40.00),
                new Invoice("004", LocalDate.parse("2024-07-10"), "001", "Rounak", new ArrayList<>(), 110.00),
                new Invoice("005", LocalDate.parse("2024-07-12"), "001", "Rounak", new ArrayList<>(), 90.00),
                new Invoice("006", LocalDate.parse("2024-06-10"), "002", "Ravi", new ArrayList<>(), 150.00),
                new Invoice("007", LocalDate.parse("2024-06-12"), "002", "Ravi", new ArrayList<>(), 30.00),
                new Invoice("008", LocalDate.parse("2024-06-15"), "002", "Ravi", new ArrayList<>(), 20.00),
                new Invoice("009", LocalDate.parse("2024-07-12"), "002", "Ravi", new ArrayList<>(), 190.00),
                new Invoice("010", LocalDate.parse("2024-07-12"), "002", "Ravi", new ArrayList<>(), 10.00),
                new Invoice("011", LocalDate.parse("2024-08-10"), "002", "Ravi", new ArrayList<>(), 5.00),
                new Invoice("012", LocalDate.parse("2024-08-12"), "002", "Ravi", new ArrayList<>(), 15.00)
        ).collect(Collectors.toList());
        
        List<CustomerRewardPoints> customerRewardPointsList = Stream.of(
                        new CustomerRewardPoints("001", "Rounak", Stream.of(
                                        new MonthlyRewardPoints(Month.JUNE, 100),
                                        new MonthlyRewardPoints(Month.JULY, 110))
                                .collect(Collectors.toList()), 210),
                        new CustomerRewardPoints("002", "Ravi", Stream.of(
                                        new MonthlyRewardPoints(Month.JUNE, 150),
                                        new MonthlyRewardPoints(Month.JULY, 230),
                                        new MonthlyRewardPoints(Month.AUGUST, 0))
                                .collect(Collectors.toList()), 380))
                .collect(Collectors.toList());
                
        when(invoiceDAO.getByInvoiceDateBetween(LocalDate.parse("2024-05-31"), LocalDate.parse("2024-09-01"))).thenReturn(invoices);
        assertEquals(customerRewardPointsList, rewardService.getCustomerRewardPoints("2024-06-01", "2024-08-31"));
    }


## Deployment

The easiest way to deploy the sample application to OpenShift is to use the [OpenShift CLI](https://docs.openshift.org/latest/cli_reference/index.html):

```shell
oc new-app codecentric/springboot-maven3-centos~https://github.com/Rounak-09/reward-points
```

This will create:

* An ImageStream called "springboot-maven3-centos"
* An ImageStream called "reward-points"
* A BuildConfig called "reward-points"
* DeploymentConfig called "reward-points"
* Service called "reward-points"

If you want to access the app from outside your OpenShift installation, you have to expose the reward-points service:

```shell
oc expose reward-points --hostname=www.example.com
```

