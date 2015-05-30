from multiprocessing import Process,Queue,Pipe
import alarm

class alarmCenter:
    
    def __init__(self):
        self.queue=Queue()
        self.alarmList={}

    def newAlarm(self,seconds,name):
        ala=Process(target=alarm.startout, args=(seconds,name,self.queue))
        ala.start()
        self.alarmList[name]=ala
        print name+' is created'

    def stopAlarm(self,name):
        try:
            self.alarmList[name].terminate()
            print name+' is terminated'
        except Exception:
            pass
'''
    def startListen(self):
        lis=Process(target=self.listener, )
        lis.start()

    def listener(self):        
        print 'center is listening'
        while True:
            recv=self.queue.get()
            do(recv)
            
    def do(self):
        if recv=='alarm1':
                self.frame.m_release1()
        elif recv=='alarm2':
                self.frame.m_release2()
        elif recv=='alarm3':
                self.frame.m_release3()
        else:
                pass
        
'''
if __name__ == '__main__':
    p_conn, c_conn = Pipe()
    a=alarmCenter(c_conn)
    a.newAlarm(4,'alarm1')
    a.newAlarm(2,'alarm2')
    print p_conn.recv()
    print p_conn.recv()
    print 'end'
