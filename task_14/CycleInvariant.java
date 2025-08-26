public class CycleInvariant {
    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 2, 1, 7, -10, 0};

        int res = findMax(arr);
        System.out.println(res);
    }

    /**
     * P {arr.length > 0}
     * C findMax(arr)
     * Q {result = max(arr)}
     * I {1<=i<arr.length, res >= arr[i]}
     *
     * Доказательство инварианта.
     * 1. Инициализация:
     * До начала первой итерации цикла: i = 1 и res = arr[0]
     * Проверка инварианта: 0 <= 1 <= arr.length и res = arr[0]
     * Инвариант истинен в начале.
     *
     * 2. Сохранение инварианта:
     * на каждой итерации цикла i увеличивается на 1
     * и res >= arr[i]
     *
     * Завершение:
     * на послдем шаге цикла arr[i] является последним элементом массива
     * и будет res = max(arr)
     *
     * */
    public static int findMax(int[] arr) {
        int i;
        int res = arr[0];

        for (i = 1; i < arr.length; i++) {
            if (res < arr[i]) {
                res = arr[i];
            }
        }

        return res;
    }
}
