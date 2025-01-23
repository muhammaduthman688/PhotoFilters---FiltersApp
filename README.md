# Photo Filters - FiltersApp

This repository demonstrates the implementation of **photo filters** in an Android application using the **MVVM (Model-View-ViewModel)** architecture. The app allows users to apply various filters to their photos.

---

## **Features**

- **Photo Filters:**
  Includes a variety of filters such as:
  - **Contrast Effect**
  - **Brightness Effect**
  - **Hot Effect**
  - **Cold Effect**
  - **GrayScale Effect**
  - **Saturation Effect**
  - **Sharpness Effect**
  - Custom convolution filters for advanced editing.

- **Camera Integration:**
  Capture images directly using the camera.

- **Gallery Integration:**
  Load images from the device gallery.

- **Dynamic Image Editing:**
  Modify images dynamically with smooth and efficient performance.

- **MVVM Architecture:**
  Implements the MVVM pattern for clean and maintainable code, ensuring separation of concerns.

---

## **How It Works**

1. **Model:**
   - Represents the data and logic for applying image filters.

2. **View:**
   - Includes activities and fragments like:
     - `MainActivity`
     - `CameraActivity`
     - `EditorActivity`
     - `SecondHomeFragment`

3. **ViewModel:**
   - Manages the UI-related data in a lifecycle-conscious way, ensuring that the UI remains responsive during changes.

---

## **Setup and Usage**

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/muhammaduthman688/PhotoFilters---FiltersApp.git
