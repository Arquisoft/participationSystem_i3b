Feature:
  Users can see proposals.
  Users can add proposals.
  Users can visit proposals.
  Users can vote proposals.
  Users can comment proposals.

  Scenario:
    Given user navigates to "localhost:8090"
    And user logs in with name "user" and password "password"
    And user creates proposal "proposal1" with content "Test content" and category "Cat1"
    And user visits "proposal1"
    And user upvotes the proposal
    When user creates a comment with content "sick proposal m8"
    Then a comment appears with content "sick proposal m8"