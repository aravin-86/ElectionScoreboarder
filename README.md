
 -----------------------------------
| Election Scoreboard Application |
 -----------------------------------

Instructions to run in Terminal(For Ex: COMMAND PROMPT)
-------------------------------

Prerequisites:
 JRE 1.8

1) Extract the zip into your preferred location.
1) Create a folder to monitor the <results-file>.xml somewhere like below.
   G:\ElectionResults (Windows)
2) Make sure that you don't have any file in the folder before we start our application.
3) Move into the folder where you extracted the zip which contains the jar.
4) Start the application by using jar command.

   java -jar election-scoreboard-0.0.1-SNAPSHOT.jar <folder-path-to-monitor>

5) Copy the files into the folder however you wish. Scoreboard will be displayed in console accordingly.

Instructions to run in IDE
---------------------------
1) Setup the project into your preferred IDE.
2) Run the main class ElectionRunner.java with program arguments that contains the folder path to monitor.
3) Start copying files into monitored folder. Scoreboard will be updated accordingly in IDE console.

Enhancements
------------
1) This app will not identify the files that was already processed if you copy it again (Modify).
2) Needs more validation on files(updates on the same file, delete and add it again).

Hope you made it through with these instructions.

--- END ----
   

 

