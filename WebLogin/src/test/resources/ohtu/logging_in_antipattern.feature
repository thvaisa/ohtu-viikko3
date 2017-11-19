Feature: As a registered user can log in with valid username/password-combination

Scenario: user can login with correct password
Given login is selected
When username "jukka" and password "akkuj" are given
Then system will respond "Ohtu Application main page"

Scenario: user can not login with incorrect password
Given login is selected
When username "jukka" and password "wrong" are given
Then system will respond "invalid username or password"

Scenario: nonexistent user can not login to 
    Given login is selected
    When  username "aajukka" and password "akkuj" are given
    Then  Then system will respond "invalid username or password"