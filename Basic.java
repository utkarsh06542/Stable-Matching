import java.util.*;
class Tnp{
    HashMap<String,Graduate> grad=new HashMap<>();
    HashMap<String,Graduate> freeGrad=new HashMap<>();
    HashMap<String,Company> comp=new HashMap<>();
    HashMap<String,ArrayList<String>> stable=new HashMap<>();
    int numGrads=0;
    int numCapacity=0;
    int secret=0;
    int freeCount=0;
    int stability=0;
    //--------------------------------------------------------------------------
    public void initFreeCount(){freeCount=freeGrad.size();}
    public void addGrad(Graduate a){
        grad.put(a.getGradID(), a);
        freeGrad.put(a.getGradID(), a);
        numGrads=numGrads+1;
    }
    public void addComp(Company a){
        comp.put(a.getCompID(), a);
        numCapacity=numCapacity+a.getCompCapacity();
    }
    public Graduate findGrad(String a){return grad.get(a);}
    public Company findComp(String a){return comp.get(a);}
    
    public int checkStability(){
        int k=0;
        Set s1=comp.entrySet();
        Iterator i1=s1.iterator();
        while(i1.hasNext()){
            Map.Entry me=(Map.Entry) i1.next();
            Company c=(Company)me.getValue();
            HashMap temp=c.getPrefList();
            Set s2=temp.entrySet();
            Iterator i2=s2.iterator();
            while(i2.hasNext()){
                Map.Entry me1=(Map.Entry)i2.next();
                String s=(String)me1.getKey();
                Graduate g=grad.get(s);
                if(!(g.getPrefList().containsKey((String)me.getKey()))){k=1;break;}
            }
        }
        if(k==0){stability=0;return 0;}
        else{stability=1;return 1;}
    }
    //--------------------------------------------------------------------------
    public void removeGrad(String a){
        if(grad.containsKey(a)){
            Graduate g=grad.get(a);
            if(!g.isFree()){comp.get(g.getCompSelected()).getSelecList().remove(a);}
            Set s=g.getPrefList().entrySet();
            Iterator i=s.iterator();
            while(i.hasNext()){
               Map.Entry me=(Map.Entry) i.next();
               String s1=(String)me.getKey();
               Company c=comp.get(s1);
               if(c!=null && c.getPrefList().containsKey(a))c.getPrefList().remove(a);
            }
            grad.remove(a);
            freeGrad.remove(a);
            numGrads=numGrads-1;
        }
        else System.out.println("Exception");
            
    }
    public void removeComp(String a){
        if(comp.containsKey(a)){
            Company c=comp.get(a);
            Set s1=c.getSelecList().entrySet();
            Set s2=c.getPrefList().entrySet();
            Iterator i1=s1.iterator();
            Iterator i2=s2.iterator();
            
            while(i1.hasNext()){
                Map.Entry me=(Map.Entry)i1.next();
                String id=(String)me.getKey();
                Graduate g=grad.get(id);
                g.setFree();
            }
            while(i2.hasNext()){
                Map.Entry me=(Map.Entry)i2.next();
                String id=(String)me.getKey();
                Graduate g=grad.get(id);
                g.getPrefList().remove(a);                
            }
            numCapacity=numCapacity-c.getCompCapacity();
            comp.remove(a);
        }
        else System.out.println("Exception");
    }
    public void clearSelectedList(){
        Set s=comp.entrySet();
        Iterator i=s.iterator();
        while(i.hasNext()){
            Map.Entry me=(Map.Entry) i.next();
            Company c=(Company)me.getValue();
            c.getSelecList().clear();
        }
        Set s1=grad.entrySet();
        Iterator i1=s1.iterator();
        while(i1.hasNext()){
            Map.Entry me=(Map.Entry) i1.next();
            Graduate g=(Graduate)me.getValue();
            g.setFree();
            if(!freeGrad.containsKey(g.getGradID()))freeGrad.put(g.getGradID(),g);
        }
      
    }
    public boolean ifFullAdd(Company c,String a){
        HashMap temp=c.getSelecList();
        int w=temp.size();
        if(w<c.getCompCapacity()){
            Graduate g=grad.get(a);
            temp.put(a,g);
            g.addCompSelected(c.getCompID());
            freeCount--;
            freeGrad.remove(a);
            return true;
        }
        else if(w==c.getCompCapacity()){
            Set set1=temp.entrySet();
            Set set2=c.getPrefList().entrySet();
            int k=0,result=0;
            HashMap<String,Integer> ar=new HashMap<>();
            Iterator i2=set2.iterator();
            while(i2.hasNext() && k<=c.getCompCapacity()+1){
                Map.Entry me=(Map.Entry)i2.next();
                String s=(String)me.getKey();
                if(c.getSelecList().containsKey(s) || s.compareTo(a)==0){
                    k=k+1;
                    ar.put(s,(Integer)me.getValue());
                }
            }   
            Set set3=ar.entrySet();
            Iterator i3=set3.iterator();
            String q="";int p=0;
            while(i3.hasNext()){
                Map.Entry me=(Map.Entry)i3.next();
                if((int)(Integer)me.getValue()>p){
                    q=(String)me.getKey();
                    p=(int)(Integer)me.getValue();
                }
            }
            if(q.compareTo(a)!=0){
                Graduate v=grad.get(a);
                temp.remove(q);
                Graduate qw=grad.get(q);
                qw.setFree();
                freeGrad.put(q,qw);
                temp.put(a,v);
                freeGrad.remove(a);
                v.addCompSelected(c.getCompID());
                return true;
            }
            else return false;
        }
        else return false;
    }
    public void match(){
        if(stability==1){System.out.println("Exception");}
        else{
        if(secret!=0)clearSelectedList();
        secret=secret+1;
        HashMap<String,Graduate> temp=new HashMap<>();
        initFreeCount();
        while(freeCount>0){
            Graduate gr=null;
            Set s=grad.entrySet();
            Iterator i=s.iterator();
            while(i.hasNext()){
                Map.Entry me=(Map.Entry)i.next();
                if(!temp.containsKey(me.getKey())){
                    Graduate g=(Graduate)me.getValue();
                    if(g.isFree()){
                        gr=g;
                        break;
                    }
                }
            }
            if(gr!=null){
            Set s1=gr.getPrefList().entrySet();
            Iterator i1=s1.iterator();
            int num=0;
            while(i1.hasNext()){
                Map.Entry me=(Map.Entry)i1.next();           
                Company c=comp.get((String)me.getKey());
                if(c!=null){if(ifFullAdd(c,gr.getGradID())){
                    num=1;
                    break;} }               
            }
            if(num==0){temp.put(gr.getGradID(), gr);}
        }
            else break;}
    }
    }
    public void printMatch(){
        if(stability==0){
        Set s1=grad.entrySet();
        Iterator i1=s1.iterator();
        while(i1.hasNext()){
         Map.Entry me=(Map.Entry) i1.next();
         Graduate g1=(Graduate)me.getValue();
         String s=g1.getCompSelected();
         if(s==null)s=" ";
         System.out.println(me.getKey()+", "+s);
    }
    }
    }
    
}