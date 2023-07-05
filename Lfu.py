n=int(input("Enter the no of frames : "))
pages=list(map(int,input("Enter the page string : ").split(" ")))
page_faults=0
f=0
frame=[ "" for _ in range(n)]
freq={}
freq=freq.fromkeys(pages,0)
def get_lfu(r,p):
    m=r
    s=[]
    for i in freq.keys():
        if freq[i]!=0 and freq[i]<freq[m]:
            m=i
    s=[i for i in freq if freq[i]==freq[m]]
    if len(s)==1:
        return m 
    fifo={}
    fifo=fifo.fromkeys(s,0)   
    for i in s:
        for j in range(p,0,-1):
            if pages[j] not in frame:
                break
            if pages[j]==i:
                fifo[i]=j
    return min(fifo,key=fifo.get)
for i in range(len(pages)):
    if pages[i] not in frame:
        if i >= n and page_faults>=n :
            lfu=get_lfu(pages[i-1],i-1)
            freq[lfu]=0
            frame[frame.index(lfu)]=pages[i]
        else:
            frame[f]=pages[i]
        f+=1
        if f==n:
            f=0
        page_faults+=1
    freq[pages[i]]+=1
    print(frame)
print("No of page faults :",page_faults)
