package demo;

import java.util.Arrays;

public class SumBigNumber {

  public static void main(String[] args) {
    SumBigNumber app = new SumBigNumber();
    System.out.println(Arrays.toString(app.add(new int[]{9,9}, new int[]{9})));
    System.out.println(Arrays.toString(app.add(new int[]{9}, new int[]{9})));
    System.out.println(Arrays.toString(app.add(new int[]{1,0}, new int[]{5})));
    System.out.println(Arrays.toString(app.add(new int[]{1,2,3,4,5}, new int[]{6,7,8})));
    System.out.println(Arrays.toString(app.add(new int[]{1,2,3}, new int[]{4,5,6,7,8})));
  }

  public int[] add(int[] a, int[] b) {
    int i = a.length - 1;
    int j = b.length - 1;
    int remain = 0;
    int[] result = a.length > b.length ? new int[a.length + 1] : new int[b.length + 1];
    int k = result.length - 1;

    while (i >= 0 && j >= 0) {
      int c = a[i] + b[j] + remain;
      if (c >= 10) {
        result[k] = c % 10;
        remain = c / 10;
      } else {
        remain = 0;
        result[k] = c;
      }
      i--; j--; k--;
    }

    if (remain == 0) {
      if (i >= 0) System.arraycopy(a, 0, result, k - i, i + 1);
      if (j >= 0) System.arraycopy(b, 0, result, k - j, j + 1);
    } else {
      int[] t = null;
      if (i >= 0) {
        t = add(Arrays.copyOf(a, i + 1), new int[]{remain});
      } else if (j >= 0) {
        t = add(Arrays.copyOf(b, j + 1), new int[]{remain});
      } else {
        result[k] = remain;
      }

      if (t != null) {
        System.arraycopy(t, 0, result, k + 1 - t.length, t.length);
      }
    }

    if (result[0] == 0) return Arrays.copyOfRange(result, 1, result.length);

    return result;
  }
}
