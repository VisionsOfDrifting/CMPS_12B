//-----------------------------------------------------------------------------
// Recursion.java
// Template for CMPS 12B pa1.  Fill in the five recursive functions below.
//-----------------------------------------------------------------------------

class Recursion {
   
   // reverseArray1()
   // Places the leftmost n elements of X[] into the rightmost n positions in
   // Y[] in reverse order
   static void reverseArray1(int[] X, int n, int[] Y){
		 if(n > 0) { 
         int[n-1] X = int[Y.length-n] Y;
         reverseArray1(A, n-1);          // print leftmost n-1 elements, reversed
		}
   }

   // reverseArray2()
   // Places the rightmost n elements of X[] into the leftmost n positions in
   // Y[] in reverse order.
   static void reverseArray2(int[] X, int n, int[] Y){
		 if(n > 0) { 
         reverseArray1(A, n-1);          
		 int[X.length-n] X = int[n-1] Y;
		}
   }
   
   // reverseArray3()
   // Reverses the subarray X[i...j].
   static void reverseArray3(int[] X, int i, int j){

   }
   
   // maxArrayIndex()
   // returns the index of the largest value in int array X
   static int maxArrayIndex(int[] X, int p, int r){
	int q, max;
	if(p < r) {
         q = (p+r)/2;
         maxArrayIndex(A, p, q);
         maxArrayIndex(A, q+1, r);
         max = max(A, p, q, r);
		 return max;
		}
   }
   
   // minArrayIndex()
   // returns the index of the smallest value in int array X
   static int minArrayIndex(int[] X, int p, int r){
	int q, min;
	if(p < r) {
         q = (p+r)/2;
         minArrayIndex(A, p, q);
         minArrayIndex(A, q+1, r);
         min = min(A, p, q, r);
		 return min;
		}
   }

   // max()
   // merges sorted subarrays A[p..q] and A[q+1..r]
   public static int max(int[] A, int p, int q, int r){
      int n1 = q-p+1;
      int n2 = r-q;
      int[] L = new int[n1];
      int[] R = new int[n2];
	  int max = 0;
      int i, j, k,;

      for(i=0; i<n1; i++){
         L[i] = A[p+i];
      }
      for(j=0; j<n2; j++){ 
         R[j] = A[q+j+1];
      }

      i = 0; j = 0;
      for(k=p; k<=r; k++){
         if( i<n1 && j<n2 ){
            if( L[i]<R[j] ){
				if(max < L[i]){
					L[i] = max;
				}
			   A[k] = L[i];
               i++;
            }else{
				if(max < R[j]){
					R[j] = max;
				}
			   A[k] = R[j];
               j++;
            }
         }else if( i<n1 ){
            A[k] = L[i];
            i++;
         }else{ // j<n2
            A[k] = R[j];
            j++;
         }
      }
	  return max;
   }
   
   // min()
   // merges sorted subarrays A[p..q] and A[q+1..r]
   public static int min(int[] A, int p, int q, int r){
      int n1 = q-p+1;
      int n2 = r-q;
      int[] L = new int[n1];
      int[] R = new int[n2];
	  int min = 0;
      int i, j, k,;

      for(i=0; i<n1; i++){
         L[i] = A[p+i];
      }
      for(j=0; j<n2; j++){ 
         R[j] = A[q+j+1];
      }

      i = 0; j = 0;
      for(k=p; k<=r; k++){
         if( i<n1 && j<n2 ){
            if( L[i]<R[j] ){
				if(min > L[i]){
					L[i] = min;
				}
			   A[k] = L[i];
               i++;
            }else{
				if(min > R[j]){
					R[j] = min;
				}
			   A[k] = R[j];
               j++;
            }
         }else if( i<n1 ){
            A[k] = L[i];
            i++;
         }else{ // j<n2
            A[k] = R[j];
            j++;
         }
      }
	  return min;
   }
   
   // main()
   public static void main(String[] args){
      
      int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
      int[] B = new int[A.length];
      int[] C = new int[A.length];
      int minIndex = minArrayIndex(A, 0, A.length-1);
      int maxIndex = maxArrayIndex(A, 0, A.length-1);
      
      for(int x: A) System.out.print(x+" ");
      System.out.println(); 
      
      System.out.println( "minIndex = " + minIndex );  
      System.out.println( "maxIndex = " + maxIndex );  

      reverseArray1(A, A.length, B);
      for(int x: B) System.out.print(x+" ");
      System.out.println();
      
      reverseArray2(A, A.length, C);
      for(int x: C) System.out.print(x+" ");
      System.out.println();
      
      reverseArray3(A, 0, A.length-1);
      for(int x: A) System.out.print(x+" ");
      System.out.println();  
      
   }
   
}
/* Output:
-1 2 6 12 9 2 -5 -2 8 5 7
minIndex = 6
maxIndex = 3
7 5 8 -2 -5 2 9 12 6 2 -1
7 5 8 -2 -5 2 9 12 6 2 -1
7 5 8 -2 -5 2 9 12 6 2 -1
*/