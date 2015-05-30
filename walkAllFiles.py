# -*- coding: cp936 -*-
import os

f=open("F:/目录结构.txt",'w')


def listdir(leval,path):
    for i in os.listdir(path):
        f.write('\t'*(leval + 1) + '|-' + i)
        f.write('\n')
        if os.path.isdir(path+os.sep+i):
            listdir(leval+1, path+os.sep+i)

#path = 'c:'+os.sep+'ant'
path='D:/DMMGAMES/Hounds/Hounds/Archive'
#或者直接 path='C:/ant' 
f.write(path+os.sep)
f.write('\n')
listdir(0, path+os.sep)
f.close()
