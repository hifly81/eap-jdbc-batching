# EAP Hibernate Batch Example

A servlet calls a stateless EJB (Transaction Required) with hibernate batch jdbc inserts.

### Compile
    $ mvn clean package

### Run

 -  Deploy the war archive in EAP
 -  Open a browser and call the servlet:<br> 
 http://localhost:8080/eap-hibernate-batch/batchServlet 
 
### persistence.xml

Enabled the property hibernate.jdbc.batch_size with the value for the batch size
