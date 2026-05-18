# 009 AI Commerce Scenarios

Status: ready-for-agent

## 1. 目标

定义并实现 AI 电商场景的最小 mock 表达，覆盖 ToC 智能客服、商品问答、ToB 商品标题生成、营销文案生成、售后话术辅助和埋点异常分析。

## 2. 为什么这个 issue 对面试有价值

AI 场景能展示候选人对现代电商工程的理解，但更重要的是边界意识：AI 是辅助，不自动影响交易、不自动承诺售后、不自动修改库存。

## 3. 涉及文件

- Uniapp AI 文案页或 mock 页面
- mock API 或 mock 数据文件
- AI 场景文档
- `docs/adr/0006-ai-commerce-scenarios.md`
- `docs/architecture.md`

## 4. 不允许修改的文件

- 真实大模型 API 调用
- API key 或密钥配置
- 真实订单、退款、库存修改逻辑
- Native 核心交易链路

## 5. 最小实现范围

- 定义 AI 场景列表和输入输出 mock。
- 可以实现一个轻量页面或文档化 mock 流程。
- AI 输出必须带人工确认状态或风险提示。
- 不调用真实 AI 服务。
- 不让 AI 自动执行交易动作。

## 6. 验收标准

- 6 个 AI 场景都有边界说明。
- 至少 1 个场景有 mock 输入输出示例。
- AI 输出不自动进入交易、售后或库存结果。
- 敏感信息脱敏原则被说明。
- 任务能在 1-2 小时内完成。

## 7. 面试时怎么讲

可以说：AI 在这个架构里是提效工具，不是自动决策系统。标题、文案、客服、售后话术和异常分析都需要人工确认，核心交易仍由确定性流程控制。

## 8. 可以交给 Codex 的下一步 prompt

请完成 `docs/issues/009-ai-commerce-scenarios.md`。先阅读 `docs/adr/0006-ai-commerce-scenarios.md`。定义 AI 电商 mock 场景和最小输入输出示例，可以补一个轻量 Uniapp 页面或文档。不要接真实大模型 API，不要配置密钥，不要让 AI 自动影响交易。
