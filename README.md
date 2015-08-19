
# HotelTonight Android Coding Exercise
1. Start a new project in your favorite Android IDE.
2. Push the empty skeleton project to this repo.
3. Add a simple Activity with an Actionbar, and add a SearchView and standard search icon to this Actionbar.
4. Using the SearchView control and the [Google Places API](https://developers.google.com/places/documentation/autocomplete), wire up an auto-complete search field in the Actionbar:
5. When a search result is tapped, launch a new Activity to display details about this city using [Google’s Place Details API](https://developers.google.com/places/documentation/details) (just a few small details is fine).

Add a file (Markdown or plain text is fine) which answers these questions:

1. What error conditions will you encounter? How should these be handled?
2. Where could the user experience break? How will you prevent this?
3. What other improvements can be made?
4. Extra credit: Without running it, what’s the result of this statement and why?
`System.out.println(-1/9);`

When done, submit your code via a pull request to this repo.


## Coding Guidelines
* Use any networking libraries you prefer, or build something yourself
* Consider limiting the number of keystrokes before making a new API request, instead of making an API request for each keystroke
* Use a pattern such as [Model View Presenter] (http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) to ensure that your code is well structured.
