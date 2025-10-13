# simulated_annealing_input.py
import math, random

def f(x):
    return x * x  # Objective function: minimize x^2

# Get user input
start_min = float(input("Enter minimum starting range (e.g., -10): "))
start_max = float(input("Enter maximum starting range (e.g., 10): "))
initial_temp = float(input("Enter initial temperature (e.g., 100): "))
cooling_rate = float(input("Enter cooling rate (e.g., 0.95): "))
threshold = float(input("Enter stopping temperature threshold (e.g., 0.1): "))

# Initialize
x = random.uniform(start_min, start_max)
T = initial_temp

# Simulated Annealing loop
while T > threshold:
    new_x = x + random.uniform(-1, 1)
    delta = f(new_x) - f(x)
    if delta < 0 or random.random() < math.exp(-delta / T):
        x = new_x
    T *= cooling_rate

# Output result
print("\nApproximate minimum found:")
print("x =", round(x, 4))
print("f(x) =", round(f(x), 4))