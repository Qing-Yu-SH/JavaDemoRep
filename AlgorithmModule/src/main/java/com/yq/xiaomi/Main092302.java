package com.yq.xiaomi;

import java.util.*;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-09-23 17:09
 **/
public class Main092302 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String[] pre = in.nextLine().split(",");
        List<Integer>[] edges = new List[n];
        int[] degree = new int[n];
        for(int i=0;i<n;i++){
            edges[i] = new ArrayList<>();
        }
        for(int i=0;i<pre.length;i++){
            int[] nodes = Arrays.stream(pre[i].split(":")).mapToInt(Integer::parseInt).toArray();
            degree[nodes[0]]++;
            edges[nodes[1]].add(nodes[0]);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=0;i<n;i++){
            if(degree[i] == 0){
                queue.offer(i);
            }
        }
        int count = 0;
        while(!queue.isEmpty()){
            int preNode = queue.poll();
            count++;
            for(int child: edges[preNode]){
                degree[child]--;
                if(degree[child] == 0){
                    queue.offer(child);
                }
            }
        }
        System.out.println(count==n ? 1:0);
    }
}
