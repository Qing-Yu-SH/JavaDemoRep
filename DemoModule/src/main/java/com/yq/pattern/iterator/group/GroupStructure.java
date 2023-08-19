package com.yq.pattern.iterator.group;

import com.yq.pattern.iterator.lang.Collection;
import com.yq.pattern.iterator.lang.Iterable;
import com.yq.pattern.iterator.lang.Iterator;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-19 09:51
 **/
public class GroupStructure implements Collection<Employee,Link> {

    /** 组织 Id */
    private String groupId;
    /** 组织 名称 */
    private String groupName;
    /** 雇员列表 */
    private Map<String, Employee> employeeMap = new ConcurrentHashMap<String, Employee>();
    /** 组织架构关系；id->list */
    private Map<String, List<Link>> linkMap = new ConcurrentHashMap<String, List<Link>>();
    /** 反向关系链 */
    private Map<String, String> invertedMap = new ConcurrentHashMap<String, String>();
    public static final Integer DFS = 0;
    public static final Integer BFS = 1;

    public GroupStructure(String groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    @Override
    public boolean add(Employee employee) {
        return null != employeeMap.put(employee.getuId(),employee);
    }

    @Override
    public boolean remove(Employee employee) {
        return null != employeeMap.remove(employee.getuId());
    }

    @Override
    public boolean addLink(String key, Link link) {
        invertedMap.put(link.getToId(), link.getFromId());
        if(linkMap.containsKey(key)){
            return linkMap.get(key).add(link);
        }else{
            List<Link> linkList = new ArrayList<>();
            linkList.add(link);
            linkMap.put(key,linkList);
            return true;
        }
    }

    @Override
    public boolean removeLink(String key) {
        return null != linkMap.remove(key);
    }

    @Override
    public Iterator<Employee> iterator() {
        return getDepthIterator();
    }

    public Iterator<Employee> iterator(Integer flag){
        if(DFS.equals(flag)){
            return getDepthIterator();
        }
        return getWidthIterator();
    }

    /**
     * 深度优先搜索遍历
     */
    private Iterator<Employee> getDepthIterator(){
        return new Iterator<Employee>() {

            // 记录 linkMap 中，List 的索引
            Map<String, Integer> keyMap = new HashMap<>();
            int totalIdx = 0;
            private String fromIdx = groupId;
            private String toIdx = groupId;

            @Override
            public boolean hasNext() {
                return totalIdx < employeeMap.size();
            }

            @Override
            public Employee next() {
                List<Link> links = linkMap.get(toIdx);
                int cursorIdx = getCursorIdx(toIdx);

                // toIdx 没有孩子节点了；则回到父节点 fromIdx，遍历同层节点
                if(null == links){
                    links = linkMap.get(fromIdx);
                    cursorIdx = getCursorIdx(fromIdx);
                }

                // 同层扫描完毕；则往上寻找其父节点并遍历，直到某节点的孩子节点还未遍历完成
                while (cursorIdx >= links.size()){
                    fromIdx = invertedMap.get(fromIdx);
                    links = linkMap.get(fromIdx);
                    cursorIdx = getCursorIdx(fromIdx);
                }

                // 获取节点
                Link link = links.get(cursorIdx);
                toIdx = link.getToId();
                fromIdx = link.getFromId();
                totalIdx++;

                return employeeMap.get(link.getToId());
            }

            private int getCursorIdx(String key){
                int idx = 0;
                if(keyMap.containsKey(key)){
                    idx = keyMap.get(key);
                    keyMap.put(key,++idx);
                }else{
                    keyMap.put(key,idx);
                }
                return idx;
            }

        };
    }

    /**
     * 广度优先搜索遍历
     * @return
     */
    private Iterator<Employee> getWidthIterator(){
        return new Iterator<Employee>() {

            Queue<String> queue = new ArrayDeque<>(Arrays.asList(groupId));
            private int totalIdx;

            @Override
            public boolean hasNext() {
                return totalIdx < employeeMap.size();
            }

            @Override
            public Employee next() {
                String curIdx = queue.poll();
                if(groupId.equals(curIdx)){
                    curIdx = linkMap.get(groupId).get(0).getToId();
                }
                List<Link> linkList = linkMap.get(curIdx);
                if(linkList!=null && linkList.size()>0){
                    for(Link next: linkMap.get(curIdx)){
                        queue.offer(next.getToId());
                    }
                }
                totalIdx++;
                return employeeMap.get(curIdx);
            }
        };

    }

}
