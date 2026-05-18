# 006 Performance Monitor

Status: ready-for-agent

## 1. 目标

建立性能监控的最小接口和 mock 指标，覆盖启动、首屏、商品列表、图片、网络、Bridge 和 Uniapp 页面加载。

## 2. 为什么这个 issue 对面试有价值

性能治理是高级 Android 面试常见追问。这个 issue 能展示候选人不是只会说优化点，而是会定义指标、采集边界、弱网策略和降级思路。

## 3. 涉及文件

- Android `core` 或 `monitor` 相关目录
- Android `bridge` 监控埋点入口
- Uniapp 页面性能 mock 记录点
- `docs/adr/0004-performance-strategy.md`
- `docs/architecture.md`

## 4. 不允许修改的文件

- 真实 APM SDK
- 真实第三方监控平台配置
- 真实网络服务
- 与性能无关的大量业务页面

## 5. 最小实现范围

- 定义 `PerformanceMonitor` 或等价接口。
- 提供 mock 指标名称和记录方法。
- 增加 2-3 个示例记录点，例如启动、Bridge 调用、Uniapp 页面加载。
- 文档化防重复提交、幂等 token、请求去重、超时重试、降级和缓存策略。
- 不接真实 APM。

## 6. 验收标准

- 指标名称覆盖启动、首屏、列表、图片、网络、Bridge、Uniapp。
- 示例代码或文档能说明指标从哪里采集。
- 弱网和高并发策略没有变成完整业务实现。
- 没有引入复杂依赖。
- 任务能在 1-2 小时内完成。

## 7. 面试时怎么讲

可以说：我把性能问题先变成指标，再谈优化。启动、列表、图片、网络、Uniapp 和 Bridge 都有采集点，弱网下再配合防重、幂等、去重、降级和兜底。

## 8. 可以交给 Codex 的下一步 prompt

请完成 `docs/issues/006-performance-monitor.md`。先阅读 `docs/architecture.md` 和 `docs/adr/0004-performance-strategy.md`。创建最小性能监控接口、mock 指标和少量示例记录点，不接真实 APM，不实现复杂业务。
