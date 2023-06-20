#include<stdio.h>
#define BUFFER 5
int S=1,buffer[BUFFER],count=0,empty,full;
void wait(int *i){
    while(*i<=0);
    *i--;
}
void signal(int *i){
    *i++;
}
void producer(int ele){
    empty=BUFFER-count;
    while(1){
    	if(count==BUFFER)
			break;
    	wait(&empty);
    	wait(&S);
        printf("Element produced is: %d\n",ele);
        buffer[count]=ele;
        count++;
        signal(&S);
    	signal(&full);
    }
    
}
void consumer(){
    int p;
    full=count-1;
    while(1){
    	if(count==0)
    		break;
    	wait(&full);
    	wait(&S);
        printf("Element consumed is: %d\n",buffer[count]);
        p=buffer[count];
        count--;
        signal(&S);
    	signal(&empty);
    }
}
void main(){
    printf("Enter the item: ");
    int n;
    scanf("%d",&n);
    producer(n);
    consumer();
}
