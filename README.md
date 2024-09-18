CSC 207 - 02 (Fall)
Mini-Project 2: Fun with Fractions

Name: Maral Bat-Erdene
Date: 09/14/2024

Project Description:
This project takes inputs from the command-line for encrypting and decrypting messages using encryption methods Caesar and Vigenre.

What It Does:
 Caesar Cipher: Shifts each letter in the message by a fixed number of positions, called a key, in the alphabet.
 Vigenere Cipher: Uses a keyword to shift letters by a fixed number of positions. Each letter in the message is shifted based on the corresponding letter in the keyword.
How Do You Use It:
 You run the program from the command line, providing parameters for what action to take (encode or decode), which cipher to use, the message, and a key.
    Example command: java edu.grinnell.csc207.main.Cipher -encode -caesar helloworld a
    This command would encode the message "helloworld" using the Caesar cipher with a shift of 'a'.

 Verification: The program checks that all inputs follow the rules: 1) Ensures the message and key contain only lowercase letters. 2) Verify that the Caesar cipher key is a single character.

 Output: Based on your input, the program will print the encoded or decoded message or error message when input didn't follow the rules.

Resources:
StackExchange: I used it to learn how to unzip the cipher.zip file. The command unzip "file.zip -d destination_folder" was particularly helpful.
Geeksforgeeks: I used it to learn how to work with exception handling, especially try and catch in  Java. 

Link to GitHub: https://github.com/1maral/mini-project-1.git

