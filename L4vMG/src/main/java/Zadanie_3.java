public class Zadanie_3 {
    public int solution(int[] A, int[] B) {
        int amount=0;
        int minA=0, minB=0;
        if(A.length>0) {
            minA=A[0];
            minB=B[0];
            amount++;
        }
        for(int i=1; i<B.length; i++) {
            if(!((minA<=A[i] && A[i]<=minB)||(A[i]<=minA && minA<=B[i]))) {
                amount++;
                minA=A[i];
                minB=B[i];
            }
        }
        return amount;
    }
}
//https://app.codility.com/demo/results/trainingG5QRK9-KS3/