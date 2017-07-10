import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class Company{
    private String companyID;
    private String companyInformation;
    private int capacity;
    Company(String a,String b,int c){
        companyID=a;companyInformation=b;capacity=c;
    }
    HashMap<String,Graduate> gradSelected=new HashMap<>();
    HashMap<String,Integer> gradPref=new HashMap<>();
    //-------------------------------------------------------------------------
    public String getCompID(){return companyID;}
    public String getCompInformation(){return companyInformation;}
    public int getCompCapacity(){return capacity;}
    public void updateCapacity(int a){capacity=a;}
    //-------------------------------------------------------------------------
    public void addPref(int k,String p){
        Integer i=new Integer(k);
        gradPref.put(p,i);
    }
    public void clearPref(){gradPref.clear();}
    public HashMap getPrefList(){return gradPref;} 
    public HashMap getSelecList(){return gradSelected;} 
    public boolean ifPrefPresent(String k){
        return gradPref.containsKey(k);
    }
    public boolean ifSelecPresent(String k){
        return gradSelected.containsKey(k);
    }
    public boolean isFull(){return gradSelected.size()==capacity;}
    public void printPref(){
        Map<String,Integer> sortedGradPref=new TreeMap<String,Integer>(gradPref);
        Set s=sortedGradPref.entrySet();
        Iterator i=s.iterator();
        while(i.hasNext()){
            Map.Entry me=(Map.Entry) i.next();
            if(i.hasNext())System.out.print((String)me.getKey()+", ");
            else System.out.println((String)me.getKey());
        }
        
    }
    public void printSelec(){
        
        Set s=gradSelected.entrySet();
        Iterator i=s.iterator();
        System.out.println(s.size());
        while(i.hasNext()){
            Map.Entry me=(Map.Entry) i.next();
            if(i.hasNext())System.out.print((String)me.getKey()+", ");
            else System.out.println((String)me.getKey());            
        }
        
    }
}