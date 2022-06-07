#  Parking-Lot
The final project in course OOD (Object Orinted Desgin),the project helps users to find and report avilabile car parking in chosen streets in different regions  . 
# Description
Developed a parking lot system with a user interface, which helps users to find and report an available parking lot in choosen area . 
Tools – Java, OOP, Java Swing for GUI, MySQL DB.

In additon, we used 3 desgin patterns (Singleton,Facde and Factory).

1) Singleton: We used this desgin pattern in our project ,. the  prviate constructor help to create only one object from specific type,we need in our applaction to make one access(only one object) for every user to the frame"access" because we want that every user will have one connection to the application .
we also used Singelton to connect to MySQL DB cause also we want one connection to database .
 ![image](https://user-images.githubusercontent.com/100870794/172393337-615ce45e-f149-4730-86d6-5f318186927a.png)
 
2) Facde: We used Facde provides comfortable API for our different frames . it helps to work easier with the database . the Facde also allows to improve the code if we want to change or add  databases without the  entire code  . 
3) Factory: This pattern takes out the responsibility of the instantiation of a class from the client program to the factory class. the factory sort all the frames in the FrameFactory which make its easier to make changes or add more frames . 
 ![image](https://user-images.githubusercontent.com/100870794/172395624-020f5b06-d973-4202-b699-1b4cf5419c15.png)


# Regitstration User Page

![image](https://user-images.githubusercontent.com/100870794/172267736-cc1e9c4b-5148-4589-bfaf-de293d73bd35.png)
# DATABASE CREATE

![image](https://user-images.githubusercontent.com/100870794/172267905-10f60aea-a971-44d4-a682-d43de06cc44a.png)
# USER HOMEPAGE

![image](https://user-images.githubusercontent.com/100870794/172268201-24c4406c-4128-482a-9cd7-d476184e6f8e.png)

