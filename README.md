# ArchiMain

ArchiMain is a sample android app which show the usage of repository pattern. It implements Clean Architecture with latest android trends.

## Libraries Used
* [Android-KTX](https://developer.android.com/kotlin/ktx)
* [Kotlin](https://kotlinlang.org/docs/reference/android-overview.html)
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)/[Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)
* [Dagger](https://dagger.dev/)
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [Livedata](https://developer.android.com/topic/libraries/architecture/livedata)
* [Pagination](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
* [Room](https://developer.android.com/topic/libraries/architecture/room)
* [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) - Yet to implement
* [App startup](https://developer.android.com/topic/libraries/app-startup)  - Yet to implement
* [Data store](https://developer.android.com/topic/libraries/architecture/datastore) - Yet to implement
* [Retrofit2](https://square.github.io/retrofit/)
* [Coil](https://coil-kt.github.io/coil/)
* [Timber](https://github.com/JakeWharton/timber)
* [Stetho](http://facebook.github.io/stetho/)
* [Chuck](https://github.com/jgilfelt/chuck)

Used api :
[Movie-db](https://developers.themoviedb.org/3/)

## Plan for next iteration but to eventually forget
* [Splash Screen](https://developer.android.com/develop/ui/views/launch/splash-screen)


## In a galaxy far far away
* Use scoped storage: https://medium.com/better-programming/android-scoped-storage-demystified-3024a062ba24
* Implement lifecycleowner with handling all lifecycle events
* Motionlayout with some magical animations


##Up Next
* To merge Local and Remote DTO into one Room entity with extra fields for local Database and extra for remote also.
* Adapter to implement recyclerview.
* CTA to open the detail movie.
* Option to add CTA for tv also with its api/data logic.