# 008 CI/CD

Status: ready-for-agent

## 1. 目标

建立 CI/CD 的最小质量门禁设计，覆盖文档检查、lint、测试、构建校验、架构边界检查和 issue/ADR 工作流。

## 2. 为什么这个 issue 对面试有价值

架构能力不只体现在代码，还体现在团队如何长期保持边界。CI/CD 能展示候选人对协作、质量门禁、文档一致性和架构治理的理解。

## 3. 涉及文件

- `.github/workflows/` 或本地 CI 说明文档
- `docs/architecture.md`
- `docs/PRD.md`
- `docs/issues/`
- `docs/adr/`

## 4. 不允许修改的文件

- 业务功能代码
- 真实发布签名配置
- 真实生产环境密钥
- 真实第三方服务配置

## 5. 最小实现范围

- 可以先写 CI/CD 设计文档，或新增最小 workflow 占位。
- 检查文档路径、ADR 存在性、markdown 基础格式。
- 预留 Android lint/test/build 命令，但不要求完整工程可发布。
- 不配置真实发布、签名、上传商店或密钥。

## 6. 验收标准

- CI/CD 设计能说明文档检查、lint、测试、构建和边界检查。
- 不包含任何真实密钥或发布配置。
- 工作流或文档能支撑后续逐步接入。
- 范围小，可在 1-2 小时内完成。

## 7. 面试时怎么讲

可以说：我用 CI/CD 保护架构边界，不只是跑测试。文档、ADR、issue、lint、构建和边界检查一起保证团队不会把核心交易链路误放到 Uniapp 或绕过 Bridge。

## 8. 可以交给 Codex 的下一步 prompt

请完成 `docs/issues/008-ci-cd.md`。先阅读 `docs/architecture.md` 和 `docs/PRD.md`。新增最小 CI/CD 设计或 workflow，占位文档检查、lint、测试、构建和架构边界检查。不要配置真实发布、签名、密钥或第三方服务。
