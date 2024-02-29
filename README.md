# fetch-rewards-take-home

A take home app for Fetch Rewards interview querying a single endpoint for data to display to a
single screen with data from endpoint, according to described business logic.


https://github.com/ddaypunk/fetch-rewards-take-home/assets/2991755/6a0d8668-70d8-4ec0-9756-fe081cc65c49


## Stack/Technologies

- **Android**
- **Kotlin**
- **Dagger/Hilt** for Dependency Injection
- **Jetpack Compose** for User Interface
- **Ktor** for HTTP client
- **MVVM** architecture
- **Junit5** for Unit Tests
- **Junit4** and **Compose Espresso** for Android Instrumented Tests
- **AssertK** for assertions

## Clean Package Structure

### Main

- hiring (single screen app for now - feature level package)
  - data (models and repositories)
  - presentation (components and screens)
- core (theoretically shared in the future)
  - client (ktor for api calls)
  - di (Hilt modules)
  - screen (error and loading screens)

### UnitTest

- set to mimic application structure

### AndroidTest

- set to mimic application structure
- very simple test as the viewmodel controls the click behavior of the card elements currently
  - so it just verifies the content that is seen
