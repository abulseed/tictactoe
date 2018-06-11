# Tic Tac Toe

We want to bring the pen-and-paper game Tic-tac-toe to the digital age, but with a little twist: the size of the playfield should be configurable between 3x3 and 10x10. And we also want the symbols (usually O and X) to be configurable. Also it should be for 3 players instead of just 2.

## Environment Setup

### Introduction

The motive of this section is to provide a detailed steps of the environment setup for the tictactoe application. The application is developed using Java, and the build and configuration processes are managed by Gradle.

#### VSCode

This application is built using VSCode. Use this [LINK](https://code.visualstudio.com/) To install VSCode.

#### Github

1. Download and install git from [HERE](http://git-scm.com/download/win).
2. Verify git version using this command `git --version`.
3. Download and install [Git Credential Manager for Windows](https://github.com/Microsoft/Git-Credential-Manager-for-Windows/releases/download/v1.15.2/GCMW-1.15.2.exe). Or check [HERE](https://git-scm.com/book/en/v2/Git-Tools-Credential-Storage) how to configure the credential storage for you OS.
4. Clone the repository to your machine using this [URL](https://github.com/abulseed/tictactoe.git). Choose a folder destination for your project.

#### Gradle

1. Download & Install the package manager for wondows [Chocolatey](https://chocolatey.org/).
2. Use Chocolatey to install Gradle. `$ choco install gradle`.
3. For other OS's check [HERE](https://gradle.org/install/#with-a-package-manager) how to install Gradle on your machine.

#### Run the application

1. Clean and build the repository using this command `gradle clean build`.
2. Make sure the build was successful and the app is ready to run using this command `gradle test`.
3. Everything is set let's run the application using `gradle run`.

## Resources

<https://en.wikipedia.org/wiki/Tic-tac-toe>

<https://sourcemaking.com/design_patterns/memento>

<https://sourcemaking.com/design_patterns/command>

<https://sourcemaking.com/design_patterns/singleton>