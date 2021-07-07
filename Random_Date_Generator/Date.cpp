#include "Date.h"
#include <random>

// Default Constructor
/*
 The default constructor Date constructs a simple random date having a 
 month attribute (1-12), a day attribute (1-31), a year attribute (yyyy),
 and a month name (January-December). 
 
 1) It creates a random year between 0 CE and 9999 CE by using the
    Mersenne Twister pseudo-random generator with a uniform distribution.
 2) Next, it checks the constructed year for a leap year and accordingly 
    creates a random date from a random month. 
*/
Date::Date()
{
	std::random_device rd;
	std::mt19937 rng(rd()); // Mersenne Twister pseudo-random generator

	std::uniform_int_distribution<int> random_year(0, 9999);
	Year_ = random_year(rng);

	if (Year_ % 4 == 0) // if divisible by 4
	{
		if (Year_ % 100 == 0) // if divisible by 100
		{
			if (Year_ % 400 == 0) // if divisible by 400
				Leap_ = true;
			else
				Leap_ = false;
		}
		else
			Leap_ = true;
	}
	else
		Leap_ = false;

	std::uniform_int_distribution<int> random_month(1, 12);
	Month_ = random_month(rng);

	int days = 0;
	switch (Month_) // assign days based on the month (given leap or not)
	{
	case 1:
		Month_name_ = "January";
		days = 31;
		break;
	case 2:
		Month_name_ = "February"; // only month that changes due to leap
		switch (Leap_)
		{
		case true:
			days = 28;
			break;
		case false:
			days = 29;
			break;
		}
		break;
	case 3:
		Month_name_ = "March";
		days = 31;
		break;
	case 4:
		Month_name_ = "April";
		days = 30;
		break;
	case 5:
		Month_name_ = "May";
		days = 31;
		break;
	case 6:
		Month_name_ = "June";
		days = 30;
		break;
	case 7:
		Month_name_ = "July";
		days = 31;
		break;
	case 8:
		Month_name_ = "August";
		days = 31;
		break;
	case 9:
		Month_name_ = "September";
		days = 30;
		break;
	case 10:
		Month_name_ = "October";
		days = 31;
		break;
	case 11:
		Month_name_ = "November";
		days = 30;
		break;
	case 12:
		Month_name_ = "December";
		days = 31;
		break;
	default:
		Month_name_ = "February";
		days = 28;
		break;
	}

	std::uniform_int_distribution<int> random_day(1, days);
	Day_ = random_day(rng);
}

// Inspectors
int Date::getMonth() const
{
	return Month_;
}

std::string Date::getMonth_name() const
{
	return Month_name_;
}

int Date::getDay() const
{
	return Day_;
}

int Date::getYear() const
{
	return Year_;
}

bool Date::isLeap() const
{
	return Leap_;
}