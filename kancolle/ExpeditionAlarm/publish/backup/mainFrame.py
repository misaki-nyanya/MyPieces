#!/usr/bin/env python
# -*- coding: utf-8 -*- 

###########################################################################
## Python code generated with wxFormBuilder (version Jun  5 2014)
## http://www.wxformbuilder.org/
##
## PLEASE DO "NOT" EDIT THIS FILE!
###########################################################################

#import wx
from wx import Frame,BoxSizer,StaticText,Button,MenuBar,Menu,Colour,FutureCall,App,Font,FlexGridSizer,TextCtrl,MenuItem
from wx import DEFAULT_FRAME_STYLE,TAB_TRAVERSAL,ALIGN_CENTER_HORIZONTAL,BOTH,DefaultSize,VERTICAL,DefaultPosition,Size,ID_ANY,ALL,EXPAND,EVT_CLOSE,EVT_BUTTON,EmptyString,ITEM_NORMAL,FLEX_GROWMODE_SPECIFIED,ALIGN_CENTER_VERTICAL,EVT_MENU
import alarmCenterModule
from string import atoi

###########################################################################
## Class aboutFrame
###########################################################################

class aboutFrame ( Frame ):
	
	def __init__( self, parent ):
		Frame.__init__ ( self, parent, id = ID_ANY, title = u"about", pos = DefaultPosition, size = Size( 300,207 ), style = DEFAULT_FRAME_STYLE|TAB_TRAVERSAL )
		
		self.SetSizeHintsSz( DefaultSize, DefaultSize )
		
		bSizer2 = BoxSizer( VERTICAL )
		
		self.m_staticText8 = StaticText( self, ID_ANY, u"\n       感谢使用~\n   本程序由Rillke制作\n    请勿用于商业用途\n      有问题请联系\n     企鹅635415677", DefaultPosition, DefaultSize, 0 )
		self.m_staticText8.Wrap( -1 )
		self.m_staticText8.SetFont( Font( 16, 70, 90, 92, False, "宋体" ) )
		
		bSizer2.Add( self.m_staticText8, 1, ALL|EXPAND, 5 )
		
		self.m_buttonAboutClose = Button( self, ID_ANY, u"close", DefaultPosition, DefaultSize, 0 )
		bSizer2.Add( self.m_buttonAboutClose, 0, ALL|ALIGN_CENTER_HORIZONTAL, 5 )
		
		
		self.SetSizer( bSizer2 )
		self.Layout()
		
		self.Centre( BOTH )
		
		# Connect Events
		self.Bind( EVT_CLOSE, self.aboutFrameOnClose )
		self.m_buttonAboutClose.Bind( EVT_BUTTON, self.m_buttonAboutCloseOnButtonClick )
	
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

class mainFrame ( Frame ):
	
	def __init__( self, parent, alaC):
		Frame.__init__ ( self, parent, id = ID_ANY, title = u"Kancolle Expedition Alarm", pos = DefaultPosition, size = Size( 494,184 ), style = DEFAULT_FRAME_STYLE|TAB_TRAVERSAL )
		
		self.SetSizeHintsSz( DefaultSize, DefaultSize )
		
		self.m_menubar1 = MenuBar( 0 )
		self.m_menu1 = Menu()
		self.m_menuItem1 = MenuItem( self.m_menu1, ID_ANY, u"About", EmptyString, ITEM_NORMAL )
		self.m_menu1.AppendItem( self.m_menuItem1 )
		
		self.m_menubar1.Append( self.m_menu1, u"Menu" ) 
		
		self.SetMenuBar( self.m_menubar1 )
		
		fgSizer3 = FlexGridSizer( 3, 5, 0, 0 )
		fgSizer3.SetFlexibleDirection( BOTH )
		fgSizer3.SetNonFlexibleGrowMode( FLEX_GROWMODE_SPECIFIED )
		
		self.m_staticText1 = StaticText( self, ID_ANY, u"kantai2", DefaultPosition, DefaultSize, 0 )
		self.m_staticText1.Wrap( -1 )
		fgSizer3.Add( self.m_staticText1, 0, ALL|ALIGN_CENTER_HORIZONTAL|ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_textCtrl1 = TextCtrl( self, ID_ANY, EmptyString, DefaultPosition, DefaultSize, 0 )
		fgSizer3.Add( self.m_textCtrl1, 0, ALL|ALIGN_CENTER_HORIZONTAL|ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_gobutton1 = Button( self, ID_ANY, u"Go", DefaultPosition, DefaultSize, 0 )
		fgSizer3.Add( self.m_gobutton1, 0, ALL|ALIGN_CENTER_HORIZONTAL|ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_stopbutton1 = Button( self, ID_ANY, u"Stop", DefaultPosition, DefaultSize, 0 )
		fgSizer3.Add( self.m_stopbutton1, 0, ALL|ALIGN_CENTER_HORIZONTAL|ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_staticText1Err = StaticText( self, ID_ANY, u"请输入一个整数(分钟)", DefaultPosition, DefaultSize, 0 )
		self.m_staticText1Err.Wrap( -1 )
		self.m_staticText1Err.SetForegroundColour( Colour( 255, 0, 0 ) )
		self.m_staticText1Err.Show(False)
		
		fgSizer3.Add( self.m_staticText1Err, 0, ALL|ALIGN_CENTER_HORIZONTAL|ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_staticText2 = StaticText( self, ID_ANY, u"kantai3", DefaultPosition, DefaultSize, 0 )
		self.m_staticText2.Wrap( -1 )
		fgSizer3.Add( self.m_staticText2, 0, ALL|ALIGN_CENTER_HORIZONTAL|ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_textCtrl2 = TextCtrl( self, ID_ANY, EmptyString, DefaultPosition, DefaultSize, 0 )
		fgSizer3.Add( self.m_textCtrl2, 0, ALL|ALIGN_CENTER_HORIZONTAL|ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_gobutton2 = Button( self, ID_ANY, u"Go", DefaultPosition, DefaultSize, 0 )
		fgSizer3.Add( self.m_gobutton2, 0, ALL|ALIGN_CENTER_HORIZONTAL|ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_stopbutton2 = Button( self, ID_ANY, u"Stop", DefaultPosition, DefaultSize, 0 )
		fgSizer3.Add( self.m_stopbutton2, 0, ALL|ALIGN_CENTER_HORIZONTAL|ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_staticText2Err = StaticText( self, ID_ANY, u"请输入一个整数(分钟)", DefaultPosition, DefaultSize, 0 )
		self.m_staticText2Err.Wrap( -1 )
		self.m_staticText2Err.SetForegroundColour( Colour( 255, 0, 0 ) )
		self.m_staticText2Err.Show(False)
		
		fgSizer3.Add( self.m_staticText2Err, 0, ALL|ALIGN_CENTER_HORIZONTAL|ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_staticText3 = StaticText( self, ID_ANY, u"kantai4", DefaultPosition, DefaultSize, 0 )
		self.m_staticText3.Wrap( -1 )
		fgSizer3.Add( self.m_staticText3, 0, ALL|ALIGN_CENTER_HORIZONTAL|ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_textCtrl3 = TextCtrl( self, ID_ANY, EmptyString, DefaultPosition, DefaultSize, 0 )
		fgSizer3.Add( self.m_textCtrl3, 0, ALL|ALIGN_CENTER_HORIZONTAL|ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_gobutton3 = Button( self, ID_ANY, u"Go", DefaultPosition, DefaultSize, 0 )
		fgSizer3.Add( self.m_gobutton3, 0, ALL|ALIGN_CENTER_HORIZONTAL|ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_stopbutton3 = Button( self, ID_ANY, u"Stop", DefaultPosition, DefaultSize, 0 )
		fgSizer3.Add( self.m_stopbutton3, 0, ALL|ALIGN_CENTER_HORIZONTAL|ALIGN_CENTER_VERTICAL, 5 )
		
		self.m_staticText3Err = StaticText( self, ID_ANY, u"请输入一个整数(分钟)", DefaultPosition, DefaultSize, 0 )
		self.m_staticText3Err.Wrap( -1 )
		self.m_staticText3Err.SetForegroundColour( Colour( 255, 0, 0 ) )
		self.m_staticText3Err.Show(False)
		
		fgSizer3.Add( self.m_staticText3Err, 0, ALL|ALIGN_CENTER_VERTICAL|ALIGN_CENTER_HORIZONTAL, 5 )
		
		
		self.SetSizer( fgSizer3 )
		self.Layout()
		
		self.Centre( BOTH )
		
		# Connect Events
		self.Bind( EVT_CLOSE, self.mainFrameOnClose )
		self.Bind( EVT_MENU, self.m_menuItem1MenuSelection, id = self.m_menuItem1.GetId() )
		self.m_gobutton1.Bind( EVT_BUTTON, self.m_gobutton1OnButtonClick )
		self.m_stopbutton1.Bind( EVT_BUTTON, self.m_stopbutton1OnButtonClick )
		self.m_gobutton2.Bind( EVT_BUTTON, self.m_gobutton2OnButtonClick )
		self.m_stopbutton2.Bind( EVT_BUTTON, self.m_stopbutton2OnButtonClick )
		self.m_gobutton3.Bind( EVT_BUTTON, self.m_gobutton3OnButtonClick )
		self.m_stopbutton3.Bind( EVT_BUTTON, self.m_stopbutton3OnButtonClick )

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
                                kantai1=atoi(self.m_textCtrl1.Value)*60
                                self.ala.newAlarm(kantai1,'alarm1')
                                self.m_textCtrl1.Disable()
                                self.m_staticText1Err.Show(False)
                                FutureCall(kantai1*1000,self.m_release1)
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
                                kantai2=atoi(self.m_textCtrl2.Value)*60
                                self.ala.newAlarm(kantai2,'alarm2')
                                self.m_textCtrl2.Disable()
                                self.m_staticText1Err.Show(False)
                                FutureCall(kantai2*1000,self.m_release2)
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
                                kantai3=atoi(self.m_textCtrl3.Value)*60
                                self.ala.newAlarm(kantai3,'alarm3')
                                self.m_textCtrl3.Disable()
                                self.m_staticText1Err.Show(False)
                                FutureCall(kantai3*1000,self.m_release3)
                        except(ValueError):
                                self.m_staticText1Err.Show()
		#event.Skip()

        def m_release3(self):
                self.m_textCtrl3.Enable()
	
	def m_stopbutton3OnButtonClick( self, event ):
		self.ala.stopAlarm('alarm3')
                self.m_textCtrl3.Enable()
		#event.Skip()

class MyApp(App):
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
