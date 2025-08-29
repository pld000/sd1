import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 2, 1, 7, -10, 0};

        quickSort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }

    /**
     * P {0 <= l <= r < A.length}
     * C quickSort(A, l, r)
     * Q {A[l..r] отсортирован по неубыванию и multiset(A[l..r] после) = multiset(A[l..r] до)}
     *
     * Инвариант partition:
     * I {для всех l<=k<=i A[k] <= pivot  и  для всех i+1<=k<=j-1 A[k] > pivot}
     *
     * Доказательство корректности:
     * 1. Инициализация:
     *    До первой итерации цикла j = l, i = l-1.
     *    Следовательно, левая часть A[l..i] пуста, а A[i+1..j-1] также пуста.
     *    Инвариант выполняется.
     *
     * 2. Сохранение инварианта:
     *    Если A[j] <= pivot, то i увеличивается, и A[i], A[j] меняются местами.
     *    В результате A[l..i] содержит только элементы <= pivot.
     *    Если A[j] > pivot, то i не изменяется, и элемент A[j] принадлежит правой части.
     *    Таким образом, инвариант сохраняется.
     *
     * 3. Завершение:
     *    После выхода из цикла j = r.
     *    Тогда A[l..i] <= pivot, A[i+1..r-1] > pivot.
     *    Обмен A[i+1] и pivot ставит pivot на позицию p = i+1.
     *    Постусловие partition: A[l..p-1] <= A[p] <= A[p+1..r].
     *
     * 4. Корректность quickSort:
     *    - База рекурсии: если l >= r, то подмассив тривиально отсортирован.
     *    - После partition массив разбит на три части: A[l..p-1], A[p], A[p+1..r].
     *      При этом A[l..p-1] <= A[p] <= A[p+1..r].
     *    - По индукции, рекурсивные вызовы сортируют A[l..p-1] и A[p+1..r].
     *    - Таким образом, весь A[l..r] отсортирован.
     *
     * */
    public static void quickSort(int[] A, int l, int r) {
        if (l < r) {
            int p = partition(A, l, r);
            quickSort(A, l, p - 1);
            quickSort(A, p + 1, r);
        }
    }

    public static int partition(int[] A, int l, int r) {
        int pivot = A[r];
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (A[j] <= pivot) {
                i++;
                swap(A, i, j);
            }
        }
        swap(A, i + 1, r);
        return i + 1;
    }

    public static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
