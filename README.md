# Teacher API

| Method | Endpoint | Description | Request | Response |
|---|---|---|---|---|
| POST | `/teachers` | Add a new teacher | `{ "name": "ali", "username": "ali123" }` | `{ "username": "ali123", "name": "ali", "created": 0 }` |
| GET | `/teachers` | Get all teachers | None | `[{ "username": "ali123", "name": "ali", "created": 0 }]` |
| GET | `/teachers/{username}` | Get a teacher by username | None | `{"username":"ali123","name":"ali","created":1}` |
| GET | `/teachers/{username}/created` | Get a teacher's created problems | None | `[{"title":"add","content":"2+4","option1":"1","option2":"3","option3":"4","option4":"6","answer":4,"difficulty":"HARD"}]` |
| GET | `/teachers/{username}/followers/students` | Get a teacher's student followers | None | `[]` |
| GET | `/teachers/{username}/followers/teachers` | Get a teacher's teacher followers | None | `[{ "username": "ali123", "name": "ali", "created": 0 }]` |
| GET | `/teachers/{username}/followings` | Get a teacher's followings | None | `[]` |
| POST | `/teachers/{username}/follow` | Add a teacher to followings | `teacherUsername=mmd123` | None |

# Student API

| Method | Endpoint | Description | Request | Response |
|---|---|---|---|---|
| GET | `/students` | Get all students | None | `[{"username":"sara123","name":"sara","score":0},{"username":"neda123","name":"neda","score":0}]` |
| POST | `/students` | Add a new student | `{"name": "neda", "username": "neda123"}` | None |
| GET | `/students/{username}` | Get a student by username | None | `{"username":"sara123","name":"sara","score":1}` |
| GET | `/students/{username}/solved` | Get a student's solved problems | None | `[{"title":"add","content":"2+4","option1":"1","option2":"3","option3":"4","option4":"6","answer":4,"difficulty":"HARD"}]` |
| POST | `/students/{username}/solved` | Add a solved problem for a student | `title=add` | None |
| GET | `/students/{username}/followers` | Get a student's followers (students) | None | `[{"username":"sara123","name":"sara","score":0}]` |
| GET | `/students/{username}/followings/students` | Get a student's followings (students) | None | `[{"username":"neda123","name":"neda","score":0}]` |
| GET | `/students/{username}/followings/teachers` | Get a student's followings (teachers) | None | `[{"username":"ali123","name":"ali","created":0}]` |
| POST | `/students/{username}/follow/student` | Add a student to followings | `studentUsername=neda123` | None |
| POST | `/students/{username}/follow/teacher` | Add a teacher to followings | `teacherUsername=ali123` | None |

# Problem API

| Method | Endpoint | Description | Request | Response |
|---|---|---|---|---|
| GET | `/problems`| Get all problems | None | `[{"title":"add","content":"2+4","option1":"1","option2":"3","option3":"4","option4":"6","answer":4,"difficulty":"HARD"}]` |
| POST | `/problems` | Add a new problem | `{ "title": "add", "content": "2+4", "option1": "1", "option2": "3", "option3": "4", "option4": "6", "answer": "4", "difficulty": "HARD"}` | None |
| POST | `/{title}/author` | Set a author for a problem | `teacherUsername=ali123` | None |
| GET | `/problems/author/{author}` | Get all problems by author | None | `[{"title":"add","content":"2+4","author":"ali123","option1":"1","option2":"3","option3":"4","option4":"6","answer":4,"difficulty":"HARD"}]` |
| GET | `/problems/{title}` | Get a problem by title | None | `{"title":"add","content":"2+4","author":"ali123","option1":"1","option2":"3","option3":"4","option4":"6","answer":4,"difficulty":"HARD"}` |
| GET | `/problems/category` | Get all categories names | None | `["math"]` |
| POST | `/problems/category` | Add a new category | `name=math` | None |
| POST | `/problems/{title}/category` | Set a category to a problem | `categoryName=math` | None |
| GET | `/problems/{title}/category` | Get category of a problem by title | None | `math` |
| GET | `/problems/category/{categoryName}` | Get all problems of a categories by categoryName | None | `[{"title":"add","content":"2+4","author":"ali123","option1":"1","option2":"3","option3":"4","option4":"6","answer":4,"difficulty":"HARD"}]` |
| GET | `/problems/{title}/check` | Check problem answer | `answerIndex=4` | `true` |