@WikiFeature
Feature: Browser Stack Sample App Home page.

  @WikiScenario
  Scenario: Validate home page buttons.
    Given User is on Wiki home page
    When User clicks List One
    When User clicks History Two
#    And User clicks on Nearby Three
#    And User clicks on Wiki Explore Four
#    Then Verify InTheNewsTitle