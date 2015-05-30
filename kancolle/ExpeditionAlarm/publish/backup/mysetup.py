
from distutils.core import setup 
import glob 
import py2exe 

setup(console=["mainFrame.py"],  )

'''
from distutils.core import setup  
import py2exe  
import sys  
includes = ["encodings", "encodings.*"]    
sys.argv.append("py2exe")  
options = {"py2exe":   { "bundle_files": 1 }    
                }   
setup(options = options,  
      zipfile=None,   
      windows = [{"script":'mainFrame.py', 'icon_resources':[(1, 'icon.ico')]}])
'''
