# 001 Project Context

Status: ready-for-agent

## 1. 目标

完善项目长期上下文，让 `docs/CONTEXT.md`、`docs/PRD.md`、`docs/architecture.md` 和 ADR 之间的叙事一致。重点说明本项目是面试架构样板，不是完整电商 App。

## 2. 为什么这个 issue 对面试有价值

面试官首先会判断候选人是否知道项目边界。这个 issue 让项目一开始就能讲清楚：为什么要做 Native + Uniapp，为什么只做 mock，为什么文档和 ADR 是架构样板的重要部分。

## 3. 涉及文件

- `docs/CONTEXT.md`
- `docs/PRD.md`
- `docs/architecture.md`
- `docs/adr/*.md`

## 4. 不允许修改的文件

- Android 源码目录
- Uniapp 源码目录
- Gradle 配置
- CI 配置

## 5. 最小实现范围

- 补充或校对 `docs/CONTEXT.md` 中的项目背景、非目标、职责边界和面试表达目标。
- 不新增业务功能。
- 不扩写成完整商业 PRD。
- 只做能支撑后续 issue 的上下文说明。

## 6. 验收标准

- `docs/CONTEXT.md` 明确“不做真实登录、真实支付、真实后端、真实第三方 SDK”。
- `docs/CONTEXT.md` 与 `docs/PRD.md`、`docs/architecture.md` 没有边界冲突。
- 文档能解释 Native、Uniapp、Bridge、privacy、performance、CI/CD、AI 的关系。
- 本次变更只涉及文档。

## 7. 面试时怎么讲

可以说：我先把上下文写清楚，是为了防止样板项目走偏。这个项目不是用功能数量证明能力，而是用边界、契约、ADR 和 issue 拆分证明架构判断。

## 8. 可以交给 Codex 的下一步 prompt

请基于 `AGENTS.md`、`docs/PRD.md`、`docs/architecture.md` 和现有 ADR，校对并完善 `docs/CONTEXT.md`。只写文档，不写代码。重点保证项目目标、非目标、Native/Uniapp 边界、mock 约束、面试表达目标一致。
