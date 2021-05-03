# Pre-Interview Exercise

## Overview
Implementation of a shopping cart in Java.

## Requirement
The goods that can be purchased, together with their normal prices are to be stored in a
product catalogue and are currently:

- Jacket – £49.90
- Trousers – £35.50
- Shirt – £12.50
- Tie – £9.50

Current special offers:
   -- Trousers have a 10% discount off their normal price this week
   -- Buy 2 shirts and get a tie for half price

## Prerequisites
- Java 8
- maven 3

## Build
`mvn clean install`

## Test
`mvn clean test`

## BDD Test (Cucumber)
`mvn clean verify -Dit.test=ICartRunner -Dcucumber.options=" --format html:report/cucumber-html-report-myReport"`
