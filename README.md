# Reporter


App Reporter is created to support the organisation with data. Two reports can be generated within the app:

Report 1 - Employees

- time (hours) worked out by the person within the month in all projects


Report 2 - Projects

- time (hours) spent on each project



# Requirements for the app usage:

Java 25, terminal, app to read .txt file format


# Input data

The Reporter app uses an .xls file added to [FILE PATH] as an input data. 

- Each excel file is assigned to one employee. The file is named by the Surname and Name of the employee (format Surname_Name)
- Each file is created for one month only.
- The file should include data in columns:
	- Column 1: "Data" -  acceptable format: any Date Format field in Excel
	- Column 2: "Zadanie" - acceptable format: any description
	- Column 3: "Czas" - acceptable format: number of hours, minutes can be added with "," or "." Separator. Examples: "4", "4.5", "4,5"
- Projects are kept in separate tabs. Each tab is a single project.

# How to start

1. Add your Excel sheet to the [FILE PATH]
2. Run the application by writing down commands in the console with sets of arguments

Code structure:
java -jar {filePath .jar} flag1 arg1 flag2 arg2@ flag1 flag2 ... 

Example code:
java -jar Reporter-1.0-SNAPSHOT.jar --from 2012-01-01 --r R1@ --path /var/home/student/IdeaProjects/Reporter/Resources/reporter-dane/2012/02/Kowalski_Jan.xls

3. Read reports in the console
4. Check the list of errors in your Excel file (if applicable) in the created Errors.txt file

# Arguments pairs

Arguments are paired. Arguments without pair or with wrong flag have no effect on starting program. 
Structure of pair: 

--flag arg 

Pairs are combined with sets. Every set generates new report.
Every set ends with "@" added to the last argument
Example:

--from 2012-01-01 --r R4@ --r R1@ --r R2@ 

# Flags
Implemented flags:
1. --path
Argument with this flag sets folders with files or file paths that are being reported

2. --from
Argument with this flag sets "from" date

Argument format:

yyyy-MM-dd

3. --to
Argument with this flag sets "to" date

Argument format:

yyyy-MM-dd

4. --r
Argument with this flag sets type of generated report

5. --out (for future development of information display methods)

# Default values
If you don't use any flags Params will be set default:
1. path = "Resources/reporter-dane"
2. from = "2010-01-01"
3. to = {OneYearFromProgramStart}
4. r = R1
5. out = "out1"

# Options for --r flag:
1. R1   for Employees


2. R2   for Projects


3. R3   


4. R4   for Tasks



# Report 1 - Employees

1. Dates priority - if you want to calculate data from designated time-frame, write down in the console: XXX. 

Example: XXX

2. Catalog priority - if you want to calculate data from a selected file or all files in the catalog, use: XXXX in the console.

Example: XXX


# Report 2 - Projects 

1. Use XXX command in the console to get the time (hours) spent in the project and the % of time from the perspective of all projects.

Result: 

# Report 4 - Tasks

1. Use XXX command in the console to get the list of tasks sorted by time spent on them (top-down)


# FAQ





