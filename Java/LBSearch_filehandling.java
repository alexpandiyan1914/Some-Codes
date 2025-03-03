import java.util.Scanner;
import java.util.*;
import java.io.*;

public class l_search{ 
     public static int linear(int arr[],int key){
          for(int i = 0;i < arr.length;i++){
              if(arr[i] == key){
                    return i;
              }
          }
          return -1;
     }
     
     public static int binarySearch(int[] arr, int key) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == key)
                return mid;
            else if (arr[mid] < key)
                left = mid + 1; 
            else
                right = mid - 1; 
        }
        return -1;
    }

     public static void main(String args[]){
          Scanner sc = new Scanner(System.in);
          String file = "numbers.txt";
          
          try(PrintWriter pr = new PrintWriter(new FileWriter(file))){
                 Random rand = new Random();
                 for(int i=0;i<100;i++){
                    pr.print(rand.nextInt(10000)+" ");
         
                 }
                 System.out.println("100 random numbers written into - "+file);
          }catch(IOException e){
               System.out.println(e.getMessage());
               return;
          }
          //System.out.println("Enter number of elements:");
          //int n = sc.nextInt();
          
          int[] arr = null;
          //System.out.println("Enter values:");
          //for(int i = 0;i < n;i++){
              // arr[i] = sc.nextInt();
          //}
          try(BufferedReader br = new BufferedReader(new FileReader(file))){
                 String line = br.readLine();
                 
                 if(line != null){
                      String[] numbers = line.split("\\s+");
                      arr = new int [numbers.length];
                      
                      for(int i = 0;i<numbers.length;i++){
                             arr[i] = Integer.parseInt(numbers[i]);
                      }
                 }
          }catch(IOException e){
              System.out.println("Error reading the file"+e.getMessage());
              return;
          }
          System.out.println("Enter the key value:");
          int key = sc.nextInt();
          int result = linear(arr,key);
          if(result == -1){
              System.out.println("Element not found !(Linear S):");
          }else {
              System.out.println("Element found at index(Linear S):"+result);
          }    
                      
          Arrays.sort(arr);
          int rresult = binarySearch(arr, key);
        if (rresult == -1){
            System.out.println("Element not found !");
            }
        else{
            System.out.println("Element found at index:(Binary S)" + rresult);
            }
     }
}

