import random
import matplotlib.pyplot as plt
from matplotlib.backends.backend_pdf import PdfPages

# Constants
HIGH = 150
LOW = 70

# User Status
age = 0
weight = 0
diet = 0
exercise = 0
diabetes = False

# initial blood sugar level
blood_sugar_level = random.randint(100, 120)

# User Specifics
USER_CATEGORIES = {
    "underweight": -3,
    "normal": 0,
    "overweight": 3,
    "young": -2,
    "middle-aged": 0,
    "old": 4,
}

DIET_CATEGORIES = {
    "poor": 2,
    "average": 0,
    "healthy": -2
}

EXERCISE_CATEGORIES = {
    "lazy": 2,
    "average": 0,
    "active": -2
}


def check_blood_sugar_level():
    global blood_sugar_level

    # Updates BSL in range of -10,10
    status = random.randint(-10, 10)

    # Check BSL
    if blood_sugar_level > HIGH:
        # User takes insulin to lower blood sugar level
        blood_sugar_level = give_insulin(blood_sugar_level)
    if blood_sugar_level < LOW:
        # User eats sugary food to raise blood sugar level
        blood_sugar_level = eat_sugar(blood_sugar_level)

    # changes in BSL based on user
    blood_sugar_level += simulate_user_status(status)

    return blood_sugar_level


def simulate_user_status(status):
    # Calculate weight and age status
    weight_status = USER_CATEGORIES.get("underweight" if weight < 150 else "normal" if weight < 200 else "overweight", 0)
    age_status = USER_CATEGORIES.get("young" if age <= 30 else "middle-aged" if age <= 50 else "old", 0)

    # Calculate diet and exercise status using a dictionary comprehension
    diet_status = sum(DIET_CATEGORIES.get(d, 0) for d in (diet,))
    exercise_status = sum(EXERCISE_CATEGORIES.get(e, 0) for e in (exercise,))

    # Adjust status based on diabetes and overall factors
    if diabetes:
        status *= 3
    overall_status = weight_status + age_status + diet_status + exercise_status
    if diabetes and exercise >= 1:
        overall_status -= 1  # Diabetes with moderate or high exercise
    if exercise == 2 and diet == 2:
        overall_status -= 3  # Healthy exercise and diet

    return status + overall_status

def eat_sugar(blood_sugar_level):
    blood_sugar_level *= 1.5
    return blood_sugar_level


def give_insulin(blood_sugar_level):
    blood_sugar_level -= (blood_sugar_level * .2)
    return blood_sugar_level


def patient_data(age, weight, diabetes, diet, exercise, blood_sugar_level):
    with PdfPages('patient_data.pdf') as pdf:
        # Create figure for blood sugar level graph
        fig, ax = plt.subplots(figsize=(6.5, 8.5))
        fig.subplots_adjust(left=0.1, bottom=0.2, right=0.9, top=0.9)

        # Generate blood sugar level data and plot on bottom subplot
        time = range(0, 24, 1)
        bsl = [blood_sugar_level]

        for min in time[1:]:
            x = check_blood_sugar_level()
            if min in [8, 12, 17]:  # Meal times at 8am, 12pm, and 5pm - Blue dots is BP after eating
                x *= 0.9  # decrease blood sugar reading by 10%
                ax.plot(min, x, 'o', 4, color='blue')
            else:
                ax.plot(min, x, 'o', 4, color='black')
            bsl.append(x)

        # Design Graph
        ax.plot(time, bsl, '-', color='black', label='Blood Sugar Level Readings')
        ax.axhline(y=HIGH, color='r', linestyle='--', label='Hyperglycemia Threshold')
        ax.axhline(y=LOW, color='g', linestyle='--', label='Hypoglycemia Threshold')
        ax.set_xlabel('Time (hr)')
        ax.set_ylabel('Blood Sugar Level (mg/dL)')
        ax.set_title('Blood Sugar Levels Over Time')
        ax.legend()

        # Add patient information to PDF
        patient = f'Patient Information\n-------------------------\nAge: {age}\nWeight: {weight}\nDiabetes Status: {diabetes}\nDiet: {diet}\nExercise: {exercise}\n'
        fig.text(0.1, 0.9, patient, fontsize=12)
        pdf.savefig(fig, bbox_inches='tight', pad_inches=0.25)


def main():
    global age, weight, diet, exercise, diabetes, blood_sugar_level

    # Patient information
    print("Patient Age: ")
    age = int(input())
    print("Patient Weight: ")
    weight = int(input())
    print("Patient Diet Status (0 - poor, 1 - average, 2 - healthy): ")
    diet = int(input())
    print("Patient Exercise Status (0 - poor, 1 - average, 2 - healthy): ")
    exercise = int(input())
    diabetes_input = input("Patient Diabetes Status (True or False): ")
    diabetes = True if diabetes_input.lower() == 'true' else False

    patient_data(age, weight, diabetes, diet, exercise, blood_sugar_level)


if __name__ == "__main__":
    main()

