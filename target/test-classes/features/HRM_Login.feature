@login
Feature: All test cases for positive & negative scenarios

  Background:
    Given User is in HRM homepage "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"

  @CheckUsername
  Scenario Outline: Check username
    When user enters username as "<username3>" and password as "<password3>"
    Then User can login
    Then check username is correct
    Then find recrument
    Then Select Job Title


    Examples:
      |username3        |password3   |
      |  Admin     |    admin123    |
