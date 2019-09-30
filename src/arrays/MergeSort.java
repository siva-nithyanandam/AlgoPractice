package arrays;

import java.util.Arrays;

public class MergeSort {
	
    public static void main(String[] args) {
    	//int[] a = {7, 4, 5, 8, 2, 1, 6, 3, 0};
    	int[] a = {9,8,7,6,5,4,3,2,1,0};
    	int[] tempArr = new int[a.length];
    	mergeSort(a, 0, a.length-1, tempArr);
		System.out.println(Arrays.toString(a));
    }
	
	private static void mergeSort(int[] a, int p, int r, int[] tempArr) {
		if(p < r) {
			int q = (p+r)/2;
			mergeSort(a, p, q, tempArr);
			mergeSort(a, q+1, r, tempArr);
			merge_modified(a, p, q, r, tempArr);
		}
	}

	private static void merge_modified(int[] arr, int low, int middle, int high, int[] tempArr) {
    	for (int i = low; i <= high; i++) {
    		tempArr[i] = arr[i];
		}
		int helperLeft = low;
    	int helperRight = middle + 1;
    	int curr = low;
    	while (helperLeft <= middle && helperRight <= high) {
    		if (tempArr[helperLeft] <= tempArr[helperRight]) {
    			arr[curr] = tempArr[helperLeft];
				helperLeft++;
			} else {
				arr[curr] = tempArr[helperRight];
				helperRight++;
			}
			curr++;
		}
		for (int i = helperLeft; i <= middle; i++) {
			arr[curr++] = tempArr[i];
		}
	}

	private static void merge(int[] a, int p, int q, int r, int[] tempArr) {
		int n1 = q-p+1;
		int n2 = r-q;
		int[] x = new int[n1];
		int[] y = new int[n2];
		for(int i = 0; i < n1; i++) {
			x[i] = a[p+i];
		}
		for(int i = 0; i < n2; i++) {
			y[i] = a[q+i+1];
		}
		int i = 0, j = 0;
		for(int k = p; k <= r; k++) {
			if(j == n2 || (i < n1 && x[i] <= y[j])) {
				a[k] = x[i];
				i++;
			}
			else {
				a[k] = y[j];
				j++;
			}
		}
	}
}