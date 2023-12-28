# KredXRepo
Design and implement njan

Certainly! The ajan CLI tool is designed to handle Nested JSON Array Notation (NJAN) by providing validation and querying functionalities. Let's break down the key aspects of the implementation:

Validation:
The njan validate command is responsible for checking whether the input conforms to the NJAN format. This involves parsing the input JSON and ensuring that it follows the rules defined for NJAN. The validation function checks if the input is a valid JSON string without quotes or backslashes or a valid JSON array with elements recursively validated as NJAN.

Querying:
The njan query command allows users to retrieve specific elements from the NJAN structure using O-based indices. The query is a dot-prefixed sequence of non-negative integers, and the tool navigates the NJAN array accordingly. The answer to a query is the last element accessed in it. The tool handles blank queries, returning the entire NJAN array if the query is empty.

Examples:
The provided examples illustrate the expected behavior of the tool. For instance, the njan validate command should exit with code 0 if the input is valid NJAN and with code 1 otherwise. Similarly, the njan query command should emit the result of the specified query, handling both valid and invalid queries gracefully.

Edge Cases:
The tool considers edge cases such as non-ASCII characters, escaped quotes, and nested structures within the NJAN array. It ensures that the validation and querying processes robustly handle various scenarios.

Error Handling:
The tool includes error handling mechanisms, such as exiting with code 1 when encountering invalid queries, out-of-bounds indices, or JSON decoding errors. This ensures a graceful response in case of unexpected input or user mistakes.

Design Philosophy:
The design philosophy revolves around simplicity, modularity, and adherence to the NJAN specifications. The tool employs common JSON parsing libraries (in this case, the json module) to maintain clarity and readability.

In summary, the ajan CLI tool combines validation and querying functionalities to interact with data in the NJAN format, ensuring adherence to the specified rules and providing a user-friendly interface for working with nested JSON arrays.
