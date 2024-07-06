Feature: Validating API
@Addplace
  Scenario Outline: Validating creating product
    Given Add json payload for add place post request with "<name>" "<language>" "<address>"
    When User calls "AddplaceAPI" with "post" http request
    Then API call is sucess with status code 200
    And "status" is "OK"
    And "scope" is "APP"
    And verify place id maps to "<name>" using "GetPlaceAPI"

    Examples:
      | name  | language | address                       |
      | kevin | english1 | No 32 ,Lower road,sweden      |
      | john  | english2 | No 44 ,Upper road,Netherlands |

@DeletePlace
    Scenario: Verify the delete place is working
      Given Vf delete place functionality is working
      When User calls "DeletePlaceAPI" with "post" http request
      Then API call is sucess with status code 200
      And "status" is "OK"