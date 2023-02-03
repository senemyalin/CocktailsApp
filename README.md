# CocktailsApp

<h3> Packages </h3>

This project is an Android application with three layers: data, domain, and UI. It is structured into four packages:

**data**: This package contains classes and interfaces related to the data layer. It is responsible for managing the data for the application, such as fetching data from a server, storing data locally, and providing data to the domain layer.


**di**: This package contains classes related to dependency injection. It includes modules that define the dependencies needed by the data layer.


**domain**: This package contains classes and interfaces related to the domain layer. It is the business logic layer of the application, responsible for processing the data from the data layer and performing any necessary calculations or manipulations. It also defines the interfaces that the UI layer uses to communicate with the data layer.


**ui**: This package contains classes and interfaces related to the UI layer. It is the presentation layer of the application, responsible for rendering the user interface and displaying the data from the domain layer to the user. 

<h3> Implementation </h3>

Implementation for this project started with the data layer, and then moved on to the domain and UI layers. The project is implemented using a combination of Retrofit for networking, Gson to parse JSON data that has been received from a server or to generate JSON data to send to a server, Dagger Hilt for dependency injection, Coil for uploading images, Mockito, Mockwebserver, Truth, Turbine for Unit test and Android's built-in UI components and libraries such as RecyclerView and ConstraintLayout.

In addition to the layers, packages and implementation, this project also has a modern and user-friendly UX. The UI has been designed to be easy to use and intuitive for the user.

The project is also implemented in a proper MVVM (Model-View-ViewModel) manner, with components such as view models and data binding used to separate the business logic from the UI. This helps to make the code more modular and easier to test and maintain.

