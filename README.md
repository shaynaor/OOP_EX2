# OOP_EX3

Authors: Alex vaisman , Shay naor.

This project is a simple game.
when run it will open a gui with a map of ariel in which you can put pacman on the map and fruits.
The program will find a route so all the fruits are eaten , it will print the time of the longest route,
And it will show you the movements of the pacmans on the screen.

you can manually create your game , or load a csv file.
there is an option to save the game into a csv file.
and an option to save the game into a kml file so you can run it in google earth.

# How to use:
Inside the Gui package there is a map class , you need to manualy put in line 31 the location of your Ariel1.png .
Inside the Gui package there is a main class, run it to start the program.

# The algorithm used.
First the program finds how many fruits it has , then the program  will sort the fruits by their distance from the top left corner
of the map. after that the program will give each pacman a list of fruits sorted by distance from the top left corner he should eat.
Then each pacman will sort the list given to him.
He will find the first fruit closest to him and eat it and go to that fruits location.
Then he will find the second fruit closest to him and eat it and go to that fruits location.
He will continue until all the fruits in his list are eaten.


 
 




