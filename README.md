Travel API Client 
=================

Clone this repo and start it (on windows systems use the gradlew.bat file):

`./gradlew bootRun`

to list all tasks:

`./gradlew tasks`

To view the assignment (after starting the application) go to:

[http://localhost:9000/travel/index.html](http://localhost:9000/travel/index.html)

Instructions 
=================

- First start the api mock.

- Start backend with: `./gradlew bootRun`

- Start frontend with: `ng serve` from the frontend directory.

- View the application in the browser: http://localhost:4200

Solution Description
=================
I was not able to complete all the requirements due to my time availability, so I gave priority
to implement a very basic backend that communicates with the Api Mock. 
Also a very basic UI was implemented using AngularJS 7 + Angular Material to perform the fares search.

Pending Tasks
=================
- Application usage statistics.
- Integrate AngularJS build with gradle.
- All of the bonus points.
- Improve error handling when communicating with the Api Mock.
- Add more test coverage.
- Improve JSON HAL response handling.
