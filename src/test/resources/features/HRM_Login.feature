@login
Feature: All test cases for positive & negative scenarios

  Background:
    Given User is in HRM homepage "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    When user enters username as "Admin" and password as "admin123"
    Then User can login









  @CheckUsername
  Scenario Outline:
    When user enters username as "<username3>" and password as "<password3>"
    Then User can login
    Then check username is correct

    Examples:
      |username3        |password3   |
      |  Admin     |    admin123    |


  @Form
  Scenario Outline: Check username
    Then find recrument
    Then Select "<Job Title>" as "Job Title"
    Then Select "<Vacancy>" as "Vacancy"
    Then Select "<Hiring Manager>" as "Hiring Manager"
    Then Select "<Status>" as "Status"
    Then Select start Date of Application




    Examples:
    |Job Title| Vacancy|Hiring Manager|Status|Candidate Name|
    |Database Administrator|Senior QA Lead|Linda Anderson|Job Offered|ha|


    @Leave

    Scenario Outline: Check Leave
      Then find Leave
      Then Select "<Employee Name>" as "Employee Name"


      Examples:
        |   | |
        |  |    |

  @Save

  Scenario Outline: Check Leave
    Then click save


    Examples:
      |   | |
      |  |    |






