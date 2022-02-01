# Mobile Automation Testing Framework
Cucumber-Java-Maven based Automation framework for Mobile App testing.
## Notes for Browserstack execution mode:
- Change devices in TestNG.xml
- Change Thread count in TestNG.xml
- Change Tag in TestRunner.java
- Use Browserstack setTLDriver in BaseTest.java
- Change desired capabilities in DesiredCapabilities.java

## Features:
- Parallel Testing
- Local Testing
- Cloud Testing
- Supports both Android and iOS
##Setup for Cloud Execution (BrowserStack)
- Browserstack
- To capture appium logs ``curl -u "haribabumaila_Elu5RJ:nSqD7s61yDhRpefqbTRb" \
  https://api.browserstack.com/automate/sessions/<session-id>/appiumlogs
  ``
##Setup For Local Execution
- ###Softwares:
- JDK
- Android Studio
- Appium
- IntelliJ
- ###Set Env variables in Windows machine:
- JAVA_HOME with C:\Program Files\Java\jdk-17.0.2
- Path with %JAVA_HOME%\bin
- ###Set Env variables in Appium Server:
- ANDROID_HOME with C:\Users\mhari\AppData\Local\Android\Sdk
- JAVA_HOME with C:\Program Files (x86)\Java\jre1.8.0_311

##Jenkins:
- #### Step1: installing maven in Jenkins Agent
  - MAVEN_HOME with C:\Program Files\apache-maven-3.6.1-bin\apache-maven-3.6.1
  - M2_HOME with C:\Program Files\apache-maven-3.6.1-bin\apache-maven-3.6.1
  - JAVA_HOME with C:\Program Files\Java\jdk-17.0.2
  - Path with C:\Program Files\Java\jdk-17.0.2\bin
  - Path with C:\Program Files\apache-maven-3.6.1-bin\apache-maven-3.6.1\bin
- #### Step2: installing maven plugins in Jenkins
  - Go to "Manage Jenkins" -> "Manage Plugins" -> click "Available" tab -> enter "maven" in search box.
  - In search results select Maven Integration plugin and click on Download Now And Install After Restart button
- #### Step3: Maven and JDK configuration in Global Tool Configuration in Jenkins
  - #### If Step1 is done Step3 is not needed and vice versa
  - Navigate to Global Tool Configuration
  - Scroll down and click on Add Maven
  - Provide name as something like Maven3, Select install automaticall checkbox, select version in the Install From Apache dropdown and Save

## ADB
- With Android Studio ADB will be installed automatically.
- Navigate to C:\Users\mhari\AppData\Local\Android\Sdk\platform-tools in command prompt and type ``adb``
- Now type `adb devices` to view the list of devices connected and their IDs
- To install apk type ``adb -s <DEVICE ID> install <PATH TO APK>``
## GitHub
- ### SSH key generation
  - Open git bash and enter
    `
    ssh-keygen -t rsa
    `
  - Enter location or just press enter for default location
  - Set password if required or just press enter
  - Navigate to /c/Users/username/.ssh/id_rsa location
  - Open id_rsa publisher file in Notepad and copy everything
  - Open GitHub in browser
  - Go to profiles and click on SSH Keys tab
  - Paste the copied key and click Add
- ###For uploading new project to GitHub
  - Right click in the project folder and click git bash here and type ``git init`` command. Now ".git" folder will be created.
  - Enter `git add .` command to add all the project files.
  - Use `git commit -m "code change description"` to commit the files.
  - Create branch with branch name as "main" ``git branch -M main``
  - Add a new remote connection to your local project folder using below command
    ````
    git remote add origin https://github.com/harigithub1/AutomationFramework.git
    ````
  - Push the project using `git push -u origin main`.
- ###For importing existing project from GitHub and creating feature branch
  - Clone the project:
    ````
    git clone https://github.com/harigithub1/AutomationFramework.git
    ````
  - Create your feature branch: `git branch my-new-feature-branch-name`
  - Add your code changes: `git add my-file-name`
  - Commit your changes: `git commit -m "commit message"`
  - Push to the branch: `git push origin my-new-feature-branch-name`
  - Open GitHub and create a pull request to main branch