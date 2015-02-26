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