import urllib
import os
import json
import sys
import time
import string
###6.5
import io
import gzip
###6.5
import socket
socket.setdefaulttimeout(3.0)

base_url=u'http://112.124.1.3:8004/api/commodity/'

### below are the subfuntions used

def getPageItem(category,field,pageNo):
    it_url=base_url+u"?category_name="+\
            category+u"&field="+field+u"&page="+str(pageNo)
    page=urllib.urlopen(it_url).read()
    try:
        pageItem=json.loads(page)
    except(ValueError):
        ###6.5
        bs = page
        bi = io.BytesIO(bs)
        gf = gzip.GzipFile(fileobj=bi, mode="rb")
        page=gf.read()
        ###6.5
        pageItem=json.loads(page)
    return pageItem

### below are the main functions:

def getPicAndName(Cate,page):
    pageItemRaw=getPageItem(Cate,
            "['productInfo.name','productInfo.img','ASIN']",page)
    pageItem=[[]]
    i=0
    for j in range(len(pageItemRaw)):
        item={}
        item['ASIN']=pageItemRaw[j]['ASIN']
        item['name']=pageItemRaw[j]['productInfo'][0]['name']
        item['img']=pageItemRaw[j]['productInfo'][0]['img']
        pageItem[i].append(item)
        print item
        if j%2==1:
            i=i+1
            pageItem.append([])
        
    return pageItem

def getAllStarInfoByCate(Cate):
    ### use the result from function quaryCategory()
    getPage=0
    try:
        pageItem=getPageItem(Cate,"['stats_info.star_info']",1)
    except(socket.timeout):
        pageItem='timeout'
    star_info=[]
    pagenum=2
    while(pageItem!=[] and pageItem!={}):
        if pageItem=='timeout':
            try:
                pageItem=getPageItem(Cate,"['stats_info.star_info']",pagenum)
                pagenum=pagenum+1
            except(socket.timeout):
                pageItem='timeout'
        else:
            for each in pageItem:
                star_info.append(each['stats_info']['star_info'])
            try:
                pageItem=getPageItem(Cate,"['stats_info.star_info']",pagenum)
                pagenum=pagenum+1
            except(socket.timeout):
                pageItem='timeout'
            getPage=getPage+1
    print 'StarInfo: get '+str(getPage)+' pages'
    starCount={1:0,2:0,3:0,4:0,5:0}
    for each in star_info:
        starCount[1]=starCount[1]+each[u'1']
        starCount[2]=starCount[2]+each[u'2']
        starCount[3]=starCount[3]+each[u'3']
        starCount[4]=starCount[4]+each[u'4']
        starCount[5]=starCount[5]+each[u'5']

    starData=[[u'1 star',starCount[1]],[u'2 star',starCount[2]],
              [u'3 stars',starCount[3]],[u'4 stars',starCount[4]],
              [u'5 stars',starCount[5]]]
    
    return starData

def getAvgStarByStarInfo(star_info):
    ### use the result from function getStarInfoByCate(Cate)
    avgStar=[]
    for no in range(0,len(star_info)):
        numOfPeople=(star_info[no]['1']+\
                     star_info[no]['2']+star_info[no]['3']+\
                     star_info[no]['4']+star_info[no]['5'])
        if numOfPeople!=0:
            avgStar.append((star_info[no]['1']*1+star_info[no]['2']*2+\
                     star_info[no]['3']*3+star_info[no]['4']*4+\
                     star_info[no]['5']*5)/numOfPeople)
        else:
            avgStar.append(0)        
    return avgStar

def getAllPriceByCate(Cate):
    ### use the result from function quaryCategory()
    getPage=0
    try:
        pageItem=getPageItem(Cate,"['offer.info']",1)
    except(socket.timeout):
        pageItem=[{u'offer':[]}]
    price_info=[]
    pagenum=2
    while(pagenum<3 and pageItem!=[] and pageItem!={}):
        for each in pageItem:
            for offer in each[u'offer']:
                if offer[u'info']!=[]:
                    for p in offer[u'info']:
                        if p.has_key(u'price'):
                            flag=False
                            name=p[u'seller'][u'name']
                            time=p[u'timestamp']
                            time2=string.atoi(time[0:4]+time[5:7]+time[8:10])
                            for line in price_info:
                                if line[u'name']==name:
                                    line[u'data'].append(
                                        [time2,p[u'price']])
                                    flag=True
                            if not flag:
                                price_info.append({u'name':name,u'data':
                                            [[time2,p[u'price']]]})
        try:
            pageItem=getPageItem(Cate,"['offer.info']",pagenum)
        except(socket.timeout):
            pageItem=[{u'offer':[]}]
        pagenum=pagenum+1
        getPage=getPage+1
    print 'PriceByCate: get '+str(getPage)+' pages'
    return price_info

def getAllReviewTimeByCate(Cate):
    ### use the result from function quaryCategory()
    getPage=0
    try:
        pageItem=getPageItem(Cate,"['review.publishTime']",1)
    except(socket.timeout):
        pageItem='timeout'
    reviewTime=[]
    pagenum=2
    while(pageItem!=[] and pageItem!={}):
        if pageItem=='timeout':
            try:
                pageItem=getPageItem(Cate,"['review.publishTime']",pagenum)
                pagenum=pagenum+1
            except(socket.timeout):
                pageItem='timeout'
        else:
            for each in pageItem:
                itemReview=[]
                for review in each['review']:
                    itemReview.append(review['publishTime'])
            reviewTime.append(itemReview)
            try:
                pageItem=getPageItem(Cate,"['review.publishTime']",pagenum)
                pagenum=pagenum+1
            except(socket.timeout):
                pageItem='timeout'
            getPage=getPage+1
    print 'ReviewTimeByCate: get '+str(getPage)+' pages'
    count={}
    for item in reviewTime:
        for each in item:
            time=string.atoi(each[0:4]+each[5:7]+each[8:10])
            if not count.has_key(time):
                count[time]=1
            else:
                count[time]=count[time]+1
    LineForCateReview=[]
    for key in sorted(count.keys()):
        LineForCateReview.append([key,count[key]])
    return LineForCateReview

def getAvgStarAndReview(Cate):
    ### use the result from function quaryCategory()
    getPage=0
    avg_star=[]
    review_count=[]
    try:
        pageItem=getPageItem(Cate,\
            "['stats_info.star_info','stats_info.review_count']",1)
    except(socket.timeout):
        pageItem='timeout'
    pagenum=2
    while(pageItem!=[] and pageItem!={}):
        if pageItem=='timeout':
            try:
                pageItem=getPageItem(Cate,\
                "['stats_info.star_info','stats_info.review_count']",pagenum)
                pagenum=pagenum+1
            except(socket.timeout):
                pageItem='timeout'
        else:
            for each in pageItem:
                star_info=each['stats_info']['star_info']
                people=float(star_info['1']+star_info['2']+\
                         star_info['3']+star_info['4']+star_info['5'])
                if people!=0:
                    avg=float(star_info['1']*1+star_info['2']*2+star_info['3']*3\
                      +star_info['4']*4+star_info['5']*5)/people
                    avg_star.append(round(avg,3))
                    review_count.append(each['stats_info']['review_count'])
            try:
                pageItem=getPageItem(Cate,\
                "['stats_info.star_info','stats_info.review_count']",pagenum)
                pagenum=pagenum+1
            except(socket.timeout):
                pageItem='timeout'
            getPage=getPage+1
    print 'AvgStarAndReview: get '+str(getPage)+' pages'
    starAndReview=[]
    for no in range(len(avg_star)):
        starAndReview.append([avg_star[no],review_count[no]])        
    return starAndReview

def getPriceChangeAndReview(Cate):
    ### use the result from function quaryCategory()
    getPage=0
    info=[]
    offer_count=[]
    review_count=[]
    try:
        pageItem=getPageItem(Cate,\
            "['stats_info.review_count','offer']",1)
    except(socket.timeout):
        pageItem='timeout'
    pagenum=2
    while(pageItem!=[] and pageItem!={}):
        if pageItem=='timeout':
            try:
                pageItem=getPageItem(Cate,\
                    "['stats_info.review_count','offer']",pagenum)
                pagenum=pagenum+1
            except(socket.timeout):
                pageItem='timeout'
        else:
            for each in pageItem:
                info.append([len(each['offer']),each['stats_info']\
                             ['review_count']])
            try:
                pageItem=getPageItem(Cate,\
                    "['stats_info.review_count','offer']",pagenum)
                pagenum=pagenum+1
            except(socket.timeout):
                pageItem='timeout'
            getPage=getPage+1
    print 'PriceChangeAndReview: get '+str(getPage)+' pages'
    return info

def getASIN(Cate):
    ### use the result from function quaryCategory()
    ASIN=[]
    pageItem=getPageItem(Cate,"['ASIN']",1)
    pagenum=2
    while(pageItem!=[] and pageItem!={}):
        for each in pageItem:
            ASIN.append(each['ASIN'])
        pageItem=getPageItem(Cate,"['ASIN']",pagenum)
        pagenum=pagenum+1
    return ASIN

def getAll(Cate):
    ### backup method, not used yet
    ASIN=[]
    star_info=[]
    price_info=[]
    reviewTime=[]
    pageItem=getPageItem(Cate,"[]",1)
    pagenum=2
    while(pageItem!=[] and pageItem!={}):
        for each in pageItem:
            ##ASIN
            ASIN.append(each['ASIN'])
            ## star
            star_info.append(each['stats_info']['star_info'])
            ## price
            price=[]
            for offer in each['offer']:
                if offer['info']!=[]:
                    for p in offer['info']:
                        if p.has_key('price'):
                            price_info.append({'timestamp':p['timestamp'],\
                                          'price':p['price']})
            ##review
            itemReview=[]
            for review in each['review']:
                itemReview.append(review['publishTime'])
            reviewTime.append(itemReview)
        ##loop
        pageItem=getPageItem(Cate,"[]",pagenum)
        pagenum=pagenum+1
    return [ASIN,star_info,price_info,reviewTime]
