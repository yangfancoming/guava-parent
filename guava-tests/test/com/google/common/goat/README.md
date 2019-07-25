# 目录
    item01
    item02 EventBus 基本用法
    item03 Listener之间的继承关系
    item04 不同类型参数的Subscribe
    item05 继承关系的event
    item06 DeadEvent
    item07 EventBus之异常处理
    
    
# 简介
    EventBus是Guava的事件处理机制，是设计模式中的观察者模式（生产/消费者编程模型）的优雅实现，在应用中可以处理一些异步任务。
    对于事件监听和发布订阅模式，EventBus是一个非常优雅和简单解决方案，我们不用创建复杂的类和接口层次结构。