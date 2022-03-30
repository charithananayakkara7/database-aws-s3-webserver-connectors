Feature: Validating API

  @AddBookProudct
  Scenario Outline: Creating new book from the api
    Given Add json payload for create new book post request with "<name>"
    When User calls resorce url  "CreateBookAPI" with "post" http request
    Then API response is sucess with status code 201
    Then Get product ID from the created product
    Examples:
      |name|
      |xyztitle|

    @GetBookProduct
  Scenario Outline: Validate created new book product
    Given verify get new product info using "GetBookAPI" "<Model>"

    Examples:
      |Model|
      |Book|

