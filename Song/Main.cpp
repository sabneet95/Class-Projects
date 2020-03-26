#include "Song.h"
#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
using namespace std;

// Prints all the songs in the vector using the overload operator <<
void printCatalog(vector <Song> a)
{
	for (int i = 0; i < a.size(); i++)
	{
		if (i < 1)
		{
			cout << "\n\n\nYou entered the following " << a.size() << " songs:\n";
			cout << "\n///////////////---Song " << (i + 1) << " Details---///////////////\n\n";
		}
		else
		{
			cout << "\n///////////////---Song " << (i + 1) << " Details---///////////////\n\n";
		}
		cout << a[i];
	}
}

//Checks the vector of songs for a matching artist and returns the title of the song if matched
void checkCatalog(vector <Song> a, string artist_name)
{
	vector <string> title_found;
	int found;
	for (int i = 0; i < a.size(); i++)
	{
		if (a[i].getArtist() == artist_name)
		{
			title_found.push_back(a[i].getTitle());
			found = 1;
		}
		else
		{
			found = 0;
		}
	}
	if (found != 0)
	{
		for (int i = 0; i < title_found.size(); i++)
		{
			if (i < 1)
			{
				cout << "\nThe following songs by " << artist_name << " are present in the Catalog:\n\n";
				cout << title_found[i] << endl;
			}
			else
			{
				cout << title_found[i] << endl;
			}
		}
	}
	else
	{
		cout << "\nUnfortunately, there are no songs by " << artist_name << " in the Catalog.\n" << endl;
	}
}

int main()
{
	vector <Song> mySongs(2);
	for (int i = 0; i < mySongs.size(); i++)
	{
		if (i < 1)
		{
			cout << "///////////////---Song " << (i + 1) << "---///////////////\n\n";
		}
		else
		{
			cout << "\n///////////////---Song " << (i + 1) << "---///////////////\n\n";
		}

		cin >> mySongs[i];
	}

	// The aquired vector of song(s) is sorted
	quick_sort(mySongs, 0, mySongs.size() - 1);

	// The print function is called on the sorted vector
	printCatalog(mySongs); 

	string search_term; // search term for the title of the song
	cout << "\nNow please enter a song title to search in the catalog: ";
	getline(cin,search_term); // gets the user provided search term as a line, so do not have to worry about spaces

	int song_search = binary_search(mySongs, search_term, 0, mySongs.size() - 1); //Gets the result from binary_search

	//if the search did find a song
	if (song_search != -1)
	{
		cout << "\nThe song " << search_term << " is indeed available in the catalog.\n" << endl;
		cout << mySongs[song_search];
	}
	else //if there were no matching songs
	{
		string answer;
		cout << "\nOh no, it seems there is no song with the title '" << search_term << "' currently available in the catalog." << endl;
		cout << "\nWould you like me to add '" << search_term << "' to the catalog? (answer y for yes or n for no) ";
		getline(cin,answer);

		Song additional_song; // kind of a temporary single song that later gets pushed back into the mySongs vector
		if ((answer == "y") || (answer == "Y"))
		{
			cout << "\nOkay, here we go:\n" << endl;
			cin >> additional_song;
			mySongs.push_back(additional_song);

			cout << "\nThe new catalog now includes: " << endl;
			quick_sort(mySongs, 0, mySongs.size() - 1);
			printCatalog(mySongs); // Calls the print function on the sorted vector
		}
		else 
		{
			cout << "\n\nYou did not say 'yes' so not much has changed, well matter of fact the catalog still is: " << endl;
			printCatalog(mySongs);
		}
	}

	system("pause");
	return 0;
}
