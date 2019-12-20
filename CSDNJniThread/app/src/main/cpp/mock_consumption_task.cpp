// 模拟生产者和消费者的多线程并发任务
// Created by test on 2019/12/20.
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

bool isCancel = false;
int count = 0;

/**
 * 生产者
 * @param data
 * @return
 */
void *producCallback(void *data) {
    LOGE("native exec producCallback");

    // 不断的放数据
    while (!isCancel) {
        LOGE("生产者: count = %d ", count);
        if (count == 5) {
            LOGE("生产者: 任务超标了,不干了...");
            isCancel = true;
            // 通知别的线程取数据
            pthread_cond_signal(&cond);
            // 解锁
            pthread_mutex_unlock(&mutex);
            break;
        }
        // 加锁
        pthread_mutex_lock(&mutex);
        task_queue.push(1);
        // 通知信号量
        LOGE("生产者: 生产了一个新产品,通知消费者消费，产品数量为 %d ", task_queue.size())

        pthread_cond_signal(&cond);

        // 解锁
        pthread_mutex_unlock(&mutex);
        count++;
        // 5s执行一次
        sleep(5);
    }

    LOGE("生产者: 停止生产了....");
    // 这里是永远进不来的
    pthread_exit(&produc_thread);
}

/**
 * 消费者
 * @param data
 * @return
 */
void *customCallback(void *data) {
    LOGE("native exec customCallback");

    // 不断的取数据
    while (1) {
        pthread_mutex_lock(&mutex);
        if (task_queue.size() == 0) {
            if(isCancel){
                // 任务取消了就不做了
                LOGE("消费者: 生产线程罢工了");
                break;
            }
            // 等待
            LOGE("消费者: 没有产品可以消费，等待中...")
            pthread_cond_wait(&cond, &mutex);
        } else {
            // 取数据
            task_queue.pop();
            LOGE("消费者: 消费了1个产品，产品剩余 %d ", task_queue.size())
        }
        pthread_mutex_unlock(&mutex);
        // 500ms执行一次
        usleep(500 * 1000);
    }
    LOGE("消费者: 不买了，走人...");
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


/**
 *  程序执行效果
 *  com.zy.jthread E/zzg_ffmpeg: native exec mockTaskRun
    com.zy.jthread E/zzg_ffmpeg: 为队列填充数据...
    com.zy.jthread E/zzg_ffmpeg: 初始化锁&条件对象...
    com.zy.jthread E/zzg_ffmpeg: 创建线程...
    com.zy.jthread E/zzg_ffmpeg: native exec customCallback
    com.zy.jthread E/zzg_ffmpeg: 消费者: 消费了1个产品，产品剩余 9
    com.zy.jthread E/zzg_ffmpeg: native exec producCallback
    com.zy.jthread E/zzg_ffmpeg: 生产者: count = 0
    com.zy.jthread E/zzg_ffmpeg: 生产者: 生产了一个新产品,通知消费者消费，产品数量为 10
    com.zy.jthread E/zzg_ffmpeg: 消费者: 消费了1个产品，产品剩余 9
    com.zy.jthread E/zzg_ffmpeg: 消费者: 消费了1个产品，产品剩余 8
    com.zy.jthread E/zzg_ffmpeg: 消费者: 消费了1个产品，产品剩余 7
    com.zy.jthread E/zzg_ffmpeg: 消费者: 消费了1个产品，产品剩余 6
    com.zy.jthread E/zzg_ffmpeg: 消费者: 消费了1个产品，产品剩余 5
    com.zy.jthread E/zzg_ffmpeg: 消费者: 消费了1个产品，产品剩余 4
    com.zy.jthread E/zzg_ffmpeg: 消费者: 消费了1个产品，产品剩余 3
    com.zy.jthread E/zzg_ffmpeg: 消费者: 消费了1个产品，产品剩余 2
    com.zy.jthread E/zzg_ffmpeg: 消费者: 消费了1个产品，产品剩余 1
    com.zy.jthread E/zzg_ffmpeg: 生产者: count = 1
    com.zy.jthread E/zzg_ffmpeg: 生产者: 生产了一个新产品,通知消费者消费，产品数量为 2
    com.zy.jthread E/zzg_ffmpeg: 消费者: 消费了1个产品，产品剩余 1
    com.zy.jthread E/zzg_ffmpeg: 消费者: 消费了1个产品，产品剩余 0
    com.zy.jthread E/zzg_ffmpeg: 消费者: 没有产品可以消费，等待中...
    com.zy.jthread E/zzg_ffmpeg: 生产者: count = 2
    com.zy.jthread E/zzg_ffmpeg: 生产者: 生产了一个新产品,通知消费者消费，产品数量为 1
    com.zy.jthread E/zzg_ffmpeg: 消费者: 消费了1个产品，产品剩余 0
    com.zy.jthread E/zzg_ffmpeg: 消费者: 没有产品可以消费，等待中...
    com.zy.jthread E/zzg_ffmpeg: 生产者: count = 3
    com.zy.jthread E/zzg_ffmpeg: 生产者: 生产了一个新产品,通知消费者消费，产品数量为 1
    com.zy.jthread E/zzg_ffmpeg: 消费者: 消费了1个产品，产品剩余 0
    com.zy.jthread E/zzg_ffmpeg: 消费者: 没有产品可以消费，等待中...
    com.zy.jthread E/zzg_ffmpeg: 生产者: count = 4
    com.zy.jthread E/zzg_ffmpeg: 生产者: 生产了一个新产品,通知消费者消费，产品数量为 1
    com.zy.jthread E/zzg_ffmpeg: 消费者: 消费了1个产品，产品剩余 0
    com.zy.jthread E/zzg_ffmpeg: 消费者: 没有产品可以消费，等待中...
    com.zy.jthread E/zzg_ffmpeg: 生产者: count = 5
    com.zy.jthread E/zzg_ffmpeg: 生产者: 任务超标了,不干了...
    com.zy.jthread E/zzg_ffmpeg: 生产者: 停止生产了....
    com.zy.jthread E/zzg_ffmpeg: 消费者: 生产线程罢工了
    com.zy.jthread E/zzg_ffmpeg: 消费者: 不买了，走人...
 * */