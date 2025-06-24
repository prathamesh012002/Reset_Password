Feature: Password Reset Functionality

  # Scenario 1: Check if reset password link is present on the login page
  Scenario: Check if reset password link is present on the login page
    Given the user is on the login page
    Then the "Forgot Password" link should be present on the login page

  # Scenario 2: Check if reset password link is clickable
  Scenario: Check if reset password link is clickable
    Given the user is on the login page
    When the user clicks the "Forgot Password" link
    Then the user should be redirected to the password reset page

  # Scenario 3: Check if reset password functionality works properly
  Scenario: Check if reset password functionality works properly
    Given the user is on the password reset page
    When the user enters a valid username
    And clicks the "Submit" button for password reset
    Then a password reset link should be sent to the registered email address

  # Scenario 4: Check if reset password using empty username
  Scenario: Check if reset password functionality works properly again
    Given the user is on the password reset page
    When the user leave empty username
    And clicks the "Submit" button for password reset
    Then error massage is displayed

  # Scenario 5: Check if cancel button works properly
  Scenario: Check if cancel button works properly
    Given the user is on the password reset page
    When the user clicks the "Cancel" button
    Then the user should be redirected to the login page
