# dogexplorer
App which will used https://dog.ceo/dog-api/ to present list of breeds and ability to view images of selected breed.


## Arhitecture 
Single activity pattern - it is a small app with two fragments, but I am using a navigation graph with a bottom app bar to navigate between these fragments.

Repository pattern - view model is getting data via DogRepository which is responsible for gathering data (in this case making API calls) 

MVVM - fragments and activities are interacting only with the view model. The view model is interacting with the repository to access data. 

## Libraries  
- Nav graph - for in-app navigation 
- Retrofit, OkHttp and Gson - for making API calls and deserialisation of data 
- Glide - for image loading
- Hilt - for dependency injections  
- Timber - for logging 
- Other standard libraries 
