Feature: Search by keyword

 @1
  Scenario: Verify that result's title contains searched word
    Given I open Google page
    When I search for "jhkjlktfougyhjipkopiugyjhkjblnjl"
    And open 0 link in search result
    Then the page title contains "automation"

  @2
  Scenario: Verify that "testautomationday.com" domain is present on search results pages: 1-5
    Given I open Google page
    When I search for "jhkjlktfougyhjipkopiugyjhkjblnjl"
    Then "testautomationday.com" domain is displayed on pages from 1 to 5