import java.util.*;

class Process {
    Scanner sc = new Scanner(System.in);
    static int twt = 0;
    String pid;
    int bt, ct, wt;

    Process() {
        System.out.println("enter process id:");
        pid = sc.next();
        System.out.println("enter burst time of process:");
        bt = sc.nextInt();
    }
}

class NonPreemptiveSJF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter no of Process :");
        int n = sc.nextInt();
        Process p[] = new Process[n];
        Process q;
        int i, j;
        for (i = 0; i < n; i++)
            p[i] = new Process();
        for (i = 0; i < n; i++)
            for (j = i + 1; j < n; j++)
                if ((p[i].bt > p[j].bt)) {
                    q = p[i];
                    p[i] = p[j];
                    p[j] = q;
                }
        p[0].wt = 0;
        p[0].ct = p[0].bt;
        System.out.println("Gantt chart");
        System.out.print(" | " + p[0].pid + " " + p[0].ct);
        for (i = 1; i < n; i++) {
            p[i].wt = p[i - 1].ct;
            p[i].ct = p[i].wt + p[i].bt;
            Process.twt += p[i].wt;
            System.out.print(" | " + p[i].pid + " " + p[i].ct);
        }
        System.out.println(" |");
        System.out.println("pid\tbt\tct\twt\n");
        for (i = 0; i < n; i++) {
            System.out.println(p[i].pid + "\t" + p[i].bt + "\t" + p[i].ct + "\t" + p[i].wt);
        }
        System.out.println("the average waiting time is " + (Process.twt / (float) n));
    }
}
