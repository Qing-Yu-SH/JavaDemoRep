package com.yq.pattern.decorator;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-06 13:27
 **/

import com.yq.pattern.decorator.sso.impl.SsoInterceptor;

/**
 * 装饰器模式
 *
 * 装饰器的核⼼就是在不改原有类的基础上给类新增功能
 * 使用装饰器模式可以避免继承导致的⼦类过多，也可以避免 AOP 带来的复杂性
 *
 * 装饰器主要解决的是直接继承下因功能的不断横向扩展导致⼦类膨胀的问题，⽽⽤装饰器模式后就会
 * ⽐直接继承显得更加灵活同时这样也就不再需要考虑⼦类的维护。
 *
 * 意图：动态地给一个对象添加一些额外的职责。就增加功能来说，装饰器模式相比生成子类更为灵活
 *
 * 主要解决：一般的，我们为了扩展一个类经常使用继承方式实现，由于继承为类引入静态特征，并且随着扩展功能的增多，子类会很膨胀
 */
public class Main {

    public static void main(String[] args) {
        LoginSsoDecorator loginSsoDecorator = new LoginSsoDecorator(new SsoInterceptor());
        String request = "1successhuahua";
        boolean success = loginSsoDecorator.preHandle(request, "ewcdqwt40liuiu", "t");
        System.out.println("登录校验：" + request + (success ? " 放⾏" : " 拦截"));
    }

}
