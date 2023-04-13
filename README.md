# Calorie and Nutrient Tracker

Going through dailyLog-to-dailyLog life it can sometimes be hard to keep track of what you're putting into your body.
As somebody who has constantly struggled to gain weight, I wanted to build an app that could help people meet their
nutritional goals. Having a way to quickly track what you're eating will be helpful for anyone trying to lose or gain
weight or just eat healthier in general!

---
## Functionality

The **Calorie and Nutrient Tracker** will allow you to create a log of all the foods you ate in a dailyLog and how many
calories, grams of protein, grams of fat, and grams of sugar it provided.

With this app you'll be able to set **goals** and get **statistics** about whether you're meeting them such as:
- Total calories consumed for a particular day
- Average grams of protein consumed per day
- Total grams of sugar consumed for a particular day
- Food you ate that contains the most fat
- ***Much more***

Those are just some examples with more to come!

Users can then use these statistics to limit how much sugar they eat or hit a particular calorie total with the app 
being able to tell them if they've met goals that they set.

---
## User Stories

- "As a user, I want to be able to select a daily log and add the foods I consumed that day"
- "As a user, I want to be able to view all the foods I ate in a particular day"
- "As a user, I want to be able to see the food with the most calories that I ate in a particular day"
- "As a user, I want to be able to see how many calories I consume on average per day" 
- "As a user, I want to be able to see how many grams of protein I consumed on a particular day"
- "As a user, I want to be able to save my diary to file (if I so choose)"
- "As a user, I want to be able to be able to load my diary from file (if I so choose)"

---
## Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by pressing the "Add Food" button in the daily log submenu and providing valid values to add a "Food" (X) to the "Daily Log" (Y)
- You can generate the second required action related to adding Xs to a Y by pressing the "X" button next to a "Food" (X) in the daily log submenu which will remove that "Food" (X) from the "Daily Log" (Y)
- You can locate my visual component by clicking the "Average Nutritional Intake" button and noting the apple icon in the popup
- You can save the state of my application by pressing the "Save" button on the main menu
- You can reload the state of my application by pressing the "Load" button on the main menu

---
## Phase 4: Task 2
Thu Apr 06 05:17:17 PDT 2023  
Added a food to a daily log

Thu Apr 06 05:17:30 PDT 2023  
Added a food to a daily log

Thu Apr 06 05:17:39 PDT 2023  
Added a food to a daily log

Thu Apr 06 05:17:41 PDT 2023  
Removed a food from a daily log

---
## Phase 4: Task 3
Both my Diary and DailyLog classes are essentially a list of objects with a few additional related fields, such as a date.
Currently, when a for each loop is used, instead of iterating over Diary or DailyLog directly, the program is using 
a getter method to retrieve the ArrayList field from the object and then iterating over that. However, this clearly causes 
potential issues such as the private list potentially being modified without using the proper setters. Excess functionality
is not being hidden from the user, defeating the purpose of the field being private and enabling users to improperly rely on
accessing the field itself.

Therefore, the one major thing I would have refactored in my code would have been to make both the Diary and DailyLog classes
implement Iterable as it would have made my for each loop implementations in the ui class simpler and in accordance with CPSC 210
design principles. After refactoring, both classes would override the iterator method and instead, return the iterator of their
respective private ArrayList fields. Then, the for each loops in the ui class would be able to iterate directly over 
Diary and DailyLog. Using this implementation wouldn't allow users of the classes to improperly access or modify the private
list fields, fixing the identified issues.