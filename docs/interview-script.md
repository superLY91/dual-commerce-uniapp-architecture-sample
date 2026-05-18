# Interview Script

## 1. 1 分钟自我介绍版

我主要是 Android 方向，比较关注 App 架构边界、复杂列表性能、JS-Native 通信、启动和页面性能优化，以及最近 AI 在移动端工程里的落地方式。

这个项目是我为了面试和架构验证做的 architecture sample，不是一个真实上线的完整电商 App。我用它来表达一个 toB + toC 电商在 Android Native + Uniapp 混合架构下怎么拆边界：核心交易链路放 Native，比如商品关键动作、购物车、订单提交、支付占位、隐私合规和性能监控；高频变化的活动页、营销页、商家工作台、订单筛选和 AI 文案页放 Uniapp。

我重点想展示的不是功能堆得多，而是架构判断：哪些东西必须稳定，哪些东西需要快速迭代，Native 和 Uniapp 怎么通过统一 Bridge 通信，模型和 API 契约怎么避免双端语义不一致，性能、弱网、隐私和 AI 风险怎么提前设计进去。

## 2. 3 分钟项目架构介绍版

这个项目可以理解成一个双端电商架构样板，面向 toC 用户和 toB 商家两类场景。toC 侧关注商品浏览、购物车、订单和售后入口；toB 侧关注商家工作台、商品轻量编辑、订单筛选、营销活动和 AI 文案生成。

我把整体拆成三层：Android Native Shell、Uniapp Business Layer 和 Shared Core。Native Shell 负责启动、路由、mock 登录态、核心交易入口、Bridge、性能监控和隐私合规。Uniapp 负责变化快的页面，比如活动页、营销页、商家工作台和 AI 文案页。Shared Core 里面主要是模型契约、mock API、Bridge command 协议、监控和隐私门禁。

Android 目录里我做了 `app`、`core-model`、`core-network`、`core-auth`、`core-bridge`、`core-monitor`、`core-compliance` 和 `feature`。这里没有接真实后端，也没有真实登录、支付或者第三方 SDK，因为这个项目的重点是架构表达。比如 `Order` 里有 `PaymentPlaceholder`，就是明确告诉面试官：支付链路应该 Native 控制，但这个 sample 不做真实支付。

Uniapp 目录里我按 `pages`、`api`、`stores`、`bridge`、`utils`、`components` 组织。页面包括商品列表和详情、订单列表和详情、商家 dashboard、商品轻量编辑、营销活动和 AI 文案。它们都走 mock API，`request.ts` 里表达 token 注入、traceId、超时、重试和错误码处理。Uniapp 调 Native 只能走 `nativeBridge.ts`，它的 command 字段和 Android 的 `BridgeCommand` 对齐。

我最想讲清楚的是：混合架构不是简单把一部分页面丢给 H5 或 Uniapp，而是要有边界。Native 保核心稳定性和安全性，Uniapp 保运营效率，Bridge 保能力治理，Model Contract 保双端语义一致。

## 3. 5 分钟详细架构介绍版

我先说项目定位。这个仓库不是完整电商，不追求真实交易闭环，也不接真实后端。它是一个 architecture sample，用来验证如果我要做一个 toB + toC 的 Android Native + Uniapp 电商 App，我会怎么设计边界、契约和治理机制。

第一层是 Android Native Shell。Native 负责 App 启动、路由、mock session、商品核心流、购物车、订单提交、支付占位、性能监控和隐私合规。代码上对应 `android/app` 和 `android/core`。比如 `AppInitializer` 负责启动初始化，并且通过 `PrivacyManager` 判断隐私协议同意前不能初始化敏感 SDK；`AppRouter` 区分 Native 页面和 Uniapp 页面；`PerformanceTracker` 记录启动、页面、接口和 Bridge 耗时。

第二层是领域模型和 mock 数据。`core-model` 里有 `Product`、`CartItem`、`Order`、`Merchant`、`User`，这些不是为了实现完整业务，而是为了表达 Model Contract。混合架构里最怕 Native 和 Uniapp 对同一个字段理解不一致，比如订单状态、库存状态、支付状态。所以我先把模型语义定下来，再用 Repository 隔离 mock 数据源，比如 `ProductRepository`、`CartRepository`、`OrderRepository`。

第三层是 Native-Uniapp Bridge。Android 侧有 `BridgeCommand`、`BridgeResult`、`NativeBridge`、`BridgePermissionChecker` 和几个 handler。Uniapp 侧有 `nativeBridge.ts` 和 `bridgeTypes.ts`。command 里固定带 `module`、`action`、`version`、`params`、`requiredPermission`、`traceId`。调用流程是：Uniapp 创建 command，进入 NativeBridge，先做参数校验，再过权限检查，再找 handler，最后返回统一结果，并记录 Bridge 耗时。这里我特意支持了 `auth.getToken`、`router.openNativePage`、`router.openUniPage`、`pay.startPayment`、`track.sendEvent` 这些示例。

第四层是 Uniapp Business Layer。Uniapp 不是承接核心交易，而是承接高频变化页面，比如营销活动、商家工作台、订单筛选、商品轻量编辑、AI 文案生成。`uniapp/src/api` 全部是 mock API，`request.ts` 体现 token 注入、错误码、超时、重试和 traceId。`track.ts` 做统一埋点和敏感字段脱敏，然后通过 Bridge 调 Native 的 `track.sendEvent`。

最后是治理能力。性能上，我会关注启动耗时、首屏、Feed/商品列表滑动、图片加载、网络耗时、Bridge 耗时和 Uniapp 页面加载。弱网和高并发上，关键动作要有防重复提交、幂等 token、请求去重、读请求有限重试、写请求谨慎重试、降级开关和状态兜底。隐私上，权限、敏感 API、SDK 初始化和埋点脱敏都要收口到 `core-compliance`。AI 上，我只把它放在辅助场景，比如商品标题、营销文案、售后话术、埋点异常分析，不让 AI 自动影响交易结果。

所以这个 sample 最核心的价值是：它不是展示我做了多少页面，而是展示我怎么把一个复杂 App 拆成可解释、可维护、可演进的架构。

## 4. Android Native 与 Uniapp 边界怎么讲

我一般会这样讲：我不是按技术喜好拆 Native 和 Uniapp，而是按业务风险和变化频率拆。

Native 负责稳定核心链路：启动、登录态、商品核心动作、购物车、订单提交、支付占位、Native SDK、性能监控、隐私合规。这些地方要稳定、可控、性能好，也要能处理弱网和异常状态。

Uniapp 负责变化快的业务页面：活动页、营销页、商家工作台、商品轻量编辑、订单筛选、AI 文案页、配置化页面。这些页面经常受运营策略影响，适合快速迭代。

边界原则很简单：核心交易动作不放 Uniapp；Uniapp 不直接调 Native 内部能力；所有 Native 能力都走 Bridge；双端共享模型语义和错误码。

## 5. Native-Uniapp Bridge 怎么讲

我会说 Bridge 不是简单的 JSBridge 工具类，而是混合架构的治理层。

这个项目里 Bridge 是 command 模式。每次调用都带 `module`、`action`、`version`、`params`、`requiredPermission`、`traceId`。比如 `router.openNativePage`、`pay.startPayment`、`track.sendEvent` 都是 command。

Android 侧流程是：`NativeBridge.dispatch` 先校验 command，再调用 `BridgePermissionChecker`，然后分发给对应 handler，最后统一返回 `BridgeResult`。失败也不是随便抛异常，而是归到 `INVALID_PARAMS`、`PERMISSION_DENIED`、`NOT_SUPPORTED`、`NATIVE_ERROR` 这类错误码。

这个设计解决三个问题：第一，能力不会散落；第二，权限和隐私可以统一卡住；第三，出了问题可以靠 traceId 和 Bridge 耗时排查。

## 6. 商品、购物车、订单、支付链路怎么讲

商品这块，我会拆成两部分：活动商品展示可以在 Uniapp，商品详情里的核心购买动作要回到 Native。这样既能支持运营快速改页面，也能保证交易入口稳定。

购物车是 Native 核心链路。因为购物车涉及勾选、数量、价格快照、失效商品、库存变化和结算入口，弱网下还要处理重复点击和状态兜底，所以不适合放到动态层里。

订单提交也在 Native。这里我会强调幂等 token、防重复提交、提交中状态、超时后的订单查询兜底。用户看到超时，不代表服务端一定失败，所以不能简单让用户无限重复提交。

支付在这个 sample 里只做 `PaymentPlaceholder` 和 `pay.startPayment`。我不会说这里接了真实支付，因为没有。我的表达是：支付应该由 Native 控制，并且真实项目里还要考虑支付 SDK 初始化、回调验签、结果轮询、订单状态同步和合规风险。

## 7. 高并发和弱网怎么讲

我会从用户动作和系统状态两个角度讲。

用户动作上，订单提交、支付、优惠领取这类写操作要防重复点击，按钮进入提交中状态，必要时用本地状态机控制。服务端或 mock 契约上要有幂等 token，保证同一个动作重复到达不会生成多笔订单。

网络层上，读请求可以做有限重试，比如商品列表、活动配置、商家 dashboard；写请求不能盲目重试，要么有幂等 token，要么让用户明确确认。对于同参数请求，可以做请求去重，避免页面重复刷新造成浪费。

页面层上，弱网要有兜底状态，比如加载中、失败重试、缓存数据、降级模块。活动页、AI 文案、推荐楼层可以降级；订单提交结果不能只信本地缓存，要有订单查询兜底。

## 8. 性能优化怎么讲

我会结合自己的 Android 优势讲，尤其是启动、Feed 列表和 JS-Native 通信。

启动上，核心是减少首屏阻塞：非必要初始化延后，隐私同意前不初始化敏感 SDK，首屏只加载必要配置。这个 sample 里 `AppInitializer` 和 `PerformanceTracker` 就是在表达这个边界。

Feed 或商品列表上，我会关注分页、预加载、Diff、View 复用、图片尺寸、缓存、滑动中暂停重任务、避免主线程做复杂计算。电商商品列表和 Feed 很像，都是高频滑动、高图片密度、高曝光埋点的场景。

Bridge 性能上，要减少页面内零散调用。能合并的 command 合并，调用要有 traceId 和耗时统计。因为 JS-Native 通信如果设计不好，很容易变成页面卡顿和问题排查困难的来源。

Uniapp 页面上，要控制首屏组件数量，活动配置分块加载，mock request 里也体现了超时、重试和错误码。真实项目里还会关注 WebView 预热、资源缓存、包体和页面白屏时间。

## 9. 隐私合规怎么讲

我会说隐私合规不能靠每个业务同学自觉，必须做成架构边界。

在这个 sample 里，`PrivacyManager` 管隐私协议状态，`PermissionGate` 管权限前置条件，`SensitiveApiGuard` 管敏感 SDK 和敏感 API 访问。隐私协议没同意前，敏感 SDK 不初始化，埋点也不能随便上报。

Uniapp 调 Native 的时候，也不能绕过这个边界。比如 `track.sendEvent` 要走 Bridge，Android 侧 `BridgePermissionChecker` 会根据 command 判断是否需要 tracking 权限。

埋点也要脱敏。像手机号、地址、身份证、原始订单号这类字段不能明文传。Uniapp 的 `track.ts` 和 Android 的 `BusinessEventTracker` 都表达了这个思路。

## 10. AI 在电商双端中的应用怎么讲

我会先讲边界：AI 在这个项目里是辅助能力，不是自动决策系统。

toC 可以做智能客服、商品问答、售后咨询总结；toB 可以做商品标题生成、营销文案生成、售后话术辅助；工程侧可以做埋点异常分析、日志归因、测试用例补全和文档维护。

但是 AI 输出不能自动下单、自动退款、自动改库存，也不能直接承诺售后结果。像 `uniapp/src/pages/ai/copywriter.vue` 这种页面，只生成候选文案，并且明确需要人工确认。

从移动端工程化角度，我会关注几个点：AI 请求的上下文要脱敏；生成结果要可追溯；页面要有 loading、失败、重试和人工确认状态；AI 能力最好作为独立模块接入，不要侵入核心交易流程。

## 11. 团队管理和 CI/CD 怎么讲

我会说架构不是画完图就结束了，关键是团队怎么长期不跑偏。

这个项目里我用了 `docs/CONTEXT.md`、`docs/architecture.md`、`docs/PRD.md`、`docs/adr/` 和 `docs/issues/`。也就是说，重要决策进 ADR，长期上下文进 CONTEXT，任务拆成小 issue，每个 issue 都有目标、范围、不允许修改的文件和验收标准。

CI/CD 上，后续可以加文档检查、lint、单元测试、构建校验和边界检查。比如可以检查 Uniapp 是否绕过 `nativeBridge.ts` 调 Native，Android 是否绕过 `PrivacyManager` 初始化敏感 SDK，或者核心交易链路是否误放进 Uniapp。

团队管理上，我比较倾向小步提交、边界先行、文档和代码同步。这样不管是新人接手还是面试官看项目，都能知道为什么这么拆。

## 12. 面试官可能追问的 20 个问题和参考回答

### 1. 这个项目是真实上线项目吗？

不是。我会明确说这是我为了面试和架构验证做的 architecture sample，不是生产项目。我没有夸大它的规模，重点是展示架构边界、契约和工程治理思路。

### 2. 为什么不用纯 Native？

纯 Native 稳定，但运营活动、营销页、商家轻量页面变化很快，每次都依赖发版会拖慢交付。所以我保留 Native 做核心链路，把高频变化页面放 Uniapp。

### 3. 为什么不用纯 Uniapp？

纯 Uniapp 对核心交易链路风险比较高，尤其是购物车、订单提交、支付、隐私和 Native SDK 能力。这些地方需要更强的稳定性、性能和合规控制，所以我放在 Native。

### 4. 商品详情为什么还要区分展示和核心动作？

商品展示、活动楼层可以快速变化，适合 Uniapp；但加入购物车、立即购买、价格库存校验这类核心动作要回 Native。这样既有迭代效率，又能守住交易安全。

### 5. 购物车为什么不放 Uniapp？

购物车涉及价格快照、库存变化、失效商品、勾选状态和结算入口。弱网下还要处理重复点击和状态兜底。我认为它属于核心交易链路，应该 Native 控制。

### 6. 支付为什么只做 placeholder？

因为这个项目目标不是接真实支付 SDK。我用 `PaymentPlaceholder` 表达支付应该由 Native 管控，同时明确不做真实支付，避免 sample 变成不必要的 SDK 集成项目。

### 7. Bridge 怎么防止能力滥用？

所有调用必须是 command，进入 `NativeBridge.dispatch` 后先校验参数，再过 `BridgePermissionChecker`。比如支付、埋点、路由都有最低权限要求，页面不能直接绕过 handler 调 Native 内部能力。

### 8. Bridge 版本兼容怎么做？

command 里有 `version` 字段。真实项目里我会按 module/action/version 做兼容，旧版本保留一段时间，新字段走可选参数，废弃能力通过错误码和灰度控制处理。

### 9. JS-Native 通信性能怎么优化？

减少碎片化调用，能合并就合并；避免在滚动过程中频繁同步调用；每个 command 带 traceId 和耗时；高频事件做节流和批量上报。这个 sample 里 `PerformanceTracker.trackBridgeCost` 就是这个方向。

### 10. Model Contract 有什么意义？

它解决双端语义漂移。比如订单状态、库存状态、支付状态，如果 Native 和 Uniapp 各定义一套，后面一定会出现解释不一致。所以我先定义 `Product`、`CartItem`、`Order` 这些共享语义。

### 11. request.ts 为什么要写 token、超时和重试？

虽然是 mock，但我要表达网络层设计。请求应该统一注入 token 和 traceId，统一处理错误码。读请求可以有限重试，写请求不能盲目重试，要结合幂等 token。

### 12. 弱网下订单提交超时怎么办？

不能简单认为失败，也不能让用户无限点提交。我的做法是提交中状态、防重复点击、幂等 token，然后超时后引导查订单结果或稍后刷新。

### 13. Feed 商品列表你会怎么优化？

我会从数据、布局、图片和线程几个方向做。分页和预加载控制数据量，Diff 减少刷新成本，图片按尺寸加载和缓存，滑动中减少重计算和曝光高频上报，主线程只做必要 UI 工作。

### 14. Uniapp 活动页性能怎么保证？

首屏模块要少，配置分块加载，图片做尺寸和缓存控制，Bridge 调用集中封装，非核心模块可以降级。必要时 Native 做容器预热和资源预加载。

### 15. 隐私协议同意前哪些事情不能做？

不能初始化敏感 SDK，不能访问敏感 API，不能上报带个人信息的埋点。像定位、相机、相册、剪贴板、设备标识都要通过统一门面控制。

### 16. AI 文案生成有什么风险？

主要是幻觉、夸大宣传、合规和误导用户。所以我只把 AI 当辅助工具，输出候选内容，必须人工确认，不自动发布，也不自动影响交易。

### 17. AI 在移动端工程化里还能做什么？

除了业务里的文案和客服，工程侧可以辅助生成测试用例、做日志摘要、分析埋点异常、维护文档和 review 风险点。但核心判断还是要工程师把关。

### 18. 这个项目现在最大的不足是什么？

它是 skeleton，不是可运行完整工程。没有 Gradle、没有真实 Uniapp 构建、没有单元测试和 CI workflow。这个是有意控制范围，后续可以按 issue 一步步补。

### 19. 如果继续做下一步，你会先补什么？

我会先补 CI/CD 和测试边界，比如 markdown 检查、Kotlin/TS 静态检查、Bridge command 单测、request mock 测试。然后再补性能 monitor 和隐私合规模块的更完整示例。

### 20. 这个 sample 最能体现你的优势在哪里？

我觉得是几个点：Android 架构边界、Feed/商品列表性能意识、JS-Native Bridge 治理、启动和页面性能优化、隐私合规收口，以及 AI 在移动端里怎么作为辅助能力落地，而不是为了炫技硬接进去。
