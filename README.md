# Human Review Automated Acceptance Tests

This project contains automated acceptance tests written in Gherkin and automated by Java using Serenity/Cucumber libraries. In addition, the UI activities are tested by the Selenium WebDriver software components.

Current tests:

- successful authentication - 1 scenario
- unsuccessful authentication 3 scenarios

To run you provide the following information inthe config.properties:

```
hr.restapi.url=http://localhost:8080/api/v1
hr.website.url=http://localhost:8081
hr.regular.username=User1
hr.regular.password=Pass1
hr.login.error.msg=Login failed. Please try again.
hr.pending.title=Pending Messages
```

To run all tests, run this command:

```
mvn -Dtest=RunCukesTest -Dwebdriver.driver=chrome -Dwebdriver.chrome.driver=/home/devsecops/dev/bin/drivers/chromedriver -Dcukes.config.file=config.properties test
```

To generate a serenity report (`located at target/site/serenity/index.html`), run this command:

```
mvn -DskipTests verify
```

