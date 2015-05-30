## 这个项目是2013年上半年软件工程与计算课程的作业，已经废止，存档纪念


——————[迭代一]————————
暂时没有采用映射的方式
Console主要是显示菜单以及翻译指令
Logger判断登陆是否成功[Admin.txt]
Administer是管理员类[Admin.txt]
其中deleteItem方法直接对目录下的文件进行定位操作：删除一行或者删除某项数据，lookListMap是预留的为映射方式保存数据提供的查看列表的方法。[Student?.txt]
IOUtility是IO的工具类，仅在Console中应用了。
——————————————————
遇到的问题：
for(int i=0;i<v.size;i=i++){}遭遇死循环，导致Student.txt达到600MB左右。改为for(int i=0;i<v.size;i++){}后正常。i++返回值为i。

i++的情况 iconst_0 istore_1 iload_1 iinc 1,1 istore_1 
++i的情况 iconst_0 istore_1 iinc 1,1 iload_1 istore_1

在这里jvm里面有两个存储区，一个是暂存区（是一个堆栈，以下称为堆栈），另一个是变量区。
语句istore_1是将堆栈中的值弹出存入相应的变量区（赋值）；语句iload_1是将变量区中的值暂存如堆栈中。
因为i = i++;是先将i的值（0）存入堆栈，然后对变量区中的i自加1，这时i的值的确是1，但是随后的istore_1又将堆栈的值（0）弹出赋给变量区的i，所以最后i = 0。
又因为i = ++i;是先对变量区中的i自加1，然后再将变量区中i的值（1）存入堆栈，虽然最后执行了istore_1，但也只是将堆栈中的值（1）弹出赋给变量区的i，所以i = ++i;的结果是i = 1。

但在C++里上面输出的确是1
————————————————————
------------[迭代二]----------
------------[和预期可能不合的地方]------
老师使用show course命令可以查看任何一门课程，无论是否是自己的。
背景描述中：教师登录系统，查看其他教师发布的课程
迭代二说明中：老师输入查看自己发布的课程的命令。 Show course [课程号]
------------[和预期可能不合的地方]------
------------DataBase----------
D:\\CourseSystemDB\\Admin.txt Format：
Admin [AdminKey]

D:\\CourseSystemDB\\Student\\Student[StuNo].txt  Format:(default:[String StuNo]  AAA  Key  0)
[String StuNo]  [String StuName]  [String StuKey]  [TAcourse]...
[String course]  [int courseScore]  
[course]  [courseScore](default score NA)
.....


D:\\CourseSystemDB\\Teacher\\Teacher[TeaNo].txt  Format:(default:[String TeaNo]  AAA  Key)
[String TeaNo]  [String TeaName]  [String TeaKey]  
[String course]  
[course]  
[course]....

D:\\CourseSystemDB\\Course\\[courseNo].txt Format:课时、学分、、时间、地点、
[String courseNo]  [String courseName]  [courseTime(changed at 0608 from courseLength to courseTime)]  [point]  [coursePlace]
T [TeaNo]  [TeaNo]  [TeaNo]...
Property  [1Compulsory/0Elective]
TA [TANo] [TANo] [TANo] [TANo]...
[StuNo]  [StuScore](default score NA)
[StuNo]  [StuScore]
[StuNo]  [StuScore]
...

D:\\CourseSystemDB\\courseList.txt Format:
[courseNo]  [courseName]
...
------------DataBase----------
[注意]DefaultTableModle自身并没有设Vector等,而是使用了引用!所以不要试图重用Vector!
[注意]Vector删除一个条目以后,后面的条目会自动上移,那么同一个操作中想删除多个条目时出问题
[注意]null输入DefaultTableModel后该单元格为空，然而修改再改回空后发现值不再是null
需要处理各列表为空时的异常[complete]
解决删除一个item牵扯到其他item的问题[complete]
第一次密码错误之后第二次输入服务器不接受[complete]
服务器与客户端在学生数据传输时格式不符[wrong judgement]
判断学生登陆失败(密码不正确依然登陆) [complete]
Student my course中score和name反了[complete]
选了一门课以后退选，那门课里的成绩记录未删除[wrong judgement]
Student需要看到课程的所有资料[complete]
Teacher需要看到所有课程[complete]
Teacher Record实现是否成绩都登陆了,成绩是否为数字判断[Complete]


------------迭代三------------
服务器类-服务器Console-网络-客户端Console-客户端各类(窗口)
----交流规范-----
"\r\n" 必须是每条信息的结尾！
服务器发送的数据：标头+双空格+各item（单空格）
客户端发送的命令都以一个空格间隔
a)LoginUI(Click "X")->System Exit;


b)LoginUI(gotten account by click)->Ask Client to check account(1)[send:"Admin/Teacher/Student  accountNo  accountPass \r\n"]
								->LoginUI gotten confirmed message[send:"Admin/Teacher/Student\r\n"]
								->set Client status to Login,hide itself(2);
								
								
c)(1)->LoginUI gotten unconfirmed message["LoginFailed\r\n"]->pop up alert;


d)(2)(Admin)->server send lists of Teacher,Student and Course[send:"Teacher  teaNo;teaName teaNo;teaName ..... \r\nStudent  stuNo;stuName stuNo;stuName.... \r\nCourse  courseNo;courseName;courseTime;coursePlace  courseNo;courseName;courseTime;coursePlace... \r\n"]
            ->Client<->Server
            {[ChangePassword 'new Password'\r\n]认证失败返回["False\r\n"]否则返回["True\r\n"]
            |[Delete Teacher 'TeacherNo'\r\n]返回需要同时删除的课程:[courseNo courseNo ....\r\n]
            |[Delete Student 'StudentNo'\r\n]认证失败返回["False\r\n"]否则返回["True\r\n"]
            |[Delete Course 'courseNo'\r\n]}认证失败返回["False\r\n"]否则返回["True\r\n"]
            |selected a student and show his course:[ShowStu 'CourseNo'\r\n]->返回[NoCou\r\n]->Warning/返回[couNo couNo couNo..... \r\n]
            ->send:[Quit 'CourseNo'\r\n]认证失败返回["False\r\n"]否则返回["True\r\n"]
            
            
e)(2)(Student)->server send [send:"StudentCourse  courseNo;Score;courseName courseNo;Score;courseName... \r\nCourse  courseNo,courseName,courseLength,coursePoint,coursePlace,coTeacher;coTeacher;......,ta;ta;ta.....,courseProperty ... \r\n"](showMyCourse/All Course)
			  ->Client<->Server
			  {
			  [ChangePassword 'new Password'\r\n]认证失败返回["False\r\n"]否则返回["True\r\n"]
			  [Select 'courseNo'\r\n]认证失败返回["False\r\n"]否则返回["True\r\n"]
			  [Quit 'courseNo'\r\n]认证失败返回["False\r\n"]否则返回["True\r\n"]
			  }
			  
			  
			  
f)(2)(Teacher)->server send [send:"TeacherCourse  CourseNo;CourseName;CourseTime;CoursePlace CourseNo;CourseName;CourseTime;CoursePlace.... \r\nCourse  courseNo,courseName,courseLength,coursePoint,coursePlace,coTeacher;coTeacher;......,ta;ta;ta.....,courseProperty ... \r\n"](showMyCourse/show all course)
			  ->Client<->Server
				{[ChangePassword 'new Password'\r\n]|
			  [Publish courseNo,courseName,courseLength,coursePoint,coursePlace,coTeacher;coTeacher;......,ta;ta;ta.....,courseProperty\r\n]
			  //publish 信息的正确性在服务器端认证,认证失败返回["False\r\n"]否则返回["True\r\n"]
			  //publish 中,合作老师或者助教为空时,必须写入一个"null"
			  //publish指令使用","分割各数据
			  //是否确认发布交给客户端
			  [ShowStudent 'courseNo'\r\n]|->返回[stuNo stuNo stuNo.....\r\n]
			   [Update oldCourseNo courseNo,courseName,courseLength,coursePoint,coursePlace,coTeacher;coTeacher;......,ta;ta;ta.....,courseProperty\r\n]
			  //update 使用了publish，在它之上增加了删除原课程文件的命令
			  send:[RecordScore 'courseNo'\r\n]->get[NoStu\r\n]->Warning
			  								   ->get[stuNo stuNo ....\r\n]->send:[Record  courseNo  score score score...\r\n](成绩为空记为null)
			  }


-----------------------------------
类与职责与协作：
	客户端：
		AdminUI：获取客户操作信息，显示客户需要的信息  Client
		TeacherUI：获取客户操作信息，显示客户需要的信息 Client
		StudentUI：获取客户操作信息，显示客户需要的信息 Client
		Client：从UI获取指令与服务器端交互，呈交从服务器获取的信息 AdminUI，TeacherUI，StudentUI
	服务器端：
		Server：服务器主线程 ServerThread
		ServerThread：服务线程，负责获取客户端指令并操控Teacher，Admin，Student类 Administer，Teacher，Student，Logger
		Logger：判断客户权限呈交给ServerThread ServerThread
		Administer：负责Admin的各种操作 FileHelper，ServerThread
		Teacher：负责Teacher的各种操作 FileHelper，ServerThread
		Student：负责Student的各种操作 FileHelper，ServerThread
		FileHelper：负责文件的读写 Administer，Teacher，Student

注：FileHelper实际上并没有包揽所有的读写操作。这个类是迭代二才加入的。于是Administer，Teacher，Student的一些在迭代一完成的功能依然是自主进行磁盘文件的读写。这样做虽然会给变更留下麻烦，但是能够避免随意修改带来的错误。
