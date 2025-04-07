import java.util.*;

public class InterpolationVsBinary {

    // Interpolation Search
    public static int interpolationSearch(int[] arr, int key) {
        int low = 0, high = arr.length - 1;

        while (low <= high && key >= arr[low] && key <= arr[high]) {
            if (arr[low] == arr[high]) {
                return (arr[low] == key) ? low : -1;
            }

            int pos = low + ((key - arr[low]) * (high - low)) / (arr[high] - arr[low]);

            if (pos < low || pos > high) break;

            if (arr[pos] == key)
                return pos;
            else if (arr[pos] < key)
                low = pos + 1;
            else
                high = pos - 1;
        }
        return -1;
    }

    // Binary Search
    public static int binarySearch(int[] arr, int key) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == key)
                return mid;
            else if (arr[mid] < key)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }

    // Main Method with User Input
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("🔢 Enter the number of elements:");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("📥 Enter the sorted array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("🔍 Enter the element to search:");
        int key = sc.nextInt();

        // Binary Search
        long start = System.nanoTime();
        int binaryResult = binarySearch(arr, key);
        long binaryTime = System.nanoTime() - start;

        // Interpolation Search
        start = System.nanoTime();
        int interpolationResult = interpolationSearch(arr, key);
        long interpolationTime = System.nanoTime() - start;

        // Results
        System.out.println("\n✅ Binary Search:");
        if (binaryResult != -1)
            System.out.println("Element found at index: " + binaryResult);
        else
            System.out.println("Element not found.");
        System.out.println("Execution time: " + binaryTime + " ns");

        System.out.println("\n✅ Interpolation Search:");
        if (interpolationResult != -1)
            System.out.println("Element found at index: " + interpolationResult);
        else
            System.out.println("Element not found.");
        System.out.println("Execution time: " + interpolationTime + " ns");
    }
}
