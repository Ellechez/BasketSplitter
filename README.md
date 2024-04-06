Basket Splitter Application
============
<img alt="Img" src="https://github.com/Ellechez/BasketSplitter/blob/main/BasketSplitter.png"/>

This is a Basket Splitter Application that was created for the recruitment process.

---

## Table of Contents
* [Prerequisites for creating a project](#prerequisites)
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)

* [Setup](#setup)
* [Usage](#usage)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)

---

## Prerequisites for creating a project
Client wants to expand the product range in his online supermarket with non-food items such as electronics and home items.
Those items are characterized by the fact that they quite often cannot be delivered with Client's standard delivery car because those items are often simply too big. The second reason is that sometimes Client also offer items that are provieded by external suppliers, so Client cannot deliver them with its fleet. In both such cases, order must be sent by a specialized courier. On the other hand, couriers cannot deliver all the items Client sell, in particular  food products.
Due to the fact that not all products can be sent using all methods
deliveries, Client need to divide the items in the customer's online shopping cart into delivery groups.

## General Information
- The task is to optimally divide the items in the basket to provide minimization of delivers required.
- In order to function properly, the library load a configuration file that contains the possible delivery methods for all products offered in the store. Since this configuration does not change often,  Client store it in a file that our library reads.
- As an expecting result, the algorithm returns the division of products into delivery groups as a map. Its key is the method of delivery and its value is list of products. The algorithm divided products into the minimum possible number of delivery groups (the largest group contained as many products as possible).

## Technologies Used
- Java - version 20.0.2
- Maven - version 4.0.0
- Jackson - version 2.16.0
- JUnit - version 3.8.1

## Features

- `JsonReader.class` reads json configuration file and its method returns `List<Product> listOfProducts`
- `BasketSplitter.class` has all necessary logic to properly split customers shopping cart
- `BasketSplitterTest.class` covers with test all key fragments of the application

## Setup
File with configurations located in: `...\basket-splitter\src\main\resources\config.json`
Files with example baskets located in: `...\basket-splitter\src\test\resources\basket-1.json` and `...\basket-splitter\src\test\resources\basket-2.json`
All necessary dependencies were added to the POM file.

## Usage
`BasketSplitter.split()` method accepts AbsolutePath to the configuration file and returns Map<String, List<String>> where key is Delivery Method and value is List of products.
- For basket in format:
`[Cocoa Butter, Tart - Raisin And Pecan, Table Cloth 54x72 White, Flower - Daisies, Fond - Chocolate, Cookies - Englishbay Wht]`
- Output will be:
`{Courier=[Cocoa Butter, Tart - Raisin And Pecan, Table Cloth 54x72 White, Flower - Daisies, Cookies - Englishbay Wht], Mailbox delivery=[Fond - Chocolate]}`


## Project Status
Project is: _complete_


## Room for Improvement

Room for improvement:
- `FileReader` could be realize with another implementations, like for example "YAMLReader" or "XMLReader" if Client in future will decide to change format of the configuration file.
- Basket of the Customer could be created with UI implementation, like ClientView where Client can provide his credentials and log in PrivateCabinet. Then provide products he wants to buy.


