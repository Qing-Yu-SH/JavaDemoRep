package com.yq;



/**
 * @program: JavaDemoRep
 * @description:
 * @author:
 * @create: 2024-02-19 22:26
 **/
public class MagicProgram {

    private static String hiddenPoker = null;

    public static void main(String[] args) {
        imitateMagic("Yu",Area.SOUTH,Gender.BOY);
    }

    public static void imitateMagic(String name,Area area,Gender gender) {
        String[] pokers = Poker.getRandomFourPokers();
        printPokers(pokers);
        String[] newPokers = repeatPokers(pokers);
        printFoldInHalfPoker(newPokers);
        int nameLen = name.length()>0 ? name.length():1;
        belowPokers(newPokers,nameLen);
        printBelowPoker(newPokers);
        insertPoker(newPokers);
        printInsertPoker(newPokers);
        String[] newPokers2 = hiddenPoker(newPokers);
        changePokerByArea(newPokers2,area.getNum());
        printChangePokerByArea(newPokers2);
        String[] newPokers3 = discardPokerByGender(newPokers2, gender.getNum());
        printDiscardPokerByGender(newPokers3);
        holdSevenTimesPoker(newPokers3);
        printHoldSevenTimesPoker(newPokers3);
        lastStep(newPokers3);
    }

    private static void printPokers(String[] pokers) {
        StringBuilder sb = new StringBuilder();
        sb.append("Step 1：当前四张牌 \n");
        sb.append("\t\t");
        for(String poker : pokers){
            sb.append(poker).append(" ");
        }
        sb.append("\n");
        sb.append("--------------------------------------------");
        System.out.println(sb);
    }

    private static String[] repeatPokers(String[] pokers){
        String[] newPokers = new String[pokers.length*2];
        System.arraycopy(pokers,0,newPokers,0,pokers.length);
        System.arraycopy(pokers,0,newPokers,4,pokers.length);
        return newPokers;
    }

    private static void printFoldInHalfPoker(String[] pokers){
        StringBuilder sb = new StringBuilder();
        sb.append("Step 2：四张卡牌对折撕开 \n");
        sb.append("\t\t");
        for(String poker : pokers){
            sb.append(poker).append(" ");
        }
        sb.append("\n");
        sb.append("--------------------------------------------");
        System.out.println(sb);
    }

    private static void belowPokers(String[] pokers,int len){
        String[] temp = new String[len];
        System.arraycopy(pokers,0,temp,0,len);
        System.arraycopy(pokers,len,pokers,0,pokers.length-len);
        System.arraycopy(temp,0,pokers,pokers.length-len,len);
    }

    private static void printBelowPoker(String[] pokers){
        StringBuilder sb = new StringBuilder();
        sb.append("Step 3：根据名字字数下放卡牌 \n");
        sb.append("\t\t");
        for(String poker : pokers){
            sb.append(poker).append(" ");
        }
        sb.append("\n");
        sb.append("--------------------------------------------");
        System.out.println(sb);
    }

    private static void insertPoker(String[] pokers){
        String[] temp = new String[3];
        System.arraycopy(pokers,0,temp,0,3);
        System.arraycopy(pokers,3,pokers,0,2);
        System.arraycopy(temp,0,pokers,2,3);
    }

    private static void printInsertPoker(String[] pokers){
        StringBuilder sb = new StringBuilder();
        sb.append("Step 4：拿起前三张牌，插进剩下牌的中间 \n");
        sb.append("\t\t");
        for(String poker : pokers){
            sb.append(poker).append(" ");
        }
        sb.append("\n");
        sb.append("--------------------------------------------");
        System.out.println(sb);
    }

    private static String[] hiddenPoker(String[] pokers){
        hiddenPoker = pokers[0];
        String[] poker = new String[pokers.length-1];
        System.arraycopy(pokers,1,poker,0,poker.length);
        StringBuilder sb = new StringBuilder();
        sb.append("Step 5：隐藏卡牌 \n");
        sb.append("\t\t");
        sb.append(hiddenPoker);
        sb.append("\n");
        sb.append("--------------------------------------------");
        System.out.println(sb);
        return poker;
    }

    private static void changePokerByArea(String[] pokers,int num){
        String[] temp = new String[num];
        System.arraycopy(pokers,0,temp,0,num);
        int middleIndex = (pokers.length-num) / 2;
        System.arraycopy(pokers,num,pokers,0,middleIndex);
        System.arraycopy(temp,0,pokers,middleIndex,num);
    }

    private static void printChangePokerByArea(String[] pokers){
        StringBuilder sb = new StringBuilder();
        sb.append("Step 6：根据地域更改卡牌位置 \n");
        sb.append("\t\t");
        for(String poker : pokers){
            sb.append(poker).append(" ");
        }
        sb.append("\n");
        sb.append("--------------------------------------------");
        System.out.println(sb);
    }

    private static String[] discardPokerByGender(String[] pokers,int num){
        String[] newPoker = new String[pokers.length - num];
        System.arraycopy(pokers,num,newPoker,0,newPoker.length);
        return newPoker;
    }

    private static void printDiscardPokerByGender(String[] pokers){
        StringBuilder sb = new StringBuilder();
        sb.append("Step 7：根据性别丢弃卡牌 \n");
        sb.append("\t\t");
        for(String poker : pokers){
            sb.append(poker).append(" ");
        }
        sb.append("\n");
        sb.append("--------------------------------------------");
        System.out.println(sb);
    }

    private static void holdSevenTimesPoker(String[] pokers){
        int len = pokers.length;
        int num = 7 % len;
        String[] temp = new String[num];
        System.arraycopy(pokers,0,temp,0,num);
        System.arraycopy(pokers,num,pokers,0,len-num);
        System.arraycopy(temp,0,pokers,len-num,num);
    }

    private static void printHoldSevenTimesPoker(String[] pokers){
        StringBuilder sb = new StringBuilder();
        sb.append("Step 8：大喊 “见证奇迹的时刻”，每喊一个字将一张卡牌下方，总共下放 7 次 \n");
        sb.append("\t\t");
        for(String poker : pokers){
            sb.append(poker).append(" ");
        }
        sb.append("\n");
        sb.append("--------------------------------------------");
        System.out.println(sb);
    }

    private static void lastStep(String[] pokers){
        int len = pokers.length;
        for(int i=0; i<4; i++){
            String poker = pokers[0];
            System.arraycopy(pokers,2,pokers,0,len-2);
            len--;
            pokers[len-1] = poker;
        }
        for(int i=0;i<len;i++){
            System.out.print(pokers[i]);
            System.out.print(" ");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Step 9：第一张卡牌将其下放，第二张卡牌将其丢掉 \n");
        sb.append("\t\t");
        sb.append("最终卡牌：").append(pokers[0]).append("；").append("是否与隐藏牌相同：").append(pokers[0].equals(hiddenPoker));
        sb.append("\n");
        sb.append("--------------------------------------------");
        System.out.println(sb);
    }

}
