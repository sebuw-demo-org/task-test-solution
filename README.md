#### 💯Points: ![Points bar](../../blob/badges/.github/badges/points-bar.svg)

#### 📝 [Report](../../blob/badges/report.md)
---


# Alloy Signature Checker

This project and worksheet is part of the lecture Formal Methods for Software Engineering. We are using the light-weight formal methods tool Alloy Analyzer.

Usually we use formal methods to analyze software designs by translating analysis problems to formal methods tools. Now we will treat the quality of formal methods specifications as a software analysis problem. In this task we will implement a tool that analyzes properties of Alloy models (using Alloy itself).

## Project Setup

[📼 see the project setup video](https://www.youtube.com/watch?v=xt-K4CnWQio&list=PLGyeoukah9NYNMJhcHXLjAGN294O2uXCB&index=18)

1. Install OpenJDK/JDK 
2. Clone your repository (either created from this template or the classroom)
3. Open in any IDE of your choice (e.g. Eclipse, VS Code, etc.)
4. Run and inspect `src/main/java/de/buw/fm4se/ExampleUsingTheCompiler.java`
5. Run and inspect `src/main/java/de/buw/fm4se/ExampleUsingTheAPI.java`

## Task 1

Implement a the body of method [``findDeadSignatures``](src\main\java\de\buw\fm4se\AlloyChecker.java#L14) in class `AlloyChecker` to determine a list of dead signatures of an Alloy model, i.e., list all signatures that do not have atoms in any instance.

* Use the first command in the Alloy file. To see how to parse Alloy models and how to access commands see, e.g., lines 57 and 65 in class `ExampleUsingTheCompiler`.
* You may update the predicate a command `cmd` checks to expression `e` by using the returned `Command` of `cmd.change(e)`.
* To see how you can create formulas from signatures and other formulas see, e.g., [line 90 in class `ExampleUsingTheAPI`](src\main\java\de\buw\fm4se\ExampleUsingTheAPI.java#L90).

## Task 2

Implement a method [`findCoreSignatures`](src\main\java\de\buw\fm4se\AlloyChecker.java#L19) in class `AlloyChecker` to determine a list of core signatures of an Alloy model, i.e., list all signatures that always have atoms except in the empty instance.

* Again, use the first command in the Alloy file.

## Task 3

Implement a method [`findMinScope`](src\main\java\de\buw\fm4se\AlloyChecker.java#L34) in class `AlloyChecker` to determine the minimum scope for each signature in an Alloy model, i.e., map each signature name to an integer scope for which the model is still satisfiable.

* Again, use the first command in the Alloy file.
* You may update the scope of a signature `sig` in a command `cmd` to integer `i` by using the returned `Command` of `cmd.change(sig, false, i)`.
* Computing a maximal scope is a bit tricky and done for you in method `getMaxScope`.

[📼 see the code walk-through and explanation of tasks 1-3](https://www.youtube.com/watch?v=INf1vaRb_a4&list=PLGyeoukah9NYNMJhcHXLjAGN294O2uXCB&index=19)
