class ProcessNode {
    int processID;
    int burstTime;
    int remainingTime;
    int priority;
    int waitingTime = 0;
    int turnAroundTime = 0;
    ProcessNode next;

    public ProcessNode(int processID, int burstTime, int priority) {
        this.processID = processID;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class CircularLLScheduler {
    private ProcessNode tail = null;

    public void addProcess(int processID, int burstTime, int priority) {
        ProcessNode newNode = new ProcessNode(processID, burstTime, priority);
        if (tail == null) {
            newNode.next = newNode;
            tail = newNode;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void simulateRR(int timeQuantum) {
        if (tail == null) {
            System.out.println("No processes to schedule");
            return;
        }

        ProcessNode current = tail.next;
        int time = 0;
        int totalWaitingTime = 0, totalTurnAroundTime = 0, processCount = 0;

        ProcessNode temp = tail.next;
        do {
            processCount++;
            temp = temp.next;
        } while (temp != tail.next);

        System.out.println("Starting Round Robin Simulation (Time Quantum = " + timeQuantum + ")\n");

        while (tail != null){
            current = tail.next;
            boolean allDone = true;

            do {
                if (current.remainingTime > 0) {
                    allDone = false;
                    System.out.println("Processing = P" + current.processID);

                    int execTime = Math.min(current.remainingTime, timeQuantum);
                    current.remainingTime -= execTime;
                    time += execTime;

                    if (current.remainingTime == 0) {
                        current.turnAroundTime = time;
                        current.waitingTime = time - current.burstTime;
                        System.out.println("Process = P" + current.processID + " finished, Turnaround = " + current.turnAroundTime + ", Waiting = " + current.waitingTime);
                    }

                    displayProcesses();
                }

                current = current.next;
            } while (current != tail.next);

            if (allDone) break;
        }

        current = tail.next;
        do {
            totalWaitingTime += current.waitingTime;
            totalTurnAroundTime += current.turnAroundTime;
            current = current.next;
        } while (current != tail.next);

        System.out.printf("Average Waiting Time = %.2f\n", (double) totalWaitingTime / processCount);
        System.out.printf("Average Turnaround Time = %.2f", (double) totalTurnAroundTime / processCount);
    }

    public void displayProcesses() {
        if (tail == null) {
            System.out.println("No processes");
            return;
        }

        ProcessNode current = tail.next;
        System.out.println("Current Queue:");
        do {
            System.out.println("P" + current.processID + " [BT = " + current.burstTime + ", RT = " + current.remainingTime + ", Priority = " + current.priority + "]");
            current = current.next;
        } while (current != tail.next);
    }
}

public class RoundRobin {
    public static void main(String[] args) {
        CircularLLScheduler scheduler = new CircularLLScheduler();

        scheduler.addProcess(1, 10, 2);
        scheduler.addProcess(2, 5, 1);
        scheduler.addProcess(3, 8, 3);

        scheduler.simulateRR(4);
    }
}
