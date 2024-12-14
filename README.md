# Property Listing App  

This project is a demonstration of an Android application for property listings. It allows users to browse properties and view details seamlessly, designed with scalability and user experience in mind.  

---

## 📹 **Project Demo**  
**Watch the project in action:**  

https://github.com/user-attachments/assets/1ce9d86e-d792-497f-b2f6-fc795ca9bc56  

---

## 🧪 **UI Testing Demo**  
**Watch the UI testing process:**  

https://github.com/user-attachments/assets/dea0c492-0a9d-41e3-9a85-b7cc92a6f081  

---
## 🧩✨ **Feature-Centric Modular Philosophy**  

The project adopts a **feature-centric modular architecture**, where each feature of the application is encapsulated within its own module. This approach ensures that each module contains everything necessary for that specific feature, including:  

- **Endpoints**: APIs and data sources required for the feature.  
- **Use Cases**: Business logic related to the feature.  
- **Presentation Components**: UI elements and ViewModels.  

### 🚀 **Key Benefits**  

#### 1. 🎯 **Separation of Concerns**  
- Each feature is self-contained, making the codebase easier to maintain and extend.  
- No feature depends on the internal workings of another, reducing the risk of unintended side effects during updates or bug fixes.  

#### 2. 🤝 **Scalability for Large Teams**  
- Modularization allows multiple developers or teams to work simultaneously on different features without conflicts.  
- For instance, in a team of 10+ Android developers, one group can focus on implementing the "Property Listings" feature, while another works on "Property Details." This improves productivity and accelerates development.  

#### 3. 🛡️ **Independent Development**  
- Each module can be developed, tested, and deployed independently, enabling rapid iteration cycles.  
- Features can even be reused across projects or repurposed with minimal changes.  

#### 4. 🧪 **Simplified Testing and Debugging**  
- Testing can be performed at the module level, isolating issues to a specific feature.  
- Debugging is more efficient as modules have minimal dependencies on one another.  

#### 5. 🏗️ **Improved Code Ownership**  
- Clear ownership of modules helps distribute responsibilities across teams.  
- Developers can specialize in certain modules, gaining deep knowledge and improving the quality of work in those areas.  

### 💡 **Practical Example in This Project**  
- The **feature-listing** module handles all functionality related to browsing property listings:  
  - Includes API endpoints for fetching property data.  
  - Contains use cases to process the data.  
  - Includes UI components to display it.  
- Similarly, the **feature-details** module manages property details, encapsulating its logic, UI, and backend interactions.  

By adopting this philosophy, the project is prepared to scale, handle future changes efficiently, and support team collaboration effectively.  

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
