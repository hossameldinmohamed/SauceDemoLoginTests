# 🧪 SauceDemo Selenium Login Tests

A simple Java automation project demonstrating how testers can integrate **Selenium**, **TestNG**, **Allure Reports**, and **Slack notifications** into a **CI/CD pipeline** using **GitHub Actions**.

> 🔧 I created this repository to demonstrate how testers can leverage GitHub Actions for test automation workflows and integrate Slack for notification — as part of a detailed [LinkedIn article](#) explaining all the steps involved in setup, execution, and reporting.

---

## 🚀 Features

- ✅ **Selenium WebDriver** with Java for browser automation
- ✅ **Maven** for project build and dependency management
- ✅ **TestNG** for test structure and execution
- ✅ **Allure** for detailed and elegant test reporting
- ✅ **GitHub Actions** for continuous integration and test execution on push/PR
- ✅ **Slack Integration** for real-time result notifications
- ✅ **Headless Chrome** support for CI environments

---

## 📋 Test Scenarios

This project includes automated tests for various login scenarios on [SauceDemo](https://www.saucedemo.com):

| Test Case | Description |
|-----------|-------------|
| ✅ **Successful Login** | Valid username and password |
| ❌ **Invalid Username** | Incorrect username with valid password |
| ❌ **Invalid Password** | Valid username with incorrect password |
| ❌ **Empty Credentials** | No username or password provided |
| ❌ **Empty Password** | Username provided but password is missing |

---

## 🔧 CI/CD Setup with GitHub Actions

This repository includes a GitHub Actions workflow file (`.github/workflows/ci.yml`) that:

1. Checks out the repository
2. Sets up Java 17 or 21
3. Runs the TestNG-based Selenium tests
4. Generates Allure reports
5. Notifies a Slack channel about pass/fail status

---

## 📡 Slack Integration

Slack notifications are handled via `rtCamp/action-slack-notify`. You'll need to:

- [Create a Slack webhook](https://api.slack.com/messaging/webhooks)
- Add the webhook URL to your GitHub repository secrets as `SLACK_WEBHOOK_URL`

---

## 📈 Allure Reporting

Allure results are generated in the `target/allure-results` directory and uploaded as artifacts in each CI run. You can download and visualize them locally using:

```bash
allure serve target/allure-results
