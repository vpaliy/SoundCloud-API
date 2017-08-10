# SoundCloud-API for Android.

This project is a wrapper for the [SoundCloud API](https://developers.soundcloud.com/).

The SoundCloud API exposes SoundCloud resources like *tracks*, *playlists*, *users*, *comments*, etc.
The API gives you the ability to access a **sound's stream URL** and use your own player to play sounds from SoundCloud.

This repository uses Retrofit2 to create Java interfaces from API endpoints.

## How do I use this wrapper? ##

### Step 1 ###  

Add this to your root `build.gradle` file:

``` gradle
allprojects {
  repositories {
     maven { url 'https://jitpack.io' }
  }
}
```
### Step 2 ###

Add the dependency

``` gradle
dependencies {
	compile 'com.github.vpaliyX:SoundCloud-API:v1.0'
}

```

### Making Request ###
You need to get your `client_id` and `client_secret` by registering your app [here](https://developers.soundcloud.com/docs/api/guide). After you have obtained that, you can start using the API.


Basically, most of the calls will look like this one:

```java
SoundCloudService service=SoundCloud.create(Config.CLIENT_ID)
	.createService(this); //just Context class
service.fetchTrack("123456678") //some dummy track id
	.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(track->{
        	//do something with the track   
 	});
```

It's pretty simple. If you want to pass query paramaters, use the following structure:

```java
service.searchTracks(Track.Filter.start()
              .byName("Imagine Dragons")
              .byGenres("rock","indie","alternative")
              .byTags("popular","rock","imagine","dragons")
              .byLicense(Track.License.ALL_RIGHTS_RESERVED)
              .byTypes(Track.Type.ORIGINAL, Track.Type.LIVE)
              .limit(30)
              .offset(10).createOptions())
         .subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())
         .subscribe(tracks->{
          //do something     
       });

```
The `Filter` class is a nested class inside of a model class like `User`, `Playlist` or `Track`. Most of them offer a different set of methods because not all models can be filtered in the same way. 
Whenever you need to filter a request, just use the `Filter` class and its available methods. After you've listed all things you need, just call the `Filter.createOptions()` method.


## Authentication ##

SoundCloud authentication uses **OAuth 2.0**, a popular open standard used by many popular API providers. 
OAuth 2.0 allows users to authorize your application without disclosing their username and password. 

I've created a class called `SoundCloudAuth` which is responsible for the authentication. 
The main purpose of this class is to obtain an **access token** for your app. 

There are 3 ways you can do this:
- with the authorization code
- with user's credentials (username, password)
- refresh token 

1. Use crentials to obtain a token:
```java
SoundCloudAuth.create(Config.CLIENT_ID,Config.CLIENT_SECRET_ID)
	.addRedirectUri(Config.REDIRECT_URI)
        .tokenWithCredentials("username","password")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(token->{
           //use your token 
	   //launch another activity
	   //or save using the shared preferences
        });
```
2. In order to get an authorization code, you need to open their url in a `WebView`.
A pop-up window will be opened allowing the user to log in to SoundCloud and approve your app's authorization request.

To make the flow smoother, you can use a `redirect_uri` with a custom protocol scheme and set your app as a handler for that protocol scheme. 
I did this for you, you just need to handle the response in your activity. 


``````
MIT License

Copyright (c) 2017 Vasyl Paliy

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
``````
