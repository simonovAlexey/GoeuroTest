
GoEuro 
====


##Task

The purpose of this test is to see how you approach a problem and what your solutions look like. The requirements for this test are simple and should be straightforward to grasp. When implementing a solution, please keep things simple but well engineered - as you would do for production code. 
That said:
Implement an API query and transform this data into a csv file
Create a Java command line tool that takes as an input parameter a string

java -jar goeurotest-1.jar "CITY_NAME"

The program takes this string and queries with it our Location JSON API:
The app should use this API endpoint:

http://www.goeuro.com/GoEuroAPI/rest/api/v3/position/suggest/en/CITY_NAME

Where CITY_NAME is the string that the user has entered as a parameter when calling the tool, e.g.

http://www.goeuro.com/GoEuroAPI/rest/api/v3/position/suggest/en/minsk

The API endpoint returns JSON documents like these:

```javascript
[
{
positionId: 372146,
primaryName: "Minsk",
secondaryName: null,
countryCode: "BY",
coreCountry: false,
fullName: "Minsk, Belarus",
country: "Belarus",
latitude: 53.9,
longitude: 27.56667,
locationId: 4306,
inEurope: true,
type: "location"
},
{
positionId: 398351,
primaryName: "Mińsk Mazowiecki",
secondaryName: null,
countryCode: "PL",
coreCountry: true,
fullName: "Mińsk Mazowiecki, Poland",
country: "Poland",
latitude: 52.17935,
longitude: 21.57251,
locationId: 30567,
inEurope: true,
type: "location"
},
{
positionId: 314269,
primaryName: "Minsk",
secondaryName: "MSQ",
countryCode: "BY",
coreCountry: false,
fullName: "Minsk (MSQ), Belarus",
country: "Belarus",
latitude: 53.8825,
longitude: 28.0325,
locationId: null,
inEurope: true,
type: "airport"
},
{
positionId: 314275,
primaryName: "Minsk",
secondaryName: "MHP",
countryCode: "BY",
coreCountry: false,
fullName: "Minsk (MHP), Belarus",
country: "Belarus",
latitude: 53.86182,
longitude: 27.53518,
locationId: null,
inEurope: true,
type: "airport"
}
]
```

The endpoint always responds with a JSON array that contains JSON objects as elements. Each object, among other keys, has a name and a geo_position key.
The geo_position key is an object with latitude and longitude fields.
If no matches are found an empty JSON array is returned.

The program should query the API with the user input and create a CSV file from it. The CSV file should have the form: _id, name, type, latitude,
longitude

##Your solution


Please implement your solution as a stand alone application which can be started from the command line, i.e. send us a fat jar file with all dependencies. You can use Java 8 and open source libraries that you think help you to fulfill this task.
Also send us the source code to your solution. We use GitHub, so if you put your source code into a GitHub repository, it will make our life easier.
We will evaluate your source code as well as the functionality of the program: Does it run, how does it handle errors, how well-engineered is the architecture etc.

