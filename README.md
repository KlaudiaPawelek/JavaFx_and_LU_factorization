# JavaFx and LU factorization

## MSc: Computational & Software Techniques in Engineering Software Engineering for Technical Computing option. Java Programming Assignment 2018/19</b>

The assignment consists of writing a Java application to enter and then display the solution to a system of linear equations.
The application provides the following functionality
1) Allow input of the matrix system and right hand side vector in two side-by-side scrollable areas in the top part of the application
2) Provide three buttons in the middle section of the application with the following functionality:
* LU Pivot computes the LU factorization of the matrix with pivoting
* Inverse compute the inverse of the matrix system
* Clear clears the output display area in the bottom part of the application
3) Display the results of the button presses in 2) in a scrollable area in the bottom part of the application as follows:
* LU Pivot the original matrix and vector and the lower and upper matrices, solution vector and determinant are displayed
* Inverse: the original matrix, lower, upper, inverse matrix and determinant are displayed
4) Add two more buttons named Load and Save. 
* The Save button shouldsave the result of a successful computation to persistent storage. 
* The Load button should allow the user to load a previously saved computation
5) If the matrix of data and/or right hand side vector entered in the top part of the application is not correct in some way (i.e not enough data, size of matrix and rhs don’t match, data has not real numbers), then the application should simply display a message
6) If there is no LU decomposition and matrix is singular then the result of pressing either LU Pivot or Inverse should be the display of the original matrix and vector followed by the text
"No LU decomposition singular matrix"

![Main window](https://github.com/KlaudiaPawelek/JavaFx_and_LU_factorization/blob/master/Project/main_window.png)
