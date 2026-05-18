# 007 Privacy Compliance

Status: ready-for-agent

## 1. 目标

建立 privacy 模块的最小合规边界，表达权限统一申请、SDK 清单、敏感 API 门面、埋点脱敏和用户授权状态管理。

## 2. 为什么这个 issue 对面试有价值

隐私合规是移动端架构的重要能力。这个 issue 能展示候选人知道权限、SDK 初始化、敏感 API 和埋点不能散落在业务里，而要由架构统一收口。

## 3. 涉及文件

- Android `privacy` 模块或目录
- Android `bridge` 权限校验入口
- SDK 清单文档
- `docs/adr/0005-privacy-compliance.md`
- `docs/architecture.md`

## 4. 不允许修改的文件

- 真实第三方 SDK
- 真实登录、支付、推送实现
- 真实权限弹窗复杂 UI
- 与隐私无关的业务页面

## 5. 最小实现范围

- 定义授权状态模型，例如 unknown、accepted、rejected、revoked。
- 定义敏感能力清单，例如 location、camera、album、clipboard、notification。
- 提供 SDK 清单模板。
- 提供埋点脱敏规则说明或简单工具占位。
- 不接真实 SDK，不做完整权限 UI。

## 6. 验收标准

- privacy 模块或文档能说明权限、SDK、敏感 API 和埋点边界。
- Bridge 调用敏感能力需要经过 privacy 校验的设计被表达出来。
- 隐私同意前不初始化受限 SDK 的原则清楚。
- 没有接入真实第三方 SDK。
- 任务能在 1-2 小时内完成。

## 7. 面试时怎么讲

可以说：我把隐私合规做成架构模块，不让业务页面自己申请权限或初始化 SDK。这样可以控制授权状态、SDK 时机、敏感 API 和埋点脱敏。

## 8. 可以交给 Codex 的下一步 prompt

请完成 `docs/issues/007-privacy-compliance.md`。先阅读 `docs/adr/0005-privacy-compliance.md`。建立最小 privacy 合规边界，包括授权状态、敏感能力清单、SDK 清单模板和埋点脱敏说明。不要接真实 SDK，不要实现复杂权限 UI。
