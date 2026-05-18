# 010 Interview Script

Status: ready-for-agent

## 1. 目标

整理项目面试讲稿，形成 1 分钟、5 分钟、15 分钟三个版本，并准备常见追问回答。

## 2. 为什么这个 issue 对面试有价值

这是面试架构样板项目，最终价值取决于能否讲清楚。这个 issue 把架构、ADR、业务边界、性能、隐私、AI 和 CI/CD 转化成面试表达。

## 3. 涉及文件

- `docs/interview-script.md`
- `docs/architecture.md`
- `docs/PRD.md`
- `docs/adr/*.md`
- `docs/CONTEXT.md`

## 4. 不允许修改的文件

- Android 源码
- Uniapp 源码
- CI 配置
- 已接受 ADR 的结论，除非只补充讲法

## 5. 最小实现范围

- 新增或完善一份面试讲稿文档。
- 包含 1 分钟、5 分钟、15 分钟版本。
- 包含 8-12 个可能追问及回答。
- 不新增业务功能。
- 不把讲稿写成冗长论文。

## 6. 验收标准

- 讲稿能覆盖 Native/Uniapp 边界、Bridge、Model Contract、性能、隐私、AI、CI/CD。
- 1 分钟版本能快速讲清楚项目价值。
- 5 分钟版本能讲清楚取舍。
- 15 分钟版本能应对架构深挖。
- 追问回答明确“不做真实登录、支付、后端、SDK”的边界。

## 7. 面试时怎么讲

可以说：我把项目准备成三层讲法。短版讲边界和价值，中版讲取舍和治理，长版讲业务模型、Bridge、弱网、隐私、AI 和团队协作。

## 8. 可以交给 Codex 的下一步 prompt

请完成 `docs/issues/010-interview-script.md`。先阅读 `docs/CONTEXT.md`、`docs/PRD.md`、`docs/architecture.md` 和所有 ADR。新增 `docs/interview-script.md`，包含 1 分钟、5 分钟、15 分钟讲稿和常见追问回答。只写文档，不写代码。
