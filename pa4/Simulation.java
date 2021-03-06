// -------------------------------------------------------------------------
// Simulation.java
// Matthew Tan
// mxtan
// pa4
// CMPS 12B/M
// -------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class Simulation 
{
    public static Job getJob(Scanner in) 
    {
        String[] s = in.nextLine().split(" ");
        int a = Integer.parseInt(s[0]);
        int d = Integer.parseInt(s[1]);
        return new Job(a, d);
    }

    public static void main(String[] args) throws IOException 
    {
        Scanner inScanner = null;
        PrintWriter report = null;
        PrintWriter trace = null;
        Queue backup = new Queue();
        Queue storage = new Queue();
        Queue[] processorQueues = null;
        int[] prevFinishTime = null;
        int m = 0;
        int time = 0;
        int finishedJobs = 0;
        int jobIndex = 0;

        try 
        {
            if (args.length != 1) 
            {
                System.out.println("Usage: Simultation infile");
                System.exit(1);
            }

            inScanner = new Scanner(new File(args[0]));
            report = new PrintWriter(new FileWriter(args[0] + ".rpt"));
            trace = new PrintWriter(new FileWriter(args[0] + ".trc"));
        } 
        catch(FileNotFoundException e) 
        {
            System.out.println("Caught Exception " + e);
            System.exit(1);
        }

        m = inScanner.nextInt();
        String getLine = inScanner.nextLine();
        while(inScanner.hasNext()) 
        {
            Job job = getJob(inScanner);
            storage.enqueue(job);
        }

        trace.println("Trace file: " + (args[0] + ".trc"));
        report.println("Report file: " + (args[0] + ".rpt"));
        report.println(m + " Jobs:");
        trace.println(m + " Jobs:");
        trace.println(storage.toString());
        trace.println();
        report.println(storage.toString());
        report.println();
        report.println("***********************************************************");

        for (int n = 1; n < m; n++) 
        {
            int totalWait = 0;
            int maxWait = 0;
            double avgWait = 0.0;
            time = 0;
            jobIndex = 0;
            finishedJobs = 0;
            int storageLength = storage.length();
            for(int i = 0; i < storageLength; i++) 
            {
                Job job = (Job) storage.dequeue();
                job.resetFinishTime();
                storage.enqueue(job);
            }

            int processors = n;
            processorQueues = new Queue[n + 1];
            prevFinishTime = new int[n];
            processorQueues[0] = storage;
            for(int i = 1; i < n+1; i++) 
            {
                processorQueues[i] = new Queue();
            }

            /*
            System.out.println("*****************************");
            if(processors == 1)
                System.out.println(processors + " processor:");
            else
                System.out.println(processors + " processors:");
            System.out.println("*****************************");
            */

            trace.println("*****************************");
            if(processors == 1)
            {
                trace.println(processors + " processor:");
            }
            else
            {
                trace.println(processors + " processors:");
            }
            trace.println("*****************************");

            int count = 1000;
            boolean needPrint = true;
            while (finishedJobs != m && count > 0) 
            {
                int gArrival = Integer.MAX_VALUE;
                int finalIndex = 1;
                int gFinish = -1;
                int length = -1;
                int finalLength = -1;
                Job allJobs = null;

                if (!storage.isEmpty()) 
                {
                    Job job  = (Job)storage.peek();
                    if (time >= job.getArrival() && job.getFinish() == -1)
                    {
                        needPrint = true;
                        Job temp = (Job) storage.dequeue();
                        if (jobIndex == 0)
                        {
                            temp.computeFinishTime(time);
                            processorQueues[1].enqueue(temp);
                            prevFinishTime[0] = temp.getFinish();
                        }
                        else if (jobIndex == 1 && n == 2)
                        {
                            temp.computeFinishTime(time);
                            processorQueues[2].enqueue(temp);
                            prevFinishTime[1] = temp.getFinish();
                        }
                        else
                        {
                            int shortest = processorQueues[1].length();
                            int shortestIndex = 1;
                            for (int i = 2; i < n + 1; i++)
                            {
                                if (shortest > processorQueues[i].length())
                                {
                                    shortest = processorQueues[i].length();
                                    shortestIndex = i;
                                }
                            }
                            if (processorQueues[shortestIndex].isEmpty())
                            {
                                temp.computeFinishTime(time);
                            }
                            processorQueues[shortestIndex].enqueue(temp); 
                        }
                        jobIndex++;
                    }
                }

                for(int i = 1; i < n + 1; i++) 
                {
                    if (!processorQueues[i].isEmpty())
                    {
                        Job temp = (Job) processorQueues[i].peek();
                        if (time == temp.getFinish())
                        {
                            Job job = (Job) processorQueues[i].dequeue();
                            storage.enqueue(job);
                            totalWait += job.getWaitTime();
                            maxWait = job.getWaitTime();
                            if (maxWait > job.getWaitTime())
                            {
                                maxWait = job.getWaitTime();
                            }
                            needPrint = true;                            
                            finishedJobs++;
                            if (!processorQueues[i].isEmpty())
                            {
                                temp = (Job) processorQueues[i].peek();
                                temp.computeFinishTime(time);
                                // System.out.println("time is: " + time);
                            }                            
                        }
                    }                  
                }
                if (needPrint)
                {
                    // System.out.println("time=" + time);
                    // System.out.println("0: " + storage.toString());
                    for(int i = 1; i < processors + 1; i++)
                    {
                        //System.out.println(i + ": " + processorQueues[i]);
                    }
                    //System.out.println();
                    trace.println("time=" + time);
                    trace.println("0: " + storage.toString());
                    for(int i = 1; i < processors+1; i++)
                    {
                        trace.println(i + ": " + processorQueues[i]);
                    }
                    trace.println();
                }
                needPrint = false;
                time++;
                count--;
            }

            avgWait = ((double) totalWait / m);
            avgWait = (double)Math.round(avgWait * 100) / 100;
            if (processors == 1)
            {
                report.println(processors + " processor: totalWait=" 
                       + totalWait + ", maxWait=" 
                       + maxWait + ", averageWait=" + avgWait);
            }
            else
            {
                report.println(processors + " processors: totalWait=" 
                       + totalWait + ", maxWait=" 
                       + maxWait + ", averageWait=" + avgWait);
            }
        }

        inScanner.close();
        report.close();
        trace.close();
    }
}
