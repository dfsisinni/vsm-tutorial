# vsm-tutorial
This repository contains a tutorial for implementing authentication and authorization in Vaadin with the Apache Shrio Java Security Framework. In this tutorial, Apache Shiro references a MySQL database (vsmtutorial) for user and user role data. SHA-512 Hashing is used for authentication.

NOTE: This tutorial emphasizes <strong>FUNCTIONALITY</strong> over appearance.

The default Apache Shrio realm for MySQL is used. Reference the contents of the READ-ME folder for more information.

##Important Files:
ivy.xml - required repository references for Apache Shiro
src/shiro.ini - MySQL and SHA-512 Hashing configuration for Shiro
WebContent/WEB-INF/web.xml - required for Shiro to function
WebContent/WEB-INF/lib - required JAR files
READ-ME - MySQL Files

##MySQL EER Diagram:
![alt tag](https://raw.github.com/dfsisinni/vsm-tutorial/master/READ-ME/vsm-eer-diagram.png)


