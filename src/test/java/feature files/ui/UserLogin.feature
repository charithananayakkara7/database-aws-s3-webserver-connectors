Feature: Login into Application
@regression
 Scenario Outline: Positive test validating login
  Given Initialize the browser and redirection
 And Navigate to the Home page "<Email>"
 And close browsers

 Examples:
 | Email | Password  |
 |noname@gmail.com | Test123 |


