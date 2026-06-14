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
2. Run the application by writing down commands in the console
3. Read reports in the console
4. Check the list of errors in your Excel file (if applicable) in the created Errors.txt file


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

XXX



