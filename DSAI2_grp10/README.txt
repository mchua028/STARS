CZ2002 Assignment "Building an OO Application": 
My STudent Automated Registration System (My STARS)
---------------------------------------------------------------------------------------------------------------------------

Objective: 
To develop a console based "My STARS" application (non-Graphical UI)

Installation Requirements:
- set CLASSPATH to directories of "javax.mail.jar; activation.jar; additionnal.jar"
- e.g. of command lines to execute java program on Windows:
	C:<directory>\CZ2002\src >javac -cp "<directories of jar files>" *.java
	C:<directory>\CZ2002\src >java -cp "<directories of jar files>" MainPage

Documentation:
- javadoc and class hierachies in </src/javadoc>

---------------------------------------------------------------------------------------------------------------------------

How to use?
Overview of program:
(Refer to report and video walkthrough for more details)

Admin Page:
(1) Edit Student Access Period
(2) Add Student
	- Enter student details (name, username, matric no., gender, nationality)
	// Alpha-numeric username and matric no.
	// Gender is either “female” or “male”

(3) Add Course
	- Enter course details (e.g.: course name, school, vacancies, info of index numbers, etc)
	// Enter 0 if no lecture, tutorial, or lab
	// Assumption: only 1 lecture, tutorial, and lab each week for each course

(4) Print Student List by Course
(5) Print Student List by Index Number
(6) Check Availability for an Index Number
(7) Update Course
	- Change course
	- Edit course index
		-> Add course index
   		-> Update course index information
			(1) Vacancy
			(2) Lecture Day
			(3) Lecture Start Time
			(4) Lecture End Time
			(5) Tutorial Day
			(6) Tutorial Start Time
			(7) Tutorial End Time
			(8) Lab Day
			(9) Lab Start Time
			(10) Lab End Time
			(11) Back
		-> Remove course index
	- Change school

Student Page:
(1) Add Course
(2) Drop Course
(3) Check Course Registered
(4) Check Vacacies Available
(5) Change Index Number of Course
(6) Swap Index Number with Another Student
(7) Change Password

