# miaosha
基于spring cloud的秒杀系统

# 项目结构

## registry
基于Eureka-Server

服务注册，服务发现

## gateway
基于Spring cloud Gateway

网关：把用户请求转发到具体的服务模块

- 待添加：安全、权限

## order-service
订单模块：负责创建、更新、展示订单

## stock-service
库存模块：展示库存、更新库存

## 服务间通信
目前基于Spring cloud openfeign，是基于http协议的通信框架，正在尝试是否能够替换为MQ