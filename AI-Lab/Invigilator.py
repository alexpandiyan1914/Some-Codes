# Install library (run once in terminal)
# pip install python-constraint

from constraint import Problem

# Define problem
problem = Problem()

# Example data
time_slots = ["Slot1", "Slot2", "Slot3", "Slot4"]
invigilators = {
    "Alice": {"dept": "CSE", "teaches": ["VLSI"]},
    "Bob": {"dept": "ECE", "teaches": ["EEE"]},
    "Charlie": {"dept": "CSE", "teaches": ["ML"]},
    "Diana": {"dept": "MECH", "teaches": ["Therm"]}
}

exams = {
    "Slot1": {"subject": "AI", "dept": "CSE"},
    "Slot2": {"subject": "VLSI", "dept": "ECE"},
    "Slot3": {"subject": "ML", "dept": "CSE"},
    "Slot4": {"subject": "Thermo", "dept": "MECH"}
}

# Add variables with domains
for slot in time_slots:
    problem.addVariable(slot, list(invigilators.keys()))

# Constraint 1: No consecutive invigilation without break
def no_consecutive(a, b):
    return a != b

for i in range(len(time_slots) - 1):
    problem.addConstraint(no_consecutive, (time_slots[i], time_slots[i+1]))

# Constraint 2: Faculty cannot invigilate their own subject
def not_own_subject(invigilator, slot):
    return exams[slot]["subject"] not in invigilators[invigilator]["teaches"]

for slot in time_slots:
    problem.addConstraint(
        lambda invigilator, s=slot: not_own_subject(invigilator, s),
        (slot,)
    )

# Constraint 3: Department faculty must invigilate at least one exam of their dept
# We enforce this after getting all solutions
solutions = problem.getSolutions()
valid_solutions = []

for sol in solutions:
    valid = True
    for invigilator, info in invigilators.items():
        dept = info["dept"]
        if not any(
            exams[slot]["dept"] == dept and sol[slot] == invigilator
            for slot in time_slots
        ):
            valid = False
            break
    if valid:
        valid_solutions.append(sol)

# Display results
print(f"Total valid solutions: {len(valid_solutions)}")
for sol in valid_solutions:
    print(sol)
