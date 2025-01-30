# Teacher API

| Method | Endpoint | Description | Request | Response |
|---|---|---|---|---|
| POST | `/teachers` | Add a new teacher | `{ "name": "ali", "username": "ali123" }` | `{ "username": "ali123", "name": "ali", "created": 0 }` |
| GET | `/teachers` | Get all teachers | None | `[{ "username": "ali123", "name": "ali", "created": 0 }]` |
| GET | `/teachers/{username}` | Get a teacher by username | None | `{ "username": "ali123", "name": "ali", "created": 0 }` |
| GET | `/teachers/{username}/created` | Get a teacher's created problems | None | `[]` |
| GET | `/teachers/{username}/followers/students` | Get a teacher's student followers | None | `[]` |
| GET | `/teachers/{username}/followers/teachers` | Get a teacher's teacher followers | None | `[{ "username": "ali123", "name": "ali", "created": 0 }]` |
| POST | `/teachers/{username}/created` | Add a created question for a teacher | `title=add` | None |
| GET | `/teachers/{username}/followings` | Get a teacher's followings | None | `[]` |
| POST | `/teachers/{username}/follow` | Add a teacher to followings | `teacherUsername=mmd123` | None |

# Student API

| Method | Endpoint | Description | Request | Response |
|---|---|---|---|---|
| GET | `/students` | Get all students | None | `[{"username":"sara123","name":"sara","score":0},{"username":"neda123","name":"neda","score":0}]` |
| POST | `/students` | Add a new student | `{"name": "neda", "username": "neda123"}` | None |
| GET | `/students/{username}` | Get a student by username | None | `{"username":"sara123","name":"sara","score":0}` |
| GET | `/students/{username}/solved` | Get a student's solved problems | None | `[]` |
| POST | `/students/{username}/solved` | Add a solved problem for a student | `title=add` | None |
| GET | `/students/{username}/followers` | Get a student's followers (students) | None | `[{"username":"sara123","name":"sara","score":0}]` |
| GET | `/students/{username}/followings/students` | Get a student's followings (students) | None | `[{"username":"neda123","name":"neda","score":0}]` |
| GET | `/students/{username}/followings/teachers` | Get a student's followings (teachers) | None | `[{"username":"ali123","name":"ali","created":0}]` |
| POST | `/students/{username}/follow/student` | Add a student to followings | `studentUsername=neda123` | None |
| POST | `/students/{username}/follow/teacher` | Add a teacher to followings | `teacherUsername=ali123` | None |

# Problem API

| Endpoint | HTTP Method | Description | Request | Response |
|---|---|---|---|---|
| `/problems` | GET | Get all problems | None | `[{"title":"add","content":"2+4","author":"ali123","option1":"1","option2":"3","option3":"4","option4":"6","answer":4,"difficulty":"HARD"}]` |
| `/problems/author/{author}` | GET | Get all problems by author | None | `[{"title":"add","content":"2+4","author":"ali123","option1":"1","option2":"3","option3":"4","option4":"6","answer":4,"difficulty":"HARD"}]` |
| `/problems/{title}` | GET | Get a problem by title | None | `{"title":"add","content":"2+4","author":"ali123","option1":"1","option2":"3","option3":"4","option4":"6","answer":4,"difficulty":"HARD"}` |
| `/problems` | POST | Add a new problem | `{ "title": "add", "content": "2+4", "option1": "1", "option2": "3", "option3": "4", "option4": "6", "answer": "4", "difficulty": "HARD", "author": "ali123" }` | None |
| `/problems/category` | GET | Get all categories names | None | `["math"]` |
| `/problems/category` | POST | Add a new category | `name=math` | None |
| `/problems/{title}/category` | POST | Add a category to a problem | `categoryName=math` | None |
| `/problems/{title}/category` | GET | Get all categories of a problem by title | None | `xxx` |
| `/problems/category/{categoryName}` | GET | Get all problems of a categories by categoryName | None | `xxx` |
| `/problems/{title}/category` | DELETE | Remove a category from a problem | `categoryName=math` | `xxx` |
| `/problems/{title}/check` | GET | Check problem answer | `answerIndex=4` | `true` |

# BUG:

// Add a category to a problem
@PostMapping("/{title}/category")
public ResponseEntity<?> addCategoryToProblem(@PathVariable String title, @RequestParam String categoryName)
http://localhost:8080/problems/add/category?categoryName=math

// Get all categories of a problem by title
@GetMapping(path = "/{title}/category")
public List<String> getAllCategoriesByTitle(@PathVariable String title)
http://localhost:8080/problems/add/category
out:xxx

// Get all problems of a categories by categoryName
@GetMapping(path = "/category/{categoryName}")
public List<ProblemDTO> getAllProblemsByCategoryName(@PathVariable String categoryName)
http://localhost:8080/problems/category/math
out:xxx

// Remove a category from a problem
@DeleteMapping("/{title}/category")
public ResponseEntity<?> removeCategoryFromProblem(@PathVariable String title, @RequestParam String categoryName)
http://localhost:8080/problems/add/category?categoryName=math
out:xxx
