#from multiprocessing import Process
import time
from winsound import PlaySound,SND_ALIAS

def startout(seconds,name):
    startTime=time.time()
    while (time.time()-startTime)<seconds:
        time.sleep(1)
    PlaySound('SystemQuestion', SND_ALIAS)
    print name+' end'

if __name__ == '__main__':
#    p = Process(target=startout, args=(4,'alarm1'))
#    q = Process(target=startout, args=(2,'alarm2'))
#    p.start()
#    q.start()
    print 'end'
