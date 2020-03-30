public class Zadanie_2 {
    public int solution(int K, int[] A) {
        int amount=0;
        for(int i=0; i<A.length; i++) {
            if(A[i]<K)
                if(i!=A.length-1)
                    A[i+1]+=A[i];
            else
                amount++;
        }
        return amount;
    }
}
//https://app.codility.com/demo/results/training2956PB-JX3/