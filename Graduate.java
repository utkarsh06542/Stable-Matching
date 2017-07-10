import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graduate {

    private String graduateID;
    private String graduateName;
    private double CGPA;
    private String compSelected=null;
    HashMap<String,Integer> compPref=new HashMap<>();
    Graduate(String a,String b,Double c){
        graduateID=a;graduateName=b;CGPA=c;
    }
    //------------------------------------------------------------------------------
    public String getGradID(){return graduateID;}
    public String getGradName(){return graduateName;}
    public double getGradCGPA(){return CGPA;}
    public String getCompSelected(){return compSelected;}
    public void updateCGPA(double a){CGPA=a;}
    public void addCompSelected(String a){compSelected=a;}
    public boolean isFree(){return compSelected==null;}
    public void setFree(){compSelected=null;}
    //------------------------------------------------------------------------------
    public void sort(){
        if(compPref.size()>0){
        Set entries=compPref.entrySet();
        Comparator<Map.Entry<String,Integer>> c1=new Comparator<Map.Entry<String,Integer>>(){
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                    return e1.getValue().compareTo(e2.getValue());
            }
            };
        List<Map.Entry<String,Integer>> listOfEntries = new ArrayList<Map.Entry<String,Integer>>(entries);
        Collections.sort(listOfEntries,c1);
        LinkedHashMap<String,Integer> sortedByValue = new LinkedHashMap<String,Integer>(listOfEntries.size());
        for(Map.Entry<String,Integer> entry : listOfEntries){ sortedByValue.put(entry.getKey(), entry.getValue()); }
        compPref=sortedByValue;
    }
    }
    public void clearPref(){compPref.clear();}
    public void addPref(int k,String p){
        Integer i=new Integer(k);
        compPref.put(p,i);
    }
    public HashMap getPrefList(){sort();return compPref;}
    public boolean ifPrefPresent(String k){
        return compPref.containsKey(k);
    }
    public void printList(){
        sort();
           Set s1=compPref.entrySet();
           Iterator i=s1.iterator();
           while(i.hasNext()){
               Map.Entry me=(Map.Entry)i.next();
               System.out.print((String)me.getKey()+" ");
           }
       }
    //-------------------------------------------------------------------------------
}
