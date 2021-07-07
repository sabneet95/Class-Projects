#include "Currency.h"
#include "Soda.h"
#include <iostream>
#include <iomanip>
#include <algorithm>

using namespace std;

/*
 The main function:

 1) Simulates the continuous operation of a soda vending machine by running a 
    forever loop while displaying the current quantity of distinct types of sodas
    and their prices.

 2) The selection buttons are simulated by requesting the user for a type of soda.
    At the same time, the function checks for input faults and prompts the user, 
    accordingly, given that the particular soda did not run out yet.

 3) Once a selection is made, the user must enter the corresponding price as given 
    in the metrics table. The logical front end then taps into “Currency.h” and 
    “Currency.cpp” to figure out if the entered amount matches, is less than, or 
    exceeds the price of the given soda.

 4) Finally, if everything goes smoothly, a soda is dispensed, change is returned 
    (if any), and the loop begins again.


 NOTE: PLEASE, CHECK "Soda.h", "Currency.h", "Soda.cpp", and "Currency.cpp" FOR THE LOGICAL BACKEND!
*/

int main()
{
	Soda soda;
	Currency change;

	string selection;
	string string_money;
	double numeric_money;
	double handled_money;

	// the vending machine must be operating at all times hence the forever while loop
	// if needed break with a KeyboardInterrupt
	while (true)
	{
		soda.displayMetrics(soda); // displays vending machine metrics at every loop

		// request the user for a type of soda to simulate selection buttons on a physical vending machine
		cout << "Please, enter a type of soda: "; 
		getline(cin, selection);
		transform(selection.begin(), selection.end(), selection.begin(), tolower); // transform the input to lowercase
		selection.erase(remove(selection.begin(), selection.end(), ' '), selection.end()); // remove trailing whitespace

		// given if the selection is a type of soda in the machine
		if (soda.isAvailable(selection)) 
		{
			// if the particular dispensing column (vector) has that soda
			if (soda.getQuantity(selection) > 0)
			{
				cout << "Please, insert the money: ";
				getline(cin, string_money);
				numeric_money = stod(string_money); // convert pseudo string money into an actual double

				// when the user enters an amount less than the price of the item
				while (numeric_money < soda.getPrice(selection)) 
				{
					handled_money = soda.getPrice(selection) - numeric_money;

					// tells the user how much more money they should enter
					cout << "You need to insert at least $" << handled_money << " more: ";
					getline(cin, string_money);
					numeric_money = numeric_money + stod(string_money);

					// if the user finally equals or exceeds the price
					if (numeric_money == soda.getPrice(selection))
					{
						soda.dispenseSoda(selection);
						break;
					}
				}

				// when the user enters an amount equal to or more than the price of the item
				if (numeric_money >= soda.getPrice(selection))
				{
					handled_money = numeric_money - soda.getPrice(selection);

					cout << setw(74) << right << "+-----------------------+\n"
					     << setw(67) << right << " | Change Returned $" << handled_money << " | \n"
					     << setw(72) << right << "\\---------------------/";
					
					change.getDenominations(handled_money); // gets individual US denominations
					change.returnChange(change); // prints out the change denominations
					soda.dispenseSoda(selection); // pops the soda from dispensing column (vector)
				}
			}

			// when the machine runs out of soda in a particular dispensing column (vector)
			else
			{
				cout << "\n" << setw(70) << right 
				     << "<!> Sorry, but the machine ran out of " << selection << "!\n\n";
			}
		}

		// when the user enters faulty input 
		else
		{
			cout 
				<< "\n<!> " << selection << " might not be a type of soda!\n"
				<< "\n<!> Please, try to enter a soda from the following:\n ";
		}
	}

	return 0;
}
