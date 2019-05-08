# miaosha
基于spring cloud的秒杀系统

# 项目结构

## 1. registry
基于Eureka-Server

服务注册，服务发现

## 2. gateway
基于Spring cloud Gateway

网关：把用户请求转发到具体的服务模块

- 待添加：安全、权限

## 3. order-service
订单模块：负责创建、更新、展示订单

## 4. stock-service
库存模块：展示库存、更新库存

### 4.1 优化过程

测试环境：jmeter，1000个读线程，1000个写线程

4.1.1 增加数据库连接池大小至1000，避免了SQLTransientConnectionException: HikariPool-1 - Connection is not available。
盲目增大数据库连接池并不现实，下一步考虑使用二级缓存并适当缩减连接池。

4.1.2 

## 5. 服务间通信
候选方案有两种

- OpenFeign：基于REST接口，实际上还是http请求
- spring cloud stream kafka：使用消息队列，将不同服务解耦

倾向于第二种方案，目前在order-service中两种方案都有demo