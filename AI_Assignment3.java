/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_assignment3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Khewbs
 */
public class AI_Assignment3 {
    static String goal = "123456789ABCDEF ";
    static String[] traversed = new String[10000];
    static int traversecount = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String start = "";
        String method= "";
        int function = 0;
        char[][] current;
        File file = new File("output.txt");
        if(args.length!=3){
            System.out.println("Invalid number of inputs");
            System.exit(0);
        }
        if(args[0].length()!=16){
            System.out.println("Invalid root node");
            System.exit(0);
        }
        else{
            start = args[0];
        }
        if(args[1].compareToIgnoreCase("AStar")==0 || args[1].compareToIgnoreCase("BFS")==0){
            method = args[1];
        }
        else{
            System.out.println("Invalid method");
            System.exit(0);
        }
        if(args[2].compareTo("1")==0 || args[2].compareTo("2")==0){
            function = Integer.parseInt(args[2]);
        }
        else{
            System.out.println("Invalid function. Either 1 or 2");
            System.exit(0);
        }
        
        current = StringtoMat(start);
        traversed[traversecount++] = start;
        FileWriter f = new FileWriter(file);
        int totalcost = 0;
        int depth = -1;
        int numcreate = 0;
        if(method.compareToIgnoreCase("BFS")==0){
            while(true){
                char[][] selected = current; int h = 1000;
                f.write("["+MattoString(current)+"],["+depth+"],["+numcreate+"],["+heuristic(function, current)+"],["+"1]\n");
                if(MattoString(current).compareTo(goal)==0){
                    f.write("["+MattoString(current)+"],["+numcreate+"],["+numcreate+"],["+heuristic(function, current)+"],["+"1] <---Goal");
                    break;
                }
                else{
                    int blank[] = FindBlank(current);
                    boolean flag = true;
                    if(blank[0]==0 && blank[1]==0){
                        char[][] temp;
                        temp = MoveBlank(blank, current, "right");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }    
                        temp = MoveBlank(blank, current, "down");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }
                        if(flag){
                            f.write("["+MattoString(current)+"],["+depth+"],["+"0"+"],["+heuristic(function, current)+"],["+"1] <---Last");
                        }
                        else{
                            current = selected;
                            numcreate++;
                            traversed[traversecount++] = MattoString(selected);
                        }
                    }
                    else if(blank[0]==3 && blank[1]==0){
                        char[][] temp;
                        temp = MoveBlank(blank, current, "right");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }    
                        temp = MoveBlank(blank, current, "up");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }
                        if(flag){
                            f.write("["+MattoString(current)+"],["+depth+"],["+"0"+"],["+heuristic(function, current)+"],["+"1] <---Last");
                        }
                        else{
                            current = selected;
                            numcreate++;
                            traversed[traversecount++] = MattoString(selected);
                        }
                    }
                    else if(blank[0]==0 && blank[1]==3){
                        char[][] temp;
                        temp = MoveBlank(blank, current, "down");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }    
                        temp = MoveBlank(blank, current, "left");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }
                        if(flag){
                            f.write("["+MattoString(current)+"],["+depth+"],["+"0"+"],["+heuristic(function, current)+"],["+"1] <---Last");
                        }
                        else{
                            current = selected;
                            numcreate++;
                            traversed[traversecount++] = MattoString(selected);
                        }
                    }
                    else if(blank[0]==3 && blank[1]==3){
                        char[][] temp;
                        temp = MoveBlank(blank, current, "left");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }    
                        temp = MoveBlank(blank, current, "up");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }
                        if(flag){
                            f.write("["+MattoString(current)+"],["+depth+"],["+"0"+"],["+heuristic(function, current)+"],["+"1] <---Last");
                        }
                        else{
                            current = selected;
                            numcreate++;
                            traversed[traversecount++] = MattoString(selected);
                        }
                    }
                    else if((blank[0]>0 && blank[0]<3) && (blank[1]==0)){
                        char[][] temp;
                        temp = MoveBlank(blank, current, "right");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }    
                        temp = MoveBlank(blank, current, "down");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }
                        temp = MoveBlank(blank, current, "up");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }
                        if(flag){
                            f.write("["+MattoString(current)+"],["+depth+"],["+"0"+"],["+heuristic(function, current)+"],["+"1] <---Last");
                        }
                        else{
                            current = selected;
                            numcreate++;
                            traversed[traversecount++] = MattoString(selected);
                        }
                    }
                    else if((blank[0]>0 && blank[0]<3) && (blank[1]==3)){
                        char[][] temp;
                        temp = MoveBlank(blank, current, "down");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }    
                        temp = MoveBlank(blank, current, "left");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }
                        temp = MoveBlank(blank, current, "up");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }
                        if(flag){
                            f.write("["+MattoString(current)+"],["+depth+"],["+"0"+"],["+heuristic(function, current)+"],["+"1] <---Last");
                        }
                        else{
                            current = selected;
                            numcreate++;
                            traversed[traversecount++] = MattoString(selected);
                        }
                    }
                    else if((blank[1]>0 && blank[1]<3) && (blank[0]==0)){
                        char[][] temp;
                        temp = MoveBlank(blank, current, "right");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }    
                        temp = MoveBlank(blank, current, "down");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }
                        temp = MoveBlank(blank, current, "left");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }
                        if(flag){
                            f.write("["+MattoString(current)+"],["+depth+"],["+"0"+"],["+heuristic(function, current)+"],["+"1] <---Last");
                        }
                        else{
                            current = selected;
                            numcreate++;
                            traversed[traversecount++] = MattoString(selected);
                        }
                    }
                    else if((blank[1]>0 && blank[1]<3) && (blank[0]==3)){
                        char[][] temp;
                        temp = MoveBlank(blank, current, "right");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }    
                        temp = MoveBlank(blank, current, "left");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }
                        temp = MoveBlank(blank, current, "up");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }
                        if(flag){
                            f.write("["+MattoString(current)+"],["+depth+"],["+"0"+"],["+heuristic(function, current)+"],["+"1] <---Last");
                        }
                        else{
                            current = selected;
                            numcreate++;
                            traversed[traversecount++] = MattoString(selected);
                        }
                    }
                    else{
                        char[][] temp;
                        temp = MoveBlank(blank, current, "right");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }    
                        temp = MoveBlank(blank, current, "down");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }
                        temp = MoveBlank(blank, current, "left");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }
                        temp = MoveBlank(blank, current, "up");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(heuristic(function, temp)<h){
                                selected = temp;
                                h = heuristic(function, selected);
                            }
                        }
                        if(flag){
                            f.write("["+MattoString(current)+"],["+depth+"],["+"0"+"],["+heuristic(function, current)+"],["+"1] <---Last");
                        }
                        else{
                            current = selected;
                            numcreate++;
                            traversed[traversecount++] = MattoString(selected);
                        }
                    }
                }
            }
        }
        else if(method.compareToIgnoreCase("AStar")==0){
            while(true){
                char[][] selected = current; totalcost = 1000;
                f.write("["+MattoString(current)+"],["+depth+"],["+numcreate+"],["+heuristic(function, current)+"],["+"1]\n");
                if(MattoString(current).compareTo(goal)==0){
                    f.write("["+MattoString(current)+"],["+numcreate+"],["+numcreate+"],["+heuristic(function, current)+"],["+"1] <---Goal");
                    break;
                }
                else{
                    int blank[] = FindBlank(current);
                    boolean flag = true;
                    if(blank[0]==0 && blank[1]==0){
                        char[][] temp;
                        temp = MoveBlank(blank, current, "right");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }    
                        temp = MoveBlank(blank, current, "down");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }
                        if(flag){
                            f.write("["+MattoString(current)+"],["+depth+"],["+"0"+"],["+heuristic(function, current)+"],["+"1] <---Last");
                        }
                        else{
                            totalcost = (numcreate+1)+heuristic(function, selected);
                            current = selected;
                            numcreate++;
                            traversed[traversecount++] = MattoString(selected);
                        }
                    }
                    else if(blank[0]==3 && blank[1]==0){
                        char[][] temp;
                        temp = MoveBlank(blank, current, "right");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }    
                        temp = MoveBlank(blank, current, "up");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }
                        if(flag){
                            f.write("["+MattoString(current)+"],["+depth+"],["+"0"+"],["+heuristic(function, current)+"],["+"1] <---Last");
                        }
                        else{
                            totalcost = (numcreate+1)+heuristic(function, selected);
                            current = selected;
                            numcreate++;
                            traversed[traversecount++] = MattoString(selected);
                        }
                    }
                    else if(blank[0]==0 && blank[1]==3){
                        char[][] temp;
                        temp = MoveBlank(blank, current, "down");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }    
                        temp = MoveBlank(blank, current, "left");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }
                        if(flag){
                            f.write("["+MattoString(current)+"],["+depth+"],["+"0"+"],["+heuristic(function, current)+"],["+"1] <---Last");
                        }
                        else{
                            totalcost = (numcreate+1)+heuristic(function, selected);
                            current = selected;
                            numcreate++;
                            traversed[traversecount++] = MattoString(selected);
                        }
                    }
                    else if(blank[0]==3 && blank[1]==3){
                        char[][] temp;
                        temp = MoveBlank(blank, current, "left");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }    
                        temp = MoveBlank(blank, current, "up");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }
                        if(flag){
                            f.write("["+MattoString(current)+"],["+depth+"],["+"0"+"],["+heuristic(function, current)+"],["+"1] <---Last");
                        }
                        else{
                            totalcost = (numcreate+1)+heuristic(function, selected);
                            current = selected;
                            numcreate++;
                            traversed[traversecount++] = MattoString(selected);
                        }
                    }
                    else if((blank[0]>0 && blank[0]<3) && (blank[1]==0)){
                        char[][] temp;
                        temp = MoveBlank(blank, current, "right");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }    
                        temp = MoveBlank(blank, current, "down");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }
                        temp = MoveBlank(blank, current, "up");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }
                        if(flag){
                            f.write("["+MattoString(current)+"],["+depth+"],["+"0"+"],["+heuristic(function, current)+"],["+"1] <---Last");
                        }
                        else{
                            totalcost = (numcreate+1)+heuristic(function, selected);
                            current = selected;
                            numcreate++;
                            traversed[traversecount++] = MattoString(selected);
                        }
                    }
                    else if((blank[0]>0 && blank[0]<3) && (blank[1]==3)){
                        char[][] temp;
                        temp = MoveBlank(blank, current, "down");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }    
                        temp = MoveBlank(blank, current, "left");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }
                        temp = MoveBlank(blank, current, "up");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }
                        if(flag){
                            f.write("["+MattoString(current)+"],["+depth+"],["+"0"+"],["+heuristic(function, current)+"],["+"1] <---Last");
                        }
                        else{
                            totalcost = (numcreate+1)+heuristic(function, selected);
                            current = selected;
                            numcreate++;
                            traversed[traversecount++] = MattoString(selected);
                        }
                    }
                    else if((blank[1]>0 && blank[1]<3) && (blank[0]==0)){
                        char[][] temp;
                        temp = MoveBlank(blank, current, "right");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }    
                        temp = MoveBlank(blank, current, "down");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }
                        temp = MoveBlank(blank, current, "left");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }
                        if(flag){
                            f.write("["+MattoString(current)+"],["+depth+"],["+"0"+"],["+heuristic(function, current)+"],["+"1] <---Last");
                        }
                        else{
                            totalcost = (numcreate+1)+heuristic(function, selected);
                            current = selected;
                            numcreate++;
                            traversed[traversecount++] = MattoString(selected);
                        }
                    }
                    else if((blank[1]>0 && blank[1]<3) && (blank[0]==3)){
                        char[][] temp;
                        temp = MoveBlank(blank, current, "right");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }    
                        temp = MoveBlank(blank, current, "left");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }
                        temp = MoveBlank(blank, current, "up");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }
                        if(flag){
                            f.write("["+MattoString(current)+"],["+depth+"],["+"0"+"],["+heuristic(function, current)+"],["+"1] <---Last");
                        }
                        else{
                            totalcost = (numcreate+1)+heuristic(function, selected);
                            current = selected;
                            numcreate++;
                            traversed[traversecount++] = MattoString(selected);
                        }
                    }
                    else{
                        char[][] temp;
                        temp = MoveBlank(blank, current, "right");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }    
                        temp = MoveBlank(blank, current, "down");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }
                        temp = MoveBlank(blank, current, "left");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }
                        temp = MoveBlank(blank, current, "up");
                        if(checkUnique(MattoString(temp))){
                            flag = false;
                            f.write("["+MattoString(temp)+"],["+depth+"],["+(numcreate+1)+"],["+heuristic(function, temp)+"],["+"0]\n");    
                            if(numcreate+1+heuristic(function, temp)<totalcost){
                                selected = temp;
                                totalcost = (numcreate+1)+heuristic(function, selected);
                            }
                        }
                        if(flag){
                            f.write("["+MattoString(current)+"],["+depth+"],["+"0"+"],["+heuristic(function, current)+"],["+"1] <---Last");
                        }
                        else{
                            totalcost = (numcreate+1)+heuristic(function, selected);
                            current = selected;
                            numcreate++;
                            traversed[traversecount++] = MattoString(selected);
                        }
                    }
                }
            }
        }
        f.close();
    }
    
    static char[][] MoveBlank(int[] blank, char[][] in, String direction){
        int i = blank[0]; int j = blank[1];
        char[][] arr = ArrayCopy(in);
        if(direction.compareTo("right")==0){
            char temp = arr[i][j+1];
            arr[i][j+1] = arr[i][j];
            arr[i][j] = temp;
        }
        else if(direction.compareTo("down")==0){
            char temp = arr[i+1][j];
            arr[i+1][j] = arr[i][j];
            arr[i][j] = temp;
        }
        else if(direction.compareTo("left")==0){
            char temp = arr[i][j-1];
            arr[i][j-1] = arr[i][j];
            arr[i][j] = temp;
        }
        else if(direction.compareTo("up")==0){
            char temp = arr[i-1][j];
            arr[i-1][j] = arr[i][j];
            arr[i][j] = temp;
        }
        return arr;
    }
    
    static char[][] ArrayCopy(char[][] arr){
        char[][] out = new char[4][4];
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                out[i][j] = arr[i][j];
            }
        }
        return out;
    }
    
    static boolean checkUnique(String str){
        for(int i = 0; i<traversecount; i++){
            if(traversed[i].compareTo(str)==0)
                return false;
        }
        return true;
    }
    
    static char[][] StringtoMat(String str){
        char[][] arr = new char[4][4];
        int count = 0;
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                arr[i][j] = str.charAt(count++);
            }
        }
        return arr;
    }
    
    static void printMat(char arr[][]){
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
    static String MattoString(char[][] arr){
        String str = "";
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                str += Character.toString(arr[i][j]);
            }
        }
        return str;
    }
    
    static int[] FindBlank(char mat[][]){ 
        for (int i = 0; i<mat.length; i++) 
            for (int j = 0; j<mat[i].length; j++) 
                if (mat[i][j] == ' '){ 
                    int[] arr = {i, j};
                    return arr;
                }
        return null;
    }
    
    static int heuristic(int option, char arr[][]){
        int count = 0;
        if(option==1){
            if(arr[0][0] != '1')
                count++;
            if(arr[0][1] != '2')
                count++;
            if(arr[0][2] != '3')
                count++;
            if(arr[0][3] != '4')
                count++;
            if(arr[1][0] != '5')
                count++;
            if(arr[1][1] != '6')
                count++;
            if(arr[1][2] != '7')
                count++;
            if(arr[1][3] != '8')
                count++;
            if(arr[2][0] != '9')
                count++;
            if(arr[2][1] != 'A')
                count++;
            if(arr[2][2] != 'B')
                count++;
            if(arr[2][3] != 'C')
                count++;
            if(arr[3][0] != 'D')
                count++;
            if(arr[3][1] != 'E')
                count++;
            if(arr[3][2] != 'F')
                count++;
            if(arr[3][3] != ' ')
                count++;
        }
        else if(option==2){
            for(int i = 0; i<4; i++){
                for(int j = 0; j<4; j++){
                    switch (arr[i][j]) {
                        case '1':
                            count += Math.abs(i-0);
                            count += Math.abs(j-0);
                            break;
                        case '2':
                            count += Math.abs(i-0);
                            count += Math.abs(j-1);
                            break;
                        case '3':
                            count += Math.abs(i-0);
                            count += Math.abs(j-2);
                            break;
                        case '4':
                            count += Math.abs(i-0);
                            count += Math.abs(j-3);
                            break;
                        case '5':
                            count += Math.abs(i-1);
                            count += Math.abs(j-0);
                            break;
                        case '6':
                            count += Math.abs(i-1);
                            count += Math.abs(j-1);
                            break;
                        case '7':
                            count += Math.abs(i-1);
                            count += Math.abs(j-2);
                            break;
                        case '8':
                            count += Math.abs(i-1);
                            count += Math.abs(j-3);
                            break;
                        case '9':
                            count += Math.abs(i-2);
                            count += Math.abs(j-0);
                            break;
                        case 'A':
                            count += Math.abs(i-2);
                            count += Math.abs(j-1);
                            break;
                        case 'B':
                            count += Math.abs(i-2);
                            count += Math.abs(j-2);
                            break;
                        case 'C':
                            count += Math.abs(i-2);
                            count += Math.abs(j-3);
                            break;
                        case 'D':
                            count += Math.abs(i-3);
                            count += Math.abs(j-0);
                            break;
                        case 'E':
                            count += Math.abs(i-3);
                            count += Math.abs(j-1);
                            break;
                        case 'F':
                            count += Math.abs(i-3);
                            count += Math.abs(j-2);
                            break;
                        case ' ':
                            count += Math.abs(i-3);
                            count += Math.abs(j-3);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return count;
    }
}
