Feature: Deleting a value in a JSON data file

  Scenario Outline: Delete a value by ID
    Given the JSON data file contains an entry with ID
    When the entry with ID <"id"> is deleted
    Then the JSON data file should not contain the entry with ID <"id">
    Examples:
      | id  |
      | 589 |