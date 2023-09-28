package com.yq.javabasic;

/**
 * @program: JavaDemoRep
 * @description: 重载和重写区别
 * @author: Yuqing
 * @create: 2023-09-28 19:42
 **/
public class OverloadAndOverride {

    public static void main(String[] args){
        // 重载
        Fruit fruit = new Fruit();
        fruit.eat();
        fruit.eat(2);
        fruit.eat(6L);
        fruit.eat("fruit");

        // 重写
        Man man = new Man();
        man.getAge();
        man.callName("man");
        man.throwException();
        man.play();
        man.doing();
        man.think();
    }

}


/**
 * 重载
 * 1.在同一个类中
 * 2.方法名相同，参数列表不同
 * 3.返回类型可不同
 * 4.抛出的异常可修改
 * 5.访问修饰符可修改
 * 6.发生在编译期
 */
class Fruit{

    public void eat() throws NullPointerException{
        System.out.println("eat()");
    }

    // 3
    public int eat(int num){
        System.out.println("eat(int)");
        return num;
    }

    // 4
    public Long eat(long num) throws ArithmeticException{
        System.out.println("eat(long)");
        return num;
    }

    // 5
    void eat(String str){
        System.out.println("eat(String)");
    }

}


/**
 * 重写
 * 1.发生在继承关系中
 * 2.方法名和参数列表相同
 * 3.返回值类型应比父类的小或者相等；如果是 void 或者 基本数据类型，则不能修改
 * 4.访问修饰符要不父类的大或者相等，即不能做更严格的限制
 * 5.子类方法声明抛出的异常类应比父类方法声明抛出的异常类更小或相等
 * 6.被 private/static/final 修饰的方法无法被重写
 * 6.发生在运行期
 */

class Person{

    Number getAge(){
        System.out.println("Person#getAge()");
        return 20;
    }

    void callName(String name){
        System.out.println("Person#callName：" + name);
    }

    void throwException() throws IndexOutOfBoundsException{
        System.out.println("Person#throwException");
    }

    private void play(){
        System.out.println("Person#play()");
    }

    public final void doing(){
        System.out.println("Person#doing()");
    }

    public static void think(){
        System.out.println("Person#think()");
    }

}

class Man extends Person{

    // 3 4
    protected Integer getAge(){
        System.out.println("Man#getAge()");
        return 22;
    }

    // 3
    void callName(String name){
        System.out.println("Man#callName：" + name);
    }

    // 5；注意：如果抛出 Exception，则编译不通过；如果抛出的异常是 RuntimeException 及其子类，则能正常运行
    void throwException() throws RuntimeException{
        System.out.println("Man#throwException");
    }

    // 实际是 Man 方法独有的，不属于重写；如果通过父类引用，指向子类对象，是无法调用该方法的
    public void play(){
        System.out.println("Man#play()");
    }

    // 报：doing()' 无法重写 'com.yq.javabasic.Person' 中的 'doing()'；重写的方法为 final
//    public final void doing(){
//        System.out.println("Person#doing()");
//    }

    public static void think(){
        System.out.println("Man#think()");
    }

}

