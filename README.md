# Issue

The issue, along with fixes, is described here: https://docs.gradle.com/enterprise/test-distribution/#tests_reading_files_from_src_test_resources

# Usage

To run without TD:

> ./gradlew build

with TD:

> ./gradlew build -Ptd

# Expectation

The build will run three tests:

1. One which reads a file from `src/test/resources`
2. One which reads a file from `build/resources/test`
3. One which uses classpath resource loading

The expectation is that all tests pass with TD disabled, while only case #1 fails with TD enabled.

# Results

* Build scan without TD: https://ge.solutions-team.gradle.com/s/4cvb74i257hdi
* Build scan with TD remote executors: https://ge.solutions-team.gradle.com/s/7qwwfcl5xn2ta
