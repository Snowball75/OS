n=int(input("Enter the no of frames : "))
pages=list(map(int,input("Enter the page string : ").split(" ")))
page_faults=0
f=0
frame=[ "" for _ in range(n)]
miss=set()
for i in range(len(pages)):
    if pages[i] not in frame:
        if i >= n and page_faults>=n :
            c=0
            miss.clear()
            for j in range(i,0,-1):
                if ( (pages[j-1] not in miss) and pages[j]!=pages[j-1]):
                    c+=1
                    miss.add(pages[j-1])
                if c==n:
                    frame[frame.index(pages[j-1])]=pages[i]
                    break
        else:
                frame[f]=pages[i]
                f+=1
        page_faults+=1
    print(frame)
print("No of page faults :",page_faults)
