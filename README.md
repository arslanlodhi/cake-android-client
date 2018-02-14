# cake-android-client

This is my First submission, Where I have’t changed the source a lot. Fixed the Bugs and addressed the issues mentioned in the comments. Although I have formatted and restructured the code.

# Libraries I might have used
As per the requirement Its a sensitive Project and i was instructed not to use third party libraries, But if i have got the chance to use 3rd party libraries. I might have used libraries mentioned below.
	
* Glide {for image loading and caching}
* GSON {For Json serlization/deserialization}
* Retrofit {for HTTP , client server communication}
* RxJava {for Observer/Subscription Pattern}
* Dagger 2 {For dependencies Injection}
* Android Architecture Components {LiveData, DataBinding, MVVM}


# Explanation
Its my first submission where I have specifically addressed the challenge, Fixed the crashes and addressed the comments in the code..
I have created a network/NetworkManager class, Which use to enqueue HTTP Calls, and prevents to add already in progress Http Call and push call backs form where initiated.
I have Modified  network/ImageLoader class, Which is using LRU cache against URLs. So before loading any image it checks if it can be served from cache. Other wise invoke Network Manager to get the bytes against any Image.
Network Manager gets initiated one time in a Singleton class managers/AppManager.

# Fixes
* Updated Gradle file and Manifest, as the targeted SDK was old.
* Extended with ListFragment and have the wrong id in xml. I have to changed the ListFragment to simple fragment and changed the ListView to RecyclerView. Fixed the issue.
* Application Does’t have Internet Permission, So it was crashing because of it. Permission added in Manifest
* XML View Relative Layout Issue in row. It has the issue in the Childs of relative layout causing the text and image over lapping. Fixed the toRightof Rule.
* Did’t contain the ViewHolder pattern in MyAdapter, causing views to draw every time when scroll, Changed it to RecyclerView.Adapter and implemented ViewHolder pattern.
* Change the adapter binding with ArrayList of CakeModel.class, Instead of JSONArray.
* Implemented LRU cache to prevent Image loading every time from network. And also implemented logic to not to load images from Network if already in  progress.
* Handled Screen orientation, by retaining instance of the Fragment.
* Created Network Manager to handle HTTP calls. Removed redundant code.
* Revamped Image loader class and use Network manager class init.
* Updated the code of input stream reading. Can be done in a simple way.
* Refactored the Project structure, And divided the code into proper hierarchy. Not a good practice to write the whole code into a single file.
