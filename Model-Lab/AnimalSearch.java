import java.util.*;

public class AnimalSearch {

    // Merge Sort
    public static void mergeSort(String[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(String[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        String[] L = new String[n1];
        String[] R = new String[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // Binary Search
    public static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = arr[mid].compareTo(target);
            if (cmp == 0) return mid;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    // Main
    public static void main(String[] args) {
        String[] animals = { "Zebra", "Elephant", "Dog", "Cat", "Giraffe", "Bear", "Lion", "Monkey" };

        System.out.println("Original list: " + Arrays.toString(animals));

        // Sort using Merge Sort
        mergeSort(animals, 0, animals.length - 1);
        System.out.println("Sorted list: " + Arrays.toString(animals));

        // Search
        String target = "Giraffe";
        int index = binarySearch(animals, target);

        if (index != -1)
            System.out.println("Found '" + target + "' at index: " + index);
        else
            System.out.println("'" + target + "' not found.");
    }
}
