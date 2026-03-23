- Name: Martin Xu
- Student ID: S2786186
- Tutorial group: 07A
- Tutor: Shlok Gupta
- Date: 2026-03-23


# Superstitious-hotel-elevator #

# Target audience #

People who has done a small amount of programming and can understand basic java.

# Prerequisite knowledge #

Understands basic java syntax of if-statements, for loops, while loops.

# Learning outcomes #
- Understand the syntax of basic java arrays<br>
- be able to use arrays to store numbers<br>
- be able to construct a simple program using for loops, while loops, if statements and arrays to solve the challenge 


# 1.Introduction #

# 2.Problem abstraction #


# Testing # 
Now let's test our program against some test cases to see if it works as intended:<br>
Test1<br>
input 
<pre>2</pre>
expected output:
<pre>-1	1</pre>
Test2<br>
input
<pre>14</pre>
expected output:
<pre>
15	16
11	12
9	10
7	8
5	6
2	3
-1	1
</pre>
Test3<br>
input
<pre>36</pre>
expected output:
<pre>
39	50
37	38
35	36
32	33
30	31
28	29
26	27
23	25
21	22
19	20
17	18
15	16
11	12
9	10
7	8
5	6
2	3
-1	1
</pre>
Test4<br>
input
<pre>100</pre>
expected output:
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


![my diagram](design01.png)


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
    
    FOR i := floorCount - COLUMNS DOWN TO 0 STEP -COLUMNS:
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