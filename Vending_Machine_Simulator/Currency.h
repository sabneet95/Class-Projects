#ifndef _CURRENCY_H_
#define _CURRENCY_H_

#include <vector>

class Currency
{
public:
	// Default Constructor
	Currency();

	// Inspectors
	std::vector<std::vector<std::vector<unsigned long long>>> 
		getDenominations(double money);

	// Mutators
	void returnChange(Currency& change) const;

private:
	std::vector<std::vector<unsigned long long>> 
		Dollar_Denominations_{ {0},{0},{0} };

	std::vector<std::vector<unsigned long long>> 
		Cent_Denominations_{ {0},{0} };

	std::vector<std::vector<std::vector<unsigned long long>>> 
		Change_{ Dollar_Denominations_,Cent_Denominations_ };
};

#endif