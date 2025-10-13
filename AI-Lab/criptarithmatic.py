# cryptoarithmetic_input.py
import itertools

# Get input from user
letters = input("Enter unique letters (e.g., SENDMORY): ").upper().strip()

# Validate input
if len(set(letters)) != len(letters):
    print("Error: Letters must be unique.")
    exit()

# Get the equation from user
equation = input("Enter equation (e.g., SEND + MORE == MONEY): ").strip().upper()

# Try all permutations of digits
for perm in itertools.permutations(range(10), len(letters)):
    s = dict(zip(letters, perm))
    # Skip if any leading letter is assigned 0
    terms = [term for term in equation.replace("==", "+").split("+")]
    if any(s[term.strip()[0]] == 0 for term in terms):
        continue
    # Replace letters with digits in the equation
    expr = equation
    for k, v in s.items():
        expr = expr.replace(k, str(v))
    # Evaluate the expression
    if eval(expr):
        print("Solution:", s)
        print("Verified:", expr)
        break
else:
    print("No solution found.")