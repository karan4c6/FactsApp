# FactsApp (Kotlin + MVVM Clean Architecture)
Simple facts app, which displays data from json hosted over the http url.

I have used MVVM Clean Architecture pattern while designing this app. This app is using Dagger2 for Dependency Injection Framework. The data is fetched from the network using Retrofit and is done in an async manner using coroutines feature of Kotlin language. The data is also stored into a local storage using Room library. I have divided this assignment into small features and have used Git to maintained a clean commit history by branching into different feature branches and then merging them back into master. The images are being lazily loaded only when the user scrolls down to its position. And it also includes a swipe to refresh functionality, where user can swipe to refresh and latest data from remote will be fetched and saved into local database.
 
I have assumed a few things while creating this assignment:
1. When the json values are null, for example the title or the description or the image url. I am displaying a static text: “Not Available” and a placeholder image for the unavailable image. There are a few cases where all the three fields(title, description and url) are null, in that case I am still displaying the data however with the filler values.
2. In case there is no internet connection, the local version of data will be displayed which is stored in SQLite db using Room Library.
2. The app’s minsdkversion is 21 and targetSdkVersion is 29
 
Libraries used : Dagger, Retrofit, Glide, Material Design, Kotlin Extensions, Room, Coroutines, SwipeRefreshLayout and  Constraint Layout.
 
