import pandas as pd
import matplotlib.pyplot as plt

# Create dataset
data = {
    "Name": ["Alice","Bob","Charlie","David","Eva","Frank"],
    "Age": [20,22,None,21,23,20],
    "Score": [85,None,90,75,None,70]
}

df = pd.DataFrame(data)

# Fill missing values using mean
df["Age"].fillna(df["Age"].mean(), inplace=True)
df["Score"].fillna(df["Score"].mean(), inplace=True)

# Central tendency measures
print("Mean Age:", df["Age"].mean())
print("Median Age:", df["Age"].median())
print("Mode Age:", df["Age"].mode()[0])

print("Mean Score:", df["Score"].mean())
print("Median Score:", df["Score"].median())
print("Mode Score:", df["Score"].mode()[0])

# Bar Chart
plt.bar(df["Name"], df["Score"])
plt.title("Scores of Students")
plt.xlabel("Name")
plt.ylabel("Score")
plt.show()

# Scatter Plot
plt.scatter(df["Age"], df["Score"])
plt.title("Age vs Score")
plt.xlabel("Age")
plt.ylabel("Score")
plt.show()
