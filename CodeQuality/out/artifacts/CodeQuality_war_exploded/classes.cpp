#include <iostream>

// create a new class called Person
class Person
{
// public variables and functions
public:
	// class constructor
	Person();

	std::string name;
	int age;

	void setName(std::string);
	void setAge(int);
};

// define the class constructor
Person::Person()
{
	// initialize some variables...
}

// define the setName() function in the Person class
void Person::setName(std::string userName)
{
	// set the name variable in the Person class to the given argument userName
	name = userName;
}

// define the setAge() function in the Person class
void Person::setAge(int userAge)
{
	// set the age variable in the Person class to the given argument userAge
	age = userAge;
}

int main(void)
{
	// create a new person named bob
	Person bob;

	// define bob
	bob.setName("Bob");
	bob.setAge(27);

	// display bob's age
	std::cout << bob.name << " is " << bob.age << " years old." << std::endl;
}
