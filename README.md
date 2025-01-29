// Add a new student
@PostMapping(path = "/students")

input: 
{
"name": "sara",
"username": "sara123"
}

output:
200
OK
1

409
Conflict
Student with username sara123' already exists.

-> 500

// Get all students
@GetMapping(path = "/students")

output:
200
OK
[
{
"solved": [],
"followings": "0",
"followers": "0",
"username": "sara123",
"score": "0",
"name": "sara"
}
]

// Get a student by username
@GetMapping(path = "/students/{username}")

output:
200
OK
{
"solved": [],
"followings": "0",
"followers": "0",
"username": "sara123",
"score": "0",
"name": "sara"
}

404
Not Found
Student not found with username: sara12

// Get a student's solved problems by username
@GetMapping(path = "/students/{username}/solved")

404
Not Found
Student not found with username: sara12