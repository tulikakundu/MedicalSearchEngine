package com.example.mainactivity;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Result extends AppCompatActivity {

    public  float[] yData =new float[12];

    public  String[] xData =new String[12];
   //private int[] yData = {0,1,0,1,1,0,0};
    //private String[] xData = {"Mitch", "Jessica" , "Mohammad" , "Kelsey", "Sam", "Robert", "Ashley"};
    PieChart pieChart;
    DatabaseReference dref;
    String arr[]=new String[12];        // !!!!TODO generalise 12
    HashMap<String, Integer> hm = new HashMap<String, Integer>();
    void initial()
    {
        for(int i=0;i<arr.length;i++)
        {
            arr[i]="NULLL";
        }
    }
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public void predict()
    {
        Map<String, Integer> hm1 = sortByValue(hm);
        int j=0;
        for (Map.Entry<String, Integer> en : hm1.entrySet()) {
            //Log.d("gibber",en.getKey());
                String s=en.getKey();
                int v=en.getValue();
                yData[j]=v;
                xData[j]=s;

                Log.d("xdata",xData[j]);
                Log.d("ydata",Float.toString(yData[j]));
                j++;
        }
        pieChart = (PieChart) findViewById(R.id.idPieChart);

        pieChart.setDescription("Diseases by their chances");
        pieChart.setRotationEnabled(true);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(1);
        pieChart.setCenterText("Super Cool Chart");
        pieChart.setCenterTextSize(10);


        addDataSet();
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d("x1", "onValueSelected: Value select from chart.");
                Log.d("x2", "onValueSelected: " + e.toString());
                Log.d("x3", "onValueSelected: " + h.toString());

                int pos1 = e.toString().indexOf("(sum): ");
                System.out.println("pos1 start vlaue is");
                System.out.println(pos1);
                try{
                    String sales = e.toString().substring(pos1 + 7);
                    System.out.println("sales is");
                    System.out.println(sales);
                    //TODO
                    for (int i = 0; i < yData.length; i++) {
                        if (yData[i] == Float.parseFloat(sales)) {
                            pos1 = i;
                            break;
                        }
                    }

                    String employee = xData[pos1];
                    Toast.makeText(Result.this, "Most probably - "+employee, Toast.LENGTH_LONG).show();
                    Intent i =new Intent(Result.this,Treatment.class);
                    i.putExtra("disease_name",employee);
                   startActivity(i);

                }
                catch(Exception e1)
                {

                    System.out.println("Erroe1 "+e1);
                }



                //Toast.makeText(MainActivity.this, "Employee " + employee + "\n" + "Sales: $" + sales + "K", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }
    private void addDataSet() {
        Log.d("hello", "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Employee Sales");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.rgb(198, 218, 38));
        colors.add(Color.rgb(218, 38, 108));
        colors.add(Color.rgb(244, 143, 177
        ));
        colors.add(Color.rgb(38, 198, 218));
        colors.add(Color.rgb(255, 204, 128));
        colors.add(Color.rgb(	0, 230, 118));
        pieDataSet.setColors(colors);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
        Log.d("end", "addDataSet ended");


    }
    public void fun()
    {
        FirebaseDatabase.getInstance().getReference().child("DISEASE")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            String str1=snapshot.child("SYMPTOMS").getValue().toString();
                            Log.d("strinngg",str1);
                            initial();
                            StringTokenizer s2= new  StringTokenizer(str1,",");
                            try
                            {
                                int i=0;
                                while(s2.hasMoreTokens())
                                {
                                    arr[i]=s2.nextToken();
                                    Log.d("Array",arr[i]);
                                    i++;
                                }
                            }
                            catch (Exception e)
                            {
                                System.out.println("Error in 77 "+e);
                            }


                            //arr now contains tokenised string
                            int count =0;int k=0;
                            boolean x;
                            for(int i=0;i<arr.length;i++)
                            {
                                for(int j=0;j<arr.length;j++)
                                {
                                    x= arr[i].equals(Find_Disease.results[j].toUpperCase());

                                    if(x==true)
                                    {
                                        count++;

                                    }
                                }
                            }
                            String unique = snapshot.child("UNIQUE SYMPTOMS").getValue().toString();
                            boolean y;
                            for(int i=0;i<arr.length;i++)
                            {
                                y=unique.equals(Find_Disease.results[i].toUpperCase());
                                if(y==true)
                                {
                                    count = count*3;break;
                                }
                            }

                            //count now contains score
                            String dname=snapshot.getKey().toString();
                            hm.put(dname,count);
                        }//end of datbase for loop
                        predict();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //added comment here
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        fun();
        try
        {
            Log.d("TEST1",xData[1]);
        }
        catch(Exception e2)
        {
            System.out.println("Error in 609 here!"+e2);
        }




    }//end of oncreate




}   //end of class