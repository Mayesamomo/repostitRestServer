
    Feature: Logging into an account
    As a user i will be able to login once i have made an account
    
   Scenario outline: user provides all details
    
      Given has an account
      And they provide the information
      When they give a <userName>
      And they give a <password>
      Then the user clicks login
      Then the user will be logged in
        
      Examples:
         |userName|password|
         |sean    | pass  |
         |sean1   | pass  |
         |sean2   | pass  |
   

   
