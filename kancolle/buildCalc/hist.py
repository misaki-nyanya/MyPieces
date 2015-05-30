import numpy as np
import matplotlib.pyplot as plt
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.mlab as mlab
import string


out = open("F:/workspace/kancolle/5-13-maruyu-time.txt",'r')
overall=open("F:/workspace/kancolle/5-13-all-time.txt",'r')

dahe=[]
alltime=[]
clock = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24]

line=out.readline()
while line:
    dahe.append(string.atof(line))
    line=out.readline()

line2=overall.readline()
while line2:
    alltime.append(string.atof(line2))
    line2=overall.readline()

out.close()
overall.close()
#plt.hist(dahe,bins=clock)
plt.subplot(211)
n, bins, patches=plt.hist([dahe,alltime],bins=clock)
percent=[]
for p in range(len(n[0])):
    percent.append(n[0][p]/n[1][p]*100)

for each in range(24):
    print str(each)+'\t'+str(percent[each])
#plt.hist(alltime,bins=clock)


plt.subplot(212)
plt.plot(range(24),percent,'-o')
plt.show()
