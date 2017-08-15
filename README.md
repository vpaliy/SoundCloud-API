# SoundCloud-API for Android.
[![](https://jitpack.io/v/vpaliyX/SoundCloud-API.svg)](https://jitpack.io/#vpaliyX/SoundCloud-API)

This project is a wrapper for the [SoundCloud API](https://developers.soundcloud.com/).

The SoundCloud API exposes SoundCloud resources like *tracks*, *playlists*, *users*, *comments*, etc.
The API gives you the ability to access a **sound's stream URL** and use your own player to play sounds from SoundCloud.

This repository uses Retrofit2 to create Java interfaces from API endpoints. It returns a `Single` which makes it very easy 
to handle asynchronous operations and you can convert an existing data structure into another Single . Please, refer to [Single Utility Operators](http://reactivex.io/documentation/single.html) for more details.  

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
	compile 'com.github.vpaliyX:SoundCloud-API:v1.2'
}

```

### Making Request ###
You need to get your `client_id` and `client_secret` by registering your app [here](https://developers.soundcloud.com/docs/api/guide). After you have obtained that, you can start using the API.


Basically, most of the calls will look like this one:

```java
SoundCloudService service=SoundCloud.create(Config.CLIENT_ID)
	.appendToken(token) //this is not mandatory, most of the calls do not require an access token
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
- with user's credentials (username, password)
- with the authorization code
- refresh token 

**1.** Use the credentials to obtain a token:
```java
SoundCloudAuth.create(Config.CLIENT_ID,Config.CLIENT_SECRET_ID)
	.addRedirectUri(Config.REDIRECT_URI)
        .tokenWithCredentials("username","password")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(token->{
           //use your token 
	   //launch another activity passing the token
	   //or save using the shared preferences
	   //eventually you will use the token to do this SoundCloud.appendToken(token)
        });
```
**2**. In order to get an authorization code, you need to open their url in a `WebView`.
A pop-up window will be opened allowing the user to log in to SoundCloud and approve your app's authorization request.

Approximately it will look like this:

![](https://github.com/vpaliyX/SoundCloud-API/blob/master/art/sound_pop_up.png)

To make the flow smoother, you can use a `redirect_uri` with a custom protocol scheme and set your app as a handler for that protocol scheme. 
That's how you should set up your activity in the manifest file:
```XML
<activity android:name=".MainActivity">
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data android:scheme="app_name" 
            android:host="soundcloud" 
            android:pathPrefix="/redirect"/>
    </intent-filter>
</activity>
```
In the case above my `redirect_url` looks like this: `app_name://soundcloud/redirect`.

Basically, I did this for you, you just need to handle the response in your activity. 
That's how the entire process should look like:

```java
SoundCloudAuth.create(Config.CLIENT_ID,Config.CLIENT_SECRET_ID)
	.loginWithActivity(this,Config.REDIRECT_URI,REQUEST_CODE);
```
It launches the login activity(that popup), which returns the authorization code wrapped into Intent.class.
You need to handle this in the `onActivityResult()` method. Here's an example:

```java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            if(resultCode==RESULT_OK){
                String string=data.getDataString();
                String code= Uri.parse(string).getQueryParameter("code");
                //get your token
                SoundCloudAuth.create(Config.CLIENT_ID,Config.CLIENT_SECRET_ID)
                        .addRedirectUri(Config.REDIRECT_URI)
                        .tokenWithAuthCode(code)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(token->{

                        });
            }
 	}
}
```
Once you have received your authorization code, request an access token as showed above.

**3**.If you received your token from using user's credentials, you will need to periodically refresh your access token when it expires. In order to achieve that, just call this method:

```java
SoundCloudAuth.create(Config.CLIENT_ID, Config.CLIENT_SECRET_ID)
	.refreshToken(expiredToken)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(token->{
	   //use your token here
        });
```

## Additional Documentation and Support ##
- The [SoundCloud API Documentation](https://developers.soundcloud.com/docs/api/reference).
- The [SoundCloud API Discussion Group](https://groups.google.com/forum/#!forum/soundcloudapi).
- If you have any questions or you have found some issues, feel free to write in the [Issue Section](https://github.com/vpaliyX/SoundCloud-API/issues).


## The End. ##

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
