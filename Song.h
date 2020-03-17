#ifndef _SONG_H_
#define _SONG_H_

#include <string>
#include <sstream>
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

enum Category
{
	PUNJABI, HINDI
};

class Song
{
public:

	//constructors
	//Song(); // default
	Song(const string& title = "Na Ja", const string& album = "N/A", const string& artist = "Pav Dharia", const Category& category = PUNJABI, const int& playing_time = 4); // alternate

																																											//inspectors
	Category getCategory() const;
	string getCategory_string() const;
	string getTitle() const;
	string getAlbum() const;
	string getArtist() const;
	int getPlaying_time() const;

	//mutators
	void setCategory(Category category);
	void setTitle(string title);
	void setAlbum(string album);
	void setArtist(string artist);
	void setPlaying_time(int playing_time);

	//facilitator
	string toString() const;

private:
	string myTitle_;
	string myAlbum_;
	string myArtist_;
	Category myCategory_;
	int myPlaying_time_;
};

//operator overloads
bool operator==(Song a, Song b);
bool operator!=(Song a, Song b);
ostream& operator<<(ostream& out, const Song a);
istream& operator>>(istream& in, Song& a);

//Binary Search
int binary_search(const vector<Song>& sorted_songs, string search_term, int begin, int end);

//Quick Sort
void quick_sort(vector<Song> & songs, int left, int right);

//Swap
void swap(vector<Song> & songs, int x, int y);

#endif // !_SONG_H_
