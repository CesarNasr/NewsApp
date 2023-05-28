# News App

This is a sample app that includes screens to view : -News List, Article Details

UX flow Description:
  - User can open the app
  - App fetches news from remote data source and saves them to local database 
  - User can then view the news list
  - The local database is updated once every 24 hours
  - The user can search news when typing in the SearchView
  - User can click on an item of the list
  - User can then view the detailed view of each article
  - User can also swipe to refresh the main list of news


SDKs and Languages used :
- Kotlin
- Android SDK

Jetpack Library
- Coroutines, StateFlow and Flow
- Navigation Component Architecture
- Safe Args
- ViewModels
- Data binding

Architecures and patterns :
- MVVM
- Repository pattern
- Dependency Injection using Dagger-Hilt, integrated with viewmodels and views(fragments and activities)
- A  single layer of abstraction between Data layer and Presention Layer using interfaces (Reposiotry & RepositoryImpl)
- SOLID principles

Layouts and Designs:
- ConstraintLayout and other Layouts
- Material Design
- Data binding

Remote Data Source:
- News APi : https://newsapi.org/

What could have been added :
- Fully implementing Clean Architecture (added UseCase classes)
- Better UX practices
- Better SOLID Principles
- Implementing Compose UI instead of XML layouts
- Adding UniTests and UI tests
- CI/CD pipelines (github actions / bitrise / etc...)

Thank you.
