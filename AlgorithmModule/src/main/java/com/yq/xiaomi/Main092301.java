package com.yq.xiaomi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-09-23 16:45
 **/
public class Main092301 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int[][] towers = new int[nums[0]][3];
        for(int i=0;i<nums[0];i++){
            towers[i] = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        }
        int maxStrength = 0;
        int[] strength = new int[nums[0]];
        for(int i=0;i<nums[0];i++){
            strength[i] += towers[i][2];
            for(int j=i+1;j<nums[0];j++){
                double d = getDistance(towers[i][0],towers[i][1],towers[j][0],towers[j][1]);
                if(d <= nums[1]){
                    int s_i = (int) Math.ceil(towers[j][2] / (1+d));
                    int s_j = (int) Math.ceil(towers[i][2] / (1+d));
                    strength[i] += s_i;
                    strength[j] += s_j;
                }
            }
            maxStrength = Math.max(maxStrength,strength[i]);
        }
        int pos_x = -1,pos_y = -1;
        for(int i=0;i<nums[0];i++){
            if(strength[i] == maxStrength){
                if(pos_x == -1){
                    pos_x = towers[i][0];
                    pos_y = towers[i][1];
                }else{
                    if (pos_x < towers[i][0]){
                        pos_x = towers[i][0];
                        pos_y = towers[i][1];
                    }else if(pos_x==towers[i][0] && pos_y<towers[i][1]){
                        pos_x = towers[i][0];
                        pos_y = towers[i][1];
                    }
                }
            }
        }
        System.out.println(pos_x + "," + pos_y);
    }

    private static Double getDistance(int x1,int y1,int x2,int y2){
        int x = Math.abs(x1-x2);
        int y = Math.abs(y1-y2);
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }

}
