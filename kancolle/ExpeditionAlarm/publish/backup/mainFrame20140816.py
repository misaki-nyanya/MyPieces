#!/usr/bin/env python
# -*- coding: utf-8 -*- 

###########################################################################
## Python code generated with wxFormBuilder (version Jun  5 2014)
## http://www.wxformbuilder.org/
##
## PLEASE DO "NOT" EDIT THIS FILE!
###########################################################################

from multiprocessing import Process,freeze_support
import time
import winsound
import wx
import string
import os
import sys

def startout(seconds,name):
    freeze_support()
    print name+':pid '+str(os.getpid())+' is created'
    startTime=time.time()
    while (time.time()-startTime)<seconds:
        time.sleep(1)
    winsound.PlaySound('SystemQuestion', winsound.SND_ALIAS)
    print name+' end'

class alarmCenter:
    
    def __init__(self):
        #self.queue=Queue()
        self.alarmList={'alarm1':None,'alarm2':None,'alarm3':None}

    def newAlarm(self,seconds,name):
        if self.alarmList[name]!=None:
            if self.alarmList[name].is_alive():
                return False
        ala=Process(target=startout, args=(seconds,name))
        ala.deamon=True
        ala.start()
        self.alarmList[name]=ala
        print '--------------alarmList Below-------------'
        for each in self.alarmList.keys():
            if self.alarmList[each]!=None:
                print each
                print self.alarmList[each].pid
        print '--------------alarmList above-------------'
        return True

    def stopAlarm(self,name):
        try:
            print name+':pid '+\
                str(self.alarmList[name].pid)+' is terminating'
            self.alarmList[name].terminate()
            self.alarmList[name].join()
            self.alarmList[name]=None
        except Exception:
            pass

    def terminateAll(self):
        for each in self.alarmList.keys():
            if self.alarmList[each]!=None:
                self.alarmList[each].terminate()
                print each+'terminated '

###########################################################################
## Class aboutFrame
###########################################################################

class aboutFrame ( wx.Frame ):
	
	def __init__( self, parent ):
		wx.Frame.__init__ ( self, parent, id = wx.ID_ANY, title = u"about", pos = wx.DefaultPosition, size = wx.Size( 300,207 ), style = wx.DEFAULT_FRAME_STYLE|wx.TAB_TRAVERSAL )
		
		self.SetSizeHintsSz( wx.DefaultSize, wx.DefaultSize )
		
		bSizer2 = wx.BoxSizer( wx.VERTICAL )
		
		self.m_staticText8 = wx.StaticText( self, wx.ID_ANY, u"\n       感谢使用~\n   本程序由Rillke制作\n    请勿用于商业用途\n      有问题请联系\n     企鹅635415677", wx.DefaultPosition, wx.DefaultSize, 0 )
		self.m_staticText8.Wrap( -1 )
		self.m_staticText8.SetFont( wx.Font( 16, 70, 90, 92, False, "宋体" ) )
		
		bSizer2.Add( self.m_staticText8, 1, wx.ALL|wx.EXPAND, 5 )
		
		self.m_buttonAboutClose = wx.Button( self, wx.ID_ANY, u"close", wx.DefaultPosition, wx.DefaultSize, 0 )
		bSizer2.Add( self.m_buttonAboutClose, 0, wx.ALL|wx.ALIGN_CENTER_HORIZONTAL, 5 )
		
		
		self.SetSizer( bSizer2 )
		self.Layout()
		
		self.Centre( wx.BOTH )
		
		# Connect Events
		self.Bind( wx.EVT_CLOSE, self.aboutFrameOnClose )
		self.m_buttonAboutClose.Bind( wx.EVT_BUTTON, self.m_buttonAboutCloseOnButtonClick )
	
	def __del__( self ):
		pass
	
	
	# Virtual event handlers, overide them in your derived class
	def aboutFrameOnClose( self, event ):
		event.Skip()
	
	def m_buttonAboutCloseOnButtonClick( self, event ):
		self.Close(True)
		
###########################################################################
## Class mainFrame
###########################################################################

class mainFrame ( wx.Frame ):
	
	def __init__( self, parent, alaC):
		wx.Frame.__init__ ( self, parent, id = wx.ID_ANY, title = u"Kancolle Expedition Alarm", pos = wx.DefaultPosition, size = wx.Size( 494,184 ), style = wx.DEFAULT_FRAME_STYLE|wx.TAB_TRAVERSAL )
		
		self.SetSizeHintsSz( wx.DefaultSize, wx.DefaultSize )
		
		self.m_menubar1 = wx.MenuBar( 0 )
		self.m_menu1 = wx.Menu()
		self.m_menuItem1 = wx.MenuItem( self.m_menu1, wx.ID_ANY, u"About", wx.EmptyString, wx.ITEM_NORMAL )
		self.m_menu1.AppendItem( self.m_menuItem1 )
		
		self.m_menubar1.Append( self.m_menu1, u"Menu" ) 
		
		self.SetMenuBar( self.m_menubar1 )
		
		fgSizer3 = wx.FlexGridSizer( 3, 5, 0, 0 )
		fgSizer3.SetFlexibleDirection( wx.BOTH )
		fgSizer3.SetNonFlexibleGrowMode( wx.FLEX_GROWMODE_SPECIFIED )
		
		self.m_staticText1 = wx.StaticText( self, wx.ID_ANY, u"kantai2", wx.DefaultPosition, wx.DefaultSize, 0 )
		self.m_staticText1.Wrap( -1 )
		fgSizer3.Add( self.m_staticText1, 0, wx.ALL|wx.ALIGN_CENTER_HORIZONTAL|wx.ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_textCtrl1 = wx.TextCtrl( self, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.DefaultSize, 0 )
		fgSizer3.Add( self.m_textCtrl1, 0, wx.ALL|wx.ALIGN_CENTER_HORIZONTAL|wx.ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_gobutton1 = wx.Button( self, wx.ID_ANY, u"Go", wx.DefaultPosition, wx.DefaultSize, 0 )
		fgSizer3.Add( self.m_gobutton1, 0, wx.ALL|wx.ALIGN_CENTER_HORIZONTAL|wx.ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_stopbutton1 = wx.Button( self, wx.ID_ANY, u"Stop", wx.DefaultPosition, wx.DefaultSize, 0 )
		fgSizer3.Add( self.m_stopbutton1, 0, wx.ALL|wx.ALIGN_CENTER_HORIZONTAL|wx.ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_staticText1Err = wx.StaticText( self, wx.ID_ANY, u"请输入一个整数(分钟)", wx.DefaultPosition, wx.DefaultSize, 0 )
		self.m_staticText1Err.Wrap( -1 )
		self.m_staticText1Err.SetForegroundColour( wx.Colour( 255, 0, 0 ) )
		self.m_staticText1Err.Show(False)
		
		fgSizer3.Add( self.m_staticText1Err, 0, wx.ALL|wx.ALIGN_CENTER_HORIZONTAL|wx.ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_staticText2 = wx.StaticText( self, wx.ID_ANY, u"kantai3", wx.DefaultPosition, wx.DefaultSize, 0 )
		self.m_staticText2.Wrap( -1 )
		fgSizer3.Add( self.m_staticText2, 0, wx.ALL|wx.ALIGN_CENTER_HORIZONTAL|wx.ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_textCtrl2 = wx.TextCtrl( self, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.DefaultSize, 0 )
		fgSizer3.Add( self.m_textCtrl2, 0, wx.ALL|wx.ALIGN_CENTER_HORIZONTAL|wx.ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_gobutton2 = wx.Button( self, wx.ID_ANY, u"Go", wx.DefaultPosition, wx.DefaultSize, 0 )
		fgSizer3.Add( self.m_gobutton2, 0, wx.ALL|wx.ALIGN_CENTER_HORIZONTAL|wx.ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_stopbutton2 = wx.Button( self, wx.ID_ANY, u"Stop", wx.DefaultPosition, wx.DefaultSize, 0 )
		fgSizer3.Add( self.m_stopbutton2, 0, wx.ALL|wx.ALIGN_CENTER_HORIZONTAL|wx.ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_staticText2Err = wx.StaticText( self, wx.ID_ANY, u"这个格子的闹钟已经在运行", wx.DefaultPosition, wx.DefaultSize, 0 )
		self.m_staticText2Err.Wrap( -1 )
		self.m_staticText2Err.SetForegroundColour( wx.Colour( 255, 0, 0 ) )
		self.m_staticText2Err.Show(False)
		
		fgSizer3.Add( self.m_staticText2Err, 0, wx.ALL|wx.ALIGN_CENTER_HORIZONTAL|wx.ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_staticText3 = wx.StaticText( self, wx.ID_ANY, u"kantai4", wx.DefaultPosition, wx.DefaultSize, 0 )
		self.m_staticText3.Wrap( -1 )
		fgSizer3.Add( self.m_staticText3, 0, wx.ALL|wx.ALIGN_CENTER_HORIZONTAL|wx.ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_textCtrl3 = wx.TextCtrl( self, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.DefaultSize, 0 )
		fgSizer3.Add( self.m_textCtrl3, 0, wx.ALL|wx.ALIGN_CENTER_HORIZONTAL|wx.ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_gobutton3 = wx.Button( self, wx.ID_ANY, u"Go", wx.DefaultPosition, wx.DefaultSize, 0 )
		fgSizer3.Add( self.m_gobutton3, 0, wx.ALL|wx.ALIGN_CENTER_HORIZONTAL|wx.ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_stopbutton3 = wx.Button( self, wx.ID_ANY, u"Stop", wx.DefaultPosition, wx.DefaultSize, 0 )
		fgSizer3.Add( self.m_stopbutton3, 0, wx.ALL|wx.ALIGN_CENTER_HORIZONTAL|wx.ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_staticText3Err = wx.StaticText( self, wx.ID_ANY, u"请输入一个整数(分钟)", wx.DefaultPosition, wx.DefaultSize, 0 )
		self.m_staticText3Err.Wrap( -1 )
		self.m_staticText3Err.SetForegroundColour( wx.Colour( 255, 0, 0 ) )
		self.m_staticText3Err.Show(False)
		
		fgSizer3.Add( self.m_staticText3Err, 0, wx.ALL|wx.ALIGN_CENTER_VERTICAL|wx.ALIGN_CENTER_HORIZONTAL, 5 )
		
		
		self.SetSizer( fgSizer3 )
		self.Layout()
		
		self.Centre( wx.BOTH )
		
		# Connect Events
		self.Bind( wx.EVT_CLOSE, self.mainFrameOnClose )
		self.Bind( wx.EVT_MENU, self.m_menuItem1MenuSelection, id = self.m_menuItem1.GetId() )
		self.m_gobutton1.Bind( wx.EVT_BUTTON, self.m_gobutton1OnButtonClick )
		self.m_stopbutton1.Bind( wx.EVT_BUTTON, self.m_stopbutton1OnButtonClick )
		self.m_gobutton2.Bind( wx.EVT_BUTTON, self.m_gobutton2OnButtonClick )
		self.m_stopbutton2.Bind( wx.EVT_BUTTON, self.m_stopbutton2OnButtonClick )
		self.m_gobutton3.Bind( wx.EVT_BUTTON, self.m_gobutton3OnButtonClick )
		self.m_stopbutton3.Bind( wx.EVT_BUTTON, self.m_stopbutton3OnButtonClick )

                # alarm
                self.ala=alaC
                #alaC.setFrame(self)

                print 'mainFrame:pid '+str(os.getpid())+' is created'
                
	def __del__( self ):
		pass
	
	
	# Virtual event handlers, overide them in your derived class
	def mainFrameOnClose( self, event ):
                self.ala.terminateAll()
                #self.Close(True)
                #os.kill(os.getpid(),1)
		event.Skip()
	
	def m_menuItem1MenuSelection( self, event ):
		self.m_aboutFrame=aboutFrame(self)
		self.m_aboutFrame.Show()
	
	def m_gobutton1OnButtonClick( self, event ):
                if self.m_textCtrl1.Enabled==True:
                        try:
                                self.m_staticText1Err.Show(False)
                                kantai1=string.atoi(self.m_textCtrl1.Value)*60
                                success=self.ala.newAlarm(kantai1,'alarm1')
                                if success:
                                    self.m_textCtrl1.Disable()
                                    self.m_gobutton1.Disable()
                                    wx.FutureCall(kantai1*1000,self.m_release1)
                                else:
                                    self.m_staticText2Err.Show(False)
                        except(ValueError):
                                self.m_staticText1Err.Show()
		#event.Skip()
                                
	def m_release1(self):
                self.m_textCtrl1.Enable()
                self.m_gobutton1.Enable()
                
	def m_stopbutton1OnButtonClick( self, event ):
                self.ala.stopAlarm('alarm1')
                self.m_textCtrl1.Enable()
                self.m_gobutton1.Enable()
		#event.Skip()
	
	def m_gobutton2OnButtonClick( self, event ):
		if self.m_textCtrl2.Enabled==True:
                        try:
                                self.m_staticText1Err.Show(False)
                                kantai2=string.atoi(self.m_textCtrl2.Value)*60
                                success=self.ala.newAlarm(kantai2,'alarm2')
                                if success:
                                    self.m_textCtrl2.Disable()
                                    self.m_gobutton2.Disable()
                                    wx.FutureCall(kantai2*1000,self.m_release2)
                                else:
                                    self.m_staticText2Err.Show(False)
                        except(ValueError):
                                self.m_staticText1Err.Show()
		#event.Skip()
                        
        def m_release2(self):
                self.m_textCtrl2.Enable()
                self.m_gobutton2.Enable()
	
	def m_stopbutton2OnButtonClick( self, event ):
		self.ala.stopAlarm('alarm2')
                self.m_textCtrl2.Enable()
                self.m_gobutton2.Enable()
		#event.Skip()
	
	def m_gobutton3OnButtonClick( self, event ):
		if self.m_textCtrl3.Enabled==True:
                        try:
                                self.m_staticText1Err.Show(False)
                                kantai3=string.atoi(self.m_textCtrl3.Value)*60
                                success=self.ala.newAlarm(kantai3,'alarm3')
                                if success:
                                    self.m_textCtrl3.Disable()
                                    self.m_gobutton3.Disable()
                                    wx.FutureCall(kantai3*1000,self.m_release3)
                                else:
                                    self.m_staticText2Err.Show(False)                                
                        except(ValueError):
                                self.m_staticText1Err.Show()
		#event.Skip()

        def m_release3(self):
                self.m_textCtrl3.Enable()
                self.m_gobutton3.Enable()
	
	def m_stopbutton3OnButtonClick( self, event ):
		self.ala.stopAlarm('alarm3')
                self.m_textCtrl3.Enable()
                self.m_gobutton3.Enable()
		#event.Skip()

class MyApp(wx.App):
        def OnInit(self):
                self.alarmC=alarmCenter()
                self.frame=mainFrame(None,self.alarmC)
                self.frame.Show()
                self.SetTopWindow(self.frame)
                #self.alarmC.startListen()
                print 'MyApp:pid '+str(os.getpid())+' is created'
                return True

if __name__=='__main__':
    print 'main:pid '+str(os.getpid())+' is created'
    freeze_support()
    app=MyApp()
    app.MainLoop()
    print 'MyApp quit'
    sys.exit(0)
