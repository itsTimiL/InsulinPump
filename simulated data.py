import random
import pandas as pd

# Define the age groups and the number of data points for each group
age_groups = ['Under 20', '20-29', '30-39', '40-49', '50-59', '60+']
num_data_points = 50

# Create a list to store the simulated data
data = []

# Loop through each age group
for age_group in age_groups:
    # Generate a random number of data points for each age group
    for i in range(num_data_points):
        # Generate a random blood sugar level within a range specific to the age group
        if age_group == 'Under 20':
            blood_sugar = random.uniform(60, 89)
        elif age_group == '20-29':
            blood_sugar = random.uniform(70, 99)
        elif age_group == '30-39':
            blood_sugar = random.uniform(80, 109)
        elif age_group == '40-49':
            blood_sugar = random.uniform(85, 119)
        elif age_group == '50-59':
            blood_sugar = random.uniform(90, 129)
        elif age_group == '60+':
            blood_sugar = random.uniform(95, 139)
        
        # Add the age group and blood sugar level to the data list
        data.append([age_group, blood_sugar])

# Create a Pandas DataFrame from the data list
df = pd.DataFrame(data, columns=['Age Group', 'Blood Sugar'])

# Output the DataFrame to a CSV file
df.to_csv('average_blood_sugar_levels.csv', index=False)
print("Data Simulated")
