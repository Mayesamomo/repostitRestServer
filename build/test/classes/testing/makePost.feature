#----------------------------------
# Empty Cucumber .feature file
#----------------------------------
        Feature: making a post
   As a user i will be able to make a post
    
   Scenario outline: user provides all details
    
      Given a user has an account
      And they provide they are logged in
      When they select make post
      Then a <user> can make a new post

      Examples:
         |post_id|post_title |post_desc    |post_date   | user_id | community_id | filePath           | userName |
         |1    |title        | wefewfwef   |2019-01-11  | 1       |    3         |  files/post1       | sean     |
         |2    |title2       | wefwefwef   |2020-03-05  | 2       |    3         |  files/post2       | sean     |
         |1    |title3       | wefwefwef   |2019-01-27  | 3       |     4        |  files/post3       | tom      |
   
