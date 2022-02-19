# springcloud基础框架集成
>https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/zh-cn/index.html
1. 服务限流降级：支持 WebServlet、WebFlux, OpenFeign、RestTemplate、Dubbo 限流降级功能的接入，可以在运行时通过控制台实时修改限流降级规则，还支持查看限流降级 Metrics 监控。(使用sentinel)
2. 分布式配置管理：支持分布式系统中的外部化配置，配置更改时自动刷新。（使用nacos）
3. Rpc服务：扩展 Spring Cloud 客户端 RestTemplate 和 OpenFeign，支持调用 Dubbo RPC 服务。（使用nacos及openfeign）
4. 分布式任务调度：提供秒级、精准、高可靠、高可用的定时（基于 Cron 表达式）任务调度服务。（使用xxljob）
