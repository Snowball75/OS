import java.util.*;

class Process {
    Scanner sc = new Scanner(System.in);
    static int tat = 0, tct = 0, twt = 0;
    String pid;
    int at, bt, ct, wt, tt, v;

    Process() {
        System.out.println("enter process id:");
        pid = sc.next();
        System.out.println("enter arrival time of process :");
        at = sc.nextInt();
        System.out.println("enter burst time of process:");
        bt = sc.nextInt();
    }
}

class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter no of process :");
        int n = sc.nextInt();
        System.out.println("enter time slice :");
        int ts = sc.nextInt();
        Process p[] = new Process[n];
        Process q;
        int b[] = new int[n];
        int i, j, k, select, m = 0, tb = 0;
        for (i = 0; i < n; i++)
            p[i] = new Process();
        for (i = 0; i < n; i++) {
            for (j = i + 1; j < n; j++) {
                if (p[i].at > p[j].at) {
                    q = p[i];
                    p[i] = p[j];
                    p[j] = q;
                }
            }
            b[i] = p[i].bt;
            tb += b[i];
        }
        int r[] = new int[tb];
        select = i = 0;
        System.out.println("Gantt chart : ");
        while (Process.tct < tb) {
            for (j = 0; j < n; j++) {
                if (p[j].at <= Process.tct && p[j].v != 1) {
                    r[i] = j;
                    i++;
                    p[j].v = 1;
                }
            }
            j = m;
            m = r[select];
            select++;
            if (p[m].at > Process.tct) {
                Process.tct = p[m].at;
            }
            if (m != j) {
                p[j].ct = Process.tct;
                System.out.print(" | " + p[j].pid + " " + p[j].ct);
                if (b[j] > 0) {
                    r[i] = j;
                    i++;
                }
            }
            k = 0;
            while (k < ts) {
                b[m]--;
                Process.tct++;
                k++;
                if (b[m] == 0)
                    break;
            }
            if (Process.tct == tb)
                p[m].ct = Process.tct;
        }
        System.out.println(" | " + p[m].pid + " " + p[m].ct + " |");
        for (i = 0; i < n; i++) {
            p[i].tt = p[i].ct - p[i].at;
            p[i].wt = p[i].tt - p[i].bt;
            Process.tat = Process.tat + p[i].tt;
            Process.twt = Process.twt + p[i].wt;
        }
        System.out.println("the average turn arround time is " + (Process.tat / (float) n));
        System.out.println("the average waiting time is " + (Process.twt / (float) n));
    }
}
