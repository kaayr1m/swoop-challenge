Developer Test
The test is not meant to be a pass/failure, but rather to gauge the candidates strengths and weaknesses, specifically in the area of full stack Java development. Candidates will be considered based on their programming response and also their deliverables. 
Goal

Build a Restful Service written in Java or Groovy with the following endpoints:

GET : http://<server_url>/math/add?n1=<numeric param 1>&n2=<numeric param 2>

This should add numbers 1 and 2 and provide the result in JSON


POST : http://<server_url>/math/add	(allow for form params 1&2 in a POST body)

This should add two numbers from a POST body


GET : http://<server_url>/time/now 

This should fetch time for MST at time of call from another service (https://www.developer.aero/WaitTime-API/Try-it-Now for YYC is a free one) and simplify the result to timezone and current time.  Return the result or the timestring in a rational JSON document.  

The service must be built using the following technologies:

Spring Boot
Gradlew (build)
Git (source control)

Please provide a Dockerfile using alpine linux:

Docker (optional - see below)

Please provide all code and answers in a public github (or equivalent) repo. Include a README.md file in the repo with any necessary documentation.
Additional Questions & Deliverables
The following is a set of questions and deliverables that should be completed and provided as part of the answer, respectively. If you cannot provide an answer please describe why. 

All code should be publicly accessible via github url (or similar public git repo).

The Rest Service should be deployed to a publicly accessible server on the internet (preferably AWS). Please provide reasons why AWS was not used. If the service is not deployed remotely please provide instructions on how to run it locally. The service should be runnable outside an IDE.


A set of integration tests asserting server functionality (Postman test suites, Rest Assured tests or bash scripts preferred). 


Documentation detailing any service design, security choices regarding api keys or credentials or implementation preferences.


A dockerfile providing packaging as a docker container. If omitted please provide reasoning why Docker was not used.


Explain how caching could be performed on the Restful service and pros/cons


All documentation and code should be provided within the README.md file in github
