# -*- mode: python -*-
a = Analysis(['mainFrame.py'],
             pathex=['aths=D:\\Python27', 'F:\\workspace\\kancolle\\ExpeditionAlarm\\KancolleExpeditionAlarm'],
             hiddenimports=['multiprocessing'],
             hookspath=None,
             runtime_hooks=None)
pyz = PYZ(a.pure)
exe = EXE(pyz,
          a.scripts,
          a.binaries,
          a.zipfiles,
          a.datas,
          name='mainFrame.exe',
          debug=False,
          strip=None,
          upx=False,
          console=True )
