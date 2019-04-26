import java.util.*;

public class MyMap{

   public static class Pair {
       private String str;
       private int value;
      
     //constructor 
     public Pair(String str, Integer value){
         this.str = str;
         this.value = value;
     }    
      
     //get the key 
     public String getKey(){ return str;}
     
     //get the value 
     public Integer getValue(){return value;}
     
     //set the a new value and return the old one
     public Integer setValue(Integer value){
         int old = this.value;
         this.value = value;
         return old;
     }
   }//closing Pair class
   
   
   private ArrayList<LinkedList<Pair>> theTable;
   private int numElements;
   
   //Constructors
   public MyMap(int numBuckets) {
        theTable = new ArrayList<>();
        
        for(int i=0; i<numBuckets; i++)
            theTable.add(new LinkedList<>());
        numElements = 0;
        
    }
   
   
   public MyMap() {
        this(10);
    }
    
    
//bucket number    
    private int whichBucket(Object data) {
        return data.hashCode() % theTable.size();
    }

//contains a specific key    
    public boolean containsKey(String key) {
        int bucketNo = whichBucket(key);
        LinkedList<Pair> listToSearch = theTable.get(bucketNo);
        for(Pair p: listToSearch)
            {
              if(p.getKey().equals(key))
               return true;
            }   
        return false;
    }     
    
//contains a value    
     public boolean containsValue(Integer value) {
        int bucketNo = whichBucket(value);
        LinkedList<Pair> listToSearch = theTable.get(bucketNo);
        for(Pair p: listToSearch)
            {
              if(p.getValue().equals(value))
               return true;
            }   
        return false;
    }  

//get by key    
    public Integer get(String key){
        int bucketNo = whichBucket(key);
        LinkedList<Pair> listToSearch = theTable.get(bucketNo);
        for(Pair p: listToSearch)
            {
              if(p.getKey().equals(key))
               return p.getValue();
            }   
        return null;
    }

//return a set of pairs
    public Set<Pair> entrySet(){
         Set<Pair> set = new HashSet<>();
         
         for(LinkedList<Pair> linkList: theTable){
            for(Pair p: linkList)
                set.add(p);
         }       
    return set;
    }

//return a list with all values  
   public List<Integer> values(){
         ArrayList<Integer> list = new ArrayList<>();
         
         for(LinkedList<Pair> linkList: theTable){
            for(Pair p: linkList)
                list.add(p.getValue());
         }
         return list;
   }  
   
//return a set of all keys
    public Set<String> keySet(){
         Set<String> kset = new HashSet<>();
         
         for(LinkedList<Pair> linkList: theTable){
            for(Pair p: linkList){
                kset.add(p.getKey());}
         }       
    return kset;
    }
    
//size  
   public int size(){
      return numElements;
   }
   
//is empty      
   public boolean isEmpty(){
      return size() == 0;
   }
         
//putt  
    public void put(String key, Integer value) {
        boolean notThere = true;
        
         for(LinkedList<Pair> linkList: theTable){
            for(Pair p: linkList){
               if(p.getKey().equals(key))
                  p.setValue(value);
                  notThere = false;
            }
         }   
        
        if(notThere){ 
           int bucketNo = whichBucket(key);
           theTable.get(bucketNo).add(new Pair(key, value));
           numElements++;
        }
    }
   
//remove 
    public boolean remove(Object key) {
        
        for(LinkedList<Pair> linkList: theTable){
            for(Pair p: linkList){
               if(p.getKey().equals(key)){
                  p.setValue(null);
                  numElements--;
                  return true;
               }  
            }
         } 
         return false;
    }    
}
