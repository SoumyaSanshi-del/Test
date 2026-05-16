@tag
Feature: Purchase the Order from Ecommerce WebSite

Background:
Given Landed on Ecommerce Page

@tag2
Scenario Outline: Positive testcase for Sumitting Order
    Given Logged with username <name> and <password>
    When Add the <productName> from cart
    And Checkout <productName> and Submit the order
    Then Verify "Thankyou for the order." message is displayed or not
    
    Examples:
        |name                   |password         |productName|
        |pinky2000@gmail.com    |Pinky@#2000      |ZARA COAT 3|
        |sai01@gmail.com        |Sai@#2000        |ZARA COAT 3|