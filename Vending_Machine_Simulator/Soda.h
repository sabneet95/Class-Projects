#ifndef _SODA_H_
#define _SODA_H_

#include <string>
#include <vector>
#include <map>

class Soda
{
public:
	// Default Constructor
	Soda();

	// Inspectors
	int getQuantity(std::string type);
	double getPrice(std::string type);
	bool isAvailable(std::string type);

	// Mutators
	void dispenseSoda(std::string type);
	static void displayMetrics(Soda soda);

private:
	std::map<std::string, std::vector<double>> SODA_;
	std::vector<double> COKE_;
	std::vector<double> PEPSI_;
	std::vector<double> FANTA_;
};

#endif