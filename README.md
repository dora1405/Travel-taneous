# Travel-taneous

## About the app
For travelers who never know if they have the money to go on a trip until it's too late (flight became too expensive, didn't RSVP on time, etc.) - well no more! They can input how many paychecks they can start saving for the trip and the app calculates the portion that has to come out of each paycheck using the traveler's trip estimate. The traveler can then decide if they can afford to go or decline.

This is also for travelers that want to compare how well they budgeted for the trip. They can compare which categories they went overbudget on and by how much (percentage), so if they see there's a recurring theme, then they can adjust those budget categories for future trips.

Tech Stack:
* Android Studio
* Kotlin
* Firebase real-time database

## Getting Started

Do the following to install this project.

#### Download Java Development Kit
1. Go to: https://www.oracle.com/technetwork/java/javase/downloads/index.html
1. Click download icon for Java Platform (JDK) 13
1. Scroll to bottom of page. In Java SE Development Kit section, click “Accept License Agreement” radio button
	* For macOS, click to download file ending with .dmg
1. Once download complete, open .dmg file in the Downloads folder
1. JDK pop up screen will ask to “double click on icon to install”, do it
1. Click “Continue” and on the next screen click “Install” then click “Close”
1. You can then move the installer to the Trash

#### Download Android Studio
1. Go to: https://developer.android.com/studio
1. Click “Download Android Studio”
1. Once download is complete, a pop up window will appear. Move Android Studio icon to the Application folder
1. Go to Applications folder and open Android Studio
1. You will see a verification pop-up, click “Open”
1. Another pop up window will appear, choose “Do not import settings” then click “OK”
1. Android Studio Setup Wizard will open, click “Next”
1. Select “Standard” for type of setup and click “Next”
1. Select either Dracula or Light UI Theme then click “Next”
1. Click “Finish”
1. Setup Wizard will download components
1. While downloading components, may ask user for password for HAXM Installer, follow prompts to allow HAXM installer
1. Once components are downloaded, click “Finish”

## Open/Run Travel-taneous
1. Click “Check out project from Version Control
1. Select “Git”
1. In the URL field, enter repo url
	* To find repo url, go to GitHub link: <url>
	* Click “Clone or Download” button (green)
	* Copy url
	* Paste url in Android Studio prompt
1. Click “Test” and a message should state “connection successful”; otherwise, double check url
1. Click “Clone”
1. Pop-up will state “You have checked out an Android Studio Project” and ask if you want to open it, select “Yes”
1. Wait for Android Studio to set up.

To run the emulator/app:
1. Go to Tools > AVD Manager > Select Nexus 6P
1. For System image download Lolliopo/API 22 and/or above
1. Accept License Agreement
1. Wait for it to download. It'll take awhile depending on how many APIs you choose to download, but API 22 alone will work
1. Click "Finish"
1. If the "Next" button is not available, simply close the window and open up AVD Manager again > Select Nexus 6P and then click "Next" > click "Finish" > close window
1. Now on the top middle of the screen next to the green play button, Nexus 6P API 22 should be shown as selected.
1. Click on the green play button located on the top middle-right of the window. When the mouse hover over it, it should read “Run ‘app’(^R)” or go to top menu and click on "Run" > "Run 'app'"
1. Emulator will show once Gradle Build is complete (you should see on the bottom something that reads "Gradle Build Running"
1. If database is starting from null or empty:
	* In Estimate section, click "Calculate/Update". This will set the categories' value to "0" to start. It will update the database for that branch.
	* In Actual section, click "Save". This will set the categories' value to "0" to start. It will update the database for that branch.
