## Mission: Destroy the Reactor Core! ##

### Mission Description ###

<img align="right" src="../images/spaceship_core.png" width = "400px">
The spy should now have exposed the secrets of the database where the Alien War Ship stores the coordinates to it's Reactor Core! When our spy returns with the information, find out the coordinates and attack it with your fighter!

### Mission Awards ###

- Maximum number of points for this mission: **500**
- Lesser points will be given to subsequent squads.

### Mission Instructions ###
1. You should now have received information from the spy about the credentials to the Alien War Ship's MySQL database where the coordinates for the Core Reactor is kept. Develop a MySQL query that queries the **SecretTable** to retrieve information about the Reactor Core coordinates! The Host IP address of the database is **129.157.179.180**

2. When you have the coordinates, hit the Reactor Core at the following URL:
```http://129.157.179.180:3000/reactorCore/x-coordinate_goes_here/y-coordinate_goes_here/Your_squad_name_goes_here(e.g yellow)/Your_microservice_name_goes_here(e.g YellowJava2Fighter)```.
**The Reactor Core will get hit by HTTP GET Request bullets!**

**Hint: Notice the changed endpoint 'reactorCore'**

3. Deploy a new version of your microservice by pushing the edited code to the Git repository in the same way as for your first deployment. If you need a reminder on how to do that, check the instructions for the [first mission](deploy.md)

4. When your updated microservice is live, it will hopefully hit the Alien War Ship's Reactor Core!

5. If you feel that your microservice is not behaving correctly or might not have been deployed correctly, have a look at the logs as described [here](../logs.md). If you are using the Continuous Integration and Deployment strategy, explore the status of your build in Developer Cloud as described [here](../devcs.md)

### Next: You are finished ###

Congratulations! You are finished! If the Alien War Ship is still alive, help out your colleagues!
