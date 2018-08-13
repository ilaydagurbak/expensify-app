#include <stdio.h>



void arrsum(int n,int arr[], int *sump){
	int sum=0;
for (int i =0; i<n ; i++){
	sum += arr[i] ; 

}
   *sump=sum;

}

void test () {
	int a[10] = {1,2,3,4,5,6,7,8,9,10};
	int k=3;
	int p;
	arrsum(k,a,&p);
	printf( "p = %d\n",p);
}

int main() {
  test();
  return 0;
}