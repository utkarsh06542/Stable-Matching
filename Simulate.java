import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Simulate {
public static void main(String args[]){
            //long startTime = System.currentTimeMillis();
    Tnp tnp=new Tnp();
    try{
            BufferedReader bufinput=new BufferedReader(new FileReader("C:/Users/Utkarsh Prabhakar/Documents/NetBeansProjects/rock/src/query3.txt"));
            String line="";
            while((line=bufinput.readLine())!=null){
                if(line.indexOf("ADD")==0){
                    if(line.indexOf("ADD COMPANY")==0){
                        int a=line.indexOf("Y");
                        int b=line.indexOf(",");
                        int c=line.indexOf(",",b+1);
                        try{String compID=line.substring(a+2,b);
                        String compDes=line.substring(b+2,c);
                        int capacity=Integer.parseInt(line.substring(c+2));
                        Company company=new Company(compID,compDes,capacity);
                        tnp.addComp(company);}catch(Exception e){System.out.println("Exception");}
                    }
                    if(line.indexOf("ADD GRADUATE")==0){
                        int a=line.indexOf("E");
                        int b=line.indexOf(",");
                        int c=line.indexOf(",",b+1);
                        int d=line.indexOf(",",c+1);
                        try{String gradID=line.substring(a+2,b);
                        String gradName=line.substring(b+2,c);
                        double cgpa=Double.parseDouble(line.substring(c+2,d));
                        Graduate gr=new Graduate(gradID,gradName,cgpa);
                        int p=d;int i=1;
                        while(line.indexOf(",",p+1)>0){
                            int x=line.indexOf(",",p+1);
                            String xy=line.substring(p+2,x);
                            gr.addPref(i,xy);
                            p=x;
                            i=i+1;
                        }
                        gr.addPref(i,line.substring(p+2));
                        
                        tnp.addGrad(gr);}catch(Exception e){System.out.println("Exception");}
                        
                }
                }
                if(line.indexOf("RANK GRADUATES")==0){
                    int a=line.indexOf("S");
                    int b=line.indexOf(",");
                    try{String c=line.substring(a+2,b);
                    Company comp=tnp.findComp(c);
                    ArrayList<String> temp=new ArrayList<>();
                    int p=b;int i=1;
                    comp.clearPref();
                    while(line.indexOf(",",p+1)>0){
                        int x=line.indexOf(",",p+1);
                        String s=line.substring(p+2,x);
                        comp.addPref(i,s);
                        p=x;
                        i=i+1;
                        if(!(tnp.findGrad(s).getPrefList().containsKey(c))){
                            temp.add(s);
                        }
                    }
                    comp.addPref(i,line.substring(p+2));if(!(tnp.findGrad(line.substring(p+2)).getPrefList().containsKey(c)))temp.add(line.substring(p+2));
                    tnp.stable.put(c,temp);
                    if(tnp.stable.size()>0){
                        if(tnp.stable.containsKey(c)){
                            ArrayList temp1=tnp.stable.get(c);
                            for(int i1=0;i1<temp1.size();i1++){
                                if(!(comp.getPrefList().containsKey(temp1.get(i1)))){
                                    temp1.remove(i1);
                                }
                            }
                            if(tnp.stable.get(c).size()==0)tnp.stable.remove(c);
                        }
                    }
                    if(tnp.stable.size()>0)tnp.stability=1;
                    else tnp.stability=0;
                    }catch(Exception e){System.out.println("Exception");}
                    
                }
                if(line.indexOf("UPDATE GRADUATE PREFERENCE")==0){
                    int a=line.indexOf("ENCE")+3;
                    int b=line.indexOf(",");
                    try{String c=line.substring(a+2,b);
                    Graduate gr=tnp.findGrad(c);
                    int p=b;int i=1;
                    HashMap temp1=(HashMap)gr.getPrefList().clone();
                    ArrayList<String> ar=new ArrayList<>();
                    gr.clearPref();
                    while(line.indexOf(",",p+1)>0){
                        int x=line.indexOf(",",p+1);
                        String s=line.substring(p+2,x);
                        gr.addPref(i,s);
                        p=x;
                        i=i+1;
                    }
                    
                    gr.addPref(i,line.substring(p+2));
                    gr.sort();
                    Set s1=temp1.entrySet();
                    Iterator i1=s1.iterator();
                    while(i1.hasNext()){
                        Map.Entry me=(Map.Entry) i1.next();
                        String s2=(String)me.getKey();
                        if(!(gr.getPrefList().containsKey(s2)))ar.add(s2);
                    }
                    for(int i2=0;i2<ar.size();i2++){
                        tnp.findComp(ar.get(i2)).getPrefList().remove(c);
                    }
                            }catch(Exception e){System.out.println("Exception");}
                }
                if(line.indexOf("UPDATE CGPA")==0){
                    int a=line.indexOf("CGPA")+3;
                    int b=line.indexOf(",");
                    try{String c=line.substring(a+2,b);
                    double d=Double.parseDouble(line.substring(b+2));
                    tnp.findGrad(c).updateCGPA(d);}catch(Exception e){System.out.println("Exception");}
                }
                if(line.indexOf("UPDATE CAPACITY")==0){
                    int a=line.indexOf("Y");
                    int b=line.indexOf(",");
                    try{String c=line.substring(a+2,b);
                    int d=Integer.parseInt(line.substring(b+2));
                    tnp.findComp(c).updateCapacity(d);}catch(Exception e){System.out.println("Exception");}
                }
                if(line.indexOf("MATCH")==0){
                    try{tnp.match();
                    tnp.printMatch();}catch(Exception e){System.out.println("Exception");}
                }
                if(line.indexOf("DISPLAY COMPANY")==0){
                    int a=line.indexOf("PANY")+3;
                    try{String s=line.substring(a+2);
                    Company c=tnp.findComp(s);
                    System.out.println(c.getCompInformation()+", "+c.getCompCapacity());}catch(Exception e){System.out.println("Exception");}
                }
                if(line.indexOf("DISPLAY GRADUATE")==0){
                    int a=line.indexOf("ATE")+3;
                    try{String s=line.substring(a+2);
                    Graduate c=tnp.findGrad(s);
                    System.out.print(c.getGradName()+", "+c.getGradCGPA()+", ");
                    c.sort();
                    Set s1=c.getPrefList().entrySet();
                    Iterator i1=s1.iterator();
                    while(i1.hasNext()){
                        Map.Entry me=(Map.Entry)i1.next();
                        if(i1.hasNext())System.out.print((String)me.getValue()+", ");
                        else System.out.println((String)me.getValue()); 
                    }
                    }catch(Exception e){System.out.println("Exception");}
                }
                if(line.indexOf("DISPLAY RANKING")==0){
                    int a=line.indexOf("G");
                    try{String s=line.substring(a+2);
                    tnp.findComp(s).printPref();}catch(Exception e){System.out.println("Exception");}
                }
                if(line.indexOf("DELETE GRADUATE")==0){
                    int a=line.indexOf("ATE")+2;
                    try{String s=line.substring(a+2);
                    tnp.removeGrad(s);}catch(Exception e){System.out.println("Exception");}
                }
                if(line.indexOf("DELETE COMPANY")==0){
                    int a=line.indexOf("Y");
                    try{String s=line.substring(a+2);
                    tnp.removeComp(s);}catch(Exception e){System.out.println("Exception");}
                }
               
              
            }
    }
    catch(FileNotFoundException e){System.out.println("Exception");} 
    catch(Exception e){System.out.println("Exception"+e);}
           // long stopTime = System.currentTimeMillis();
          //  long elapsedTime = stopTime - startTime;
        //System.out.println("time taken="+elapsedTime);

}    
}
