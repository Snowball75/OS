import java.util.*;

class Process {
    Scanner sc = new Scanner(System.in);
    static int tat = 0, tct = 0, twt = 0;
    String pid;
    int at, bt, ct, wt, tt;

    Process() {
        System.out.println("Enter process id:");
        pid = sc.next();
        System.out.println("Enter arrival time of process :");
        at = sc.nextInt();
        System.out.println("Enter burst time of process:");
        bt = sc.nextInt();
    }
}

class Fcfs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no of process :");
        int n = sc.nextInt();
        Process p[] = new Process[n];
        Process q;
        int i, j;
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
        }
        System.out.println("Gantt chart : ");
        Process.tct = p[0].ct = p[0].bt;
        System.out.print(" | " + p[0].pid + " " + p[0].ct);
        for (i = 1; i < n; i++) {
            if (p[i].at > Process.tct) {
                Process.tct = p[i].at;
            }
            Process.tct = Process.tct + p[i].bt;
            p[i].ct = Process.tct;
            System.out.print(" | " + p[i].pid + " " + p[i].ct);
        }
        for (i = 0; i < n; i++) {
            p[i].tt = p[i].ct - p[i].at;
            p[i].wt = p[i].tt - p[i].bt;
            Process.tat = Process.tat + p[i].tt;
            Process.twt = Process.twt + p[i].wt;
        }
        System.out.println(" |");
        System.out.println("the average turn arround time is " + (Process.tat / (float) n));
        System.out.println("the average waiting time is " + (Process.twt / (float) n));
    }
}
