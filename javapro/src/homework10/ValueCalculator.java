package homework10;

import java.util.Arrays;

public class ValueCalculator extends Thread {

  float[] array;
  int halfarrayLength;

  public ValueCalculator(int halfarrayLength) {
    this.halfarrayLength = halfarrayLength;
    this.array = new float[halfarrayLength * 2];
  }

  public void doCalc(float j) {
    float[] arrayOne = new float[halfarrayLength];
    float[] arrayTwo = new float[halfarrayLength];
    long start = System.currentTimeMillis();
    System.out.println("Calculation starts at: " + start + " .......");

    Arrays.fill(array, j);
    System.arraycopy(array, 0, arrayOne, 0, halfarrayLength);
    System.arraycopy(array, halfarrayLength - 1, arrayTwo, 0, halfarrayLength);

    MyThread thread1 = new MyThread(arrayOne);
    MyThread thread2 = new MyThread(arrayTwo);

    try {
      thread1.start();
      thread2.start();

      thread1.join();
      thread2.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.arraycopy(arrayOne, 0, array, 0, halfarrayLength);
    System.arraycopy(arrayTwo, 0, array, halfarrayLength - 1, halfarrayLength);

    long finish = System.currentTimeMillis();
    System.out.println("Calculating finished time: " + finish);
    long usedtime = finish - start;
    System.out.println("Used time: " + usedtime + " ms.");
  }
}
