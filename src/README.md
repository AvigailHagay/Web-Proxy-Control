# Exercise 2

## Authors
* Avigail Hagay:  avigilha@edu.hac.ac.il
* Racheli Benchamo:  rachelbenha@edu.hac.ac.il

This program is a console-based web proxy that allows you to control the downloading of URL resources from the web. It provides several options to block certain types of resources or deny access to specific URLs.

## Design

In our program, we use object-oriented programming (OOP) principles to help us structure and organize our code.

One example of OOP in our program is the use of classes to represent different types of commands and options. Each class encapsulates the data and behavior needed for a specific task, making it easier to manage and modify the code as needed. This also allows us to easily add new commands and options to the program without having to change the existing code.

By using OOP principles in our program, we can write code that is more modular, easier to understand and maintain, and more flexible to changes in requirements or functionality.

The program uses the Factory design pattern - 
The factories: `CommandFactory` and `OptionFactory`.
- `CommandFactory` -
The CommandFactory class is responsible for creating command objects based on user input. Each command object is responsible for performing a specific operation. To create a new command, simply subclass the Command class and implement the execute method.

- `OptionFactory` -
The OptionFactory class is responsible for creating option objects based on user input. Each option object is responsible for parsing its corresponding argument and storing its value. To create a new option, simply subclass the Option class and implement the parse method.

## Usage

The program provides several console-based commands that allow you to control the downloading of URL resources. Here are the available commands:

- b <url>: block a URL, adds the URL to the list of blocked URL saves to the file.
- u <url>: unblock the URL and updates the file.
- p: prints the current list of blocked sites, alphabetically ordered.
- q: exit the program.
- d <-options> <url> <out>: download the given URL contents to a file out according to denying options defined by one character code.

If a URL is blocked (by b, c, h, or i), the command outputs denied. If a URL is not reachable (HTTP response 4XX or 5XX), it prints the HTTP status code.

The API documentation for this project can be found [here](../doc/index.html).
