package mianShi;

import java.util.Arrays;

public class ArrayMergeSort {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8};
        int[] merge = merge(arr1, arr1.length, arr2, arr2.length);
        System.out.println(Arrays.toString(merge));
    }

    private static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;
        int[] sorted = new int[m + n];
        int cur;
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }
        return sorted;
    }
}
