# 002 Android Shell

Status: ready-for-agent

## 1. 目标

创建 Android Native Shell 的最小架构骨架，用来表达启动、路由、mock 登录态、核心交易入口、SDK 门面、性能监控和隐私合规的边界。

## 2. 为什么这个 issue 对面试有价值

Android Shell 是面试中展示 Native 架构能力的入口。它能说明为什么 Native 负责稳定核心链路，以及 app、core、feature、bridge、privacy 等模块如何协作。

## 3. 涉及文件

- Android 工程入口文件
- Android 模块配置文件
- `app`
- `core`
- `feature`
- `bridge`
- `privacy`
- `docs/architecture.md`

## 4. 不允许修改的文件

- Uniapp 页面实现
- 真实后端配置
- 真实登录或支付相关 SDK
- `docs/adr/` 中已接受决策，除非只补充不改变结论

## 5. 最小实现范围

- 建立 Native Shell 的最小目录或模块骨架。
- 提供 mock 启动状态、mock 登录态或游客态入口。
- 只保留商品、购物车、订单、支付占位的路由声明或页面占位。
- 不实现真实商品列表、真实支付、真实登录、真实推送。
- 不引入复杂依赖。

## 6. 验收标准

- Android Native 边界从包名或模块名上可读。
- Shell 能表达启动、路由、核心交易入口和合规入口。
- 核心交易链路没有放到 Uniapp。
- 代码量控制在架构骨架级别，能在 1-2 小时完成。
- 文档中能说明每个 Native 模块存在的理由。

## 7. 面试时怎么讲

可以说：我先做 Shell，不急着堆业务，因为 Shell 决定核心链路在哪里收口。商品关键动作、购物车、订单提交和支付占位放 Native，是为了稳定性、性能和合规。

## 8. 可以交给 Codex 的下一步 prompt

请完成 `docs/issues/002-android-shell.md`。先阅读 `AGENTS.md`、`docs/CONTEXT.md`、`docs/architecture.md` 和 `docs/adr/0001-native-uniapp-boundary.md`。只创建 Android Native Shell 的最小架构骨架和必要占位，不实现真实登录、支付、推送或后端调用。完成后输出修改文件、设计理由、验收标准和面试讲法。
