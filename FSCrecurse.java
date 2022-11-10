// brandon wilson// program 3 - fsc telecom - linked lists
// Brandon Wilson
// Dr. Cazalas 3280
// 10-20-22
// brandonewilson2002@gmail.com
// 1260637
import java.util.*;
public class FSCrecurse {
    
    // changing the arrays to either 0's or 1's depending on size of array
    public static void changeArray(char [][] n, int indicator, int rs, int re, int cs, int ce){
        // if indicator is 0 that means white
        // if indicator is 1 that means black
        // loop from row start to row end
        for (int i = rs; i < re; i++) {
            // loop from column start to column end
            for (int j = cs; j < ce; j++) {
                // when it runs the whole thing will b white
                if(indicator == 0){
                    n[i][j] = '0';
                }else{ // indicator == 1
                    // when it runs the whole thing will be black
                    n[i][j] = '1';
                }
            }
        }
    }
    
    // helper method to make the array
    public static void makeArray(char [][] n, Scanner in){
        // loop over the size of n array
        for (int i = 0; i < n.length; i++) {
            // read new lines of input and saves it into the array
            char[] tempInfo = in.next().toCharArray();
            // loop over the size of n array
            for (int j = 0; j < n.length; j++) {
                // saves the tempinfo into the array
                n[i][j] = tempInfo[j];
            }
        }
    }
    
    // helper method to coount num of whites and black
    public static int countWhite(char [][] n,int rs, int re, int cs, int ce){
        // white is 0
        int white = 0;
        // loop from row start to row end
        for (int i = rs; i < re; i++) {
            // loop from column start to column end
            for (int j = cs; j < ce; j++) {
                // if array is equal to 0 (white)
                if(n[i][j] == '0'){
                    white += 1;
                }
            }
        }
        //System.out.println("count of white");
        //System.out.println(white);
        return white;
    }
    
    // helper method to coount num of whites and black
    public static int countBlack(char [][] n,int rs, int re, int cs, int ce){
        // black is 1
        int black = 0;
        // loop from row start to row end
        for (int i = rs; i < re; i++) {
            // loop from column start to column end
            for (int j = cs; j < ce; j++) {
                // if array is equal to 1 (black)
                if(n[i][j] == '1'){
                    black += 1;
                }
            }
        }
        //System.out.println("count of black");
        //System.out.println(black);
        // return count of black
        return black;
    }
    
    // method for compress
    public static void FSCcompress(char [][] compressArray, int percent, int rowStart, int rowEnd, int columnStart, int columnEnd){
        // count tiles
        int white = countWhite(compressArray, rowStart, rowEnd, columnStart, columnEnd);
        int black = countBlack(compressArray, rowStart, rowEnd, columnStart, columnEnd);
        
        // double totalBW = countBlack(compressArray, rowStart, rowEnd, columnStart, columnEnd) + countWhite(compressArray, rowStart, rowEnd, columnStart, columnEnd);
        double totalBW = white + black;
       
        //System.out.println(totalBW);
        //System.out.println(percent);
        if(((black / totalBW) * 100) >= percent){
             changeArray(compressArray, 1, rowStart, rowEnd, columnStart, columnEnd);
             return;
        }
        else if(((white / totalBW) * 100) >= percent){
            changeArray(compressArray, 0, rowStart, rowEnd, columnStart, columnEnd);
            return;
        }
        else{ // if it is neither of the base cases
            int stepRow = (rowEnd - rowStart) / 2;
            int stepColumn = (columnEnd - columnStart) / 2;
            // making the quadrant for top left by making the array smaller by 2 each way
            //char[][] topLeft = new char[compressArray.length / 2][compressArray.length / 2];
            // save the old array into the new one
            //makeQuadrant(compressArray, topLeft);
            // recurse and check again for top left
            FSCcompress(compressArray,percent ,rowStart ,rowEnd - stepRow, columnStart ,columnEnd - stepColumn);
             
            // making the quadrant for top right by making the array smaller by 2 each way
            //char[][] topRight = new char[compressArray.length - compressArray.length / 2][compressArray.length / 2];
            // save the old array into the new one
            //makeQuadrant(compressArray, topRight);
            // recurse and check again for top right
            FSCcompress(compressArray,percent ,rowStart, rowEnd-stepRow,columnEnd-stepColumn ,columnEnd);
            
            // making the quadrant for top left by making the array smaller by 2 each way
            //char[][] bottomRight = new char[compressArray.length - compressArray.length / 2][compressArray.length - compressArray.length / 2];
            // save the old array into the new one
            //makeQuadrant(compressArray, bottomRight);
            // recurse and check again for bottom right
            FSCcompress(compressArray, percent,rowEnd - stepRow, rowEnd, columnEnd-stepColumn, columnEnd);
            
            // making the quadrant for top left by making the array smaller by 2 each way
            //char[][] bottomLeft = new char[compressArray.length / 2][compressArray.length - compressArray.length / 2];
            // save the old array into the new one
            //makeQuadrant(compressArray, bottomLeft);
            // recurse and check again for bottom left
            FSCcompress(compressArray, percent ,rowEnd - stepRow, rowEnd ,columnStart, columnEnd - stepColumn );
        }
    }
    
    
    // helper method
    public static void printFlip(char [] result, int n) {
        // loop over size of n
        for (int i = 0; i < n; i++) {
            // print the cbaracter at that index
            System.out.print(result[i]);
        }
        // new line
        System.out.println("");
    }
    
    // method for flip
    public static void FSCflip(char [] result , int n, int control){
        // control is 0 on the first iterattion and if it equals n it prints
        if(control == n){
            // print the itteration of flip its on
            printFlip(result, n);
            // return and leave
            return;
        }
        else{
            // set result to the index of control with c
            result[control] = 'C';
            FSCflip(result, n, control + 1);
            // set result to the index of control with s
            result[control] = 'S';
            FSCflip(result, n, control + 1);
        }
    }
    
    // doesnt print the star out right
    public static String FSCshape(int base, int star){
        // string the for stars
        String s = "";
        // string for the spaces
        String space = "";
        // my base case that star is equal to 1
        // prints the last star
        if(star == 1){
            // loops over the base - 1 to add spaces
            for (int i = 0; i < base - 1; i++) {
                    space += " ";
                }
            // add star and a new line
            s += "* \n";
            // return spaces and star
            return space + s;
        }else{ // not the base case
            // print the first star
            if(star % 2 == 1 && base == 1){
                // x is the amount of exact spaces it needs to be
                int x = star - base;
                for (int i = 0; i < x ; i++) {
                    space += " ";
                }
                // return the space plus all the stars and recurses adding 2 to the base
                return space + "* \n" + FSCshape(base + 2,  star);
            }else{ // after the first star is printed
                // loop to get the middle amount of stars( first half)
                if(base < star){
                    // loop through base to add the amount of stars
                    for (int i = 0; i < base; i++) {
                        s += "* ";
                    }
                    // x is the amount of exact spaces it needs to be
                    int x = star - base;
                    for (int i = 0; i < x; i++) {
                        space += " ";
                    }
                    // returns the spaces plus the string of star and recurses while adding 2 to base
                    return space + s + "\n"+ FSCshape(base + 2, star);
                }
                // loop to get the middle amount of stars( second half)
                else if(base > star){
                    // loop through the amount of stars to add stars
                    for (int i = 0; i < star; i++) {
                        s += "* ";
                    }
                    // x is the amount of exact spaces it needs to be
                    int x = base - star;
                    for (int i = 0; i < x; i++) {
                        space += " ";
                    }
                    // returns the spaces plus the string of star and recurses while subtracting 2 to star
                    return space + s + "\n"+ FSCshape(base, star - 2);
                }
                 //base == star
                else{
                   // loop through amount of stars and adds it to s
                    for (int i = 0; i < star; i++) {
                        s += "* ";
                    } // go back down for the second half
                    // returns the middle line of stars and the usage of base and star switch here
                    return s + "\n" + FSCshape(base, star - 2);
                }
            }
            
        }
        
    }
    
    // fibonnaci for fsc math
    public static int fib(int value) {
        // wont work if the value is less than 2
        if(value <= 2 ){
            return 1;
        }
        // return the fib of the numbers
        return fib(value - 1) + fib(value - 2);
    }
    
    // basically fibonnacci
    public static int FSCmath(int start, int end){
        // the first user number is equal to the second one
        if(start == end){
            return fib(start);
        }else{ // if start is not equal to end
            // recurse
            return fib(start) + FSCmath(start+1, end);
        }
    }
    

    // main method
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // takes the first line of input
        int numOfCommands = in.nextInt();
        // number of recursion calls
        int recursionCalls = 0;
        // userinputed max amt of calls doesnt equal how many calls there are. if there the main terminates
        while(numOfCommands != recursionCalls){
             String input[] = in.nextLine().split(" ");
            // if the user wants to do mocs math 
            if(input[0].equals("MocsMath")){
                System.out.println("MocsMath:");
                // prints out the integer value
                System.out.println(FSCmath(Integer.parseInt(input[1]),Integer.parseInt(input[2])));
                // new line
                System.out.println("");
                // recursion counter plus 1
                recursionCalls += 1;
               // FSCmath(Integer.parseInt(input[1]),Integer.parseInt(input[2]))
            }// if the user wants to do mocs shape
            else if(input[0].equals("MocsShape")){
                // set the base to be 1 so i put more than one paramter in
                int base = 1;
                System.out.println("MocsShape:");
                // prints the specific diamond
                System.out.println(FSCshape(base, Integer.parseInt(input[1])));
                // new line
                System.out.println("");
                // recursion counter plus one
                recursionCalls += 1;
            }// if the user wants to do mocs flip
            else if(input[0].equals("MocsFlip")){
                System.out.println("MocsFlip:");
                // set the size of character array to what the user selected
                char[] letters = new char[Integer.parseInt(input[1])];
                // calls the flip method
                FSCflip(letters,Integer.parseInt(input[1]),0);
                // new line
                System.out.println("");
                // recursion counter plus one
                recursionCalls += 1;
            } // if the user wants to do 
            else if(input[0].equals("MocsCompress")){
                System.out.println("MocsCompress:");
                // new char array with the size of what the user wanted it to be
                char[][] theArray = new char[Integer.parseInt(input[1])][Integer.parseInt(input[1])];
                //makes the array
                makeArray(theArray, in);
                // calls the compress array
                FSCcompress(theArray,Integer.parseInt(input[2]),0,theArray.length,0,theArray.length);
                // loop over all the rows and columns
                for (int i = 0; i < theArray.length; i++) {
                    for (int j = 0; j < theArray.length; j++) {
                        // prints the row
                        System.out.print(theArray[i][j]);
                    }
                    // new line after every row is complete
                    System.out.println();
                }
                // reursion counter plus one
                recursionCalls += 1;
            } // i dont know if this is implemented right or not
        }
    }
}
