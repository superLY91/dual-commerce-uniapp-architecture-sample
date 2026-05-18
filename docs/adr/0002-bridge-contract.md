# ADR 0002: Native-Uniapp Bridge 契约

## 状态

Accepted

## 背景

混合架构中最常见的问题是页面直接调用系统能力、协议散落、错误码不统一、权限校验缺失和版本兼容困难。Bridge 如果只是工具函数，会逐渐变成不可治理的通道。

## 决策

Bridge 是 Uniapp 调用 Native 能力的唯一入口。每个 Bridge 能力必须声明：

- `module`：能力归属，例如 `privacy`、`device`、`trade`、`monitor`。
- `action`：具体动作，例如 `requestPermission`、`openOrderDetail`、`trackEvent`。
- `version`：协议版本。
- `params`：入参 schema。
- `result`：成功返回结构。
- `error`：统一错误码、错误消息和可恢复建议。
- `permission`：是否需要隐私授权或系统权限。
- `traceId`：用于日志、监控和问题排查。

## 错误模型

- `INVALID_PARAMS`：参数缺失或类型错误。
- `UNAUTHORIZED`：隐私协议或业务状态不满足。
- `PERMISSION_DENIED`：系统权限被拒绝。
- `NOT_SUPPORTED`：当前 App 版本或设备不支持。
- `TIMEOUT`：调用超时。
- `NATIVE_ERROR`：Native 内部异常。

## 备选方案

- 页面直接调用 Native 注入对象：实现简单，但能力散落。
- 每个页面独立约定协议：短期快，长期无法维护。
- 只用 URL scheme：适合简单跳转，不适合复杂参数、回调和权限治理。

## 取舍理由

统一 Bridge 契约可以把安全、权限、日志、监控和兼容收口在一个地方。它让 Uniapp 页面只关心业务意图，不关心 Native 内部实现。

## 后续影响

- 后续新增 Native 能力前必须先补 Bridge 契约。
- Bridge 调用需要记录耗时、成功率、错误码和 traceId。
- Privacy 相关能力必须在 Bridge 层做授权状态检查。

## 面试讲法

Bridge 不是简单 JSBridge，而是混合架构的安全网。它统一能力、参数、错误、权限和监控，避免 Native 能力被页面随意调用。
