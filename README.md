# OOP_EX2

Authors: Alex vaisman , Shay naor.

This code can read CSV files extract the infromation from the files and creat a KML file
that runs in google earth.

The code will read all the CSV files in a given folder.

This code extracs each line in the cvm file that represents a gps point with meta data and creates a new object called element.

each CSV file will be represented as a layer, a layer is made out of elements.

A project is an array list of layers.

This code contains methods to calculate distance , pitch and yaw between gps points , and an option to move them in meters.

How to use:

Inside the Algorithms package there is a main class.

it will read from a given folder all the CSV files translate them into layers made out of elements , and will creat a new kml file in an out put path.

the Multi CSV object will return a project object which consists of multipal layers.
