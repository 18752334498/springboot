https://jinnianshilongnian.iteye.com/blog/1902886


事件		具体代表者是：ApplicationEvent

目标（发布事件者）		具体代表者是：ApplicationEventPublisher

监听器		具体代表者是：ApplicationListener

（1）ApplicationStartingEvent：项目刚启动时触发，此时除了注册监听器和初始器之外，其他所有处理都没有开始；
（2）ApplicationEnvironmentPreparedEvent：上下文得到环境信息之后触发，此时上下文创建还没有创建； 

（3）ApplicationPreparedEvent：bean的定义信息加载完成之后触发，此时bean还没有初始化； 

（4）ApplicationReadyEvent：在所有bean初始化完毕，所有回调处理完成，系统准备处理服务请求时触发； 

（5）ApplicationFailedEvent：启动过程出现异常时候触发。

ApplicationReadyEvent	此监听可以获取容器里的bean