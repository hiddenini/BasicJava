package collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by pc on 2019/11/7.
 */
public class ConcurrentLinkedQueueSight {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> concurrentLinkedQueue = new ConcurrentLinkedQueue<Integer>();

        ArrayBlockingQueue<Integer> arrayBlockingQueue=new ArrayBlockingQueue<Integer>(10);


        /**
         *  put方法:
                  //判断队列是否已满，如果已满阻塞等待
                     while (count.get() == capacity) {
                     notFull.await();
                     }
                     // 把node放入队列中
                     enqueue(node);
                     c = count.getAndIncrement();
                     // 再次判断队列是否有可用空间，如果有唤醒下一个线程进行添加操作
                     if (c + 1 < capacity)
                     notFull.signal();
                     } finally {
                     putLock.unlock();
                     }
                     // 如果队列中有一条数据，唤醒消费线程进行消费
                     if (c == 0)
                     signalNotEmpty();
         */

        /**
         *      enqueue方法
                     last = last.next = node;

                理解链表的关键在于把Node当做一个整体，next是这个整体的一部分（也是一个Node）,那么赋值的时候
                last.next = node;
                是将数据节点node赋值给last这个Node的成员变量next,其他的是什么样就是什么样。



                可以分解为2步
                    //头结点和尾结点最开始的时候都是一个空节点，并且指向(即next节点为)null的
                    //这一步是先将尾结点指向(将新的数据节点赋值给last节点的next)新的数据节点
                    1:  last.next = node;

                    //将新的节点赋值给last,相当于last节点后移,此时last节点又称为了最后一个数据节点，且next为null
                    //以此类推，数据节点一个个加入到链表中来
                    2:  last = last.next;
         */


        /**
         *   take方法
                     // 队列为空，阻塞等待
                     while (count.get() == 0) {
                     notEmpty.await();
                     }
                     x = dequeue();
                     c = count.getAndDecrement();
                     // 队列中还有元素，唤醒下一个消费线程进行消费
                     if (c > 1)
                     notEmpty.signal();
                     } finally {
                     takeLock.unlock();
                     }
                     // 移除元素之前队列是满的，唤醒生产线程进行添加元素
                     if (c == capacity)
                     signalNotFull();

         */


        /**
         *    dequeue方法
         *          //h为头结点
                     Node<E> h = head;
                    //first为第一个数据节点
                     Node<E> first = h.next;
                    // 头结点指向自己
                     h.next = h; // help GC
                    //head节点指向下一个节点
                     head = first;
                    //返回第一个数据节点的值
                     E x = first.item;
                    //将第一个数据节点的值置为null
                     first.item = null;
                    //返回x
                     return x;
         */

        BlockingQueue<Integer> blockingQueue= new LinkedBlockingQueue<Integer>(10);
    }
}
