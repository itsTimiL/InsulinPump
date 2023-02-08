# InsulinPump
An embedded system in an insulin pump used by diabetics to maintain blood glucose control

# The Goal
 - Collects data from a blood sugar sensor and calculates the amount of insulin required to be injected. 
 - Calculation based on the rate of change of blood sugar levels.
 - Sends signals to a micro-pump to deliver the correct dose of insulin.
 - Safety-critical system as low blood sugars can lead to brain malfunctioning, coma and death; high-blood sugar levels have long - term consequences such as eye and kidney damage.

 - The system shall be available to deliver insulin when required.
 - The system shall perform reliably and deliver the correct amount of insulin to counteract the current level of blood sugar.
 - The system must therefore be designed and implemented to ensure that the system always meets these requirements.
 
 # Essential high-level requirements
 - The system shall be available to deliver insulin when required.
 - The system shall perform reliably and deliver the correct amount of insulin to counteract the current level of blood sugar.
 - The system must therefore be designed and implemented to ensure that the system always meets these requirements.
 
 # Insulin pump hardware architecture
 ![image](https://user-images.githubusercontent.com/124085275/215911205-bc0f688b-8913-4fc8-bfb0-39a962143486.png)
 
  [controller] will have control over the system as a whole. This part contains the run state and an error state. However, there will be a state that will only execute when possible hardware issues occur.
 
 [Displays] There will be a total of four displays. One of the displays will express the hardware issues that may occur. There will be one display that expresses the last blood sugar measurement and another display will express the computation of last dose of insulin. Lastly, there will a display that showcases the current time.
 
 [clock] The clock will allow [controller] to have the current time.
 
 Due to not having real information, the pump, the production of the needle, the sensor, and alarm will be simulations. And, due to these things being simulations, hardware isssue occurances will be a simulation as well. With that being said, these things will be made up.
 
 # Activity model of the insulin pump
 ![image](https://user-images.githubusercontent.com/124085275/217400185-274591ca-3bcc-4d04-8150-dde1a960b1f0.png)
 
 # The Preconditions
 
 1. The insulin dose delivered will be calculated by taking measurments of the current state of the blood sugar and comparing it to a level that has already been measured. This will prompt the computation of the dose that is required.
 2. The system will measure the level of blood sugar and then dieliver insulin in [time period]
 3. The total amount of insulin delivered will be calculated by [controller], which will be because of the current blood sugar analysis measured by the sensor.
 
  - No insulin should be delivered if the analysis is below a safe mininmum.
  - During the time that the anaylsis is in a safe proximity, the insulin will only be delievered if the blood sugar level is increasing and the rate of of the increasing blood sugar is increasing as well.
  - If the analysis goes above the recommended level, the insulin will be delivered. However, if the blood sugar level is decreasing and the rate of the decreasing blood sugar level is increasing, then the insulin will not be delivered.
  - Note: There are limits to everything. So, there will be limits on doses per day. And, there will be limits the amount of the dose in one injection. This means that the amount of insulin delievred may be slightly different than the calculated dose provided.
  
  Conditions that prompt execution
  
  Note: Table for the state of sugar level and activity
  
  
  
  # The Situations
  
  Siutation #1 [ Blood sugar level decreasing]
  
  




  

  
 
 

