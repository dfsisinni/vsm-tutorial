#Vaadin Shiro MySQL Tutorial
This sample project provides a template for implementing authentication and authorization in Vaadin with the Apache Shrio Java Security Framework. In this tutorial, Apache Shiro references a MySQL database (vsmtutorial) for user and user role data (2 separate tables). SHA-512 Hashing is used for authentication.

NOTE: This tutorial emphasizes <strong>FUNCTIONALITY</strong> over appearance.

The default Apache Shrio realm for MySQL is used. Reference the contents of the READ-ME folder for more information.

##Important Files:
<ul>
<li>ivy.xml - required repository references for Apache Shiro </li>
<li>src/shiro.ini - MySQL and SHA-512 Hashing configuration for Shiro </li>
<li>WebContent/WEB-INF/web.xml - required for Shiro to function </li>
<li>WebContent/WEB-INF/lib - required JAR files </li>
<li>READ-ME - MySQL Files </li>
</ul>

##App Login Info:
<ul>
<li>Manager:  dfsisinni - dfsisinni</li>
<li>Customer: jsmith - jsmith</li>
</ul>

##MySQL EER Diagram:
![alt tag](https://raw.github.com/dfsisinni/vsm-tutorial/master/READ-ME/vsm-eer-diagram.png)


