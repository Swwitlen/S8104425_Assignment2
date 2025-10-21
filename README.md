## ** Author **
- Student ID: s8104425
- Student name: Thanh Dat Pham
- University: Victoria University
- Unit: NIT3213 Mobile Application Development
- Project name: s8104425_Assignment2

## **Overview**
The app has 3 main screen which is:
1. **Login screen**: users need to provide their username and password in order to login in via the API
2. **Dashboard screen**:after login, a list of sports which is fetched from the API will appear. By clicking one of the sport entity, it will open the **Detail screen**
3. **Detail screen**: after clicking one of the sport entity, it will show more detail about that sport

## **Structure**
we created 7 package:
- main: MainActivity.kt
- di: MyApp.kt, NetworkModule.kt
- model:AuthRequest.kt, AuthResponse.kt, DashboardResponse.kt, SportEntity.kt
- network: ApiService.kt
- repository: MainRepository.kt
- ui: - dashboard: DashboardActivity.kt, DashboardViewModel.kt, EntityAdapter.kt
      - details: DetailsActivity.kt
      - login: LoginActivity.kt, LoginViewModel.kt
- util: Extensions.kt

and 5 layout:
- activity_dashboard.xml
- activity_details.xml
- activity_login.xml
- activity_main.xml
- item_entity.xml

##**Technology used**
- Kotlin          : Android development
- Retrofit        : networking
- Hilt            :dependancy injection
- Moshi           :JSON serialization
- Coroutines      : async operation
- RecycleView     : list display
- JUnit & Mockito : unit test
- Android jetpack components

## **Build and run the app**
**Notice**
- Make sure we use the lastes version of Android Studio
- JDK 17 or later
- Gradle handling by Android studio automatically
- Internet connection is good so that the app can fetches data from postman
**Step 1: Run the postman**
- Open Postman and enter the url: https://nit3213api.onrender.com/footscray/auth, using POST method to collect the topic name after giving username and pass word (username: Thanh Dat, password: 8104425) --> we will got "Sports" topic
- Enter the url: https://nit3213api.onrender.com/dashboard/sports to get the entities
- Keep postman running while you build and run the project

**Step 2: Clone the repository**
- Download zipfile from github and extract it
- 
**Step 3: Open the project on Android Studio**
- Open the file after launching Android Studio by clicking file --> Open
(Wait for Gradle to sync or click "Sync Now" if it doesnt sync automatically

**Step 4: Configure SDK**
- Make sure Android SDK 36 intalling by clicking file --> Project Structure --> SDK Location 
- Create a virtual device by clicking Tools --> Device Manager --> Create Device
- 
**Step 5: Build the app**
- Building the app by clicking Build --> Make Project
(notes: "BUILDING SUCCESSFUL" will appeared in the bottom status bar when the builds succeeds

**Step 6: Run the app**
- Select your emulator and click "Run" button
- 
**Step 7: Testing**
- The login screen will appear after you click run button, enter username and pass word to be taken to the Dashboard screen and select on any entity to go to the Details screen


## **Running unit test**
- Open project view 
- Go to com.example.s8104425_assignment2 (test)
- Click the run button next to DashboardViewModelTest class to testing dashboard viewmodel, same thing with login view model or you can right-click on DashboardViewModelTest and choose "Run'DashboardViewModelTest" to test it
