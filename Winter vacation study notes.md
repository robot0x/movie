#<center>寒假学习报告</center>

我的课题是：电影评论网站的设计与实现。

##操作系统

代码打算部署在Linux机器上。

####1.免费的操作系统
Linux 内核源代码可以免费下载。大多数 Linux 发布版本，包括 GNU/Linux 的发行版本和商业的发行版本几乎都提供免费下载服务。免费意味着零试用成本，无需购买任何licence，也不需要为安装在第二台机器上付费。
####2.软件优势
Linux内置GCC、VI、Mysql等强大的编程软件和工具以及强大的命令行支持，你仅需几行命令就能完成一系列的工作。同时市面上的编程软件和工具几乎都对Linux支持最好，而在其它平台如windows可能支持不太好，甚至完全没法使用。
####3.社区支持
开源的解决方案几乎都是最先基于linux的，linux有最强大的社区支持，几乎你遇到的任何问题都能在社区上找到答案，你所需要做的事情就是放心折腾，出现麻烦Google一下，或者去社区发帖求助，问题就能迎刃而解。


虽然Java是跨平台的语言，但Linux对Java提供了最好的支持。以最少的资源消耗，最方便的操作，优雅的完成你所需要的做事情。


##Web容器
Web容器打算采用Nginx和Tomcat

####Jsp容器采用Tomcat
Apache Tomcat是Serlet和Jsp的一种Web容器，开放源代码，由Apache基金会支持。Tomcat是由Apache软件基金会下属的Jakarta项目开发的一个Servlet容器，按照Sun Microsystems提供的技术规范，实现了对Servlet和JavaServer Page（JSP）的支持，并提供了作为Web服务器的一些特有功能，如Tomcat管理和控制平台、安全域管理和Tomcat阀等。由于Tomcat本身也内含了一个HTTP服务器，它也可以被视作一个单独的Web服务器。但是，不能将Tomcat和Apache Web服务器混淆，Apache Web Server是一个用C语言实现的HTTP web server；这两个HTTP web server不是捆绑在一起的。Apache Tomcat包含了一个配置管理工具，也可以通过编辑XML格式的配置文件来进行配置。

####静态资源、反向代理服务器采用Nignx
Nginx是一款面向性能设计的HTTP服务器，相较于Apache、lighttpd具有占有内存少，稳定性高等优势。与旧版本（<=2.2）的Apache不同，nginx不采用每客户机一线程的设计模型，而是充分使用异步逻辑，削减了上下文调度开销，所以并发服务能力更强。整体采用模块化设计，有丰富的模块库和第三方模块库，配置灵活。 在Linux操作系统下，nginx使用epoll事件模型，得益于此，nginx在Linux操作系统下效率相当高。Nginx在官方测试的结果中，能够支持五万个平行连接，而在实际的运作中，是可以支持二万至四万个平行链接。


###数据库
数据库采用Mysql
MySQL是一个开放源代码的关系数据库管理系统，是世界上使用最多的数据库。
MySQL在过去由于性能高、成本低、可靠性好，已经成为最流行的开源数据库，因此被广泛地应用在Internet上的中小型网站中。随着MySQL的不断成熟，它也逐渐用于更多大规模网站和应用，比如维基百科、Google和Facebook等网站。非常流行的开源软件组合LAMP中的“M”指的就是MySQL。

###编程语言
编程语言采用Java
Java是一种计算机编程语言，拥有跨平台、面向对象、泛型编程的特性，广泛应用于企业级Web应用开发和移动应用开发。Java是最流行的语言之一，国内外大型互联网公司大多使用Java。

###编程框架
Bean管理使用Spring Framework
Spring Framework是一个开源的Java／Java EE全功能栈（full-stack）的应用程序框架。Spring Framework提供了一个简易的开发方式，这种开发方式，将避免那些可能致使底层代码变得繁杂混乱的大量的属性文件和帮助类。强大的基于JavaBeans的采用控制翻转（Inversion of Control，IoC）原则的配置管理，使得应用程序的组建更加快捷简易。并且Spring Framework和市面上主流的Java编程框架都能较好的兼容和集成。

MVC框架使用SpringMVC
Spring MVC属于SpringFrameWork的后续产品，已经融合在Spring Web Flow里面。Spring 框架提供了构建 Web 应用程序的全功能 MVC 模块。使用 Spring 可插入的 MVC 架构，可以选择是使用内置的 Spring Web 框架还可以是 Struts 这样的 Web 框架。通过策略接口，Spring 框架是高度可配置的，而且包含多种视图技术，例如 JavaServer Pages（JSP）技术、Velocity、Tiles、iText 和POI。Spring MVC 框架并不知道使用的视图，所以不会强迫您只使用 JSP 技术。Spring MVC 分离了控制器、模型对象、分派器以及处理程序对象的角色，这种分离让它们更容易进行定制。


Dao层使用Hibernate
Hibernate是一种Java语言下的对象关系映射解决方案。它为面向对象的领域模型到传统的关系型数据库的映射，提供了一个使用方便的框架。Hibernate也是目前Java开发中最为流行的数据库持久层框架，现已归JBOSS所有。它的设计目标是将软件开发人员从大量相同的数据持久层相关编程工作中解放出来。无论是从设计草案还是从一个遗留数据库开始，开发人员都可以采用Hibernate。
Hibernate不仅负责从Java类到数据库表的映射（还包括从Java数据类型到SQL数据类型的映射），还提供了面向对象的数据查询检索机制，从而极大地缩短的手动处理SQL和JDBC上的开发时间。


####下面是一些学习笔记

######基于注解的权限认证
电影评论网站需要后台去管理，有后台必然有权限认证。
最近突然发现Java注解真心神器。一行简单的注解可以搞定很多事情，非常便于使用。

注解可以看成是一个接口，注解实例就是一个实现了该接口的动态代理类。 注解大多是用做对某个类、方法、字段进行说明，标识的。以便在程序运行期间我们通 过反射获得该字段或方法的注解的实例，来决定该做些什么处理或不该进行什么处理。

定义和调用注解的方法都很简单，这里就不说明了。

重点说明下怎么让注解work起来。

注解本身并不会做任何事情，它需要工具支持才会有用。比如JUnit4的@Test注解自身不会做任何事情，JUnit会识别并调用所有标识为@Test的方法，这种识别处理一般是采用代理模式，通过反射来调用。

大致代码如下

    import java.lang.annotation.Annotation;
    import java.lang.reflect.Method;
    
    
    public class ReadAnnotationInfoTest {
    public static void main(String[] args) throws Exception {
        //测试AnnotationTest类，得到此类的类对象
        Class c = Class.forName(&quot;className&quot;);
    
        //获取该类所有声明的方法
        Method[] methods = c.getDeclaredMethods();
    
        //声明注解集合
        Annotation[] annotations;
    
        //遍历所有的方法得到各方法上面的注解信息
        for (Method method : methods) {
            //获取每个方法上面所声明的所有注解信息
            annotations = method.getDeclaredAnnotations();
    
            //再遍历所有的注解，打印其基本信息
            for (Annotation an : annotations) {
                System.out.println("方法名为：" + method.getName() +  "其上面的注解为：" +
                    an.annotationType().getSimpleName());
    
                Method[] meths = an.annotationType().getDeclaredMethods();
    
                //遍历每个注解的所有变量
                for (Method meth : meths) {
                    System.out.println("注解的变量名为：" + meth.getName());
                }
            }
        }
    }  
	

这个类可以得到类上的所有注解信息。 然后就可以根据需要植入代码段看来实现功能了。

用注解实现权限认证可以如下：

	public abstract class AbstractAuthenticationFilter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        FreeAccess freeAccess = method.getMethodAnnotation(FreeAccess.class);
        if (freeAccess != null) {
            return true;
        }
        Set<Privilege> priv = new HashSet<>();
        PrivilegeRequired pr = method.getClass().getAnnotation(PrivilegeRequired.class);
        if (pr != null && pr.value() != null && pr.value().length > 0) {
            priv.addAll(Arrays.asList(pr.value()));
        }
        pr = method.getMethodAnnotation(PrivilegeRequired.class);
        if (pr != null && pr.value() != null && pr.value().length > 0) {
            priv.addAll(Arrays.asList(pr.value()));
        }

        Privilege[] privileges = priv.toArray(new Privilege[priv.size()]);

        boolean loginRequired = privileges.length > 0
                || AnnotationUtils.findAnnotation(method.getBean().getClass(), LoginRequired.class) != null
                || method.getMethodAnnotation(LoginRequired.class) != null;
        if (loginRequired && !checkLogin(request)) {

            String url = request.getRequestURI();
            String queryString = request.getQueryString();
            if (StringUtils.isNotEmpty(queryString)) {
                url = url + "?" + queryString;
            }
            url = new String(Base64.encodeBase64(url.getBytes()));
            url = URLEncoder.encode(url);

            //如果是ajax请求响应头会有，x-requested-with
            if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                response.setHeader("sessionStatus", "timeout");//在响应头设置session状态
                return false;
            }

            response.setStatus(401);
            response.sendRedirect("/login?redirect=" + url);
            return false;
        }
        if (privileges.length > 0 && !checkPrivileges(request, privileges)) {
            response.setStatus(403);
            response.sendRedirect("/403");
        }
        return true;
    }


    public abstract boolean checkLogin(HttpServletRequest request);

    public abstract boolean checkPrivileges(HttpServletRequest request, Privilege... privileges);
	}


大致意思是继承springmvc的HandlerInterceptorAdapter，然后拦截每个请求，判断是否有@LoginRequired或者PrivilegeRequired的注解，如果有， 记录下当前uri（base64 URLEncoder之后），然后401到登陆界面，登陆成功后再跳回来。

可以在一个需要验证的Controller class上写上@LoginRequired，然后整个Controller的所有method都会去做权限验证。 可以用@FreeAccess 去排除不需要的的method。



######Nginx得到反向代理前的真实IP

Java Servlet可以通过request.getRemoteAddr()得到请求的客户端的IP

现在一般情况下都不是直接用Tomcat或者Jetty这样的web容器，都会在前面加上Nginx或者Tengine之类的静态Web容器来反向代理。
由于经过了Nginx转发请求，通过request.getRemoteAddr()得到的IP就成了127.0.0.1
可以在Nginx配置里加上 
 

        proxy_set_header x-forwarded-for $proxy_add_x_forwarded_for;

这个意思是在nginx做反向代理的时候把代理前的地址放到http header 的 x-forwarded-for 中，然后如下获取：
	 public static String getIP(HttpServletRequest request) {
        String IP = request.getRemoteAddr();
        String forwarded = request.getHeader("x-forwarded-for");

        if (forwarded != null) {
            forwarded = forwarded.split(",", 2)[0];
            if (pattern.matcher(forwarded).matches()) {
                return forwarded;
            }
        }
        if (pattern.matcher(IP).matches()) {
            return IP;
        } else {
            logger.warn("IP is not valid.[IP=" + IP + "]");
            return "";
        }
    }



