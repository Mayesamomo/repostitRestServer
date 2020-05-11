#----------------------------------
# Empty Cucumber .feature file
#----------------------------------
        Feature: getting all posts
    As a user i will be able to view all posts
    
   Scenario outline: user provides all details
    
      Given a user has an account
      And they provide they are logged in
      When view the home page
      Then a <user> can view all posts


   
