# Matters
Displays a list of matters

The app will retrieve matters from the server, display them in a listview, and blow up a matter when a row item is clicked.
Offline support has also been implemented.

Retrofit was used for requesting the matters from the server.
DBFlow was used to retain the data.
ButterKnife was used to reduce the boilerplate for using view objects.

I broke up the tasks as follows:
1. create the project.
2. retrieve the list of matters and populate them in a listview.
3. display matter details in a separate activity.
4. add offline support.

Greek mytholigal characters was the them for the release version names.

The Json seemed to cut off at the 8th item causing problems with some of the fields.
The next step would be to figure out what was happening there,
then to add support for the remaining Matter fields that were ignored.
