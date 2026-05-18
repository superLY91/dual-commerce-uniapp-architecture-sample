# 004 Native Uniapp Bridge

Status: ready-for-agent

## 1. 目标

建立 Native-Uniapp Bridge 的最小契约和示例能力，统一 module、action、version、params、result、error、permission、traceId 的结构。

## 2. 为什么这个 issue 对面试有价值

Bridge 是混合架构的关键风险点。这个 issue 能展示候选人如何避免调用散落、权限失控、错误码混乱和版本兼容困难。

## 3. 涉及文件

- Android `bridge` 模块或目录
- Uniapp `bridge` 封装目录
- Bridge 契约文档
- `docs/adr/0002-bridge-contract.md`
- `docs/architecture.md`

## 4. 不允许修改的文件

- 真实 Native SDK 集成
- 真实支付、登录、推送能力
- Uniapp 页面大规模业务逻辑
- 与 Bridge 无关的领域模型

## 5. 最小实现范围

- 定义 Bridge 请求、响应和错误结构。
- 增加 2-3 个示例能力，例如 `privacy.getConsentStatus`、`trade.openOrderDetail`、`monitor.trackEvent`。
- Uniapp 侧只写统一调用封装或伪调用示例。
- 不实现 WebView 容器完整通信。
- 不暴露任意 Native 内部能力。

## 6. 验收标准

- Bridge 契约结构清楚，包含版本、权限、错误码和 traceId。
- 示例能力能体现隐私、交易跳转和埋点的边界。
- Uniapp 调用不散落，必须经过统一封装。
- 错误码与 ADR 保持一致。
- 代码任务可在 1-2 小时内完成。

## 7. 面试时怎么讲

可以说：Bridge 不是简单 JSBridge，而是混合架构治理层。我把权限、参数校验、错误码、版本和监控都放进契约里，避免页面随意调用 Native。

## 8. 可以交给 Codex 的下一步 prompt

请完成 `docs/issues/004-native-uniapp-bridge.md`。先阅读 `docs/adr/0002-bridge-contract.md`。实现或文档化最小 Bridge 契约，并提供 2-3 个示例能力。不要接真实 SDK，不要实现完整 WebView 通信，只表达架构边界。
