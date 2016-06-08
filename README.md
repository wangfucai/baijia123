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

