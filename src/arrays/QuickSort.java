package arrays;
import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {
		System.out.println(Math.log(3));
		int arr[] = {9, 7, 5, 11, 12, 2, 14, 3, 10, 6};
	    quickSort(arr, 0, arr.length - 1);
	    System.out.println(Arrays.toString(arr));
    }
	
	private static void quickSort(int[] arr, int p, int r) {
		if(p < r) {
			int q = partition(arr, p, r);
			quickSort(arr, p, q-1);
			quickSort(arr, q+1, r);	
		}
	}

	private static int partition(int[] arr, int low, int high) {
		int pivot = arr[high];
		int i = (low - 1);
		
		for(int j = low; j <= high - 1; j++) {
			if(arr[j] <= pivot) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, ++i, high);
		return i;
	}

	private static void swap(int[] arr, int i, int j) {
		int key = arr[j];
		arr[j] = arr[i];
		arr[i] = key;
	}
}
