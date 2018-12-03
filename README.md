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
mvn -Dhr.restapi.url=http://human-review-backend-labs-test.apps.domino.rht-labs.com/api/v1 -Dhr.website.url=http://vue-app-labs-test.apps.domino.rht-labs.com/ -Dhr.regular.username=User1 -Dhr.regular.password=Pass1 -Dtest=RunCukesTest -Dwebdriver.remote.driver=chrome -Dwebdriver.remote.url=http://zalenium:zalenium1234@zalenium-zalenium.apps.domino.rht-labs.com/wd/hub -Dwebdriver.timeouts.implicitlywait=5000 -Dcukes.config.file=config.properties clean test
```

To generate a serenity report (`located at target/site/serenity/index.html`), run this command:

```
mvn -DskipTests verify
```

```
"spec_version": "2.0",
  "objects": [
    {
      "aliases": ["Equipo del Discoteca"],
      "created": "2014-11-19T23:39:03.893Z",
      "sophistication": "expert",
      "roles": ["agent"],
      "description": "!!!###HUMAN REVIEW###!!!This organized threat actor group operates to create profit from all types of crime.",
      "type": "threat-actor",
      "resource_level": "organization",
      "labels": ["crime-syndicate"],
      "primary_motivation": "!!!###HUMAN REVIEW###!!!personal-gain",
      "name": "!!!###HUMAN REVIEW###!!!Disco Team Threat Actor Group",
      "modified": "2014-11-19T23:39:03.893Z",
      "id": "threat-actor--dfaa8d77-07e2-4e28-b2c8-92e9f7b04428",
      "goals": ["Steal Credit Card information"]
    },
    {
      "identity_class": "organization",
      "created": "2016-08-23T18:05:49.307Z",
      "name": "Disco Team",
      "modified": "2016-08-23T18:05:49.307Z",
      "description": "Disco Team is the name of an organized threat actor crime-syndicate.",
      "contact_information": "disco-team@stealthemail.com",
      "id": "identity--733c5838-34d9-4fbf-949c-62aba761184c",
      "type": "identity"
    },
    {
      "target_ref": "identity--733c5838-34d9-4fbf-949c-62aba761184c",
      "relationship_type": "attributed-to",
      "created": "2016-08-23T18:05:49.307Z",
      "modified": "2016-08-23T18:05:49.307Z",
      "source_ref": "threat-actor--dfaa8d77-07e2-4e28-b2c8-92e9f7b04428",
      "id": "relationship--966c5838-34d9-4fbf-949c-62aba7611837",
      "type": "relationship"
    }
  ],
  "id": "bundle--7a3c6bef-76f3-4072-9789-c706186308f8",
  "type": "bundle"
}
```
