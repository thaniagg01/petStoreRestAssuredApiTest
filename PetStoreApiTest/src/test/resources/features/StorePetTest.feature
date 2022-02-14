Feature: Tests for Petstore API Swagger URL https://petstore.swagger.io


  Scenario: Add a new pet to the store
    Given I have pets in the petstore
    When I add a new Pet to my petStore
    Then The pet is added
    #Clean up
    And I remove the pet from the store

  Scenario: Place an order
    Given I have a pet in the store
    When I place the order for the pet
    Then I expect the order is added
    #clean up
    And I delete the order

  Scenario: Update pet
    Given I have a pet in the store
    When I update the pet
    #clean up
    Then The pet is added


