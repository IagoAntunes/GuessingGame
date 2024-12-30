![Capa (1)](https://github.com/IagoAntunes/GuessingGame/blob/master/app/src/main/res/drawable/github/img_thumb.png)

# :purple_heart::yellow_heart: GuessingGame

:purple_heart::yellow_heart: GuessingGame App

The GuessingGame project is a Kotlin-based application designed to provide an interactive word-guessing game experience. The primary objective of the game is to guess a hidden word by trying different letters. 
The game provides feedback on whether the guessed letters are correct or incorrect, helping the player to eventually guess the entire word.

## :iphone: FrontEnd (App)

## Introduction

In this application, you will be able to:

- Register and log in to access all the app's features.
- Create game rooms for your favorite games and invite your friends or other players to join.
- Explore and join existing game rooms, filtering by categories such as 'ranked', '1vs1', and 'fun'.
- Use secure authentication to ensure that only authorized users can create or join rooms.

Below, you will find instructions for installation, usage, and more details about this project.

## Installation

To install this project, follow the steps below:

1. Clone the repository:
    ```sh
    git clone https://github.com/IagoAntunes/GuessingGame.git
    ```

## :wrench: Technologies and Tools

### Technologies
- Android
- Kotlin
- JetpackCompose

### Tools
- [hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=pt-br) - Dependency Injection.
- [serialization](https://kotlinlang.org/docs/serialization.html) - Json serialization

## Architecture
  
This project uses Clean Architecture to organize the code in a modular and decoupled way, facilitating system maintenance and evolution. The folder structure is organized as follows:

### Folder Structure

```
guessinggame
│
├── core
│
├── src
|

```

- **core:** Contains the code and rules shared between the app's features.
- **src** Contains the code for each feature.

```
home
│
├── domain
│
├── external
|
├── infra
│
└── presentation
```
- **domain:** Contains repository abstractions and models (entities) representing the application's core data.

- **external:** Responsible for implementing external data sources and data access objects (DAOs).

- **infra:** This layer is responsible for the implementation of the repository interfaces defined in the domain layer. It acts as a bridge between the external data sources and the domain layer.

- **presentation:** Contains the code related to the user interface (UI) and controllers.
