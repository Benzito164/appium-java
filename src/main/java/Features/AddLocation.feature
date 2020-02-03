Feature: Add Location
  As a user
  I want to be able to add a location and see the weather information of that location

  Background:
    Given I launch the app

  Scenario: User adds a location
    When I add "Lagos, Nigeria" as a location
    Then I should see a "Lagos, Nigeria" forecast on my Homescreen


  Scenario: User deletes a location
    Given I add "Lagos, Nigeria" as a location
    When I long press on "Lagos, Nigeria" forecast
    Then I should not see any more forecast for "Lagos, Nigeria"



  Scenario: User tries to add an already existing location
    Given I add "London,UK " as a location
    And  I add "London,UK " as a location
    Then I should see a pop up telling me the location is already added


  Scenario: User taps on an added location
    Given I add "Lagos, Nigeria" as a location
    When  I tap on the forecast information for "Lagos"
    Then  The weather information for "Lagos" should be displayed above
