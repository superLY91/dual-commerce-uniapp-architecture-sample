# 005 Uniapp Pages

Status: ready-for-agent

## 1. 目标

创建 Uniapp Business Layer 的最小页面组织，展示活动页、营销页、商家工作台、商品管理轻量页、订单筛选页、AI 文案页和配置化页面的边界。

## 2. 为什么这个 issue 对面试有价值

这个 issue 能说明 Uniapp 为什么适合承载高频变化页面，也能证明候选人知道哪些页面不能放 Uniapp：核心交易链路不能下沉到动态页面。

## 3. 涉及文件

- Uniapp `pages`
- Uniapp `api`
- Uniapp `store`
- Uniapp `bridge`
- Uniapp `utils`
- `docs/architecture.md`
- `docs/adr/0001-native-uniapp-boundary.md`

## 4. 不允许修改的文件

- Android 核心交易实现
- 真实登录、支付、推送逻辑
- 真实后端 API
- Bridge Native 内部实现，除非只是调用已有契约

## 5. 最小实现范围

- 建立 Uniapp 目录分层和 3-5 个页面占位。
- 页面只用 mock 数据。
- 页面可以展示 Bridge 调用入口，但不直接访问 Native 内部能力。
- 不实现完整活动系统、商家后台或商品管理后台。
- 不承载购物车结算、订单提交和支付流程。

## 6. 验收标准

- Uniapp 目录体现 `pages`、`api`、`store`、`bridge`、`utils` 分层。
- 页面能表达活动、营销、商家工作台和 AI 文案的定位。
- 核心交易链路仍然留在 Native。
- mock 数据和状态流简单可读。
- 任务能在 1-2 小时内完成。

## 7. 面试时怎么讲

可以说：Uniapp 不是为了替代 Native，而是为高频运营和商家轻量页面提速。交易链路仍由 Native 控制，Uniapp 通过 Bridge 调用受控能力。

## 8. 可以交给 Codex 的下一步 prompt

请完成 `docs/issues/005-uniapp-pages.md`。先阅读 `docs/architecture.md` 和 `docs/adr/0001-native-uniapp-boundary.md`。创建 Uniapp 最小页面组织和 mock 页面，占位活动、营销、商家工作台、订单筛选、AI 文案或配置化页面。不要实现购物车结算、订单提交、真实登录、真实支付或真实后端。
