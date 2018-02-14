# cake-android-client

This is my First submission, Where I have’t changed the source a lot. Fixed the Bugs and addressed the issues mentioned in the comments. Although I have formatted and restructured the code.

# Libraries I might have used
As per the requirement Its a sensitive Project and i was instructed not to use third party libraries, But if i have got the chance to use 3rd party libraries. I might have used libraries mentioned below.
	1.	Glide {for image loading and caching}
	2.	GSON {For Json serlization/deserialization}
	3.	Retrofit {for HTTP , client server communication}
	4.	RxJava {for Observer/Subscription Pattern}
	5.	Dagger 2 {For dependencies Injection}
	6.	Android Architecture Components {LiveData, DataBinding, MVVM}


# Explanation
Its my first submission where I have specifically addressed the challenge, Fixed the crashes and addressed the comments in the code..
I have created a network/NetworkManager class, Which use to enqueue HTTP Calls, and prevents to add already in progress Http Call and push call backs form where initiated.
I have Modified  network/ImageLoader class, Which is using LRU cache against URLs. So before loading any image it checks if it can be served from cache. Other wise invoke Network Manager to get the bytes against any Image.
Network Manager gets initiated one time in a Singleton class managers/AppManager.

# Fixes
1. Updated Gradle file and Manifest, as the targeted SDK was old.
2. Extended with ListFragment and have the wrong id in xml. I have to changed the ListFragment to simple fragment and changed the ListView to RecyclerView. Fixed the issue.
2. Application Does’t have Internet Permission, So it was crashing because of it. Permission added in Manifest
3.  XML View Relative Layout Issue in row. It has the issue in the Childs of relative layout causing the text and image over lapping. Fixed the toRightof Rule.
4. Did’t contain the ViewHolder pattern in MyAdapter, causing views to draw every time when scroll, Changed it to RecyclerView.Adapter and implemented ViewHolder pattern.
5. Change the adapter binding with ArrayList of CakeModel.class, Instead of JSONArray.
6. Implemented LRU cache to prevent Image loading every time from network. And also implemented logic to not to load images from Network if already in  progress.
7. Handled Screen orientation, by retaining instance of the Fragment.
8 . Created Network Manager to handle HTTP calls. Removed redundant code.
9.  Revamped Image loader class and use Network manager class init.
10. Updated the code of input stream reading. Can be done in a simple way.
11. Refactored the Project structure, And divided the code into proper hierarchy. Not a good practice to write the whole code into a single file.

