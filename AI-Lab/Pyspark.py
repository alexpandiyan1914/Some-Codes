# Step 1: Import libraries
from pyspark.sql import SparkSession
from pyspark.ml.feature import VectorAssembler
from pyspark.ml.regression import LinearRegression

# Step 2: Create Spark Session
spark = SparkSession.builder \
    .appName("HousePricePrediction") \
    .getOrCreate()

# Step 3: Create Dataset
data = [
    (1000, 200000),
    (1500, 300000),
    (2000, 400000),
    (3000, 600000)
]

columns = ["square_feet", "price"]

df = spark.createDataFrame(data, columns)

# Step 4: Convert feature column into vector
assembler = VectorAssembler(
    inputCols=["square_feet"],
    outputCol="features"
)

dataset = assembler.transform(df)

# Step 5: Train Linear Regression Model
lr = LinearRegression(
    featuresCol="features",
    labelCol="price"
)

model = lr.fit(dataset)

# Step 6: Create Test Data
test_data = [(2500,)]

test_df = spark.createDataFrame(
    test_data,
    ["square_feet"]
)

test_dataset = assembler.transform(test_df)

# Step 7: Predict Price
prediction = model.transform(test_dataset)

# Step 8: Display Prediction
prediction.select(
    "square_feet",
    "prediction"
).show()
