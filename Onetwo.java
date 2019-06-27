/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onetwo;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author Marium Baig
 */

class node{
   int arr[];
   int layer;
  
   node(int arr[], int i){
       this.arr = arr;
       layer = i;
   }
  }



public class Onetwo {

static ArrayList<node> global = new ArrayList<>();
static int[] goal = {2,2,0,1,1};    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        global = new ArrayList<>();
        Stack<node> stack = new Stack<>();
        int[] arr = {1,1,0,2,2};
        stack.push(new node(arr, 0));
        global.add(stack.peek());
        while(!stack.isEmpty()){
            arr = stack.peek().arr;
            int layer = stack.peek().layer;
            stack.pop();
            for(int i = 0; i<layer; i++){
                if(i==layer-1){
                    System.out.print("   |--> ");
                }
                else{
                    System.out.print("   |    ");
                }
            }
            if(Arrays.equals(arr, goal)){
                System.out.println(Arrays.toString(arr)+" (Goal)");
            }
            else{
                System.out.println(Arrays.toString(arr));
            }
            for(int i = 0; i<5; i++){
                if(arr[i]==0){
                    if((i+1<5) && arr[i+1]==2){
                        int[] temp = new int[5];
                        System.arraycopy(arr, 0, temp, 0, arr.length);
                        temp[i] = 2;
                        temp[i+1] = 0;
                        if(!check(temp)){
                            stack.push(new node(temp, layer+1));
                            global.add(stack.peek());
                        }
                    }
                    if((i+2<5) && arr[i+2]==2){
                        int[] temp = new int[5];
                        System.arraycopy(arr, 0, temp, 0, arr.length);
                        temp[i] = 2;
                        temp[i+2] = 0;
                        if(!check(temp)){
                            stack.push(new node(temp, layer+1));
                            global.add(stack.peek());
                        }
                    }
                    if((i-1>-1) && arr[i-1]==1){
                        int[] temp = new int[5];
                        System.arraycopy(arr, 0, temp, 0, arr.length);
                        temp[i] = 1;
                        temp[i-1] = 0;
                        if(!check(temp)){
                            stack.push(new node(temp, layer+1));
                            global.add(stack.peek());
                        }
                    }
                    if((i-2>-1) && arr[i-2]==1){
                        int[] temp = new int[5];
                        System.arraycopy(arr, 0, temp, 0, arr.length);
                        temp[i] = 1;
                        temp[i-2] = 0;
                        if(!check(temp)){
                            stack.push(new node(temp, layer+1));
                            global.add(stack.peek());
                        }
                    }
                }
            }
        }
        System.out.println("Nodes in total: "+global.size());
        DFS();
        BFS();
    }
    
    static public void DFS(){
        int count = 0;
        global = new ArrayList<>();
        Stack<node> stack = new Stack<>();
        int[] arr = {1,1,0,2,2};
        stack.push(new node(arr, 0));
        global.add(stack.peek());
        while(!stack.isEmpty()){
            arr = stack.peek().arr;
            int layer = stack.peek().layer;
            stack.pop();
            count++;
            if(Arrays.equals(arr, goal)){
                System.out.println("(DFS) Nodes traversed: "+count);
                return;
            }
            for(int i = 0; i<5; i++){
                if(arr[i]==0){
                    if((i+1<5) && arr[i+1]==2){
                        int[] temp = new int[5];
                        System.arraycopy(arr, 0, temp, 0, arr.length);
                        temp[i] = 2;
                        temp[i+1] = 0;
                        if(!check(temp)){
                            stack.push(new node(temp, layer+1));
                            global.add(stack.peek());
                        }
                    }
                    if((i+2<5) && arr[i+2]==2){
                        int[] temp = new int[5];
                        System.arraycopy(arr, 0, temp, 0, arr.length);
                        temp[i] = 2;
                        temp[i+2] = 0;
                        if(!check(temp)){
                            stack.push(new node(temp, layer+1));
                            global.add(stack.peek());
                        }
                    }
                    if((i-1>-1) && arr[i-1]==1){
                        int[] temp = new int[5];
                        System.arraycopy(arr, 0, temp, 0, arr.length);
                        temp[i] = 1;
                        temp[i-1] = 0;
                        if(!check(temp)){
                            stack.push(new node(temp, layer+1));
                            global.add(stack.peek());
                        }
                    }
                    if((i-2>-1) && arr[i-2]==1){
                        int[] temp = new int[5];
                        System.arraycopy(arr, 0, temp, 0, arr.length);
                        temp[i] = 1;
                        temp[i-2] = 0;
                        if(!check(temp)){
                            stack.push(new node(temp, layer+1));
                            global.add(stack.peek());
                        }
                    }
                }
            }
        }
    }
    
    static public void BFS(){
        int count = 0;
        global = new ArrayList<>();
        Queue<node> queue = new LinkedList<>();
        int[] arr = {1,1,0,2,2};
        queue.add(new node(arr, 0));
        global.add(queue.peek());
        while(!queue.isEmpty()){
            arr = queue.peek().arr;
            int layer = queue.peek().layer;
            queue.remove();
            count++;
            if(Arrays.equals(arr, goal)){
                System.out.println("(BFS) Nodes traversed: "+count);
                return;
            }
            for(int i = 0; i<5; i++){
                if(arr[i]==0){
                    if((i+1<5) && arr[i+1]==2){
                        int[] temp = new int[5];
                        System.arraycopy(arr, 0, temp, 0, arr.length);
                        temp[i] = 2;
                        temp[i+1] = 0;
                        if(!check(temp)){
                            queue.add(new node(temp, layer+1));
                            global.add(queue.peek());
                        }
                    }
                    if((i+2<5) && arr[i+2]==2){
                        int[] temp = new int[5];
                        System.arraycopy(arr, 0, temp, 0, arr.length);
                        temp[i] = 2;
                        temp[i+2] = 0;
                        if(!check(temp)){
                            queue.add(new node(temp, layer+1));
                            global.add(queue.peek());
                        }
                    }
                    if((i-1>-1) && arr[i-1]==1){
                        int[] temp = new int[5];
                        System.arraycopy(arr, 0, temp, 0, arr.length);
                        temp[i] = 1;
                        temp[i-1] = 0;
                        if(!check(temp)){
                            queue.add(new node(temp, layer+1));
                            global.add(queue.peek());
                        }
                    }
                    if((i-2>-1) && arr[i-2]==1){
                        int[] temp = new int[5];
                        System.arraycopy(arr, 0, temp, 0, arr.length);
                        temp[i] = 1;
                        temp[i-2] = 0;
                        if(!check(temp)){
                            queue.add(new node(temp, layer+1));
                            global.add(queue.peek());
                        }
                    }
                }
            }
        }
    }
    
    static public boolean check(int[] arr){
        boolean flag = false;
        for(node a: global){
            if(Arrays.equals(arr, a.arr)){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
