## ASE - Y2S1 - Client Project ShopHub - Team 4

### Process for starting the database
- Load your preferred version of a MariaDB viewing program 
  - We suggest MySQL Workbench as this is what we used during development
- Open and run the "schema.sql" file
  - Located at "src/main/resources/database"
- Open and run the "script.sql" file
  - Located at "src/main/resources/database/data script"

### Process for starting the server
- Assure that the database is started, if not follow the instructions above
- Edit your login details for your database connection in the "application-maria.properties" file
  - Alter the "spring.datasource.username" and "spring.datasource.password" values, replacing "root" and "comsc" respectively
  - These fields must match that of your root user within your Database
  - This can normally be located when the connection to your database server is made
- In the root directory for the project, open a command prompt
- Run the command "gradle build" to create an executable version of the main script files
- Then, run the command "java -jar "build/libs/ClientProject-0.0.1-SNAPSHOT.jar""
- The server is now running, and can be accessed using the url "localhost:5000"
- To close the server, simply press "CONTROL + C" in the command prompt

### Miscellaneous Helpful Information
- If the database becomes corrupted or data unusable
  - Simply follow and complete the instructions for starting the database
  - It will automatically delete the old database as the new one is created
- Database Structure
  - If you require knowledge about the structure of the Database, an Entity Relationship Diagram has been made
  - There are multiple in the directory "src/main/resources/database" from throughout the project
  - The most up-to-date version of this image is "EndOfProjectERD.png"
- Team Members
  - Josh Gill (gillj8@cardiff.ac.uk)
  - John Watkins (watkinsj18@cardiff.ac.uk)
  - Sebastian Barnard (barnards3@cardiff.ac.uk)
  - Ethan Allen-Harris (allen-harrise@cardiff.ac.uk)
