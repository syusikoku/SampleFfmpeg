//
// Created by ubt on 2019/12/20.
//

#include "mock_consumption_task.h"
#include <queue>
#include "unistd.h"
#include <pthread.h>

using namespace std;


// 用于存储数据的队列
std::queue<int> task_queue;
// 生产者线程
pthread_t produc_thread;
// 消费者线程
pthread_t custom_thread;
pthread_mutex_t mutex;
pthread_cond_t cond;

void runMockConsumptionTask() {
    LOGE("native exec runMockConsumptionTask");
    mockTaskRun();
}

/**
 * 生产者
 * @param data
 * @return
 */
void *producCallback(void *data) {
    LOGE("native exec producCallback");
    pthread_exit(&produc_thread);
}

/**
 * 消费者
 * @param data
 * @return
 */
void *customCallback(void *data) {
    LOGE("native exec customCallback");
    pthread_exit(&custom_thread);
}

void mockTaskRun() {
    LOGE("native exec mockTaskRun");

    LOGE("为队列填充数据...");

    for (int i = 0; i < 10; i++) {
        task_queue.push(1);
    }

    // 初始化锁&条件对象
    LOGE("初始化锁&条件对象...");
    // 锁对象
    pthread_mutex_init(&mutex, NULL);
    // 条件控制对象:计数使用
    pthread_cond_init(&cond, NULL);

    // 创建线程
    LOGE("创建线程...");
    pthread_create(&produc_thread, NULL, producCallback, NULL);
    pthread_create(&custom_thread, NULL, customCallback, NULL);
}
