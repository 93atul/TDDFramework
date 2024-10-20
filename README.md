**Overview**

Welcome to the **Test Automation Repository !!** This is a self-contained project that is great for training or demo-ing all capabilities of Testdata driven automation stratergy. This repository contains automated test scripts for **API, Web and Mobile app**, designed to ensure code quality and streamline the testing process.

**Introduction**

This repository aims to provide a robust framework for automated testing, allowing for continuous integration and delivery. Automated tests help identify bugs early and enhance the overall reliability of the software.

**Technologies Used**

[Test Framework] (Selenium, Appium, Rest Assured, Lombok, Spring boot, TestNG)
[Programming Language] (Java)
[CI/CD Tool] (Jenkins, GitHub)
[Other Tools/Dependencies] (Docker, Extent Reports)

**Prerequisites**

Git - To clone this project, or you could just download the source code as a ZIP file
Java JDK - (at least version 17 or greater), OpenJDK also works
JAVA_HOME environment variable set

**Verify Setup**

If the following command runs the Test fine, you are all set:
Windows	Linux / Mac
mvnw clean test	./mvnw clean test

**Test Reports**
After running tests, reports can be found in the reports directory 
AutomationFramework/reports
AutomationFramework/PdfReports

**Running Tests**

One of the easiest ways to run tests, recommended for non-programmers or teams that are not familiar with Java, is to browse to AutomationFramework/testRunner and select the appropiate test xml as per the requiremnt.

There are more tests and examples in this project, but the following are the simplest ones to get started with:
GoogleSearchTests for UI
WeatherApiTests for API
