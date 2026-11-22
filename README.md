# QA Test Task

## 1. Prerequisites

### Local

The following tools are required:

- Java JDK 17+
- Apache Maven 3.10.2+
- Google Chrome (required for UI tests)
- Internet connection

### Docker

- Docker Desktop or another Docker-compatible environment
- Docker Compose

Java, Maven, Google Chrome, and ChromeDriver do not need to be installed on the host when running tests through Docker.

## 2. Local Execution

Run commands from the project root directory.

### UI Tests

```bash
mvn test -Dtest="LoginTest,ProductsTest"
```

### API Tests

```bash
mvn test -Dtest="BookingTest"
```

### All Tests

```bash
mvn test
```

## 3. Docker Execution

The project uses a single Docker container containing Java, Maven, Google Chrome, and the test framework.

UI tests run in headless Chrome. API tests execute directly through REST Assured.

### Build Docker Image

```bash
docker compose build
```

### Execute All Tests

```bash
docker compose run --rm tests
```

The container is automatically removed after test execution.

## 4. Test Approach

### Locator Strategy

UI tests primarily use SauceDemo's `data-test` attributes.

Examples:
- `[data-test="inventory-item"]`
- `[data-test="product-sort-container"]`

These attributes provide stable, purpose-specific selectors that are less likely to break when the UI layout or styling changes.

### Synchronization

Selenide's built-in waiting mechanism is used to synchronize browser interactions and assertions.

Conditions such as `shouldBe()`, `shouldHave()`, and collection assertions wait for the expected state instead of relying on fixed delays.

No explicit `Thread.sleep()` calls are used.

### Code Structure

**UI:**
- Page Object Model (POM) separates page-specific locators and actions from test scenarios.
- Browser configuration is centralized in `BrowserConfig`.
- Common test setup and browser cleanup are handled by `BaseUITest`.
- JUnit 5 is used as the test framework.

**API:**
- Controllers encapsulate HTTP requests using REST Assured.
- Models represent API payloads.
- Test classes handle status code checks and response validation.
- Jackson is used for JSON deserialization through REST Assured.

### Test Independence

Each test establishes its own test state and does not depend on another test's execution order.

UI tests use separate browser sessions, while API tests create their own booking data when required.

### Assumptions and Limitations

- Tests depend on the availability of the external SauceDemo and Restful Booker services.
- The negative API test uses booking ID `-1` as a non-existing booking identifier.
- Chrome is configured with `--no-sandbox` to support execution inside the isolated Docker test container.
- Cross-browser testing, parallel execution, and advanced test reporting are outside the scope of this assignment.

## 5. Test Coverage

### UI — SauceDemo

| Test | Description |
|---|---|
| `ShouldLoginSuccessfully` | Verifies successful login and the Products page |
| `ShouldSortProducts` | Verifies sorting by price and name in ascending and descending order |
| `ShouldAddProductToCart` | Adds a product to the cart and verifies its presence |

Test credentials:
- Username: `standard_user`
- Password: `secret_sauce`

### API — Restful Booker

| Test | Description |
|---|---|
| `ShouldCreateBooking` | Creates a booking, validates the POST response, retrieves the booking by ID, and validates the GET response |
| `ShouldReturn404ForNonExistingBooking` | Verifies that retrieving a non-existing booking returns HTTP 404 |