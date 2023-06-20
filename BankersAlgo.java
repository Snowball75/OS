import java.util.*;
import java.io.*;

class BankersAlgo {
    static void findNeedValue(int needArray[][], int maxArray[][], int allocArray[][], int tp, int tr) {
        for (int i = 0; i < tp; i++)
            for (int j = 0; j < tr; j++)
                needArray[i][j] = maxArray[i][j] - allocArray[i][j];
    }

    static boolean checkSafeSystem(int processes[], int availableArray[], int maxArray[][], int allocArray[][], int tp,
            int tr) {
        int[][] needArray = new int[tp][tr];
        findNeedValue(needArray, maxArray, allocArray, tp, tr);
        boolean[] finishP = new boolean[tp];
        int[] safeSequenceArray = new int[tp];
        int[] workArray = new int[tr];

        for (int i = 0; i < tr; i++)
            workArray[i] = availableArray[i];
        int c = 0;
        while (c < tp) {
            boolean foundSafeSystem = false;
            for (int m = 0; m < tp; m++) {
                if (finishP[m] == false) {
                    int j;
                    for (j = 0; j < tr; j++)
                        if (needArray[m][j] > workArray[j])
                            break;
                    if (j == tr) {
                        for (int k = 0; k < tr; k++)
                            workArray[k] += allocArray[m][k];
                        safeSequenceArray[c++] = m;
                        finishP[m] = true;
                        foundSafeSystem = true;
                    }
                }
            }
            if (foundSafeSystem == false) {
                System.out.print("The system is not in the safe state because lack of resources");
                return false;
            }
        }
        System.out.print("The system is in safe sequence and the sequence is as follows: ");
        for (int i = 0; i < tp; i++)
            System.out.print("P" + safeSequenceArray[i] + " ");
        return true;
    }

    public static void main(String[] args) {
        int np, nr;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter total number of processes");
        np = sc.nextInt();
        System.out.println("Enter total number of resources");
        nr = sc.nextInt();
        int p[] = new int[np];
        for (int i = 0; i < np; i++) {
            p[i] = i;
        }
        int availableArray[] = new int[nr];
        System.out.println("Enter the availability of resource");
        for (int i = 0; i < nr; i++)
            availableArray[i] = sc.nextInt();
        int maxArray[][] = new int[np][nr];
        System.out.println("Enter the maximum resource Matrix :");
        for (int i = 0; i < np; i++) {
            for (int j = 0; j < nr; j++) {
                maxArray[i][j] = sc.nextInt();
            }
        }
        int allocArray[][] = new int[np][nr];
        System.out.println("Enter the allocation resource matrix : ");
        for (int i = 0; i < np; i++) {
            for (int j = 0; j < nr; j++) {

                allocArray[i][j] = sc.nextInt();
            }
        }
        checkSafeSystem(p, availableArray, maxArray, allocArray, np, nr);
    }
}
