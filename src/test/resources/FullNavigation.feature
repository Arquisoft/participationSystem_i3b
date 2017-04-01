Feature:
  Users can see proposals.
  Users can add proposals.
  Users can visit proposals.
  Users can vote proposals.
  Users can comment proposals.

  Scenario:
    Given the database is loaded
    And user navigates to "localhost:8090"
    And user "user" with password "password" is logged in
    And user creates "proposal1" with content "Test content" and category "Cat1"
    And user visits "proposal1"
    And user upvotes the proposal
    When user creates a comment
    Then a comment appears on the comment list