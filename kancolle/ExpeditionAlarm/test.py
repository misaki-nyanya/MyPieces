from multiprocessing import Process
import time
import win32process
import os

def f2():
    print 'f2()in pid '+str(os.getpid())
    i=5
    while i>0:
        time.sleep(2)
        print 'grandson living'
        i=i-1
    win32process.ExitProcess(0)
    

def f():
    print 'f() in pid '+str(os.getpid())
    b=Process(target=f2)
    b.start()
    i=10
    while i>0:
        time.sleep(1)
        print 'son living'
        i=i-1
    print 'closing son'
    win32process.ExitProcess(0)

if __name__=='__main__':
    print 'main in pid '+str(os.getpid())
    a=Process(target=f)
    a.start()
    
#PyCTreeCtrl.GetChildItem
