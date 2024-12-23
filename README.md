# Android Test Automation Project (based on Salesforce)

This is the test automation project. It supports testing of:
* Android Mobile UI

### Supported Platforms
* (Mac) OS X

### Supported CRM System
* **Salesforce**:<br/>
  * utilized the `com.sforce.soap.metadata` package to interact with the Metadata API. Utils classes (package `io.example.tools.metadata`) include initializing the connection to Salesforce, creating requests, processing responses, and programmatically managing various metadata components.<br/>
  * used the Metadata API to deploy and retrieve metadata via ZIP files (class `DeploymentUtils.java`) to improve development workflows and maintain a robust Salesforce environment.<br/>

### Supported Android Devices
* Samsung Galaxy Tab S8

## Table of Contents
1. Getting Started<br/>
2. Project Overview<br/>
3. Project Structure<br/>
4. Running Mobile Tests<br/>
4.1. IntelliJ IDEA UI<br/>
4.2. Maven Command Terminal<br/>
5. Generating and Reviewing Allure Report<br/>

## 1. Getting Started
#### This guide assumes to install the following:
* Android Studio with latest SDK and required Android OS version for emulators (AndroidOS 10+)<br/>
* Appium2 with [appium-flutter-driver](https://github.com/appium-userland/appium-flutter-driver)<br/>
* [appium-doctor](https://www.npmjs.com/package/appium-doctor)<br/>
* [Flutter (3.7.7)](https://docs.flutter.dev/get-started/install/macos)<br/>

And ensure you have **Maven** and **Java 17** installed.<br/>

## 2. Project Overview
This platform improved sales tracking and streamlined collaboration with healthcare providers, leveraging Salesforce with custom features for sales representatives.<br/>

## 3. Project Structure
The Maven project has a pom.xml file and a directory structure based on defined conventions:<br/>
```
|——android-frmk
  |—-pom.xml
      |—-testng.xml
      |—-local.properties
      |—-.env
      |—-app
          |—-[APP_NAME].apk
      |—-lib/
          |—-enterprise.jar
      |—-src/
          |—-main/
              |—-java/
          |—-test/
              |—-java
                  |—-resources
      |—-target
```
The `src` directory is the root directory of source code and test code.<br/>
The `main` directory is the root directory for source code related to the application itself (page-object classes, custom UI elements, tools), not test code.<br/>
The `test` directory contains the test source code (data providers, data generators, data models, implemented test cases).<br/>
The `java` directories under `main` and `test` include the Java code for the application itself which is under `main` and the Java code for the tests which is under `test`.<br/>
The `resources` contains resources whose API metadata was received via ZIP files.<br/>
The `target` directory is created by Maven. It contains all the compiled classes, files, etc. Maven would clean the target directory when executing the `$ mvn clean` command.<br/>
The `lib` directory is the root directory and contains `enterprise.jar` file used for Salesforce Soap API.<br/>
The `app` directory contains the last build of the Android app.<br/>
The `.env` file is a text file used for storing configuration settings and environment variables in a project. It allows you to easily manage configuration parameters such as BASE_URL, APP_NAME, etc. without hardcoding them directly in the code.<br/>
The `pom.xml` file contains information on the project and configuration information for the maven to build the project such as dependencies, build directory, source directory, test source directory, plugin, goals, etc.<br/>
The `testng.xml` file is a configuration file that helps organize the test classes.<br/>
The `local.properties` contains credentials for the environment.<br/>

## 4. Running Mobile Tests
Create a hidden `.env` file containing the parameters `ANDROID_URL`, `APP_NAME`, and `APP_BUNDLE_ID` to configure the testing environment for mobile tests.<br/>
Place your iOS application (e.g., `android.apk`) into the local `app` directory.<br/>
The `APP_BUNDLE_ID` is a unique bundle identifier for a mobile application on the iOS or Android platform. This identifier is used to differentiate apps in systems and services such as the App Store or Google Play.<br/>
```
ANDROID_URL=http://localhost:4723/wd/hub
APP_NAME=android.apk
APP_BUNDLE_ID=com.example.myapp
```
### 4.1. IntelliJ IDEA UI
Before executing the mobile test, it is necessary to ensure that the required configurations are installed for this test and the metadata is generated. To execute the mobile tests please use the following **VM command** in run/debug configurations from IntelliJ IDEA:<br/>

    $ -ea -Denv=local -Ddevice=SamGalTabS8
*Parameters:*<br/>
`-Denv={String}` - environment for execution test<br/>
`-Ddevice={String}` - name of Device on which should be executed test

**NOTE:** The Android SDK should be installed. It includes Android device emulators, which are virtual devices that run on your machine. The following command is used to start the emulator and executed in a separate process automatically through Java code:<br/>

    $ emulator @SamGalTabS8 -wipe-data
*Parameters:*<br/>
`@{String}` - name of Device which should be started

**Create a run/debug configuration:**
1. From the main menu, select *Run | Edit Configurations*<br/>
2. In the *Run/Debug Configuration* dialog, click on the toolbar. The list shows the run/debug configuration templates. Select *TestNG*<br/>
3. In the *Configuration tab*, specify the parameters listed below:<br/>
`Test kind:` select *Class*<br/>
`Class:` input the *name of the test class*<br/>
4. In the *JDK Settings tab*, specify the parameters listed below:<br/>
`VM options:` input the *VM command* mentioned above for the execution test<br/>
5. Apply the changes and close the dialog<br/>

### 4.2. Maven Command Terminal
Maven is also used as a tool to build and manage the project.<br/>
To execute test itself the next command line should be used:

    $ mvn test -Dtest=...Test -Denv=local -Ddevice=SamGalTabS8
*Parameters:*<br/>
`-Dtest={String}` - name of Test class<br/>
`-Denv={String}` - environment for execution test<br/>
`-Ddevice={String}` - name of Device on which should be executed test

## 5. Generating and Reviewing Allure Report
To generate a report by Allure after tests have finished, use the following command:
		
    $ allure serve 
This command immediately opens the generated report in the default browser.<br/>

To generate the report from existing Allure results you can use the following command:

    $ allure generate <directory-with-results>
The report will be generated in the `allure-report` folder.<br/>

