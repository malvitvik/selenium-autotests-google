$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/google.feature");
formatter.feature({
  "name": "Search by keyword",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Verify that result\u0027s title contains searched word",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@1"
    }
  ]
});
formatter.step({
  "name": "I open Google page",
  "keyword": "Given "
});
formatter.match({
  "location": "GoogleHomeSteps.openGoogleHome()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I search for \"jhkjlktfougyhjipkopiugyjhkjblnjl\"",
  "keyword": "When "
});
formatter.match({
  "location": "GoogleHomeSteps.searchForPhrase(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "open 0 link in search result",
  "keyword": "And "
});
formatter.match({
  "location": "SearchResultsPageSteps.openResultPage(int)"
});
formatter.result({
  "error_message": "java.lang.AssertionError: Page has no results. Actual: 0\r\n\tat org.junit.Assert.fail(Assert.java:88)\r\n\tat org.junit.Assert.failEquals(Assert.java:185)\r\n\tat org.junit.Assert.assertNotEquals(Assert.java:199)\r\n\tat cucumber.steps.SearchResultsPageSteps.openResultPage(SearchResultsPageSteps.java:19)\r\n\tat ✽.open 0 link in search result(src/test/resources/google.feature:7)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "the page title contains \"automation\"",
  "keyword": "Then "
});
formatter.match({
  "location": "ResultPageTest.containsDomainOnPages(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.scenario({
  "name": "Verify that \"testautomationday.com\" domain is present on search results pages: 1-5",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@2"
    }
  ]
});
formatter.step({
  "name": "I open Google page",
  "keyword": "Given "
});
formatter.match({
  "location": "GoogleHomeSteps.openGoogleHome()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I search for \"jhkjlktfougyhjipkopiugyjhkjblnjl\"",
  "keyword": "When "
});
formatter.match({
  "location": "GoogleHomeSteps.searchForPhrase(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "\"testautomationday.com\" domain is displayed on pages from 1 to 5",
  "keyword": "Then "
});
formatter.match({
  "location": "SearchResultsPageSteps.containsDomainOnPages(String,int,int)"
});
formatter.result({
  "error_message": "java.lang.AssertionError: Page has no results. Actual: 0\r\n\tat org.junit.Assert.fail(Assert.java:88)\r\n\tat org.junit.Assert.failEquals(Assert.java:185)\r\n\tat org.junit.Assert.assertNotEquals(Assert.java:199)\r\n\tat cucumber.steps.SearchResultsPageSteps.containsDomainOnPages(SearchResultsPageSteps.java:28)\r\n\tat ✽.\"testautomationday.com\" domain is displayed on pages from 1 to 5(src/test/resources/google.feature:14)\r\n",
  "status": "failed"
});
});