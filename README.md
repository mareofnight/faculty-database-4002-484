Faculty Database
================
Program written by David Desorcie (daviddesorcie@gmail.com), Jessica Dopkant (coolgrl989@gmail.com) and Pawel Baltaziuk. 2012-11-10

This program was a project for the RIT class 4002-484 Database Client/Server Connectivity. The task was to design and develop a database of university faculty and classes, and an accompanying application to view and update them.

Detailed documentation
----------------------
UML diagrams, and HTML-format documentation based on the method comments, are in the /documentation folder.

/documentation/UserDocs.docx is a word document with explanations and screenshots of the program's basic functions, from the user's perspective.

Installation and use
--------------------

1. Make sure you have Java and MySQL installed
2. Download the project
3. Open MySQL, and run facultyactivity.sql to create the database and insert dummy data.
4. Set the classpath for the JDBC database driver. The driver location is /drivers/mysql-connector-java-5.1.31-bin.jar. More info on classpaths: http://dev.mysql.com/doc/connector-j/en/connector-j-installing-classpath.html
5. While in the main faculty-database folder, use Java to run the presentation_layer.LoginGUI class.

This program was designed to work with the root user of the computer lab databases, which always had no password. The application won't work when the root MySQL user does have a password. This will be fixed soon because it is insecure.

### Login credentials
Valid user IDs are 1, 2, and 3. The passwords are all "password". These users can be modified through the program.
