public class SelectionSort {
    int[] arr;
    public SelectionSort(int[] inputArr) {
        arr = inputArr;
        for (int i = 0; i < arr.length; i++) {
            int minVal = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minVal] > arr[j]) {
                    minVal = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minVal];
            arr[minVal] = temp;
        }
    }

    public void printSort() {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i] + " ");
        }
        System.out.print("]");
    }
}
