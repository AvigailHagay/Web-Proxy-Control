# Web-Proxy-Control

## Author
* Avigail Hagay:  avigilhagay@gmail.com

This program is a console-based web proxy that allows you to control the downloading of URL resources from the web. It provides several options to block certain types of resources or deny access to specific URLs.

## Design

The program is designed using object-oriented programming (OOP) principles to ensure code organization and structure.

One key aspect of the program's design is the utilization of classes to represent different commands and options. Each class encapsulates the necessary data and functionality for a specific task, enabling easier code management and modifications. This design approach facilitates the addition of new commands and options without requiring extensive changes to the existing code.

By applying OOP principles, the program achieves modularity, enhanced comprehensibility, maintainability, and adaptability to evolving requirements or functionalities.

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

