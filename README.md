# baijia123
JAVA并发编程实战的示例及其他

第5章：基础构建模块

对应类包com.baijia123.concurrent

TestHarnes->在计时测试中使用CountDownLatch（闭锁）来启动和停止线程

Preloader->使用FutureTask来提前加载稍后需要的数据

BoundedHashSet->使用Semaphore为容器设置边界

CellularAutomata->通过CyclicBarrier(栅栏)协调细胞自动衍生系统中的计算

Memoizer1->使用HashMap和同步机制来初始化缓存

Memoizer2->用ConcurrentHashMap替换HashMap

Memoizer3->基于FutureTask的Memoizing封装器

Memoizer->Memoizer的最终实现

第6章：任务执行
对应类包com.baijia123.executor

TaskExecutionWebServer->基于线程池的Web服务器

LifecycleWebServer->支持关闭操作的Web服务器

OutOfTime->错误的Timer行为

FutureRender->使用Future等待图像下载

Renderer->使用CompleteService，使页面元素在下载外城后立即显示出来

TravelQuote->在预订时间内请求旅游报价

第7章：取消与关闭

对应类包：com.baijia123.interrupt

PrimeGenerator->使用volatile类型的域来保存取消状态

BrokenPrimeProducer->不可靠的取消操作将把生产者置于阻塞的操作中（不要这么做）

PrimeProducer->通过中断来取消

ReaderThread->通过改写interrupt方法将非标准的取消操作封装在Thread中

SocketUsingTask->通过newTaskFor将非标准的取消操作封装在一个任务中

LogWriter->不支持关闭的生产者-消费者日志服务

LogService1->向LogWriter添加可靠的取消操作

LogService->使用ExecutorService的日志服务

IndexingService->通过毒丸对象(Poison Pill)来关闭服务(部分代码为空)

TrackingExecutor->在ExecutorService中跟踪在关闭之后被取消的任务

WebCrawler->使用TrackingExecutorService来保存未完成的任务以备后续执行

UEHLogger->将异常写入日志的UncaughtExceptionHandler

第8章：线程池的使用

对应类包：com.baijia123.threadpool

BoundedExecutor->使用Semaphore来控制任务的提交速率

MyThreadFactory->自定义的线程工厂

MyAppThread->定制Thread基类

TimingThreadPool->增加了日志和计时等功能的线程池

ValueLatch->携带结果的闭锁

第10章：避免活跃性危险

对应类包：com.baijia123.deadlock

Dispatcher和Taxi->通过公开调用来避免在相互协作的对象之间产生死锁

第11章：性能与可伸缩性

对应类包：com.baijia123.extension

ServerStatus1->对锁进行分解

ServerStatus2->将ServerStatus重新改写为使用锁分解技术

StripedMap->在基于散列的Map中使用锁分段技术

第13章：显式锁

对应类包：com.baijia123.reentrantlock

ReadWriteMap->用读-写锁来包装Map

BoundedBuffer->使用条件队列实现的有界缓存

第14章：构建自定义的同步工具

对应类包：com.baijia123.synctool

BaseBoundedBuffer->有界缓存实现的基类

GrumpyBoundedBuffer->当不满足前提条件时，有界缓存不会执行相应的操作

SleepyBoundedBuffer->使用简单阻塞实现的有界缓存

第15章：原子变量与非阻塞同步机制

对应类包：com.baijia123.atomic

SimulatedCAS->模拟CAS操作

CasCounter->基于CAS实现的非阻塞计数器

CasNumberRange->通过CAS来维持包含多个变量的不变性条件

ConcurrentStatck->使用Treiber算法构造的非阻塞栈

LinkedQueue->非阻塞算法中的插入算法

第16章：JAVA内存模型

对应类包：com.baijia123.lazyinitial

SafeLazyInitialization->线程安全的延迟初始化

EagerInitialization->提前初始化

ResourceFactory->延长初始化占位类模式

2016-08-16 新增jdk和CGLIB动态代理、RPC的简单例子

对应类包：com.baijia123.rpc->使用动态代理简单实现RPC功能

对应类包com.baijia123.proxy

BookFacadeCglib->使用CGLIB动态代理，应用于没有实现接口的类

CountProxy->静态代理，在编译期间就已经存在

DynamicProxy->JDK动态代理，应用于实现接口的类

2016-09-19 新增RMI相关例子
对应包名com.baijia123.rmi

2016-12-08 新增Guava相关例子
对应包名com.baijia123.guava

2016-12-12 新增Stream相关例子
对应包名com.baijia123.java8

2017-02-16 新增堆外内存的测试例子
对应包名com.baijia123.offheap

2017-02-22 新增LDAP相关例子
对应包名com.baijia123.ldap

2017-03-07 新增JMX相关例子
对应包名com.baijia123.jmx

2017-03-13 新增闭锁、栅栏、callable和final相关例子
对应包名com.baijia123.concurrent.latch\barrier\finaltest\runnable

2017-03-30 增加安全发布类
对应包名com.baijia123.safepublic

2017-04-11 增加通知类和反射类
对应包名com.baijia123.notify\reflect

2017-06-20 增加防XSS跨站脚本攻击代码
对应包名com.baijia123.xss

2017-06-20 增加lombok测试代码
对应包名com.baijia123.lombok

2017-06-20 增加javacc的词法语法分析文本rcc.jj（来源于zookeep-jute）
对应包名com.baijia123.javacc

2017-07-19 增加exchange支持两个线程之间交换数据
对应包名com.baijia123.thread

2017-07-19 增加java8 lambda和stream测试用例
对应包名com.baijia123.java8.lambda和com.baijia123.java8.stream

2017-07-24 增加unsafe类以及bloomfilter布隆过滤器实现类
对应包名com.baijia123.unsafe.util和com.baijia123.bloom

2017-07-24 增加string和bean之间的转换函数
对应包名com.baijia123.beanmap
