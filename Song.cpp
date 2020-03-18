#include "Song.h"
#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <algorithm>
using namespace std;

// default constructor
// Song::Song()
// {
// 	myTitle_ = "title";
// 	myAlbum_ = "album";
// 	myArtist_ = "artist";
// 	myCategory_ = PUNJABI;
// 	myPlaying_time_ = 0;
// }

// alternate constructor
Song::Song(const string& title, const string& album, const string& artist, const Category& category, const int& playing_time)
{
	myTitle_ = title;
	myAlbum_ = album;
	myArtist_ = artist;
	myCategory_ = category;
	myPlaying_time_ = playing_time;
};

//inspectors
Category Song::getCategory() const
{
	return myCategory_;
}

string Song::getCategory_string() const
{
	switch (myCategory_)
	{
	case PUNJABI:
		return "Punjabi";
	case HINDI:
		return "Hindi";
	}
}

string Song::getTitle() const
{
	return myTitle_;
};
string Song::getAlbum() const
{
	return myAlbum_;
};
string Song::getArtist() const
{
	return myArtist_;
};
int Song::getPlaying_time() const
{
	return myPlaying_time_;
};
//mutators
void Song::setCategory(Category category)
{
	myCategory_ = category;
};
void Song::setTitle(string title)
{
	myTitle_ = title;
};
void Song::setAlbum(string album)
{
	myAlbum_ = album;
};
void Song::setArtist(string artist)
{
	myArtist_ = artist;
};
void Song::setPlaying_time(int playing_time)
{
	myPlaying_time_ = playing_time;
}

// facilitator (not really used; utilized a different algorithm)
string Song::toString() const
{
	ostringstream os;
	os << myAlbum_;
	string return_string = os.str();
	return return_string;
}

bool operator==(Song a, Song b)
{
	if ((a.getAlbum() == b.getAlbum()) && (a.getArtist() == b.getArtist())
		&& (a.getCategory() == b.getCategory()) && (a.getPlaying_time() == b.getPlaying_time())
		&& (a.getTitle() == b.getTitle()))
		return true;
	else
		return false;
}

bool operator!=(Song a, Song b)
{
	if ((a.getAlbum() != b.getAlbum()) && (a.getArtist() != b.getArtist())
		&& (a.getCategory() != b.getCategory()) && (a.getPlaying_time() != b.getPlaying_time())
		&& (a.getTitle() != b.getTitle()))
		return true;
	else
		return false;
}

ostream & operator<<(ostream & out, const Song a)
{
	//Present all the attributes the user just defined according to their choice
	cout << "\t\tTITLE: " << a.getTitle() << endl;
	cout << "\t\tALBUM: " << a.getAlbum() << endl;
	cout << "\t\tARTIST: " << a.getArtist() << endl;
	cout << "\t\tPLAYING TIME: " << a.getPlaying_time() << " minutes" << endl;
	cout << "\t\tCATEGORY: " << a.getCategory_string() << "\n" << endl;
	return out;
}

istream & operator>>(istream & in, Song & a)
{
	string title, album, artist, category_string, playing_time_string;

	cout << "Please enter the title of the song: " << endl;
	getline(cin, title);
	if (!(title.empty())) // If the user presses enter then use the default otherwise...
	{
		a.setTitle(title);
	}

	cout << "Please enter the album of the song (if none enter N/A): " << endl;
	getline(cin, album);
	if (!(album.empty())) // If the user presses enter then use the default otherwise...
	{
		a.setAlbum(album);
	}

	cout << "Please enter the artist of the song: " << endl;
	getline(cin, artist);
	if (!(artist.empty())) // If the user presses enter then use the default otherwise...
	{
		a.setArtist(artist);
	}
	cout << "Please enter the playing time of the song in minutes: " << endl;
	getline(cin, playing_time_string);

	if (!(playing_time_string.empty())) // If the user presses enter then use the default otherwise...
	{
		//Check if the user enter 0 for playing time
		while (playing_time_string == "0")
		{
			cout << "Please enter a positive non-zero number in minutes: " << endl;
			getline(cin, playing_time_string);
		}

		//Check if the user actually entered numbers or not
		while (!(isdigit(playing_time_string[0])))
		{
			cout << "Please enter a positive non-zero number in minutes: " << endl;
			getline(cin, playing_time_string);
		}
		int playing_time_check = stoi(playing_time_string);

		//If the user enters a negative number keep on iterating the prompt
		while (playing_time_check < 0)
		{
			cout << "Please enter a positive non-zero number in minutes: " << endl;
			getline(cin, playing_time_string);
			playing_time_check = stoi(playing_time_string);
		}

		a.setPlaying_time(playing_time_check);
	}

	cout << "Please enter the category of the song (punjabi or hindi): " << endl;
	getline(cin, category_string);
	if (!(category_string.empty())) // If the user presses enter then use the default otherwise...
	{
		//transform the input into lowercase for easy boolean check
		transform(category_string.begin(), category_string.end(), category_string.begin(), tolower);
		if (category_string == "punjabi")
		{
			a.setCategory(PUNJABI);
		}
		else if (category_string == "hindi")
		{
			a.setCategory(HINDI);
		}
		else //when the user enters neither of the specified inputs
		{
			while (!((category_string == "punjabi") || (category_string == "hindi")))
			{
				cout << "Please enter the category of the song (punjabi or hindi): " << endl;
				getline(cin, category_string);
				transform(category_string.begin(), category_string.end(), category_string.begin(), tolower);
			}
		}
	}
	return in;
}

void swap(vector<Song> & songs, int x, int y)
{
	Song temp;
	temp = songs[x];
	songs[x] = songs[y];
	songs[y] = temp;
}

void quick_sort(vector<Song> & songs, int left, int right)
{
	int i = left;
	int j = right;
	Song mid = songs[(left + right) / 2];
	while (i <= j)
	{
		while (songs[i].getTitle() < mid.getTitle())
			i++;
		while (songs[j].getTitle() > mid.getTitle())
			j--;
		if (i <= j)
		{
			swap(songs, i, j);
			i++;
			j--;
		}
	};
	if (left < j)
		quick_sort(songs, left, j);
	if (i < right)
		quick_sort(songs, i, right);
}

int binary_search(const vector<Song>& sorted_songs, string search_term, int begin, int end)
{
	if (begin == end)
		if (sorted_songs[end] == search_term)
			return end;
	if (begin > end)
		return -1; // I could have followed the homework and returned 1 but instead returned -1 as that is the convention for when outside the vector plus returning a 1 is a logical flaw
	else
	{
		int mid = ((begin + end) / 2);
		string check = sorted_songs[mid].getTitle();
		if (check == search_term)
			return mid;
		if (check > search_term)
			return binary_search(sorted_songs, search_term, begin, mid - 1);
		if (check < search_term)
			return binary_search(sorted_songs, search_term, mid + 1, end);
	}
}
