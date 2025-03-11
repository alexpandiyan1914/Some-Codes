import java.util.*;

public class Interpolation {
    static int Interpol(int[] arr, int n, int key){
        int low = 0;
        int high = n - 1;
        //check for Arithmetic exception
        if(arr[high] == arr[low]){
            if(arr[low] == key) return low;
            else return -1;
        }

        while(low <= high && key >= arr[low] && key <= arr[high]){
            int pos = low + ((key - arr[low]) * (high - low) / (arr[high] - arr[low]));
            if(arr[pos] == key){
                return pos;
            }else if(arr[pos] < key){
                low = pos + 1;
            }else if(arr[pos] > key){
                high = pos - 1;
            }
        }
        return -1;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements:");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the values :");
        for(int i = 0;i < n;i++){
            arr[i] = sc.nextInt();
        }
        System.out.println("Enter the key :");
        int key = sc.nextInt();
        Arrays.sort(arr);
        int result = Interpol(arr,n,key);
        if(result != -1){
            System.out.println("The value "+key+" is founded at index "+result);
        }else{
            System.out.println("Element not found !");
        }
        sc.close();
    }
}
