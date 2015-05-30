import urllib
import os
import json
import sys
import datetime
import string
###6.5
import io
import gzip
###6.5


base_url=u'http://112.124.1.3:8004/api/commodity/'

### below are the subfuntions used

def printPage(category,field,pageNo):
    print '\n\n'
    it_url=base_url+u"?category_name="+\
            category+"&field="+field+"&page="+str(pageNo)
    page=urllib.urlopen(it_url).read()
    pageItem=json.loads(page)
    
    for item in pageItem:
        if item.has_key('ASIN') and item.has_key('productInfo'):
            try:
                print str(item['ASIN'])+'\t'+str(item['productInfo'][0]['name'])
            except UnicodeEncodeError:
                continue
    

### below are the main functions:

def getInfoByASIN(ASIN):
    it_url=base_url+ASIN+u'//'
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
    info={'review':[],'star':[],'price':[]}
    
    for each in pageItem['review']:
        info['review'].append(each['publishTime'])
        
    info['star']=pageItem['stats_info']['star_info']
    
    for each in pageItem['offer']:
        if each['info']!=[]:
            price=[each['timestamp']]
            info['price'].append(price)
            for seller in each['info']:
                se=seller['seller']['name']
                if se==None:
                    se=u'Null'
                pi=seller['price']
                price.append({'seller':se,'price':pi})
                
    return info

def getPriceByASINGroupByOffer(ASIN):
    it_url=base_url+ASIN+u'//'
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
    info={}
    js=[]
    for each in pageItem['offer']:
        if each['info']!=[]:
            time=string.atoi(each['timestamp'][0:4]+each['timestamp'][5:7]+\
                               each['timestamp'][8:10])
            for seller in each['info']:
                if not info.has_key(seller['seller']['name']):
                    info[seller['seller']['name']]={'price':[],'timestamp':[]}
                    info[seller['seller']['name']]['price'].append(\
                        seller['price'])
                    info[seller['seller']['name']]['timestamp'].append(time)
                else:
                    info[seller['seller']['name']]['price'].append(\
                        seller['price'])
                    info[seller['seller']['name']]['timestamp'].append(time)
    for name in info.keys():
        line={u'name':name,u'data':[]}
        for no in range(len(info[name]['timestamp'])):
            line[u'data'].append([info[name]['timestamp'][no],
                                  info[name]['price'][no]])
        js.append(line)
        
    return js

def getReviewCountByASINGroupByMonth(ASIN):
    it_url=base_url+ASIN+u'//'
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
    info={}
    js=[]
    for each in pageItem['review']:
        time=string.atoi(each['publishTime'][0:4]+each['publishTime'][5:7])
        if not info.has_key(time):
            info[time]=1
        else:
            info[time]=info[time]+1

    for each in sorted(info.keys()):
        js.append([each,info[each]])
        
    return js

def getReviewCountCulMuByMonth(ASIN):
    it_url=base_url+ASIN+u'//'
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
    info={}
    js=[]
    for each in pageItem['review']:
        time=string.atoi(each['publishTime'][0:4]+each['publishTime'][5:7])
        if not info.has_key(time):
            info[time]=1
        else:
            info[time]=info[time]+1
    culmulater=0
    for each in sorted(info.keys()):
        culmulater=culmulater+info[each]
        js.append([each,culmulater])
        
    return js

def getStarByASIN(ASIN):
    it_url=base_url+ASIN+u'//'
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
    info={}
    info=pageItem['stats_info']['star_info']
    js=[]
    for each in sorted(info.keys()):
        js.append(info[each])
    return js

def getPriceAndReview(ASIN):
    price=getPriceByASINGroupByOffer(ASIN)
    for each in price:
        each['yAxis']=0
    review=getReviewCountByASINGroupByMonth(ASIN)
    #print review
    review2={u'name':u'review',u'data':review,u'yAxis':1}
    #print review2
    a=price
    a.append(review2)
    return a
