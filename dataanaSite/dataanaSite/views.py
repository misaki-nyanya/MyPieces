from django.http import HttpResponse
from django.template.loader import get_template
from django.template import Template,Context
from django.shortcuts import render_to_response

import dataanaSite.CateAllData as CateAllData
import dataanaSite.ItemData as ItemData

import datetime
import urllib
import json
import string
import sys
###6.5
import io
import gzip
###6.5
import re

reload(sys)
sys.setdefaultencoding('utf-8')

base_url=u'http://112.124.1.3:8004/api/commodity/'

cate_all=[]
cate_all_display=[]
RE=re.compile(r"\"(\w+)'(\w+)\"")
if cate_all==[]:
    cate_page=urllib.urlopen(base_url).read()
    ###6.5
    bs = cate_page
    bi = io.BytesIO(bs)
    gf = gzip.GzipFile(fileobj=bi, mode="rb")
    cate_page=gf.read()
    ###6.5
    commodity=json.loads(cate_page)
    for item in commodity:
        cate_all.append(item['name'].replace('&','$'))
        cate_all_display.append(item['name'])
else:
    for item in cate_all:
        cate_all_display.append(item.replace('$','&'))

def index(request):
    print 'in index'
    now=datetime.datetime.now()
    t=get_template('index.html')
    c=Context({'time':str(now)})
    html=t.render(c)
    return HttpResponse(html)

def single_init(request):
    print 'in single_init'
    return render_to_response('single.html',
                              {'cate_all_display':cate_all_display})

def cate_init(request):
    print 'in cate_init'
    return render_to_response('cate.html',
                              {'cate_all_display':cate_all_display})

def about(request):
    print 'in about'
    return render_to_response('about.html', None)



def singleCate(request,cate):
    print 'in singleCate'
    print cate+'|'
    page=string.atoi(request.GET['page'])
    pageItem=CateAllData.getPicAndName(cate.replace('&','$'),page)    
    if page<=1:
        lastPage=1
        nextPage=2
    else:
        lastPage=page-1
        nextPage=page+1
    return render_to_response('singleList.html',
            {'cate_all_display':cate_all_display,'search':cate,'curItem':cate,
             'pageItem':pageItem,'lastPage':lastPage,'nextPage':nextPage})

def searchASIN(request):
    print 'in searchASIN'
    ASIN=request.GET['s']
    if not ASIN :
        message = u'no input'
        return render_to_response('singleView.html',
                    {'cate_all_display':cate_all_display,'search':message})
    else :
        message = ASIN
        
        priceByOffer=ItemData.getPriceByASINGroupByOffer(ASIN)
        priceByOffer=str(priceByOffer)
        priceByOffer=priceByOffer.replace(u"u'data'",u'data').\
                    replace(u"u'name'",u'name').replace(u'u',u'')
        #priceByOffer=RE.sub("\'\\1\\'\\2\'",priceByOffer)
        priceByOffer, number = re.subn('''\"([^\']+)\'([^\']+)\"''',
                    "\'\\1\\\\\\'\\2\'",priceByOffer)

        print priceByOffer
        reviewByMonth=str(ItemData.getReviewCountByASINGroupByMonth(ASIN))
        reviewByMonth=reviewByMonth.replace(u'u',u'')

        reviewCulByMonth=str(ItemData.getReviewCountCulMuByMonth(ASIN))
        reviewCulByMonth=reviewCulByMonth.replace(u'u',u'')

        star=ItemData.getStarByASIN(ASIN)

        priceByReview=str(ItemData.getPriceAndReview(ASIN)).replace(u'u',u'')
        
        return render_to_response('singleView.html',
                    {'cate_all_display':cate_all_display,'search':message,
                     'priceByOffer':priceByOffer,'reviewByMonth':reviewByMonth,
                     'reviewCulByMonth':reviewCulByMonth,'star':star,
                     'priceByReview':priceByReview})

def cateCate(request,cate):
    print 'in cateCate'
    #a="\"Day's\""
    #RE=re.compile(r"\"(\w+)'(\w+)\"")
    #RE.sub("\'\\1\\'\\2\'",a)
    #price=RE.sub("\'\\1\\'\\2\'",
    #            str(CateAllData.getAllPriceByCate(cate)).replace(u'u',u''));
    #print '###############################'
    print str(CateAllData.getAllPriceByCate(cate)).replace(u'u',u'')
    price, number = re.subn('''\"([^\']+)\'([^\']+)\"''', "\'\\1\\\\\\'\\2\'",
                    str(CateAllData.getAllPriceByCate(cate)).replace(u'u',u''))
    #print price
    #print '###############################'
    review=str(CateAllData.getAllReviewTimeByCate(cate))
    starCount=str(CateAllData.getAllStarInfoByCate(cate)).replace(u'u',u'')
    avgStarAndReview=str(CateAllData.getAvgStarAndReview(cate))
    priceChangeByReview=str(CateAllData.getPriceChangeAndReview(cate))
    
    return render_to_response('cateView.html',
            {'cate_all_display':cate_all_display,'search':cate,
             'price':price,'LineForCateReview':review,'starCount':starCount,
             'avgStarAndReview':avgStarAndReview,
             'priceChangeByReview':priceChangeByReview})
