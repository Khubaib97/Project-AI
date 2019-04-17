/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wolfsheepcabbage;

import java.util.ArrayList;

/**
 *
 * @author Khewbs
 */
public class Wolfsheepcabbage {

    /**
     * @param args the command line arguments
     */
    static ArrayList<String> traversedStates = new ArrayList<String>();
    public static void main(String[] args) {
        System.out.println("Format is BWSC\nB = boat/man\nW = wolf\nS = sheep\nC = cabbage");
        String start = "RRRR";
        String end = "LLLL";
        System.out.println();
        getNext(start, 0);
        System.out.println("Number of states: "+traversedStates.size());
    }
    public static void getNext(String str, int indent){
        traversedStates.add(str);
        boolean flag = false;
        for(int i = 0; i<indent; i++){
            if(i==indent-1){
                System.out.print("   |--> ");
                flag = true;
            }
            else if(!flag)
                System.out.print("   |    ");
            else{
                System.out.print("   --   ");
            }
        }
        if(str.equals("LLLL")){
            System.out.println(str+" (Goal node)<--------------------------------------|");
        }
        else{
            System.out.println(str);
        }
        if(str.charAt(0)=='R' && str.charAt(1)=='R'){
            StringBuilder s = new StringBuilder(str);
            s.setCharAt(0, 'L');
            s.setCharAt(1, 'L');
            if(isLegal(s.toString())){
                if(!isTraversed(s.toString()))
                    getNext(s.toString(), indent+1);
                else
                    printAlreadyTraversed(s.toString(), indent+1);
            }
            else{
                printIllegal(s.toString(), indent+1);
            }
        }
        if(str.charAt(0)=='L' && str.charAt(1)=='L'){
            StringBuilder s = new StringBuilder(str);
            s.setCharAt(0, 'R');
            s.setCharAt(1, 'R');
            if(isLegal(s.toString())){
                if(!isTraversed(s.toString()))
                    getNext(s.toString(), indent+1);
                else
                    printAlreadyTraversed(s.toString(), indent+1);
            }
            else{
                printIllegal(s.toString(), indent+1);
            }
        }
        if(str.charAt(0)=='R' && str.charAt(2)=='R'){
            StringBuilder s = new StringBuilder(str);
            s.setCharAt(0, 'L');
            s.setCharAt(2, 'L');
            if(isLegal(s.toString())){
                if(!isTraversed(s.toString()))
                    getNext(s.toString(), indent+1);
                else
                    printAlreadyTraversed(s.toString(), indent+1);
            }
            else{
                printIllegal(s.toString(), indent+1);
            }
        }
        if(str.charAt(0)=='L' && str.charAt(2)=='L'){
            StringBuilder s = new StringBuilder(str);
            s.setCharAt(0, 'R');
            s.setCharAt(2, 'R');
            if(isLegal(s.toString())){
                if(!isTraversed(s.toString()))
                    getNext(s.toString(), indent+1);
                else
                    printAlreadyTraversed(s.toString(), indent+1);
            }
            else{
                printIllegal(s.toString(), indent+1);
            }
        }
        if(str.charAt(0)=='R' && str.charAt(3)=='R'){
            StringBuilder s = new StringBuilder(str);
            s.setCharAt(0, 'L');
            s.setCharAt(3, 'L');
            if(isLegal(s.toString())){
                if(!isTraversed(s.toString()))
                    getNext(s.toString(), indent+1);
                else
                    printAlreadyTraversed(s.toString(), indent+1);
            }
            else{
                printIllegal(s.toString(), indent+1);
            }
        }
        if(str.charAt(0)=='L' && str.charAt(3)=='L'){
            StringBuilder s = new StringBuilder(str);
            s.setCharAt(0, 'R');
            s.setCharAt(3, 'R');
            if(isLegal(s.toString())){
                if(!isTraversed(s.toString()))
                    getNext(s.toString(), indent+1);
                else
                    printAlreadyTraversed(s.toString(), indent+1);
            }
            else{
                printIllegal(s.toString(), indent+1);
            }
        }
        if(str.charAt(0)=='R'){
            StringBuilder s = new StringBuilder(str);
            s.setCharAt(0, 'L');
            if(isLegal(s.toString())){
                if(!isTraversed(s.toString()))
                    getNext(s.toString(), indent+1);
                else
                    printAlreadyTraversed(s.toString(), indent+1);
            }
            else{
                printIllegal(s.toString(), indent+1);
            }
        }
        if(str.charAt(0)=='L'){
            StringBuilder s = new StringBuilder(str);
            s.setCharAt(0, 'R');
            if(isLegal(s.toString())){
                if(!isTraversed(s.toString()))
                    getNext(s.toString(), indent+1);
                else
                    printAlreadyTraversed(s.toString(), indent+1);
            }
            else{
                printIllegal(s.toString(), indent+1);
            }
        }
    }
    public static void printAlreadyTraversed(String str, int indent){
        boolean flag = false;
        for(int i = 0; i<indent; i++){
            if(i==indent-1){
                System.out.print("   |--> ");
                flag = true;
            }
            else if(!flag)
                System.out.print("   |    ");
            else{
                System.out.print("   --   ");
            }
        }
        System.out.println(str+" (Already branched before)");
    }
    public static void printIllegal(String str, int indent){
        boolean flag = false;
        for(int i = 0; i<indent; i++){
            if(i==indent-1){
                System.out.print("   |--> ");
                flag = true;
            }
            else if(!flag)
                System.out.print("   |    ");
            else{
                System.out.print("   --   ");
            }
        }
        System.out.println(str+" (Game Over)");
    }
    public static boolean isTraversed(String str){
        boolean res = false;
        for(String temp : traversedStates){
            if(temp.equals(str)){
                res = true;
                break;
            }
        }
        return res;
    }
    public static boolean isLegal(String str){
        if(str.charAt(2)=='R' && str.charAt(3)=='R' && str.charAt(0)=='L')
            return false;
        else if(str.charAt(2)=='L' && str.charAt(3)=='L' && str.charAt(0)=='R')
            return false;
        else if(str.charAt(2)=='R' && str.charAt(1)=='R' && str.charAt(0)=='L')
            return false;
        else if(str.charAt(2)=='L' && str.charAt(1)=='L' && str.charAt(0)=='R')
            return false;
        else
            return true;
    }
}
