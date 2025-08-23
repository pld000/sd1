public class HoarsTriple {
    public static void main(String[] args) {
        /**
         * P { a <= 0 or a >= 0 and b <= 0 or b >= 0}
         * C res := maxAbs(a,b)
         * Q { res >= a and res = b or res >= b and res = a }
         * */
        int res = maxAbs(-10, 4);
        System.out.println(res);
    }

    /**
     * P { a <= 0 or a >= 0 and b <= 0 or b >= 0}
     * C1 x := abs(a), y := abs(b)
     * Q1 { x >=0 and y >= 0 }
     * C2 res := max(x,y)
     * Q2 { res >= x and res = y or res >= y and res = x}
     */
    public static int maxAbs(int a, int b) {
        return max(abs(a), abs(b));
    }

    /**
     * P { a <= 0 or a >= 0 and b <= 0 or b >= 0 }
     * С x := max(a, b)
     * Q { x >= a and x = b or x >= b and x = a }
     *
     * Для любого a > b вернется a
     * Для любого a <= b вернется b
     */
    public static int max(int a, int b) {
        if (a > b) {
            return a;
        }

        return b;
    }

    /**
     * P { a <= 0 or a >= 0 }
     * С x:= abs(a)
     * Q { x >= 0 }
     *
     * Для а < 0 вернется значение (-1 * a) > 0
     * Для a >= вернется значение a >= 0
     */
    public static int abs(int a) {
        if (a < 0) {
            return -1 * a;
        }

        return a;
    }
}
