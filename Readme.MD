Login Application

Built using Spring Boot 3.0.6, 

Import the MySQL database, with root/password.

Run LoginexampleApplication.java, website will be hosted on localhost:8080.

Accounts are manager/manger, user/user. Any additional accounts need to be added to the user DB (password has to be Bcrypt-ed), and roles assigned in the user_roles DB (INSERT INTO `user_roles` (`id`, `role`) VALUES ([user_id], [role (1=manager, 2=user)]);)

Index page shows different options for users and manager. Trying to access /manager with a user account redirects to a 403 page.



En/decoder (en-decoder.java)

Run with java en-decoder.java [(E)ncrypt/(D)ecrypt] [input String] [offset Char]
With no args, program will run with "'hello world!!{}[]' and random offset.
