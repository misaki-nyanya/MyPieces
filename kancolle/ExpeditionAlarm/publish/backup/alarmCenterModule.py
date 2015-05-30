from multiprocessing import Process
import alarm

class alarmCenter:
    
    def __init__(self):
        self.alarmList={}

    def newAlarm(self,seconds,name):
        ala=Process(target=alarm.startout, args=(seconds,name))
        ala.start()
        self.alarmList[name]=ala
        print name+' is created'

    def stopAlarm(self,name):
        try:
            self.alarmList[name].terminate()
            print name+' is terminated'
        except Exception:
            pass
if __name__ == '__main__':
    a=alarmCenter()
    a.newAlarm(4,'alarm1')
    a.newAlarm(2,'alarm2')
    a.stopAlarm('alarm2')
    print 'end'
