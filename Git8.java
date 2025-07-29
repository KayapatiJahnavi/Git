class Solution {
    public static boolean isSorted(ArrayList<Integer> list) {
       if(list.size()<=1) return true;
       boolean ascending =true;
       boolean descending =true;
       for (int i=0;i<list.size()-1;i++){
           if(list.get(i)<list.get(i+1)){
               descending =false;
           }
           else if(list.get(i)>list.get(i+1)){
               ascending=false;
           }
       }
       return ascending|| descending;
        
    }
}
