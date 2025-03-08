# Task Manager

## Overview
Task Manager is an Android application built using Jetpack Compose and Room Database. It allows users to create, manage, and track tasks efficiently. The app follows the MVVM architecture and leverages Hilt for dependency injection.

## Features
- Create, edit, and delete tasks
- View task details
- Sort and filter tasks manually (handled in `TaskScreenViewModel`)
- Persistent data storage using Room Database
- Clean architecture with MVVM pattern

## Project Structure
```
com.example.taskManager
│── hilt
│   ├── MyApplication.kt
│   ├── NetworkModule.kt
│── model
│   ├── Screen.kt
│   ├── TaskEntity.kt
│── navigation
│   ├── NavHost.kt
│── networks
│   ├── TaskRepository.kt
│── roomDb
│   ├── TaskDao.kt
│   ├── TaskDatabase.kt
│── screens
│   ├── CreateTaskScreen.kt
│   ├── FilterPopup.kt
│   ├── SortPopup.kt
│   ├── TaskDetailScreen.kt
│   ├── TaskItem.kt
│   ├── TaskList.kt
│   ├── TasksScreen.kt
│── ui.theme
│── utils
│   ├── DateConverter.kt
│   ├── encodeDecodeUtils.kt
│── viewModel
│   ├── CreateTaskViewModel.kt
│   ├── TaskDetailViewModel.kt
│   ├── TaskScreenViewModel.kt
│── MainActivity.kt
```

## Technologies Used
- **Kotlin** for application development
- **Jetpack Compose** for UI
- **Room Database** for local data storage
- **Hilt** for dependency injection
- **MVVM Architecture** for better maintainability

## Database (Room)
- `TaskEntity.kt` defines the structure of a task.
- `TaskDao.kt` handles database operations.
- `TaskDatabase.kt` is the main Room database class.

## Sorting and Filtering
- Sorting and filtering are manually handled in `TaskScreenViewModel.kt`.
- For large datasets, sorting and filtering should be handled via SQL queries in `TaskDao.kt` for better performance.

## ViewModels
- `CreateTaskViewModel.kt` manages task creation.
- `TaskDetailViewModel.kt` handles task details.
- `TaskScreenViewModel.kt` manages task listing, sorting, and filtering.

## How to Run
1. Clone the repository.
2. Open the project in **Android Studio**.
3. Build and run the application on an emulator or physical device.

## Future Improvements
- Optimize sorting and filtering with SQL queries in DAO.
- Implement cloud sync for tasks.
- Add notifications for task reminders.

---
Developed by Akash Sharma.

