## Mission: Hack the Fuel Tank! ##

### Mission Description ###

<img align="right" src="../images/fueltank.jpg" width = "400px">
If we are lucky, our spy has found an encrypted password that can be used to remotely control the Fuel Tank of the Alien War Ship.
If we manage to decrypt the password, we should be able to access the control panel and destroy the Fuel Tank!

### Mission Awards ###

- Maximum number of points for this mission: **1000**
- Lesser points will be given to subsequent squads.

### Mission Instructions ###

1. Wait until our spy has found the encrypted password!

2. Try to decrypt the password. You will be given the following hints:
**HEX**, **Decimal**, **Alphabet**, **-100**, **-200**, **Pattern**, **Ceasar Box**

![Ceasar Map](../images/caesarbox.jpg)
<!--
+ Note to instructor: Edit this page with the correct IP address and port in the URL.
-->
3. To access the Fuel Tank you will need to enter the decrypted password in the Authentication header of the request. The base URL of the Fuel Tank is ```REPLACE_WITH_IP:3000/fueltank/Your_squad_name_goes_here(e.g yellow)/Your_microservice_name_goes_here(e.g YellowJava2Fighter)```. **The shield will get hit by HTTP GET Request bullets!** ```Headers: Authorization: passwordGoesHere```

4. Deploy a new version of your microservice either by using [Continous Integration and Deployment](../deployment/cicd.md) or the [manual](../deployment/manually.md) approach.

5. When your updated microservice is live, it will hopefully hack the Fuel Tank!

6. If you feel that your microservice is not behaving correctly or might not have been deployed correctly, have a look at the logs as described [here](../logs.md). If you are using the Continuous Integration and Deployment strategy, explore the status of your build in Developer Cloud as described [here](../devcs.md)

### Next: End ###

Congratulations! You have completed all the missions!
