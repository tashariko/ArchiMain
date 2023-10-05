# ArchiMain

ArchiMain is a sample android app which show the usage of repository pattern. It implements Clean Architecture with latest android trends. Here I have come up with a new way for the [Repository](https://github.com/tashariko/ArchiMain/blob/main/app/src/main/java/com/tasha/archimain/ui/movie/MovieRepository.kt) by creating a new class which handles everything related to data i.e. [BaseRepository](https://github.com/tashariko/ArchiMain/blob/main/app/src/main/java/com/tasha/archimain/network/BaseRepository.kt).

## Libraries Used
* [Android-KTX](https://developer.android.com/kotlin/ktx)
* [Kotlin](https://kotlinlang.org/docs/reference/android-overview.html)
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) with [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)
* [Hilt](https://dagger.dev/hilt/)
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [Livedata](https://developer.android.com/topic/libraries/architecture/livedata)
* [Room](https://developer.android.com/topic/libraries/architecture/room)
* [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) - Yet to implement
* [App startup](https://developer.android.com/topic/libraries/app-startup)  - Yet to implement
* [Data store](https://developer.android.com/topic/libraries/architecture/datastore) - Yet to implement
* [Retrofit2](https://square.github.io/retrofit/)
* [Coil](https://coil-kt.github.io/coil/)
* [Timber](https://github.com/JakeWharton/timber)
* [Stetho](http://facebook.github.io/stetho/)
* [Chuck](https://github.com/jgilfelt/chuck)
* [Lint-Baseline](https://medium.com/swlh/what-is-android-lint-17fa0d87abb2)
* [Lints](https://googlesamples.github.io/android-custom-lint-rules/usage/baselines.md.html#:~:text=If%20you%20want%20to%20create,location%20of%20the%20lint%2Dbaseline)

Used api :
[Movie-db](https://developers.themoviedb.org/3/)

## Plan for next iteration but to eventually forget
* [Splash Screen](https://developer.android.com/develop/ui/views/launch/splash-screen)


## In a galaxy far far away
* Use scoped storage: https://medium.com/better-programming/android-scoped-storage-demystified-3024a062ba24
* Activity result api


##Up Next
* *TDD(on going)
* CTA to open the detail movie.
* Option to add CTA for tv also with its api/data logic.
