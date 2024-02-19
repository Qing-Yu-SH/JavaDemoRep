package com.yq;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: JavaDemoRep
 * @description: 扑克
 * @author:
 * @create: 2024-02-19 22:27
 **/
public class Poker {

    private static String[] pokers = new String[]{
      "♥A","♥2","♥3","♥4","♥5","♥6","♥7","♥8","♥9","♥10","♥J","♥Q","♥K",
      "♦A","♦2","♦3","♦4","♦5","♦6","♦7","♦8","♦9","♦10","♦J","♦Q","♦K",
      "♣A","♣2","♣3","♣4","♣5","♣6","♣7","♣8","♣9","♣10","♣J","♣Q","♣K",
      "♠A","♠2","♠3","♠4","♠5","♠6","♠7","♠8","♠9","♠10","♠J","♠Q","♠K"
    };

    public static String[] getRandomFourPokers() {
        Set<Integer> indexs = new HashSet<>();
        while (indexs.size() < 4) {
            indexs.add((int) (Math.random() * 52));
        }
        String[] poker = new String[4];
        int index = 0;
        for(Integer i: indexs){
            poker[index++] = pokers[i];
        }
        return poker;
    }

}
