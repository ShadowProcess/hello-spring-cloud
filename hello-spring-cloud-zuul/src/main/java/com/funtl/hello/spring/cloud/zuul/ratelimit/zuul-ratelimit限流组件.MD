首先简单说一下 spring cloud zuul-rateLimit，他是外国人专门针对 zuul 编写的限流库，提供来4种限流策略，如下。
- User      针对请求的用户进行限流
- Origin    针对请求的Origin进行限流
- URL       针对URL/接口进行限流
- ServiceId 针对服务进行限流，如果没有配置限流类型，则此类型生效


多种粒度临时变量储存方式

存储方式	        说明:
IN_MEMORY	    基于本地内存，底层是ConcurrentHashMap，默认的。
REDIS	        基于redis存储，使用时必须搭建redis 
CONSUL          consul的kv存储
JPA	            spring data jpa，基于数据库
BUKET4J	        使用一个Java编写的基于令牌桶算法的限流库
————————————————


#该组件的使用方法是：只需要在配置文件中进行限流配置就可以了，可以不用写代码
