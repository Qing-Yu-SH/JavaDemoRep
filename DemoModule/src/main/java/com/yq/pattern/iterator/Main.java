package com.yq.pattern.iterator;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-19 09:34
 **/

import com.yq.pattern.iterator.group.Employee;
import com.yq.pattern.iterator.group.GroupStructure;
import com.yq.pattern.iterator.group.Link;
import com.yq.pattern.iterator.lang.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 迭代器模式
 * 迭代器模式，常⻅的就是我们⽇常使⽤的 iterator 遍历
 * 迭代器模式的特点是实现 Iterable 接⼝，通过 next 的⽅式获取集合元素，同时具备对元素的删除等操作
 *
 * 这种设计模式的优点是可以让我们以相同的⽅式，遍历不同的数据结构元素
 * 这些数据结构包括； 数组 、 链表 、 树 等，⽽⽤户在使⽤遍历的时候并不需要去关⼼每⼀种数据结构的遍历处理逻辑，让使⽤变得统⼀易⽤
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        GroupStructure groupStructure = new GroupStructure("GS002", "公司");

        // 添加员工信息
        groupStructure.add(new Employee("1","y","一级部门"));
        groupStructure.add(new Employee("2","l","二级部门"));
        groupStructure.add(new Employee("3","for","二级部门"));
        groupStructure.add(new Employee("4","o","三级部门"));
        groupStructure.add(new Employee("5","v","三级部门"));
        groupStructure.add(new Employee("6","w","三级部门"));
        groupStructure.add(new Employee("7","ever","三级部门"));
        groupStructure.add(new Employee("8","e","四级部门"));

        // 节点关系
        groupStructure.addLink("GS002",new Link("GS002","1"));
        groupStructure.addLink("1",new Link("1","2"));
        groupStructure.addLink("1",new Link("1","3"));
        groupStructure.addLink("2",new Link("2","4"));
        groupStructure.addLink("2",new Link("2","5"));
        groupStructure.addLink("2",new Link("2","6"));
        groupStructure.addLink("5",new Link("5","8"));
        groupStructure.addLink("3",new Link("3","7"));

        // 默认深度遍历
        Iterator<Employee> iterator = groupStructure.iterator();
        while (iterator.hasNext()){
            Employee employee = iterator.next();
            logger.info("{}，雇员 Id：{} Name：{}",employee.getDesc(),employee.getuId(),employee.getName());
        }

        logger.info("\n————————————————————————————————————————————————————————————\n");

        Iterator<Employee> iterator2 = groupStructure.iterator(GroupStructure.BFS);
        while (iterator2.hasNext()){
            Employee employee = iterator2.next();
            logger.info("{}，雇员 Id：{} Name：{}",employee.getDesc(),employee.getuId(),employee.getName());
        }
    }

}
