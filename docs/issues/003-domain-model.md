# 003 Domain Model

Status: ready-for-agent

## 1. 目标

定义电商核心领域模型和 mock API 契约，覆盖商品、搜索、购物车、订单、支付占位、售后、商家管理、库存、营销活动和 AI 内容的最小模型。

## 2. 为什么这个 issue 对面试有价值

领域模型能展示候选人是否理解电商业务边界。这个 issue 的重点不是实现业务，而是证明 Native 与 Uniapp 可以围绕同一套 Model Contract 协作，避免双端语义漂移。

## 3. 涉及文件

- Android `domain` 模块或目录
- Android `data` mock 模块或目录
- Uniapp `api` 契约文档或 mock 数据目录
- `docs/adr/0003-api-model-contract.md`
- `docs/architecture.md`

## 4. 不允许修改的文件

- 真实网络环境配置
- 真实后端 API 地址
- 登录、支付、推送 SDK
- 复杂数据库或 ORM 配置

## 5. 最小实现范围

- 定义核心模型名称、字段含义和状态枚举。
- 提供少量 mock 数据或契约文档。
- 使用 Repository 边界表达数据源可替换。
- 不实现完整搜索、库存、营销或订单引擎。
- 不接真实后端。

## 6. 验收标准

- 商品、购物车、订单、库存、营销至少有最小模型说明。
- 支付明确是 placeholder，不是真实支付。
- 模型字段能支持 Native 和 Uniapp 共用语义。
- Repository 或 mock API 边界清楚。
- 代码或文档能在 1-2 小时内完成。

## 7. 面试时怎么讲

可以说：我先定义模型契约，是因为混合架构最怕两端对同一个状态理解不一致。订单状态、库存状态、活动状态必须先有共同语言，再做页面。

## 8. 可以交给 Codex 的下一步 prompt

请完成 `docs/issues/003-domain-model.md`。先阅读 `docs/architecture.md` 和 `docs/adr/0003-api-model-contract.md`。定义最小电商领域模型和 mock API 契约，覆盖商品、搜索、购物车、订单、支付占位、售后、商家、库存、营销和 AI 内容。不要接真实后端，不要实现完整业务引擎。
