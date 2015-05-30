# -*- coding: cp936 -*-
import sys
default_encoding = 'utf-8'
if sys.getdefaultencoding() != default_encoding:
    reload(sys)
    sys.setdefaultencoding(default_encoding)
    
inf = open("F:/workspace/kancolle/log.txt",'r')
dahe=open("F:/workspace/kancolle/5-13-dahe.txt",'w')
dafeng=open("F:/workspace/kancolle/5-13-dafeng.txt",'w')
bsm=open("F:/workspace/kancolle/5-13-bsm.txt",'w')
maruyu=open("F:/workspace/kancolle/5-13-maruyu.txt",'w')

line = inf.readline()
while line:
    if line.find(u'結果:大和')>0:
        dahe.write(line)
    elif line.find(u'結果:大鳳')>0:
        dafeng.write(line)
    elif line.find(u'結果:Bismarck')>0:
        bsm.write(line)
    elif line.find(u'結果:まるゆ')>0:
        maruyu.write(line)
    line = inf.readline()

inf.close()
dahe.close()
dafeng.close()
bsm.close()
maruyu.close()


