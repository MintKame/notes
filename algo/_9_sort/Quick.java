package _9_sort;

public class Quick {
    static int cmp = 0, move = 0;
    public static void quickSort(int []arr, int first, int last, int version){
        if (first > last)   //err
            return;
        cmp = move = 0;
        if (version == 0)
            quickSort0(arr, first, last);
        else
            quickSort1(arr, first, last);
        System.out.println("quick" + version + " \t" + cmp + "\t" + move);
    }

    // ζ arr[first,last] ζεΊ
    private static void quickSort0(int []arr, int first, int last){
        int mid = partition(arr, first, last);
        //recur
        if (first < mid - 1)
            quickSort0(arr, first, mid - 1);
        if (mid + 1 < last)
            quickSort0(arr, mid + 1, last);
    }

    private static void quickSort1(int []arr, int first, int last){
        /* 1. dec recur
        if (last - first < 5) {
            insertSort(arr, first, last);
            return;
        }
        */
        // 2. mid of 3
        int m = first + (last - first) / 2;
        cmp ++;
        if (arr[first] > arr[last]){    // larger -> last
            move += 3;
            int tmp = arr[first];
            arr[first] = arr[last];
            arr[last] = tmp;
        }
        cmp ++;
        if (arr[m] > arr[last]){    // larger -> last
            move += 3;
            int tmp = arr[m];
            arr[m] = arr[last];
            arr[last] = tmp;
        }
        cmp++;
        if (arr[first] < arr[m]) {    // mid -> first
            move += 3;
            int tmp = arr[m];
            arr[m] = arr[first];
            arr[first] = tmp;
        }
        // 3. 2*recur -> 1*recur + 1*loop
        while (first < last){
            int l = partition(arr, first, last);
            //recur left part
            if (first < l - 1)
                quickSort1(arr, first, l - 1);
            //loop right part
            first = l + 1;
        }
    }

    private static int partition(int[] arr, int l, int r){
        int key = arr[l];
        move++;
        while (l < r){  //why not use != (< avoid para have problem)
            //r -> l: find 1st < key, put to left
            while (l < r && arr[r] >= key){
                // η?ζ ζ―ζζζε°ηζΎkeyε·¦θΎΉοΌζζε€§ηζΎkeyε³θΎΉ
                // η­δΊkeyηη΄ζ₯θ·³θΏοΌδΈδΊ€ζ’οΌδΈη¨θθ
                // ε δΈΊεη»­ηιε½οΌζη»δΌδ½Ώη­δΊkeyηοΌη§»ε¨ε° ε·¦θΎΉηζε³δΎ§/ε³θΎΉηζε·¦δΎ§
                r--;
                cmp++;
            }
            arr[l] = arr[r];
            move++;
            //l -> r: find 1st > key, put to right
            while (l < r &&arr[l] <= key){
                l++;
                cmp++;
            }
            arr[r] = arr[l];
            move++;
        }
        arr[l] = key;   
        // why not use r ? 
        //      para err, when l > r, loop 0 times
        //      set arr[1] = arr[1]  
        move++;
        return l;
    }

}
