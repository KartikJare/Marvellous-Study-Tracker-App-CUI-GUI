////////////////////////////////////////////////////////////////////
//
// File Name   :   StudyTracker.java
// Description :   CUI-based application to track and manage study sessions
// Author      :   Kartik Ganesh Jare
// Date        :   01/08/2025
//
////////////////////////////////////////////////////////////////////

import java.util.*;
import java.time.LocalDate;
import java.io.*;

////////////////////////////////////////////////////////////////////
//
// Class Name  :   StudyLog
// Description :   Represents a single study log entry
// Author      :   Kartik Ganesh Jare
// Date        :   01/08/2025
//
////////////////////////////////////////////////////////////////////

class StudyLog
{
    public LocalDate Date;
    public String Subject;
    public double Duration;
    public String Descrption;

    ////////////////////////////////////////////////////////////////////
    //
    // Function Name :   StudyLog (Constructor)
    // Description   :   Initializes values for the study log
    // Author        :   Kartik Ganesh Jare
    // Date          :   01/08/2025
    // Prototype     :   StudyLog(LocalDate, String, double, String)
    // Input         :   LocalDate, String, double, String
    // Output        :   None
    //
    ////////////////////////////////////////////////////////////////////
    public StudyLog(LocalDate A,String B,double C,String D)
    {
        this.Date = A;
        this.Subject = B;
        this.Duration = C;
        this.Descrption = D;
    }

    ////////////////////////////////////////////////////////////////////
    //
    // Function Name :   toString
    // Description   :   Converts study log object to string format
    // Author        :   Kartik Ganesh Jare
    // Date          :   01/08/2025
    // Prototype     :   public String toString()
    // Input         :   None
    // Output        :   String
    //
    ////////////////////////////////////////////////////////////////////
    @Override
    public String toString()
    {
        return Date+" | "+Subject+" | "+Duration+" | "+Descrption;
    }

    // Getter methods
    public LocalDate getDate()         { return Date; }
    public String getSubject()          { return Subject; }
    public double getDuration()         { return Duration; }
    public String getDescrption()       { return Descrption; }
}

////////////////////////////////////////////////////////////////////
//
// Class Name  :   StudyTracker
// Description :   Handles all study log operations in CUI
// Author      :   Kartik Ganesh Jare
// Date        :   01/08/2025
//
////////////////////////////////////////////////////////////////////

class StudyTracker
{
    // Datastructure to hold the data about study
    private ArrayList <StudyLog> Database = new ArrayList<StudyLog>();

    ////////////////////////////////////////////////////////////////////
    //
    // Function Name :   InsertLog
    // Description   :   Accepts user input and adds a new log
    // Author        :   Kartik Ganesh Jare
    // Date          :   01/08/2025
    // Prototype     :   public void InsertLog()
    // Input         :   None
    // Output        :   void
    //
    ////////////////////////////////////////////////////////////////////
    public void InsertLog()
    {
        Scanner Scannerobj = new Scanner(System.in);

        System.out.println("--------------------------------------------------------------");
        System.out.println("--------Please enter the vaild details of your study----------");
        System.out.println("--------------------------------------------------------------");

        LocalDate Dateobj = LocalDate.now();

        System.out.println("Please provide the name of subject like C/C++/OS/DS");
        String sub = Scannerobj.nextLine();

        System.out.println("Enter the time period if your study in hours");
        double dur = Scannerobj.nextDouble();
        Scannerobj.nextLine();

        System.out.println("Please provide the description about the study for future reference");
        String desc = Scannerobj.nextLine();

        StudyLog Studyobj = new StudyLog(Dateobj, sub, dur, desc);

        Database.add(Studyobj);

        System.out.println("Study Log gets stored successfully");
        System.out.println("---------------------------------------------------------");
    }

    ////////////////////////////////////////////////////////////////////
    //
    // Function Name :   DisplayLog
    // Description   :   Displays all stored logs
    // Author        :   Kartik Ganesh Jare
    // Date          :   01/08/2025
    // Prototype     :   public void DisplayLog()
    // Input         :   None
    // Output        :   void
    //
    ////////////////////////////////////////////////////////////////////
    public void DisplayLog()
    {
        System.out.println("--------------------------------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("----------------------------------------------------------");
            return;
        }

        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------Log report from Marvellous study Tracker-------");
        System.out.println("-----------------------------------------------------------");

        for(StudyLog sobj:Database)
        {
            System.out.println(sobj);    
        }
        System.out.println("-----------------------------------------------------------");
    }

    ////////////////////////////////////////////////////////////////////
    //
    // Function Name :   ExportCSV
    // Description   :   Exports all logs to a CSV file
    // Author        :   Kartik Ganesh Jare
    // Date          :   01/08/2025
    // Prototype     :   public void ExportCSV()
    // Input         :   None
    // Output        :   void
    //
    ////////////////////////////////////////////////////////////////////
    public void ExportCSV()
    {
        System.out.println("--------------------------------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("----------------------------------------------------------");
            return;
        }

        String fileName = "MarvellousStudy.csv";

        try(FileWriter fwobj = new FileWriter(fileName))
        {
            fwobj.write("Date,Subject,Duration,Description\n");

            for(StudyLog sobj:Database)
            {
                fwobj.write(sobj.getDate() + ","+
                            sobj.getSubject().replace(",", " ")+ ","+
                            sobj.getDuration()+ ","+
                            sobj.getDescrption().replace(",", " ")+"\n");
            }

            System.out.println("Log Created Successfully");
        }
        catch (Exception eobj)
        {
            System.out.println("Exception occurred while creating the CSV.");
            System.out.println("Report this issue to Marvellous Infosystems");
        }
    }

    ////////////////////////////////////////////////////////////////////
    //
    // Function Name :   SummaryByDate
    // Description   :   Displays total study hours grouped by date
    // Author        :   Kartik Ganesh Jare
    // Date          :   01/08/2025
    // Prototype     :   public void SummaryByDate()
    // Input         :   None
    // Output        :   void
    //
    ////////////////////////////////////////////////////////////////////
    public void SummaryByDate()
    {   
        System.out.println("--------------------------------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("----------------------------------------------------------");
            return;
        }

        System.out.println("-----------------------------------------------------------");
        System.out.println("-------Summary By Date from Marvellous study Tracker-------");
        System.out.println("-----------------------------------------------------------");

        TreeMap <LocalDate,Double> tobj =  new TreeMap<LocalDate,Double>();

        for(StudyLog sobj:Database)
        {
           LocalDate lobj = sobj.getDate();
           double d = sobj.getDuration();
    
           tobj.put(lobj, tobj.getOrDefault(lobj,0.0)+d);
        }

        for(LocalDate ldobj:tobj.keySet())
        {
            System.out.println("Date : "+ldobj+" Total Study "+tobj.get(ldobj));
        }
        System.out.println("-----------------------------------------------------------");
    }

    ////////////////////////////////////////////////////////////////////
    //
    // Function Name :   SummaryBySubject
    // Description   :   Displays total study hours grouped by subject
    // Author        :   Kartik Ganesh Jare
    // Date          :   01/08/2025
    // Prototype     :   public void SummaryBySubject()
    // Input         :   None
    // Output        :   void
    //
    ////////////////////////////////////////////////////////////////////
    public void SummaryBySubject()
    {   
        System.out.println("--------------------------------------------------------------");

        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("----------------------------------------------------------");
            return;
        }

        System.out.println("-------------------------------------------------------------");
        System.out.println("-------Summary By Subject from Marvellous study Tracker-------");
        System.out.println("--------------------------------------------------------------");

        TreeMap <String ,Double> tobj =  new TreeMap<String,Double>();

        for(StudyLog sobj:Database)
        {
            String s = sobj.getSubject();
            double d = sobj.getDuration();
           
            tobj.put(s, tobj.getOrDefault(s,0.0)+d);
        }

        for(String str :tobj.keySet())
        {
            System.out.println("Subject : "+str+" Total Study "+tobj.get(str));
        }
        System.out.println("-----------------------------------------------------------");
    }
}

////////////////////////////////////////////////////////////////////
//
// Class Name  :   StudyTrackerStarterCUI
// Description :   Entry point of the Study Tracker application
// Author      :   Kartik Ganesh Jare
// Date        :   01/08/2025
//
////////////////////////////////////////////////////////////////////

class StudyTrackerStarterCUI
{
    public static void main(String A[])
    {   
        StudyTracker stobj = new StudyTracker();
        Scanner Scannerobj = new Scanner(System.in);
        int iChoice = 0;

        System.out.println("-----------------------------------------------------------");
        System.out.println("--Welcome to Marvellous Study Tracker Application--");
        System.out.println("-----------------------------------------------------------");

        do
        {
            System.out.println("Please select the appropriate option");
            System.out.println("1 : Insert new study Log into Database");
            System.out.println("2 : View All study Logs");
            System.out.println("3 : Summary of Study Log Date");
            System.out.println("4 : Summary of study Log by subject");
            System.out.println("5 : Export study Log to csv file");
            System.out.println("6 : Exit the application");

            iChoice =  Scannerobj.nextInt();

            switch(iChoice)
            {
                case 1: 
                stobj.InsertLog(); 
                break;
                case 2: 
                stobj.DisplayLog(); 
                break;
                case 3: 
                stobj.SummaryByDate(); 
                break;
                case 4: 
                stobj.SummaryBySubject(); 
                break;
                case 5: 
                stobj.ExportCSV(); 
                break;
                case 6:
                    System.out.println("--------------------------------------------------------------");
                    System.out.println("-----Thank you for using Marvellous Study log Application-----");
                    System.out.println("--------------------------------------------------------------");
                    break;
                default:
                    System.out.println("Please Enter the valid option.......");
            }
        }while(iChoice != 6);
    }
}