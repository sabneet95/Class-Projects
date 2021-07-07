#include "Currency.h"
#include <iostream>
#include <iomanip>
#include <vector>

using namespace std;

// Stream Overload 
/*
 Due to the utilization of multidimensional vectors in the 
 Currency::getDenominations() logic, an overload template 
 to print vectors/matrices is defined here:
*/
template <typename T>
ostream& operator<<(ostream& out, const vector<T>& v)
{
	for (int i = 0; i < v.size(); ++i)
	{
		out << v[i];
		// separates discrete vectors or vector elements with a comma
		if (i != v.size() - 1)
		{
			out << ", "; 
		}
	}
	return out;
}

// Default Empty Constructor
Currency::Currency()
{
}

// Inspector
/*
 The Currency::getDenominations() function takes in any amount of US currency 
 and breaks it down into its denominations of bills and cents. Likewise, to 
 keep the complexity metrics to a minimum, the logic is primarily hard-coded 
 even though specific patterns can be looped.

 @param money - a double argument
 @return Change_ - a vector<vector<vector<unsigned long long>>> filled with:
				   1) Dollar_Denominations_ 
				   2) Cent_Denominations_

 NOTE: THE FUNCTION ALWAYS CREATES DENOMINATIONS GIVEN THE LARGEST POSSIBLE COMBINATION! 
*/
vector<vector<vector<unsigned long long>>> Currency::getDenominations(double money)
{
	// Parsing dollars
	unsigned long long dollars = floor(money); // removes cents
	unsigned long long dollar_hundreds = floor(dollars / 100) * 100;
	unsigned long long dollar_tens = floor((dollars - dollar_hundreds) / 10) * 10;
	unsigned long long dollar_ones = floor(dollars - (dollar_hundreds + dollar_tens));

	vector<unsigned long long> dollar_place_values = { {dollar_hundreds}, {dollar_tens}, {dollar_ones} };
	Dollar_Denominations_ = { { dollar_hundreds / 100 }, {0}, {0} }; // fills just number of benjamins

	// specifies tens place dollar denominations
	switch (dollar_place_values.at(1))
	{
	case 90:
		Dollar_Denominations_.at(1) = { {50, 20, 20} };
		break;
	case 80:
		Dollar_Denominations_.at(1) = { {50, 20, 10} };
		break;
	case 70:
		Dollar_Denominations_.at(1) = { {50, 20} };
		break;
	case 60:
		Dollar_Denominations_.at(1) = { {50, 10} };
		break;
	case 50:
		Dollar_Denominations_.at(1) = { {50} };
		break;
	case 40:
		Dollar_Denominations_.at(1) = { {20, 20} };
		break;
	case 30:
		Dollar_Denominations_.at(1) = { {20, 10} };
		break;
	case 20:
		Dollar_Denominations_.at(1) = { {20} };
		break;
	case 10:
		Dollar_Denominations_.at(1) = { {10} };
		break;
	case 0:
		Dollar_Denominations_.at(1) = { {0} };
		break;
	}

	// specifies ones place dollar denominations
	switch (dollar_place_values.at(2))
	{
	case 9:
		Dollar_Denominations_.at(2) = { {5, 2, 2} };
		break;
	case 8:
		Dollar_Denominations_.at(2) = { {5, 2, 1} };
		break;
	case 7:
		Dollar_Denominations_.at(2) = { {5, 2} };
		break;
	case 6:
		Dollar_Denominations_.at(2) = { {5, 1} };
		break;
	case 5:
		Dollar_Denominations_.at(2) = { {5} };
		break;
	case 4:
		Dollar_Denominations_.at(2) = { {2, 2} };
		break;
	case 3:
		Dollar_Denominations_.at(2) = { {2, 1} };
		break;
	case 2:
		Dollar_Denominations_.at(2) = { {2} };
		break;
	case 1:
		Dollar_Denominations_.at(2) = { {1} };
		break;
	case 0:
		Dollar_Denominations_.at(2) = { {0} };
		break;
	}

	// Parsing cents
	unsigned long long cents = round((money - dollars) * 100); // removes dollars
	unsigned long long cent_tens = floor((cents) / 10) * 10;
	unsigned long long cent_ones = floor(cents - cent_tens);

	vector<unsigned long long> cent_place_values = { cent_tens, cent_ones };
	Cent_Denominations_ = { {0}, {0} }; // creates a zero vector

	// specifies tens place cent denominations
	switch (cent_place_values.at(0))
	{
	case 90:
		Cent_Denominations_.at(0) = { {50, 25, 10, 5} };
		break;
	case 80:
		Cent_Denominations_.at(0) = { {50, 25, 5} };
		break;
	case 70:
		if (cent_place_values.at(1) < 5) // when less than 75, dimes are used
		{
			Cent_Denominations_.at(0) = { {50, 10, 10} };
		}
		switch (cent_place_values.at(1)) // when more than 75, quarters take precedence
		{
		case 5:
			Cent_Denominations_.at(0) = { {50, 25} };
			break;
		case 6:
			Cent_Denominations_.at(0) = { {50, 25, 1} };
			break;
		case 7:
			Cent_Denominations_.at(0) = { {50, 25, 1, 1} };
			break;
		case 8:
			Cent_Denominations_.at(0) = { {50, 25, 1, 1, 1} };
			break;
		case 9:
			Cent_Denominations_.at(0) = { {50, 25, 1, 1, 1, 1} };
			break;
		}
		break;
	case 60:
		Cent_Denominations_.at(0) = { {50, 10} };
		break;
	case 50:
		Cent_Denominations_.at(0) = { {50} };
		break;
	case 40:
		Cent_Denominations_.at(0) = { {25, 10, 5} };
		break;
	case 30:
		Cent_Denominations_.at(0) = { {25, 5} };
		break;
	case 20:
		if (cent_place_values.at(1) < 5) // when less than 25, dimes are used
		{
			Cent_Denominations_.at(0) = { {10, 10} };
		}
		switch (cent_place_values.at(1)) // when more than 25, quarters take precedence
		{
		case 5:
			Cent_Denominations_.at(0) = { {25} };
			break;
		case 6:
			Cent_Denominations_.at(0) = { {25, 1} };
			break;
		case 7:
			Cent_Denominations_.at(0) = { {25, 1, 1} };
			break;
		case 8:
			Cent_Denominations_.at(0) = { {25, 1, 1, 1} };
			break;
		case 9:
			Cent_Denominations_.at(0) = { {25, 1, 1, 1, 1} };
			break;
		}
		break;
	case 10:
		Cent_Denominations_.at(0) = { {10} };
		break;
	case 0:
		Cent_Denominations_.at(0) = { {0} };
		break;
	}

	// specifies one place cent denominations
	switch (cent_place_values.at(1))
	{
	case 9:
		if (cent_place_values.at(0) != 20 && cent_place_values.at(0) != 70) // when not in 20s and 70s
		{
			Cent_Denominations_.at(1) = { {5, 1, 1, 1, 1} };
		}
		break;
	case 8:
		if (cent_place_values.at(0) != 20 && cent_place_values.at(0) != 70) // when not in 20s and 70s
		{
			Cent_Denominations_.at(1) = { {5, 1, 1, 1} };
		}
		break;
	case 7:
		if (cent_place_values.at(0) != 20 && cent_place_values.at(0) != 70) // when not in 20s and 70s
		{
			Cent_Denominations_.at(1) = { {5, 1, 1} };
		}
		break;
	case 6:
		if (cent_place_values.at(0) != 20 && cent_place_values.at(0) != 70) // when not in 20s and 70s
		{
			Cent_Denominations_.at(1) = { {5, 1} };
		}
		break;
	case 5:
		if (cent_place_values.at(0) != 20 && cent_place_values.at(0) != 70) // when not in 20s and 70s
		{
			Cent_Denominations_.at(1) = { {5} };
		}
		break;
	case 4:
		Cent_Denominations_.at(1) = { {1, 1, 1, 1} };
		break;
	case 3:
		Cent_Denominations_.at(1) = { {1, 1, 1} };
		break;
	case 2:
		Cent_Denominations_.at(1) = { {1, 1} };
		break;
	case 1:
		Cent_Denominations_.at(1) = { {1} };
		break;
	case 0:
		Cent_Denominations_.at(1) = { {0} };
	}

	Change_ = { Dollar_Denominations_, Cent_Denominations_ }; // fills the previously created vector

	return Change_;
}

/*
The Currency::returnChange() function takes in an object of the Currency class 
and neatly presents the exact denominations of change through the vending machine’s 
change return slot.

@param change - a Currency object
*/
void Currency::returnChange(Currency& change) const
{
	if (Change_[0][0][0] != 0) // Hundreds
	{
		cout << "\n" << setw(75) << right << "> Hundred Dollar Bills: " << Change_[0][0];
	}
	if (Change_[0][1][0] != 0) // Tens
	{
		cout << "\n" << setw(62) << right << "> Dollars: " << Change_[0][1];
	}
	if (Change_[0][2][0] != 0) // Ones
	{
		cout << "\n" << setw(62) << right << "> Dollars: " << Change_[0][2];
	}
	if (Change_[1][0][0] != 0) // Tens
	{
		cout << "\n" << setw(62) << right << "> Cents:   " << Change_[1][0];
	}
	if (Change_[1][1][0] != 0) // Ones
	{
		cout << "\n" << setw(62) << right << "> Cents:   " << Change_[1][1];
	}
}
