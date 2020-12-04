# DbJDBC
For every table, there's a corresponding model in the `models` folder. The purpose of the `model` is to store the information of each table in an object oriented manner (such as ArrayList\<Club>). 

# API Details
I wanted to make a DatabaseObject interface and implement it for all the DAOs since most of the classes have the same methods but I don't know if we're allowed to. 

So, almost all the classes/tables have the following method capabilities:

>  `Find All`  returns a list of objects in table.
>  `Find by ID` returns an object with a matching id.
>  `Add *Object*` adds an object to the database.
>  `Delete *Object*` deletes an object from the database.
>  `Update *Object*` changes an existing object with a replacement.

 
