# Architecture Overview

## 目的

本文件是后续任务的架构入口。每次开发或文档任务开始前，都必须先阅读：

- `AGENTS.md`
- `docs/CONTEXT.md`
- `docs/architecture.md`

本项目当前阶段只建立架构样板的文档和规则，不实现完整业务。

## 总体架构

项目采用 Android Native + Uniapp 的混合架构：

- Android Native 承担 App 壳、核心链路、性能敏感能力、系统能力、Bridge 和隐私合规。
- Uniapp 承担高频变化页面、运营配置页面、mock 页面流和跨端展示逻辑。
- Native 与 Uniapp 之间只能通过统一 Bridge 交互。
- 所有数据接口都使用 mock，不连接真实后端。

## Android 模块边界

- `core`：通用基础能力。
- `feature`：业务页面与用例。
- `bridge`：Native 与 Uniapp 通信协议、能力注册和调用分发。
- `privacy`：权限、隐私弹窗、合规状态和 SDK 初始化边界。
- `data`：mock 数据源和 Repository 实现。
- `domain`：业务用例和领域模型。
- `ui`：页面状态和展示逻辑。

## Uniapp 分层边界

- `pages`：页面入口和页面编排。
- `api`：mock API 调用。
- `store`：页面状态。
- `bridge`：Bridge 调用封装。
- `utils`：通用工具。

## 架构决策记录

关键设计取舍必须记录到 `docs/adr/`。每条 ADR 应说明：

- 背景
- 决策
- 备选方案
- 取舍理由
- 后续影响
- 面试讲法

## Issue 拆分

开发任务使用本地 markdown issue，统一放在 `docs/issues/`。

每个 issue 必须足够小，能单独验收，并且服务于面试表达。不允许一个 issue 同时生成完整 Android、Uniapp、Bridge、CI/CD 和文档体系。

