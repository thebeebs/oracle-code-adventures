## Mission: Shoot down the Mini Fighters ##

### Mission Description ###

<img align="right" src="../images/fighters.png" width = "300px">
The Alien War Ship has sent out 10 Mini Fighters to attack your fighters! You need to take them down as soon as possible. The mission is completed when a squad has shot down all the Mini Fighters.

### Mission Awards ###

- Maximum number of points for this mission: **500**
- Lesser points will be given to subsequent squads.

### Mission Instructions ###

1. You should now have received information from the spy that will give you the y-coordinates of the Mini Fighters. The example below would shoot down ***one*** of the Mini Fighters. ***The x-coordinate is locked at coordinate 45***.

```http://129.157.179.180:3000/fighters/45/y-coordinate_goes_here/Your_squad_name_goes_here(e.g yellow)/Your_microservice_name_goes_here(e.g YellowJava2Fighter)```. **The Mini Fighters will get hit by HTTP GET Request bullets!**

**Hint: Notice the changed endpoint 'fighters'**

3. Deploy a new version of your microservice by pushing the edited code to the Git repository in the same way as for your first deployment. If you need a reminder on how to do that, check the instructions for the [first mission](deploy.md)

3. When your updated microservice is live, it will hopefully hit the Mini Fighters sent out by the Alien War Ship!

4. If you feel that your microservice is not behaving correctly or might not have been deployed correctly, have a look at the logs as described [here](../logs.md). If you are using the Continuous Integration and Deployment strategy, explore the status of your build in Developer Cloud as described [here](../devcs.md)

### Next: Fifth Mission ###

Excellent! Now it is time to hack their database and destroy the Reactor Core! [Click here](database.md) to continue!
