from multiprocessing import Process,Queue
import time
import winsound

def startout(seconds,name,queue):
    startTime=time.time()
    while (time.time()-startTime)<seconds:
        time.sleep(1)
    winsound.PlaySound('SystemQuestion', winsound.SND_ALIAS)
    #winsound.Beep(6200,1000)
    #queue.put(name)
    print name+' end'

if __name__ == '__main__':
    #p_conn1, c_conn1 = Pipe()
    #p_conn2, c_conn2 = Pipe()
    center=Queue()
    p = Process(target=startout, args=(4,center,'alarm1'))
    q = Process(target=startout, args=(2,center,'alarm2'))
    p.start()
    q.start()
    while True:
        rev=center.get(timeout=7)
        print rev 
    print 'end'
