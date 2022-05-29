# WeatherTop

/**
**Name :** Isadora Fitzgerald
****Student Number:** W20099887
VideoLink:** https://www.youtube.com/watch?v=s7J_EZzDlwY

**Brief description of the App developed 1/2 sentences:**

- Creation of a Desktop App to record weather data at set locations, created for a client.

**__Known bugs/problems:__**

**Issue 1 seems to delete all the latest readings when you delete a reading midway in the list.**

**Issue 2 - Ok I cannot add readings to a new station under Homer or when you add a new person they can add a station but can't add readings.
However you can see the reading under the existing stations under Homer.
I have run out of time trying to work this out**

This PLAY/IDEA Combo loves null pointer exceptions if I get this project handed in without one I would be suprised! 
This is the one I am currently stuck on- it is 29th May Sunday 11am! Could I have more feedback on what causes these going fowarard so I have a better understanding?
I know it is a runtime error but I am unable to figure out why?
Basically what do I keep doing wrong!?


As Follows:

Execution exception
NullPointerException occurred : Try to read weatherCode on null object models.Reading (controllers.StationCtrl.index, line 20)

In /app/controllers/StationCtrl.java (around line 20)

16:
Station station = Station.findById(id);
17:
Logger.info("Station id = " + id);
18:
//call to util method to output the latest readings
19:
Reading latestReading = StationAnalytics.getLatestReading(station.readings);
20:
**String conditions = latestConditions(latestReading.weatherCode);**
21:
//call to method to output the Fahrenheit reading
22:
double f = changeTemp(latestReading.tempC);
23:
//call to  method to output the beaufort scale
24:
int beaufortScale = bScale(latestReading.windSpeed);
25:
//call to method to read text output for beaufort label - double test my understanding
26:
String beaufortLabel = windConditions(latestReading.windSpeed);





**Any sources referred to during the development of the assignment (be precise and specific with references) {there no need to reference lecture/lab materials}:**

**Images:**
Rainbow Image by https://pixabay.com/photos/rainbow-cloud-evening-sun-rain-4047523/
Lightning Image by https://pixabay.com/photos/lightning-storm-sea-clouds-sky-7166580/

**Extra Help with HashMap:**
https://www.w3schools.com/java/java_hashmap.asp
https://dev.to/sumusiriwardana/if-else-or-switch-case-which-one-to-pick-4p3h

nullpointier stuff
https://rollbar.com/blog/how-to-catch-and-fix-nullpointerexception-in-java/

Step attribute in labels
https://www.w3schools.com/tags/att_input_step.asp#:~:text=The%20step%20attribute%20specifies%20the,a%20range%20of%20legal%20values

Number format exceptions
https://rollbar.com/blog/java-numberformatexception/

Wind Chill Calculation:
http://www.java2s.com/example/java/java.lang/calculate-wind-chill.html

**/
