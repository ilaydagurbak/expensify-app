#include <stdio.h>



void squares(int n,int arr[]){
for (int i=0; i < n ; i++ ){
	arr[i] = i*i;
	printf("%d ",arr[i]);
}


}

void test () {
	int a[10] = {1,2,3,4,5,6,7,8,9,10};
	int p = 4;
	squares(p,a);
	
}

int main() {
  test();
  return 0;
}