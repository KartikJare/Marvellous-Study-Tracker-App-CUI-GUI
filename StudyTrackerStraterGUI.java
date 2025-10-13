////////////////////////////////////////////////////////////////////
//
// File Name   :   StudyTracker.java
// Description :   GUI-based application to track and manage study sessions
// Author      :   Kartik Ganesh Jare
// Date        :   01/08/2025
//
////////////////////////////////////////////////////////////////////

import java.util.*;
import java.time.LocalDate;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;

////////////////////////////////////////////////////////////////////
//
// Class Name  :   StudyLog
// Description :   Represents a single study session log
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
    // Date          :   04/08/2025
    // Prototype     :   StudyLog(LocalDate, String, double, String)
    // Input         :   LocalDate A, String B, double C, String D
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
    public LocalDate getDate()
    {
        return Date;
    }
    
    public String getSubject()
    {
        return Subject;
    }

    public double getDuration()
    {
        return Duration;
    }

    public String getDescrption()
    {
        return Descrption;
    }
}

////////////////////////////////////////////////////////////////////
//
// Class Name  :   StudyTracker
// Description :   GUI application to manage and analyze study logs
// Author      :   Kartik Ganesh Jare
// Date        :   01/08/2025
//
////////////////////////////////////////////////////////////////////

class StudyTracker implements ActionListener
{
    private JFrame frame;
    private JButton InsertLogButton,DisplayLogButton,ExportCSVButton,SummaryByDateButton,SummaryBySubjectButton;
    private JLabel Headlabel;

    // Datastructure to hold the data about study
    private ArrayList <StudyLog> Database = new ArrayList<StudyLog>();

    ////////////////////////////////////////////////////////////////////
    //
    // Function Name :   StudyTracker (Constructor)
    // Description   :   Initializes GUI and components
    // Author        :   Kartik Ganesh Jare
    // Date          :   01/08/2025
    // Prototype     :   StudyTracker(String, int, int)
    // Input         :   Title, Width, Height
    // Output        :   None
    //
    ////////////////////////////////////////////////////////////////////
    
    public StudyTracker(String Title, int Width, int Height)
    {
        frame = new JFrame();

        Headlabel = new JLabel("Marvellous Study Tracker");
        Headlabel.setBounds(140,20,300,50);

        InsertLogButton = new JButton("Insert new study Log into Database");
        InsertLogButton.setBounds(100,100,250,50);

        DisplayLogButton = new JButton("View All study Logs");
        DisplayLogButton.setBounds(100,200,250,50);

        SummaryByDateButton = new JButton("Summary of Study Log Date");
        SummaryByDateButton.setBounds(100,300,250,50);

        SummaryBySubjectButton = new JButton("Summary of study Log by subject");
        SummaryBySubjectButton.setBounds(100,400,250,50);

        ExportCSVButton = new JButton("Export study Log to csv file");
        ExportCSVButton.setBounds(100,500,250,50);

        frame.add(Headlabel);
        frame.add(InsertLogButton);
        frame.add(DisplayLogButton);
        frame.add(SummaryByDateButton);
        frame.add(SummaryBySubjectButton);
        frame.add(ExportCSVButton);

        InsertLogButton.addActionListener(this);
        DisplayLogButton.addActionListener(this);
        SummaryByDateButton.addActionListener(this);
        SummaryBySubjectButton.addActionListener(this);
        ExportCSVButton.addActionListener(this);

        frame.setLayout(null);
        frame.setTitle(Title);
        frame.setSize(Width, Height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }    

    ////////////////////////////////////////////////////////////////////
    //
    // Function Name :   actionPerformed
    // Description   :   Handles button click events
    // Author        :   Kartik Ganesh Jare
    // Date          :   04/08/2025
    // Prototype     :   public void actionPerformed(ActionEvent)
    // Input         :   ActionEvent
    // Output        :   void
    //
    ///////////////////////////////////////////////////////////////////
    
    public void actionPerformed(ActionEvent aobj)
    {   
        Object oobj = aobj.getSource();

        if(oobj == InsertLogButton)
        {
            InsertLog();
        }
        else if(oobj == DisplayLogButton)
        {
            DisplayLog();
        }
        else if(oobj == SummaryByDateButton)
        {
            SummaryByDate();
        }
        else if(oobj == SummaryBySubjectButton)
        {
            SummaryBySubject();
        }
        else if(oobj == ExportCSVButton)
        {
            ExportCSV();
        }
    }

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
        LocalDate Dateobj = LocalDate.now();

        String sub = JOptionPane.showInputDialog(frame, "Please provide the name of subject like C/C++/OS/DS");

        String durStr = JOptionPane.showInputDialog(frame, "Enter the time period of your study in hours");
        double dur = Double.parseDouble(durStr);

        String desc = JOptionPane.showInputDialog(frame, "Please provide the description about the study for future reference");

        StudyLog Studyobj = new StudyLog(Dateobj, sub, dur, desc);

        Database.add(Studyobj);

        JOptionPane.showMessageDialog(frame, "Study Log stored successfully");
    }

    ////////////////////////////////////////////////////////////////////
    //
    // Function Name :   DisplayLog
    // Description   :   Displays all stored logs in a scrollable window
    // Author        :   Kartik Ganesh Jare
    // Date          :   01/08/2025
    // Prototype     :   public void DisplayLog()
    // Input         :   None
    // Output        :   void
    //
    ////////////////////////////////////////////////////////////////////

    public void DisplayLog()
    {
        if(Database.isEmpty())
        {
            JOptionPane.showMessageDialog(frame, "Nothing to display as database is empty");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("----------- Log report from Marvellous Study Tracker -----------\n\n");

        for(StudyLog sobj : Database)
        {
            sb.append(sobj.toString()).append("\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));

        JOptionPane.showMessageDialog(frame, scrollPane, "Study Logs", JOptionPane.INFORMATION_MESSAGE);
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
        if(Database.isEmpty())
        {
            JOptionPane.showMessageDialog(frame, "Nothing to export, database is empty");
            return;
        }

        String fileName = "MarvellousStudy.csv";

        try(FileWriter fwobj = new FileWriter(fileName))
        {
            fwobj.write("Date,Subject,Duration,Description\n");

            for(StudyLog sobj : Database)
            {
                fwobj.write(sobj.getDate() + "," +
                            sobj.getSubject().replace(",", " ") + "," +
                            sobj.getDuration() + "," +
                            sobj.getDescrption().replace(",", " ") + "\n");
            }

            JOptionPane.showMessageDialog(frame, "CSV file created successfully: " + fileName);
        }
        catch (Exception eobj)
        {
            JOptionPane.showMessageDialog(frame, "Exception occurred while creating CSV.\nReport this issue to Marvellous Infosystems.");
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
        if(Database.isEmpty())
        {
            JOptionPane.showMessageDialog(frame, "Nothing to summarize, database is empty");
            return;
        }

        TreeMap<LocalDate, Double> tobj = new TreeMap<>();

        for(StudyLog sobj : Database)
        {
            LocalDate date = sobj.getDate();
            double dur = sobj.getDuration();

            tobj.put(date, tobj.getOrDefault(date, 0.0) + dur);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("------- Summary By Date from Marvellous Study Tracker -------\n\n");

        for(Map.Entry<LocalDate, Double> entry : tobj.entrySet())
        {
            sb.append("Date: ").append(entry.getKey()).append(" | Total Study: ").append(entry.getValue()).append("\n");
        }

        JOptionPane.showMessageDialog(frame, sb.toString(), "Summary By Date", JOptionPane.INFORMATION_MESSAGE);
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
        if(Database.isEmpty())
        {
            JOptionPane.showMessageDialog(frame, "Nothing to summarize, database is empty");
            return;
        }

        TreeMap<String, Double> tobj = new TreeMap<>();

        for(StudyLog sobj : Database)
        {
            String subject = sobj.getSubject();
            double dur = sobj.getDuration();

            tobj.put(subject, tobj.getOrDefault(subject, 0.0) + dur);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("------- Summary By Subject from Marvellous Study Tracker -------\n\n");

        for(Map.Entry<String, Double> entry : tobj.entrySet())
        {
            sb.append("Subject: ").append(entry.getKey()).append(" | Total Study: ").append(entry.getValue()).append("\n");
        }

        JOptionPane.showMessageDialog(frame, sb.toString(), "Summary By Subject", JOptionPane.INFORMATION_MESSAGE);
    }
}    

////////////////////////////////////////////////////////////////////
//
// Class Name  :   StudyTrackerStraterGUI
// Description :   Entry point of the Study Tracker application
// Author      :   Kartik Ganesh Jare
// Date        :   01/08/2025
//
////////////////////////////////////////////////////////////////////

class StudyTrackerStraterGUI
{
    public static void main(String A[])
    {   
        StudyTracker sobj = new StudyTracker("MarvellousStudyTacker",420,600);
    }        
}