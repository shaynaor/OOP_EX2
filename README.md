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

# The structure of the program.
When you run the program it will creat a gui with all the option you can choose from on the top left side of the gui.
The creation of the gui and all the options given to you are managed by MyFrame class.
If you load a csv file MyFrame will send the path of that file to Game class which will read the file line by line,
And will create a new object fruit or pacman depending on what is writen in the csv file.
The game class has two arraylist in it, one for pacmans and one for fruits.
After creating an object from the csv file it will add it to the correct array list depending on the type of the object.
After game finished reading the file , MyFrame will paint the fruits and pacmans on the map.
To paint them on the map MyFrame will have to convert the gps position of pacmans and fruits to a pixel position depending
on the size of the gui.
MyFrame will call Convert_pixel_gps class which converts a gps location to a pixel location and the other way around.
after that MyFrame will have a pixel location for pacman or fruit and then it can paint it on the map.

now after you loaded a file and can see the pacmans and fruits on the map you will want to start a simulation and see
how they move on the map and eat all the fruits.
 




