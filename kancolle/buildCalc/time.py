# -*- coding: cp936 -*-
import sys
import string

default_encoding = 'utf-8'
if sys.getdefaultencoding() != default_encoding:
    reload(sys)
    sys.setdefaultencoding(default_encoding)
    
out = open("F:/workspace/kancolle/5-13-maruyu-time.txt",'w')
#dahe=open("F:/workspace/kancolle/log.bak",'r')
#dafeng=open("F:/workspace/kancolle/5-13-dafeng.txt",'r')
#bsm=open("F:/workspace/kancolle/5-13-bsm.txt",'r')
maruyu=open("F:/workspace/kancolle/5-13-maruyu.txt",'r')

line = maruyu.readline()
lineNo=1
while line:
    
    time=line[-9:]
    #print str(lineNo)+'  '+time
    hour=string.atoi(time[0:2])
    minute=string.atoi(time[3:5])
    towrite=float(hour)+float(minute)/float(60)
    out.write(str(towrite)+'\n')
    line = maruyu.readline()
    lineNo=lineNo+1
    
out.close()
maruyu.close()
