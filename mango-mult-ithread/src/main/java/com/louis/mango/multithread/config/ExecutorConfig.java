package com.louis.mango.multithread.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: 线程池配置策略：cpu密集型、IO密集型
 * @Author: created by wangkaishuang on 2019-07-08
 */

@Configuration
@EnableAsync
public class ExecutorConfig {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);

    //参数初始化
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    //核心线程数量大小
    private static final int corePoolSize = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    //线程池最大容纳线程数
    private static final int maximumPoolSize = CPU_COUNT + 1;
    //线程空闲后的存活时长
    private static final int keepAliveTime = 30;

    @Bean
    public Executor asyncServiceExecutor() {

        logger.info("start asyncServiceExecutor,执行该语句表示线程池已经配置到spring容器");

        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();

        // 配置核心线程数
        executor.setCorePoolSize(corePoolSize);
        logger.info("核心线程数：{}", corePoolSize);

        // 配置最大线程数
        executor.setMaxPoolSize(maximumPoolSize);
        logger.info("最大线程数：{}", maximumPoolSize);

        // 线程空闲后的存活时间
        executor.setKeepAliveSeconds(keepAliveTime);

        //配置队列大小
        executor.setQueueCapacity(5000);

        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-service-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // 当线程池中的数量等于最大线程数时，重试添加当前的任务；它会自动重复调用execute()方法。
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        // 当线程池中的数量等于最大线程数时,抛弃线程池中工作队列头部的任务(即等待时间最久的任务)，并执行新传入的任务。：
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
//        // 当线程池中的数量等于最大线程数时,默默丢弃不能执行的新加任务，不报任何异常。
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
//        // 当线程池中的数量等于最大线程数时抛 java.util.concurrent.RejectedExecutionException 异常，涉及到该异常的任务也不会被执行，线程池默认的拒绝策略就是该策略。
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        // 等待所有线程执行完关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);

        // 等待xx秒后强行停止
        executor.setAwaitTerminationSeconds(50);

        // 线程池数量销毁到0个
        executor.setAllowCoreThreadTimeOut(true);

        // 执行初始化
        executor.initialize();

        return executor;
    }
}
