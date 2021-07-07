#include "Soda.h"
#include <iostream>
#include <iomanip>
#include <random>

// Default Constructor
/*
 The default constructor Soda constructs three types of Sodas, namely 
 Coke, Pepsi, and Fanta, for the vending machine's dispensing columns 
 with random initial quantities and predefined prices before mapping 
 these attributes into a single map.

 1) It creates a random quantity between 10 and 20 cans using the 
    Mersenne Twister pseudo-random generator with a uniform distribution.

 2) Next, it resizes the corresponding private vectors and then combines 
    them into a single map: map<string, vector<double>> SODA_.
*/
Soda::Soda()
{
	std::random_device rd;
	std::mt19937 rng(rd()); // Mersenne Twister pseudo-random generator
	std::uniform_int_distribution<int> random_quantity(10, 20);

	COKE_.resize(random_quantity(rng), 1.75);
	PEPSI_.resize(random_quantity(rng), 1.85);
	FANTA_.resize(random_quantity(rng), 2.25);

	SODA_ =
	{
		{"coke", COKE_},
		{"pepsi", PEPSI_},
		{"fanta", FANTA_}
	};

}

// Inspectors
int Soda::getQuantity(std::string type)
{
	// when the vector no longer exists
	// returns a zero to make the math work
	if (SODA_[type].size() < 1)
	{
		return 0;
	}
	else
	{
		return SODA_[type].size();
	}
}

double Soda::getPrice(std::string type)
{
	// when the vector no longer exists
    // returns a zero to make the math work
	if (SODA_[type].size() < 1)
	{
		return 0;
	}
	else
	{
		return SODA_[type][0];
	}
}

/*
 The Soda::isAvailable() function checks if the soda type is even
 available in the first place!

 @param type - a string argument
 @return boolean true or false
*/
bool Soda::isAvailable(std::string type)
{
	return SODA_.contains(type);
}

// Mutators

/*
 The Soda::dispenseSoda() function pops the specific inner vector
 given a soda type and then displays a quick ASCII representation
 of the soda delivery slot.

 @param type - a string argument
*/
void Soda::dispenseSoda(std::string type)
{
	if (SODA_[type].size() > 0)
	{
		SODA_[type].pop_back();
		std::cout
			<< "\n" << std::setw(30) << std::right << "|    SODA     |\n"
			<< std::setw(30) << std::right << "|  DISPENSED  |\n"
			<< std::setw(30) << std::right << "|             |\n"
		    << std::setw(30) << std::right << " (___________)\n\n";
	}
}

/*
 The Soda::displayMetrics() function displays a table of current 
 Soda Type, Price, and Quantity metrics using the soda.getQuantity 
 and the soda.getPrice inspectors.
 
 @param soda - a Soda object
*/
void Soda::displayMetrics(Soda soda)
{
	std::cout
		<< "\n+---------------+---------------+-------------+\n"
	    << std::setw(13) << std::left << "|   Soda Type\t"
		<< std::setw(12) << std::left << "|     Price\t"
		<< std::setw(14) << std::left << "|   Quantity" << "|\n"
		<< "+---------------+---------------+-------------+\n" 
		
		<< std::setw(9) << std::left << "|    Coke\t"
		<< "|    $ " << soda.getPrice("coke") << "\t|      "
		<< soda.getQuantity("coke") << "     |\n"
		<< "|    Pepsi\t"
		<< "|    $ " << soda.getPrice("pepsi") << "\t|      "
		<< soda.getQuantity("pepsi") << "     |\n"
		<< "|    Fanta\t"
		<< "|    $ " << soda.getPrice("fanta") << "\t|      "
		<< soda.getQuantity("fanta") << "     |\n"

		<< "+---------------+---------------+-------------+\n" << std::endl;
}