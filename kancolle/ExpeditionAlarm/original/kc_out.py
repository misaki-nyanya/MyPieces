out2=[-28,100,30,0,float(30)]
out2kira=[-28,150,45,0,float(30)]

out3=[18,21,40,0,float(20)]

out5kira=[265,300,30,30,float(90)]

out6kira=[-18,-16,0,120,float(40)]

out21kira=[412,351,0,0,float(140)]

out37kira=[-80,470,405,0,float(165)]

out38kira=[558,-96,300,0,float(175)]

def cal(a,b,c):
    result=[0,0,0,0]
    for no in range(4):
        result[no]=a[no]/a[4]*60+b[no]/b[4]*60+c[no]/c[4]*60
    return result


print cal(out2,out5kira,out6kira)
print cal(out6kira,out21kira,out37kira)
print cal(out5kira,out38kira,out6kira)
