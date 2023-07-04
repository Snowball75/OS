n=int(input("Enter the no of frames : "))
pages=list(map(int,input("Enter the page string : ").split(" ")))
page_faults=0
f=0
frame=[ "" for _ in range(n)]
for i in pages:
    if i not in frame:
        frame[f]=i
        page_faults+=1
        f+=1
        if f==n:
            f=0
    print(frame)
print("No of page faults :",page_faults)
