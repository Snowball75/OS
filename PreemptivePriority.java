import java.util.*;

class Process {
    Scanner sc = new Scanner(System.in);
    static int tat = 0, tct = 0, twt = 0;
    String pid;
    int at, bt, ct, wt, tt, pt;

    Process() {
        System.out.println("enter process id:");
        pid = sc.next();
        System.out.println("enter arrival time of process :");
        at = sc.nextInt();
        System.out.println("enter burst time of process:");
        bt = sc.nextInt();
        System.out.println("enter priority of process:");
        pt = sc.nextInt();
    }
}

class PreemptivePriority {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter no of Process :");
        int n = sc.nextInt();
        Process p[] = new Process[n];
        int b[] = new int[n];
        int pr[] = new int[n];
        Process q;
        int i, j, m, tb = 0;
        for (i = 0; i < n; i++) {
            p[i] = new Process();
        }
        for (i = 0; i < n; i++) {
            for (j = i + 1; j < n; j++) {
                if ((p[i].at > p[j].at) || (p[i].at == p[j].at && p[i].pt > p[j].pt)) {
                    q = p[i];
                    p[i] = p[j];
                    p[j] = q;
                }
            }
            b[i] = p[i].bt;
            pr[i] = p[i].pt;
            tb += b[i];
        }
        int Max = tb;
        m = 0;
        System.out.println("Gantt chart : ");
        while (Process.tct < tb) {
            j = m;
            for (i = 0; i < n; i++) {
                if (p[i].at <= Process.tct && pr[m] > pr[i]) {
                    m = i;
                }
            }
            if (m != j) {
                p[j].ct = Process.tct;
                System.out.print(" | " + p[j].pid + " " + p[j].ct);
            }
            b[m]--;
            if (b[m] == 0)
                pr[m] = ++Max;
            Process.tct++;
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
