###服务链路追踪组件ZipKin
ZipKin简介

ZipKin是一个开放源代码的分布式跟踪系统，由Twitter公司开源，它致力于
收集服务的定时数据，以解决微服务架构中的延迟问题，包括数据收集，存储，查找和展现，
它的理论模型来自Google Dapper论文。
  每个服务向ZipKin报告计时数据，ZipKin会根据调用关系通过ZipKin UI生成依赖关系图
  显示了多少跟踪请求通过每个服务，该系统开发者可通过一个web前端轻松收集和分析数据
  例如用户每次请求服务的处理时间等，可方便的监测系统中存在的瓶颈
  
  
  
 ## 服务追踪说明
  微服务架构是通过业务来划分服务的，使用REST调用，对外暴露的一个接口，可能需要很多歌服务协同才能完成这个接口功能
  如果链路上任何一个服务出现问题或者网络超时，都会形成导致接口调用失败，随着业务的不断扩张，服务之间的调用会越来越复杂
  
使用浏览器打开：
http://localhost:9411  

刚进去可能看不到所有服务，需要调用几次服务，让后刷新，就有了