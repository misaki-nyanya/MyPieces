from django.conf.urls import patterns, include, url

from django.contrib import admin

from dataanaSite.views import index
from dataanaSite.views import single_init
from dataanaSite.views import about
from dataanaSite.views import cate_init
from dataanaSite.views import cateCate
from dataanaSite.views import singleCate
from dataanaSite.views import searchASIN

from settings import TEMPLATE_DIRS

admin.autodiscover()

urlpatterns = patterns('',
    # Examples:
    # url(r'^$', 'dataanaSite.views.home', name='home'),
    # url(r'^blog/', include('blog.urls')),

    #url(r'^admin/', include(admin.site.urls)),
    #url(r'(?P<url>.*)',test),
    url(r'^index/$', index),
    url(r'^single/$', single_init),
    url(r'^about/$', about),
    url(r'^cateView/$', cate_init),
    url(r'^ASIN/$', searchASIN),
    url(r'^CateCate/@(?P<cate>[\w+\W+]+)', cateCate,),
    url(r'^SingleCate/@(?P<cate>[\w+\W+]+)', singleCate,),
    url(r'css/(?P<path>.*)$','django.views.static.serve',
                         {'document_root':TEMPLATE_DIRS[0]+'/css'}),
    url(r'images/(?P<path>.*)$','django.views.static.serve',
                         {'document_root':TEMPLATE_DIRS[0]+'/css/images'}),
    url(r'js/(?P<path>.*)$','django.views.static.serve',
                         {'document_root':TEMPLATE_DIRS[0]+'/js'}),
)

