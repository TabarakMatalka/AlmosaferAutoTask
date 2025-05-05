# Almosafer Website Automated UI Testing with Selenium

This project is a **TestNG-based automated UI test suite** for the [Almosafer website](https://www.almosafer.com/en), built using **Java**, **Selenium WebDriver**, and **TestNG**. It verifies the website’s core functionalities, such as language settings, currency, flight and hotel tabs, contact details, and user interactions.

## 🧪 Project Features

- ✅ Verify default language and currency
- ✅ Confirm presence of contact number and Qitaf logo
- ✅ Check flights and hotel booking UI functionality
- ✅ Dynamic test of language switching (English/Arabic)
- ✅ Validate hotel search and guest selection process
- ✅ Full end-to-end test of the search flow

## 📂 Project Structure

```
.
├── src
│   └── test
│       └── java
│           └── Alomsafer_TestCases.java
├── testdata
│   └── TestData.java
├── reports
│   └── ExcelReportListener.java
├── README.md
```

## 🛠️ Tools and Technologies

- **Java 11+**
- **Selenium WebDriver**
- **TestNG**
- **ChromeDriver**
- **Maven or Gradle (optional)**
- **Excel Reporting Listener (custom)**

## 🚀 How to Run the Tests

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/almosafer-ui-tests.git
   cd almosafer-ui-tests
   ```

2. **Configure WebDriver**:
   - Make sure ChromeDriver is installed and added to your system’s PATH.

3. **Update Expected Test Data**:
   - All expected values (e.g., default language, currency, expected titles) are defined in the `TestData` class. Update these values to match the current behavior of the website.

4. **Run using TestNG**:
   - Using your IDE: Right-click the test class > Run as TestNG Test
   - Or use Maven (if configured):
     ```bash
     mvn test
     ```

## 🧪 Example Test Scenarios

- `checkTheDefaultLanguageIsEnglish()`: Asserts the website language is English (`en`) by default.
- `checkTheDefaultCurrency()`: Confirms that the default currency matches expected value.
- `checkQitafLogo()`: Validates presence of Qitaf logo in the footer after scrolling.
- `chooseRandomLanguage()`: Dynamically switches language based on test configuration.
- `stayLocation()`: Sends city name and checks if it was correctly selected from autocomplete.

## 📊 Reports

The project uses a custom listener `ExcelReportListener` to generate test execution reports in Excel format. Ensure you have permissions and directory set up correctly for report generation.

## 📌 Notes

- The tests rely on dynamic selectors from Almosafer's current website structure, which may change. If any test fails, review the selectors and update them accordingly.
- Thread sleeps are used in some areas to simulate waiting — for more robust testing, consider using `WebDriverWait`.

## 👩‍💻 Author

**Tabarak Matalka**
