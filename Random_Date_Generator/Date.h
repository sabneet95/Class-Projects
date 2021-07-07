#ifndef _DATE_H_
#define _DATE_H_

#include<string>

class Date
{
public:
	// Default Constructor
	Date();

	// Inspectors
	int getMonth() const;
	std::string getMonth_name() const;
	int getDay() const;
	int getYear() const;
	bool isLeap() const;

private:
	int Month_;
	std::string Month_name_;
	int Day_;
	int Year_;
	bool Leap_;
};
#endif