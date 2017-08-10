# SoundCloud-API for Android.

This project is a wrapper for the ![SoundCloud API](https://developers.soundcloud.com/).

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
