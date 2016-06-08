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


