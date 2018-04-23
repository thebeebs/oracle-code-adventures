# Introduction to Fn

Fn is a lightweight Docker-based serverless functions platform you can
run on your laptop, server, or cloud.  In this adventure  we will walk through installing Fn, develop a function and
deploy them to a local Fn server.

The function we are going to create will control the launch of a Space Rocket. It will accept some parameters, perform a calculation and then output some variables to control the launch. 

Once we have hosted our function we will submit it to a Mission Control Dashboard to see how our function performs.  

So let's get started!

## Installing Fn

Setting up a working Fn install is a two-step process.  First you need
to ensure you have the necessary prerequisites and then you can install
Fn itself.

### Prerequisites

Before we can install Fn you'll need:

1. A computer running Linux or MacOS.  If you have a Windows machine the
easiest thing to do is install [VirtualBox](https://www.virtualbox.org/)
and run a free Linux virtual machine.
2. [Docker](https://www.docker.com/) 17.05 (or higher) needs to be
installed and running.

> __NOTE__ In this tutorial we'll work in a purely local development
mode.  However, when deploying functions to a remote Fn server, a Docker
Hub (or other Docker registry) account is required.

That's it.  You can use your favorite IDE for function development.
However, for this tutorial, an IDE isn't necessary.

### Downloading and Installing Fn

From a terminal type the following:


![](images/userinput.png)
>`curl -LSs https://raw.githubusercontent.com/fnproject/cli/master/install | sh`

Once installed you'll see the Fn version printed out.  You should see
something similar to the following displayed (although likely with a later
version number):

```sh
fn version 0.4.62
```

### Starting Fn Server

The final install step is to start the Fn server.  Since Fn runs on
Docker it'll need to be up and running too.

To start Fn you can use the `fn` command line interface (CLI).  Type the
following but note that the process will run in the foreground so that
it's easy to stop with Ctrl-C:

![user input](images/userinput.png)
>`fn start`

You should see output similar to:

```sh
time="2017-09-18T14:37:13Z" level=info msg="datastore dialed" datastore=sqlite3 max_idle_connections=256
time="2017-09-18T14:37:13Z" level=info msg="available memory" ram=1655975936
time="2017-09-18T14:37:13Z" level=info msg="Serving Functions API on address `:8080`"

      ______
     / ____/___
    / /_  / __ \
   / __/ / / / /
  /_/   /_/ /_/
```

Let's verify everthing is up and running correctly.

**Open a new terminal** and run the following:

![user input](images/userinput.png)
>`fn version`

You should see the version of the fn CLI (client) and server displayed (your version will
likely differ):

```sh
Client version:  0.4.62
Server version:  0.3.335
```

If the server version is "?" then the fn CLI cannot reach the server.  If
this happens it's likely you have something else running on port 8080. In this
case stop the other process, and stop (ctrl-c) and restart the fn server as
described previously.

## Your First Function

Before we start developing we need to set the `FN_REGISTRY`
environment variable.  Normally, it's set to your Docker Hub username.
However in this tutorial we'll work in local development mode so we can set
the `FN_REGISTRY` variable to an arbitrary value. Let's use `fndemouser`.

![user input](images/userinput.png)
>`export FN_REGISTRY=fndemouser`


With that out of the way, let's create a new function. In the terminal type the
following.

![user input](images/userinput.png)
>`fn init --runtime node launch`

The output will be

```yaml
Creating function at: /launch
Runtime: node
Function boilerplate generated.
func.yaml created.
```

The `fn init` command creates an simple function with a bit of boilerplate to
get you started. The `--runtime` option is used to indicate that the function
we're going to develop will be written in Node. A number of other runtimes are
also supported.  Fn creates the simple function along with several supporting files in the `/launch` directory.

### Reviewing your Function File

With your function created change into the `/launch` directory.

![user input](images/userinput.png)
>`cd launch`

Now get a list of the directory contents.

![user input](images/userinput.png)
>`ls`

>```sh
> func.js func.yaml  test.json
>```

The `func.js` file which contains your actual node function is generated along
with several supporting files. To view your Node function type:

![user input](images/userinput.png)
>cat func.js

Open up the func.js file and add the following code:

```
fs = require('fs')
try{
incoming = JSON.parse(fs.readFileSync('/dev/stdin').toString())
incoming.mass = 1000000
console.log(JSON.stringify(incoming))
}
catch{
    console.log(`{"message": "There was no input"}`)
}
```

This function looks for JSON input in the form of `{"mass": 2000000, "propellant": 1000000,"thrust": 300000,"colour": "white", "burntime": 124}`. If this
JSON example is passed to the function, you should return your configuration, you can simply return it without altering (but you can modify some of the parameters to get better heights)  `{"mass": 1000000, "propellant": 1000000,"thrust": 300000,"colour": "white", "burntime": 124}`.


### Understanding func.yaml
The `fn init` command generated a `func.yaml` function
configuration file. Let's look at the contents:

![user input](images/userinput.png)
>cat func.yaml

```yaml
TODO
```

The generated `func.yaml` file contains metadata about your function and
declares a number of properties including:

* name--the name of the function. Matches the directory name.
* version--automatically starting at 0.0.1
* runtime--the name of the runtime/language which was set based on the value set
in `--runtime`.
* entrypoint--the name of the executable to invoke when your function is called,
in this case `./func`
* format--the function uses JSON as its input/output method ([see: Open Function Format](https://github.com/fnproject/fn/blob/master/docs/developers/function-format.md)).

There are other user specifiable properties but these will suffice for
this example.  Note that the name of your function is taken from the containing
folder name.  We'll see this come into play later on.

### Other Function Files
The `fn init` command generated two other files.

* `test.json` -- a test file that is used to test your function, it defines an
input and the output of the function, helps to identify if the function works
correctly or not. Function testing is not covered in this tutorial.


## Running Your First Function

With the `launch` directory containing `func.js` and `func.yaml` you've
got everything you need to run the function.  So let's run it and
observe the output.  Note that the first time you build a
function of a particular language it takes longer as Fn downloads
the necessary Docker images.

![user input](images/userinput.png)
> `fn run`

```sh
Building image fndemouser/gofn:0.0.1 ...
{"message": "There was no input"}
```

The last line of output should be `{"message": "There was no input"}` that was produced
because the try statement failed as there was not input parameters.

If you ever want more details on what a given fn command is doing behind the
scenes you can add the `--verbose` switch.  Let's rerun with verbose output
enabled.

![user input](images/userinput.png)
> `fn --verbose run`

```sh
TODO
```

You can also pass data to the run command. For example:

![user input](images/userinput.png)
> `echo -n '{"mass": 2000000}' | fn run`

```sh
Building image fndemouser/launch:0.0.1 .....
{"mass": 1000000}
```

The JSON data was parsed and since `mass` was set, that value is passed
back in the output.

### Understanding fn run
If you have used Docker before the output of `fn --verbose run` should look
familiar--it looks like the output you see when running `docker build`
with a Dockerfile.  Of course this is exactly what's happening!  When
you run a function like this Fn is dynamically generating a Dockerfile
for your function, building a container, and then running it.

> __NOTE__: Fn is actually using two images.  The first contains
the language compiler and is used to generate a binary.  The second
image packages only the generated binary and any necessary language
runtime components. Using this strategy, the final function image size
can be kept as small as possible.  Smaller Docker images are naturally
faster to push and pull from a repository which improves overall
performance.  For more details on this technique see [Multi-Stage Docker
Builds for Creating Tiny Go Images](https://medium.com/travis-on-docker/multi-stage-docker-builds-for-creating-tiny-go-images-e0e1867efe5a).

`fn run` is a local operation.  It builds and packages your function
into a container image which resides on your local machine.  As Fn is
built on Docker you can use the `docker` command to see the local
container image you just generated.

You may have a number of Docker images so use the following command
to see only those created by fndemouser:

![user input](images/userinput.png)
>`docker images | grep fndemouser`

You should see something like:

```sh
fndemouser/launch      0.0.1               7b586506a195        5 minutes ago        15MB
```

## Deploying Your First Function

When we used `fn run` your function was run in your local environment.
Now let's deploy your function to the Fn server we started previously.
This server could be running in the cloud, in your datacenter, or on
your local machine like we're doing here.

Deploying your function is how you publish your function and make it
accessible to other users and systems.

In your terminal type the following:

![user input](images/userinput.png)
> `fn deploy --app lauchapp --local`

You should see output similar to:

```sh
Deploying launch to app: launchapp at path: /launch
Bumped to version 0.0.2
Building image fndemouser/launch:0.0.2 .....
Updating route /launch using image fndemouser/launch:0.0.2...
```

Functions are grouped into applications so by specifying `--app launchapp`
we're implicitly creating the application "launchapp" and associating our
function with it.

Specifying `--local` does the deployment to the local server but does
not push the function image to a Docker registry--which would be necessary if
we were deploying to a remote Fn server.

The output message
`Updating route /launch using image fndemouser/launch:0.0.2`
let's us know that the function packaged in the image
"fndemouser/launch:0.0.2" has been bound by the Fn server to the route
"/launch".  We'll see how to use the route below.

Note that the containing folder name 'launch' was used as the name of the
generated Docker container and used as the name of the route that
container was bound to.

The fn CLI provides a couple of commands to let us see what we've deployed.
`fn apps list` returns a list of all of the defined applications.

![user input](images/userinput.png)
> `fn apps list`

Which, in our case, returns the name of the application we created when we
deployed our gofn function:

```sh
launchapp
```

We can also see the functions that are defined by an application.  Since
functions are exposed via routes, the `fn routes list <appname>` command
is used.  To list the functions included in "goapp" we can type:

![user input](images/userinput.png)
>`fn routes list launchapp`

```sh
path   image                  endpoint
/launch  fndemouser/launch:0.0.2  localhost:8080/r/launchapp/launch
```

The output confirms that launchapp contains a `launch` function that is implemented
by the Docker container `fndemouser/launch:0.0.2` which may be invoked via the
specified URL.  Now that we've confirmed deployment was successsful, let's
call our function.

## Calling Your Deployed Function

There are two ways to call your deployed function.  The first is using
the `fn` CLI which makes invoking your function relatively easy.  Type
the following:

![user input](images/userinput.png)
>`fn call launchapp /launch`

which results in our familiar output message.

```sh
{"message": "There was no input"}
```

Of course this is unchanged from when you ran the function locally.
However when you called "launchapp /launch" the fn server looked up the
"launchapp" application and then looked for the Docker container image
bound to the "/launch" route.

The other way to call your function is via HTTP.  The Fn server
exposes our deployed function at "http://localhost:8080/r/launchapp/launch", a URL
that incorporates our application and function route as path elements.

Use curl to invoke the function:

![user input](images/userinput.png)
>`curl http://localhost:8080/r/launchapp/launch`

The result is once again the same.

```sh
{"message": "There was no input"}
```

We can again pass JSON data to out function get the value of name passed to the
function back.

![user input](images/userinput.png)
>`curl http://localhost:8080/r/launchapp/launch -d '{"mass": 2000000}'`

The result is now:

```sh
{"mass": 1000000}
```

## Launching your Rocket

To launch your rocket we need to first expose the function publically and then add the function URL to the mission control dashboard.

Since our fnserver is on our local machine we will use [https://ngrok.com/](https://ngrok.com/) to expose our local server to an endpoint for testing.

Signup to ngrok and then follow installatin instructions at https://dashboard.ngrok.com/get-started

Once installed if you then type 

```sh
./ngrok http 8080
```

The FN Server will be generated a public URL.

You can then use this Public URL by going to the dashboard http://missioncontrol.thebeebs.co.uk/. Enter the url for your function and submit. The mission control system will then queue your launch attempt.

See if you can modify the parameters and get your rocket higher than anybody elses today.

## Wrapping Up

Congratulations!  In this adventure you've accomplished a lot.  You've
installed Fn, started up an Fn server, created your first function,
run it locally, and then deployed it where it can be invoked over HTTP. You've even done some rocket science. 
