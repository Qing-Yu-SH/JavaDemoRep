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
 *
 * 扩展新功能的方式一般有两种机制：
 * 1.继承机制，一个类继承另一个类。静态方式，一定要写一个新的子类，对类层级进行扩展
 * 2.关联机制，将一个类注入到另一个类。动态方式，在不修改原有逻辑的基础上增加新功能
 *
 *
 * 1.模型 SSO 单点登录；定义 HandlerInterceptor 接口 和 单点登录功能 SsoInterceptor 类
 * 2.定义抽象装饰类 SsoDecorator，实现 HandlerInterceptor 接口，将 SsoInterceptor 注入；实现 preHandle() 方法，并调用 SsoInterceptor 的 preHandle() 方法
 * 3.定义装饰角色 LoginSsoDecorator，继承 SsoDecorator 抽象类；在调用原有功能的基础上，扩展新的功能
 */
public class Main {

    public static void main(String[] args) {
        LoginSsoDecorator loginSsoDecorator = new LoginSsoDecorator(new SsoInterceptor());
        String request = "1successhuahua";
        boolean success = loginSsoDecorator.preHandle(request, "ewcdqwt40liuiu", "t");
        System.out.println("登录校验：" + request + (success ? " 放⾏" : " 拦截"));
    }

}