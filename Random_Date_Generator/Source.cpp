#include "Date.h"
#include <iostream>
#include <iomanip>

using namespace std;

/*
 The integer check function enforces a "correct" positive non-zero input by 
 reading the user input as a string and conditionally determining its validity.

 @param input - a string argument
 @return boolean true or false
*/
bool integer_check(string input)
{
	if (input.empty()) // can't "enter" without typing anything!
		return false;
	else
		if (input[0] == '0') // no leading zeros allowed!
			return false;
		for (int i = 0; i < input.length(); i++)
			if (isdigit(input[i]) == false) // only positive non-zero integers allowed! 
				return false;
		return true;
}

/*
 The main function:
 
 1) Requests the user for a positive non-zero integer, in order to produce that many 
	random dates ranging from 0 CE to 9999 CE. Built-in error checking keeps prodding 
	the user for a correct input, in case of faulty input.
	
 2) Then utilizing the Date object a for loop constructs the desired amount of dates 
	along with name of the month and whether the constructed year is a leap year or not.
	
 NOTE: PLEASE, CHECK "Date.h" & "Date.cpp" FOR THE LOGICAL BACKEND!
*/

int main()
{
	string input;
	cout
		<< "\n// 605.604 Assignment 2, [Part 1]\n\n"
		<< "How many random dates would you like? (1,2...n): ";
		getline(cin, input); // number of random dates to print

	
	while (integer_check(input) == false) // input fault check
	{
		cout << "Please, enter a positive non-zero integer (1,2...n): ";
		getline(cin, input); // number of dates to print
	}

	int number_of_dates = stoi(input); // converts the "pseudo" string integer into a real int 

	cout
		<< "\n+---------------+---------------+-------------+\n"
		<< left << setw(14) << "| Random Date\t" 
		<< left << setw(14) << "|  Month Name\t" 
		<< left << setw(14) << "|  Leap Year" << "|\n"
		<< "+---------------+---------------+-------------+" << endl;

	// constructs Date objects by looping over the number of dates 
	for (int i = 0; i < number_of_dates; i++)
	{
		Date date;
		
		cout
			<< left << "| " << date.getMonth() << "/"
			<< date.getDay() << "/"
			<< setw(4) << date.getYear() << "\t|  "
			<< left << setw(11) << date.getMonth_name() << "\t|  "
			<< left << setw(11) << boolalpha << date.isLeap() << "|" << endl;
	}
	cout
		<< "+---------------+---------------+-------------+" << endl;
	//system("pause"); // May need to uncomment on older systems.
	return 0;
}