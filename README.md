# Test Assignment (Integration Test)
This repository is a exercise project for Software development (PBA) Test course. Daniel (cph-dh136)

## Description
This is a test assigmment which goal is to do integration testing. Testing the integration points eg. UI - Business logic and/or business logic - Database. Hence integration testing. The assignment description can be found [Here](https://gyazo.com/d556007e1f71fba0727179ae9e63eced)

## Assignment
Starting off with a plan to test. The approach to testing follows a sandwhich approach, meaning we will move in from both bottom and top. Differnt from top to buttom or buttom to top. First up, would be the database and the DataAccessor class integration point. And then the UI - controller, and afterwards the whole thing. This way will mean a stubbing is required. This is typically on the lower layers. ie. Since we want to test the UI and controller independently of the database and the dataAccesor class, we want to stub away this part. However we don't need to stub the UI to test the integration between database and dataAccesor. The chosen techniques for testing is a little mix of Black box and white box testing. For the database-acessor we want to be sure that the right data is getting accessed, created, deleted and so on. But we also want to do whitebox testing in the manner that we want to test it doesn't take ages to get this data. ie. Performance testing the connection. We have chosen to do everything Automated, but if we never had to change the code. The conceptual idea of Automation isn't so good. Since the value comes from regression, that we can run the test fast, easy again to see if the system still behaves fine after change. So if we didn't need to change the code, we could easily get a manual test over with quickly.

## Wise customs
These are wise decisions/Customs/idiomatic approaches picked up on this assignments.

- Never test on live data (This idiotic, and self-explanatory. Lets change someones password because of a test)
- Should avoid testing on test data in live database (This can potentially screw up the production software and can be catastrophic)
- It is good idea to copy the database of the actual live data, to get a close representation of the live data. This will result in very similar circumstances in the tests and developments.
- It is a good idea to have scripts to init (copy or setup) and clean up (Delete or teardown) of the test database.
- Another good custom is to have a enviroment variable. ie. When code is run on developer machine it is connecting to test database, but if the code is run on server it is connecting to live database. This ensures the developers don't have to change the connection back after development, which means they cannot screw it up.

## Tests
Tests can be found in this repo.

see:
- [DataAccessorDatabaseTest.java](Test7/src/test/java/data/DataAccessorDatabaseTest.java)
- [ControllerTest.java](Test7/src/test/java/logic/ControllerTest.java)
- [MainTest.java](Test7/src/test/java/MainTest.java)
