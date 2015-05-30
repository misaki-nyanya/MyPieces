#!/usr/bin/env python
# -*- coding: utf-8 -*- 

###########################################################################
## Python code generated with wxFormBuilder (version Jun  5 2014)
## http://www.wxformbuilder.org/
##
## PLEASE DO "NOT" EDIT THIS FILE!
###########################################################################

from multiprocessing import Pipe,Process,Queue
import wx
import alarmCenterModule
import string

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
		
		self.m_staticText2Err = wx.StaticText( self, wx.ID_ANY, u"请输入一个整数(分钟)", wx.DefaultPosition, wx.DefaultSize, 0 )
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
                
	def __del__( self ):
		pass
	
	
	# Virtual event handlers, overide them in your derived class
	def mainFrameOnClose( self, event ):
		event.Skip()
	
	def m_menuItem1MenuSelection( self, event ):
		self.m_aboutFrame=aboutFrame(self)
		self.m_aboutFrame.Show()
	
	def m_gobutton1OnButtonClick( self, event ):
                if self.m_textCtrl1.Enabled==True:
                        try:
                                kantai1=string.atoi(self.m_textCtrl1.Value)*60
                                self.ala.newAlarm(kantai1,'alarm1')
                                self.m_textCtrl1.Disable()
                                self.m_staticText1Err.Show(False)
                                wx.FutureCall(kantai1*1000,self.m_release1)
                        except(ValueError):
                                self.m_staticText1Err.Show()
		#event.Skip()
                                
	def m_release1(self):
                self.m_textCtrl1.Enable()
                
	def m_stopbutton1OnButtonClick( self, event ):
                self.ala.stopAlarm('alarm1')
                self.m_textCtrl1.Enable()
		#event.Skip()
	
	def m_gobutton2OnButtonClick( self, event ):
		if self.m_textCtrl2.Enabled==True:
                        try:
                                kantai2=string.atoi(self.m_textCtrl2.Value)*60
                                self.ala.newAlarm(kantai2,'alarm2')
                                self.m_textCtrl2.Disable()
                                self.m_staticText1Err.Show(False)
                                wx.FutureCall(kantai2*1000,self.m_release2)
                        except(ValueError):
                                self.m_staticText1Err.Show()
		#event.Skip()
                        
        def m_release2(self):
                self.m_textCtrl2.Enable()
	
	def m_stopbutton2OnButtonClick( self, event ):
		self.ala.stopAlarm('alarm2')
                self.m_textCtrl2.Enable()
		#event.Skip()
	
	def m_gobutton3OnButtonClick( self, event ):
		if self.m_textCtrl3.Enabled==True:
                        try:
                                kantai3=string.atoi(self.m_textCtrl3.Value)*60
                                self.ala.newAlarm(kantai3,'alarm3')
                                self.m_textCtrl3.Disable()
                                self.m_staticText1Err.Show(False)
                                wx.FutureCall(kantai3*1000,self.m_release3)
                        except(ValueError):
                                self.m_staticText1Err.Show()
		#event.Skip()

        def m_release3(self):
                self.m_textCtrl3.Enable()
	
	def m_stopbutton3OnButtonClick( self, event ):
		self.ala.stopAlarm('alarm3')
                self.m_textCtrl3.Enable()
		#event.Skip()

class MyApp(wx.App):
        def OnInit(self):
                self.alarmC=alarmCenterModule.alarmCenter()
                self.frame=mainFrame(None,self.alarmC)
                self.frame.Show()
                self.SetTopWindow(self.frame)
                #self.alarmC.startListen()
                return True


if __name__=='__main__':
    app=MyApp()
    app.MainLoop()
