- Name: Martin Xu
- Student ID: S2786186
- Tutorial group: 07A
- Tutor: Shlok Gupta
- Date: 2026-03-30


# Superstitious-hotel-elevator #

# Target audience #

People who has done a small amount of programming.

# Prerequisite knowledge #

Understands basic programming concepts such as loops, conditions and arrays.

# Learning outcomes #
Be able to construct a simple program using for loops, while loops, if statements and arrays to solve the code-golf challenge: Superstitious hotel elevator 


# 1.Introduction #
Have you ever stepped into an elevator and noticed something strange? Floor 4 is missing. Or floor 13. Sometimes both are gone entirely.

For example, you might see the buttons layout like this below:
<pre>
15  16
11  12
9   10
7   8
5   6
2   3
-1  1
</pre>
In countries like China, Japan, and South Korea, elevators often skip floor 4 entirely. The word for "four" sounds almost identical to the word for "death" in these languages, making the number deeply unlucky. In Western countries, the 13th floor is commonly omitted due to triskaidekaphobia which is the fear of the number 13.

When these numbers are removed, the floors above can't simply be renumbered. That would cause a lot of problems! Instead, the physical elevator buttons must show the actual floor numbers as they appear in the building by skipping the unlucky numbers entirely.

## Your Task ##

You will need to design an algorithm to be able to generate and print all the valid floor button numbers given an input n.

The floor numbers should respect both traditions:

-No floor 13 (Western superstition)

-No floor containing the digit 4 (East Asian tradition)

The hotel has n floors (where n is an even number). You must generate the sequence of valid floor numbers starting with -1 (basement) and continuing upward, skipping any number that is 13 or contains the digit 4. Then print the buttons in two neat columns so that reading from the bottom row to the top row gives the floors in ascending order.

This problem combines programming concepts you already know: loops, arrays, selection, and input validation with a real life problem.

Does everything above sound overwhelming at first?

If it does, don't panic! This worksheet will guide you through this problem step by step in detail.

By the end, you will be more than comfortable with this problem.

Let's jump right in!

# 2.Problem abstraction #
Before we start building the algorithm, we need to understand exactly what the problem is asking. Let's start by breaking down the problem into smaller subproblems.
## What we know ##
Input: A single positive even integer n (the number of floors)

Output: A list of n numbers printed in two columns with a tab between them, arranged in a specific order

## The Rules for Valid Numbers ##

We need exactly n numbers in total, following these rules:

-Start with -1 - this is always the first button

-Skip 13 – floor 13 is omitted entirely

-Skip any number containing the digit 4 – including 4, 14, 24, 40, 41, 42, and so on

-Take the next valid numbers – after -1, we take the next valid positive integers in order (1, 2, 3, 5, 6, ...)

## The Layout Format ##
The numbers aren't printed in ascending order. Instead, they're arranged in two columns so that reading from bottom to top, left to right gives the sequence in ascending order.

Let's see an example given in the challenge, where n = 14:
<pre>
15  16
11  12
9   10
7   8
5   6
2   3
-1  1
</pre>
If we read this bottom row first (-1, 1), then the next row up (2, 3), then the next (5, 6), and so on to the top row (15, 16), we get the original sequence:

-1, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16

Notice how 4, 13, and 14 are all missing – following the rules for valid numbers.

## What you need to do ##
Here are the steps you would need to take to build this algorithm:

1.Validate that the user enters a positive even integer

2.Generate the valid floor numbers following the skip rules

3.Store them in an array in ascending order

4.Print them in the correct two-column format

Let's start with the first one: making sure the input is valid.


# 3.Error handling #
Before we can generate any floor numbers, we need to make sure the user gives us valid input. What happens if someone types "hello" or "-6" or "7"? Our program would crash or produce incorrect output. We need to make sure our program handles the invalid inputs gracefully.

## What inputs are valid? ##
For this problem, valid input must satisfy three conditions:

1.It must be an integer – not a word, decimal, or other random symbols

2.It must be positive – greater than 0 (we can't have negative number of floors!)

3.It must be even – so that the numbers can be arranged in perfect pairs across two columns

If any of these conditions fail, we should display a helpful error message and ask the user to try again. We'll keep asking until we get valid input.

## The validation logic ##

Let's break down what our validation function needs to do:
<pre>
FUNCTION isValidInput(input):
    TRY:
        inputNum := CONVERT input TO INTEGER
    CATCH NumberFormatException:
        OUTPUT "Input needs to be an Integer, please try again."
        RETURN false
    END TRY
    
    IF inputNum <= 0:
        OUTPUT "Input needs to be a positive integer, please try again."
        RETURN false
    END IF
    
    IF inputNum % COLUMNS != 0:
        OUTPUT "Input needs to be even, please try again."
        RETURN false
    END IF
    
    RETURN true
END FUNCTION
</pre>

## What does this do? ##
Let's walk through this step by step:

Step1- Check whether the input is an integer

The TRY block attempts to convert the input string into a number. If the input contains letters (like "abc") or symbols (like "3.5"), the conversion fails and we jump to the CATCH block, which prints an error and returns false.

Step2- Check whether the input is positive

Once we know it's a number, we check if it's greater than 0. Zero or negative numbers don't make sense for floors, so we reject them.

Step3 - Check whether the input is even

Using the modulus operator (%), we check if the number divides evenly by 2. 
<pre>inputNum % COLUMNS</pre> 
gives the remainder after division. If the remainder is 0, it's even; otherwise, it's odd.

Step4 - Return

If all checks pass, we return true to indicate the input is valid.

## The main program loop ##
Now we need to use this validation function in our main program:
<pre>
MAIN:
    CREATE scanner TO READ INPUT
    
    userInput := scanner.nextLine()
    
    WHILE NOT isValidInput(userInput):
        userInput := scanner.nextLine()
    END WHILE
    
    floorCount := CONVERT userInput TO INTEGER
    
    CALL printButtonLayout(floorCount)
    
    CLOSE scanner
END MAIN
</pre>
The WHILE loop keeps asking for input until the user provides something valid. Note how we don't need to check for positivity or evenness again as the validation function handles all of that for us.

Examples of inputs

|     Input     |    	Valid?    | 	Why?                 |
|:-------------:|:-------------:|:----------------------|
|     "14"      |     	Yes	     | Positive even integer |
|      "0"      |      No	      | Not positive          |
|     "7"	      |      No	      | Not even              |
|    "abc"	     |      No	      | Not an integer        |
|     "-2"	     |      No	      | Not positive          |
|    "4.5"	     |      No	      | Not an integer        |

Once we have valid input, we can to move on to generating the actual floor numbers.


# 4.Generating valid floor button numbers #
Now that we have a valid input n, we need to generate exactly n valid floor numbers. 

We follow the rules from above: start with -1, then take the next positive integers that aren't 13 and don't contain the digit 4.

## Abstract ## 
We don't know in advance how many numbers we'll need to check. Some numbers (like 4, 13, 14, 24) will be skipped, so we need to keep checking candidates until we've collected enough valid numbers to fill our array.

## Data structure used ##
We'll use an array to store our valid numbers in ascending order. The array size will be exactly n (the number of floors):

<pre>
CREATE array validNumbers OF SIZE floorCount
</pre>

## The algorithm ##
We need two variables to track our progress:

- currentIndex – points to the next empty position in the array (starts at 1 because index 0 already holds -1)

- candidate – the next number we're testing (starts at 1)

The loop continues until the array is completely filled:

<pre>
validNumbers[0] := -1

currentIndex := 1
candidate := 1

WHILE currentIndex < floorCount:
    isSkipped := (candidate == SKIPPED_NUMBER)
    containsForbiddenDigit := (CONVERT candidate TO STRING).contains(FORBIDDEN_DIGIT)
    
    IF NOT isSkipped AND NOT containsForbiddenDigit:
        validNumbers[currentIndex] := candidate
        currentIndex := currentIndex + 1
    END IF
    
    candidate := candidate + 1
END WHILE
</pre>

## What does it do? ##
We fill an array with valid floor numbers. Start with -1 at index 0, then test numbers from 1 upward.

For each candidate number:

- Convert it to a string (e.g., 14 to "14") and check if it contains "4"

- Also check if it equals 13

If neither is true, the number is valid. Add it to the array and move to the next position.

If either is true, skip it (add nothing, position stays same).

Continue until the array has n numbers. The array is now filled in ascending order.

Converting to string: This is how we detect the digit 4. Numbers like 40, 41, 144 all become strings, making it easy to check if "4" appears anywhere inside them.

## Step by step walk through ##
Let's trace through the loop with the case of 

<pre>
floorCount = 14
</pre>


| Iteration | candidate | isSkipped? | contains4? | Action |  currentIndex after  |
|-----------|-----------|------------|------------|--------|:--------------------:|
| Start | - | - | - | Set validNumbers[0] = -1 |          1           |
| 1 | 1 | false | false | ADD to index 1 |          2           |
| 2 | 2 | false | false | ADD to index 2 |          3           |
| 3 | 3 | false | false | ADD to index 3 |          4           |
| 4 | 4 | false | true | SKIP |          4           |
| 5 | 5 | false | false | ADD to index 4 |          5           |
| 6 | 6 | false | false | ADD to index 5 |          6           |
| 7 | 7 | false | false | ADD to index 6 |          7           |
| 8 | 8 | false | false | ADD to index 7 |          8           |
| 9 | 9 | false | false | ADD to index 8 |          9           |
| 10 | 10 | false | false | ADD to index 9 |          10          |
| 11 | 11 | false | false | ADD to index 10 |          11          |
| 12 | 12 | false | false | ADD to index 11 |          12          |
| 13 | 13 | true | false | SKIP |          12          |
| 14 | 14 | false | true | SKIP |          12          |
| 15 | 15 | false | false | ADD to index 12 |          13          |
| 16 | 16 | false | false | ADD to index 13 |          14          |

And here is a trace of what's happening to our array validNumbers as the loop iterates:

| Iteration | candidate | validNumbers state                               |
|-----------|-----------|--------------------------------------------------|
| Start | - | [-1, _, _, _, _, _, _, _, _, _, _, _, _, _]      |
| 1 | 1 | [-1, 1, _, _, _, _, _, _, _, _, _, _, _, _]      |
| 2 | 2 | [-1, 1, 2, _, _, _, _, _, _, _, _, _, _, _]      |
| 3 | 3 | [-1, 1, 2, 3, _, _, _, _, _, _, _, _, _, _]      |
| 4 | 4 | [-1, 1, 2, 3, _, _, _, _, _, _, _, _, _, _]      |
| 5 | 5 | [-1, 1, 2, 3, 5, _, _, _, _, _, _, _, _, _]      |
| 6 | 6 | [-1, 1, 2, 3, 5, 6, _, _, _, _, _, _, _, _]      |
| 7 | 7 | [-1, 1, 2, 3, 5, 6, 7, _, _, _, _, _, _, _]      |
| 8 | 8 | [-1, 1, 2, 3, 5, 6, 7, 8, _, _, _, _, _, _]      |
| 9 | 9 | [-1, 1, 2, 3, 5, 6, 7, 8, 9, _, _, _, _, _]      |
| 10 | 10 | [-1, 1, 2, 3, 5, 6, 7, 8, 9, 10, _, _, _, _]     |
| 11 | 11 | [-1, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, _, _, _]    |
| 12 | 12 | [-1, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, _, _]   |
| 13 | 13 | [-1, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, _, _]   |
| 14 | 14 | [-1, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, _, _]   |
| 15 | 15 | [-1, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 15, _]  |
| 16 | 16 | [-1, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16] |

The loop stops when currentIndex = 14 because currentIndex < floorCount (14 < 14) is now false.

## The final array ##
After the loop completes, validNumbers contains:

<pre>
Index:    0     1     2     3     4     5     6     7     8     9    10    11    12    13
Value: │ -1  │  1  │  2  │  3  │  5  │  6  │  7  │  8  │  9  │ 10  │ 11  │ 12  │ 15  │ 16  │
</pre>

Notice how 4, 13, and 14 are missing, this is exactly as the rules require. The array contains exactly 14 numbers in ascending order.

## Key things to note ##
- The array is filled in ascending order 

- currentIndex only increases when we add a valid number, skipped numbers like 4, 13 don't change it because they don't get filled in 

- candidate increases every iteration as we want to fill in ascending order

- The loop condition checks if the array is full which is irrelevant to the number of candidates checked

Now that we have our valid numbers stored in order, we're ready to print them in two columns in order, as the problem requested.


# 5.Printing out in format #
We now have an array filled with valid floor numbers in ascending order. For n = 14, our array looks like this:
<pre>
Index:    0     1     2     3     4     5     6     7     8     9    10    11    12    13
Value: │ -1  │  1  │  2  │  3  │  5  │  6  │  7  │  8  │  9  │ 10  │ 11  │ 12  │ 15  │ 16  │
</pre>

However, we will need to print the numbers in the format below:
<pre>
15  16
11  12
9   10
7   8
5   6
2   3
-1  1
</pre>
Note that the last two numbers in the array (15 and 16) appear on the first line of output. The first two numbers (-1 and 1) appear on the last line. This means we need to print the array backwards.

## Printing pattern ##
The array is printed in pairs, starting from the end and moving backwards to the beginning:

|    Print line    | Array indices | Values |
|:----------------:|---------------|--------|
|   Line 1 (top)   | 12 and 13 | 15 and 16 |
|      Line 2      | 10 and 11 | 11 and 12 |
|      Line 3      | 8 and 9 | 9 and 10 |
|      Line 4      | 6 and 7 | 7 and 8 |
|      Line 5      | 4 and 5 | 5 and 6 |
|      Line 6      | 2 and 3 | 2 and 3 |
| Line 7 (bottom)  | 0 and 1 | -1 and 1 |

## The print loop ##
We can achieve this with a FOR loop that starts at the last pair and works backwards:
<pre>
FOR i := floorCount - COLUMNS,DOWN TO 0, STEP = COLUMNS:
    OUTPUT validNumbers[i] + TAB + validNumbers[i + 1]
END FOR
</pre>

Let's break this down for floorCount = 14 and COLUMNS = 2:

- floorCount - COLUMNS = 12 – this is the index of the first number in the last pair

- We go DOWN TO 0 – stopping at the first pair

- We decrement each step by COLUMNS = -2 each time to move to the previous pair

<pre>
Iteration 1: i = 12 -> prints validNumbers[12] (15) + tab + validNumbers[13] (16)
Iteration 2: i = 10 -> prints validNumbers[10] (11) + tab + validNumbers[11] (12)
Iteration 3: i = 8 -> prints validNumbers[8] (9) + tab + validNumbers[9] (10)
Iteration 4: i = 6 -> prints validNumbers[6] (7) + tab + validNumbers[7] (8)
Iteration 5: i = 4 -> prints validNumbers[4] (5) + tab + validNumbers[5] (6)
Iteration 6: i = 2 -> prints validNumbers[2] (2) + tab + validNumbers[3] (3)
Iteration 7: i = 0 -> prints validNumbers[0] (-1) + tab + validNumbers[1] (1)
</pre>

giving the plain output as requested by the challenge:

<pre>
15  16
11  12
9   10
7   8
5   6
2   3
-1  1
</pre>

## Explanation ##
Why This Works

Reading the output from bottom to top (last line first) gives us:

-1, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16

Which is exactly the ascending order we stored in the array. The reverse printing creates the illusion that the buttons are arranged in a special order, but really we're just controlling how the array is printed.

Here is the complete algorithm in pseudocode for the function printButtonLayout, which both generates the valid floor button numbers and prints them out in format:

<pre>
FUNCTION printButtonLayout(floorCount):
    CREATE array validNumbers OF SIZE floorCount
    validNumbers[0] := -1
    
    currentIndex := 1
    candidate := 1
    
    WHILE currentIndex < floorCount:
        isSkipped := (candidate == SKIPPED_NUMBER)
        containsForbiddenDigit := (CONVERT candidate TO STRING).contains(FORBIDDEN_DIGIT)
        
        IF NOT isSkipped AND NOT containsForbiddenDigit:
            validNumbers[currentIndex] := candidate
            currentIndex := currentIndex + 1
        END IF
        
        candidate := candidate + 1
    END WHILE
    
    FOR i := floorCount - COLUMNS,DOWN TO 0, STEP = COLUMNS:
        OUTPUT validNumbers[i] + TAB + validNumbers[i + 1]
    END FOR
END FUNCTION
</pre>

And that marks the end of our algorithm for this problem.

It wasn't that bad, was it?

However, it is not over just yet, we still have to make sure our algorithm is correct by testing it.

# 6.Testing # 
Testing is a crucial part of programming. You need to verify that your code works correctly for different inputs, including edge cases. Let's walk through some test cases and what outputs you should get.

## Test Case 1: n = 2 (Smallest possible even number) ##
Input: 2

Expected Output:
<pre>
-1  1
</pre>
With only 2 buttons, we have just one row. The array fills with [-1, 1], and we print the only pair.

## Test Case 2: n = 4  ##
Input: 4

Expected Output:
<pre>
2   3
-1  1
</pre>
Valid numbers: -1, 1, 2, 3. Printed in reverse pairs: (2,3) then (-1,1).

## Test Case 3: n = 6  ##
Input: 6

Expected Output:
<pre>
5   6
2   3
-1  1
</pre>
Valid numbers: -1, 1, 2, 3, 5, 6. Note that 4 is skipped. Printed in reverse pairs: (5,6), (2,3), (-1,1).

## Test Case 4: n = 14 (given as an example in the challenge)  ##
Input: 14

Expected Output:
<pre>
15  16
11  12
9   10
7   8
5   6
2   3
-1  1
</pre>
Valid numbers: -1, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16. Numbers 4, 13, and 14 are skipped. Printed in reverse pairs of two.

## Test Case 5: n=100 (also given in the challenge) ##
Input: 100

Expected Output:
<pre>
120 121
118 119
116 117
113 115
111 112
109 110
107 108
105 106
102 103
100 101
98  99
96  97
93  95
91  92
89  90
87  88
85  86
82  83
80  81
78  79
76  77
73  75
71  72
69  70
67  68
65  66
62  63
60  61
58  59
56  57
53  55
51  52
39  50
37  38
35  36
32  33
30  31
28  29
26  27
23  25
21  22
19  20
17  18
15  16
11  12
9   10
7   8
5   6
2   3
-1  1
</pre>

## Test Case 6: Invalid inputs (Error handling) ##

| Input	 | Expected output                                           |
|:------:|:----------------------------------------------------------|
|  abc   | "Input needs to be an Integer, please try again."         |
|  3.6   | "Input needs to be an Integer, please try again."         |
|   -4   | "Input needs to be a positive integer, please try again." |
|   7	   | "Input needs to be even, please try again."               |
|   0	   | "Input needs to be a positive integer, please try again." |


After an invalid input, the program should prompt the user again and keep asking until a valid input is provided.

## Once you've passed all these tests, your elevator button layout program is complete! ##

## Diagram ##
![This is a diagram I have created helping you to visualise our algorithm. On the left side, there is a flow diagram displaying how numbers are checked and filled in our array if valid. On the right, there is a diagram showing how we print the numbers from our array into the format requested.](design01.jpg)

# Algorithm pseudocode #
<pre>
CONSTANTS:
    SKIPPED_NUMBER := 13
    FORBIDDEN_DIGIT := "4"
    COLUMNS := 2

FUNCTION isValidInput(input):
    TRY:
        inputNum := CONVERT input TO INTEGER
    CATCH NumberFormatException:
        OUTPUT "Input needs to be an Integer, please try again."
        RETURN false
    END TRY
    
    IF inputNum <= 0:
        OUTPUT "Input needs to be a positive integer, please try again."
        RETURN false
    END IF
    
    IF inputNum % COLUMNS != 0:
        OUTPUT "Input needs to be even, please try again."
        RETURN false
    END IF
    
    RETURN true
END FUNCTION

FUNCTION printButtonLayout(floorCount):
    CREATE array validNumbers OF SIZE floorCount
    validNumbers[0] := -1
    
    currentIndex := 1
    candidate := 1
    
    WHILE currentIndex < floorCount:
        isSkipped := (candidate == SKIPPED_NUMBER)
        containsForbiddenDigit := (CONVERT candidate TO STRING).contains(FORBIDDEN_DIGIT)
        
        IF NOT isSkipped AND NOT containsForbiddenDigit:
            validNumbers[currentIndex] := candidate
            currentIndex := currentIndex + 1
        END IF
        
        candidate := candidate + 1
    END WHILE
    
    FOR i := floorCount - COLUMNS,DOWN TO 0, STEP = COLUMNS:
        OUTPUT validNumbers[i] + TAB + validNumbers[i + 1]
    END FOR
END FUNCTION

MAIN:
    CREATE scanner TO READ INPUT
    
    userInput := scanner.nextLine()
    
    WHILE NOT isValidInput(userInput):
        userInput := scanner.nextLine()
    END WHILE
    
    floorCount := CONVERT userInput TO INTEGER
    
    CALL printButtonLayout(floorCount)
    
    CLOSE scanner
END MAIN
</pre>

# Original challenge question from CodeGolf #

[Short link to CodeGolf challenge](https://codegolf.stackexchange.com/q/68866 "tooltip text")

Here's a very superstitious hotel elevator in Shanghai:

An elevator's button panel, missing the number 13.

It avoids the number 13, because thirteen is unlucky in the Western world, and it avoids the digit 4, because four is unlucky in parts of Asia. What if this hotel was taller?

Read a positive even integer n from STDIN, representing the number of floors, and print what the button layout would look like to STDOUT: -1, followed by the next n-1 positive integers that aren't equal to 13 and don't contain digit 4. Arrange these numbers in two columns such as in the above image: print two floor numbers per line, separated by a horizontal tab, so that reading the lines in reverse order from left-to-right yields the sequence in ascending order. (You may optionally print a trailing newline character, too.)

Test cases
For the input 14, output should be as in the above image:

15  16<br>
11  12<br>
9   10<br>
7   8<br>
5   6<br>
2   3<br>
-1  1<br>
where the whitespace in each line is a single horizontal tab character.

For the input 2, you should print -1  1.

For the input 100, you should print:

120 121<br>
118 119<br>
116 117<br>
113 115<br>
111 112<br>
109 110<br>
107 108<br>
105 106<br>
102 103<br>
100 101<br>
98  99<br>
96  97<br>
93  95<br>
91  92<br>
89  90<br>
87  88<br>
85  86<br>
82  83<br>
80  81<br>
78  79<br>
76  77<br>
73  75<br>
71  72<br>
69  70<br>
67  68<br>
65  66<br>
62  63<br>
60  61<br>
58  59<br>
56  57<br>
53  55<br>
51  52<br>
39  50<br>
37  38<br>
35  36<br>
32  33<br>
30  31<br>
28  29<br>
26  27<br>
23  25<br>
21  22<br>
19  20<br>
17  18<br>
15  16<br>
11  12<br>
9   10<br>
7   8<br>
5   6<br>
2   3<br>
-1  1<br>


<STYLE>
* { /* Don't leave any empty lines or IntelliJ might not render correctly */
  /* Text size */
  font-size:   1.1rem;
  /*font-size:   1.2rem;*/
  /* Zenburn dark theme */
  background-color: #3D3D3D;
  color:            #ebebe6;
  /* One Dark theme */
  /*background-color: #282C34;
  color:            #ABB2BF;*/
  /* white-ish on dull blue-ish */
  /*background-color: DarkSlateGray;
    color:            AntiqueWhite;*/
  /* white on black */
  /*background-color: black;
  color: white;*/
  /* black on white */
  /*background-color: white;
  color: black;*/
  /* nearly black on bright yellow */
  /*background-color: #FFFFAA;
  color:            #080808;*/
  /* black on bright blue */  
  /*background-color: #99CCFF;
  color:            black;*/
}
body {
  /* width of the text column */
  width: 80%;
  /* line spacing */
  line-height: 180%;
  /*line-height: 200%;*/
  /* Font styles: */
  /* Default sans serif */
  /*font-family: sans-serif;*/
  /* Default serif */
  font-family: serif;
  /* Specific font with generic fall-back */
  /* font-family: "Calibri Light", sans-serif; */
  /*font-family: "OpenDyslexic", sans-serif;*/
}
pre,
code,
pre code {
  /* line spacing */
  line-height: 150%;
  /* Default monospace */
  font-family: monospace;
  /* Specific fixed-width font with generic fall-back */
  /*font-family: "Consolas", monospace;*/
  /*font-family: "OpenDyslexicMono", monospace;*/
}
ol,
ol ol,
ol ol ol { /* Nested lists all use decimal numbering */
  list-style-type: decimal;
}
em {
  /* if you want underlining instead of italics */
  /*font-style: normal;
  border-bottom-style: solid;
  border-bottom-width: 1px;
  padding-bottom:      2px;*/
  text-decoration-skip-ink: auto;
}
h2 { /* Put a horizontal line above major headings to assist screen viewing */
  border-top:  1px solid #D5DAD5;
  margin-top:  80px;
  padding-top: 20px;
  }
</STYLE>