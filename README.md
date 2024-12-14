# Property Listing App  

This project is a demonstration of an Android application for property listings. It allows users to browse properties and view details seamlessly, designed with scalability and user experience in mind.  

---

## **Feature-Centric Modular Philosophy**  
This project adopts a **feature-centric modular approach**, where each module encapsulates all endpoints, use cases, and logic specific to a single feature.  

- **Separation of Concerns**: Features are decoupled from each other, making the codebase easier to maintain and scale.  
- **Team Collaboration**: Clear boundaries allow individual modules to be owned by different teams or developers.  
  - *Example*: The "Property Details" module can be developed and tested independently without affecting the "Listing" module.  
- **Testability**: Modules can be tested independently, reducing cross-dependencies and isolating issues effectively.  

This design ensures that the codebase remains organized and scalable as the application grows in complexity.  

---

## 📹 **Project Demo**  
**Watch the project in action:**  

https://github.com/user-attachments/assets/1ce9d86e-d792-497f-b2f6-fc795ca9bc56  

---

## 🧪 **UI Testing Demo**  
**Watch the UI testing process:**  

https://github.com/user-attachments/assets/dea0c492-0a9d-41e3-9a85-b7cc92a6f081  

---

## 🛠️ **Features**  
- Display property listings fetched from a remote API.  
- View detailed information about each property.  
- User-friendly and responsive UI built using Jetpack Compose.  
- Modular architecture following Clean Architecture principles.  
- State-of-the-art navigation and dependency injection (Hilt).  
- Unit and UI testing with robust coverage.  

---

## 🚀 **Technologies Used**  
- **Kotlin**: Primary programming language.  
- **Jetpack Compose**: Modern toolkit for building native UIs faster and easier.  
- **MVVM with Clean Architecture**: Ensures scalability and maintainable code.  
- **Hilt (Dependency Injection)**: Simplifies dependency injection in a modular setup.  
- **Retrofit & OkHttp**: Efficient API calls and networking.  
- **JUnit and Espresso**: Comprehensive testing for both unit and UI levels.  

---

## 🏗️ **Architecture and Project Structure**  
This project follows the MVVM (Model-View-ViewModel) architectural pattern with Clean Architecture principles to ensure scalability, testability, and maintainability.  

### **Architecture Overview**  
- **Presentation Layer**:  
  Handles UI and user interactions. Built with Jetpack Compose, this layer communicates with the ViewModel to display data and react to state changes.  
- **Domain Layer**:  
  Acts as the bridge between the Presentation and Data layers. Contains business logic and use cases to ensure a clear separation of concerns.  
- **Data Layer**:  
  Manages data sources, including APIs and local databases. It includes repositories that abstract data operations and provide a unified interface for the domain layer.  

### **Project Structure**  
📂 **app**  
  └── Main application module containing the navigation graph and Hilt setup.  

📂 **core**  
  └── Shared utilities and components (e.g., network utilities, base classes).  

📂 **feature-listing**  
  └── Handles the property listing feature.  
      - `data`: Contains data sources (APIs, repositories).  
      - `domain`: Contains use cases and models specific to this feature.  
      - `presentation/ui`: Jetpack Compose UI components.  
      - `presentation/viewmodel`: ViewModels for managing UI state.  

📂 **feature-details**  
  └── Handles the property details feature.  
      - Similar structure as `feature-listing`.  

---

## **Why This Structure?**  
- **Scalability**: Modularized features allow easy addition or removal of components.  
- **Testability**: Clean separation of layers simplifies unit testing and UI testing.  
- **Reusability**: Shared resources (e.g., utility functions, components) are centralized in the core module.  

---

## **Design Highlights**  
- **Reactive Programming**: Leveraged StateFlow to update UI reactively.  
- **Error Handling**: Centralized error handling in the repository layer to ensure consistent behavior.  
- **Navigation**: Decoupled navigation using Jetpack Navigation to maintain single responsibility.  
- **Optimized Network Calls**: Used Retrofit with caching mechanisms for offline access.  
- **Custom Animations**: Built with Jetpack Compose to enhance user engagement.  
