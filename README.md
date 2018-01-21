<p align="center">
<a href="http://functionalhub.com"><img src="./art/logo.png" alt="Functional Hub" width="400"/></a>
</p>

# Final App: Bandhook

## How to use this project

You can use Android Studio or Intellij to work with this repository.

First thing you will need to compile this project is to get an [API Key from Last.fm](http://www.lastfm.es/api). It will be used to connect to the service that will provide artists info. Then create a resource file `app/src/main/res/values/api_key.xml` (this path is ignored by git) with the following content:

```xml
<string name="last_fm_api_key">YOUR_KEY</string>
```

The `Kotlin` plugin for Android Studio is also required.

## Acknowledgements

This project was originally a fork from [Antonio Leiva - Bandhook-Kotlin](https://github.com/antoniolg/Bandhook-Kotlin)

## License

Copyright © [FunctionalHub.com](http://functionalhub.com) 2018. All rights reserved.

<p align="center">
    <img src="./art/bandhook.gif" alt="Screenshot" width="300"/>
</p>