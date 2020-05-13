hello???#----------------------------------
# Empty Cucumber .feature file
#----------------------------------
    Feature: Making a new user
    As a user i will be able to make a new account
    
   Scenario outline: user provides all details
    
      Given a user does not have an account
      And they provide the information
      When they give a <userName>
      And they give a  <email>
      And they give a <password>
      And they give a <fullName>
      Then the user clicks register
      Then a <user> is created
        
      Examples:
         |userName|email      |password|fullName |
         |sean    |sk@gmail   | pass   |seanKer  |
         |sean1   |sk1@gmail  | pass   |seanKer1 |
         |sean2   |sk2@gmail  | pass   |seanKer2 |
   
