# AGENTS.md

## Project Goal

This repository is not a complete e-commerce App.

The goal is to provide an interview-oriented architecture sample for a dual-end commerce system: toB + toC, Android Native + Uniapp.

The project should demonstrate architecture design capability, especially around:

- Android Native architecture boundaries
- Uniapp page organization
- Native-Uniapp Bridge design
- performance strategy
- privacy and compliance
- CI/CD
- AI-assisted engineering practices
- team collaboration and maintainability

The priority is not feature completeness. The priority is clear architecture boundaries and explainable design decisions.

## Technical Boundaries

- Android only provides an architecture skeleton.
- Android must not connect to a real backend.
- Uniapp only provides page organization and mock logic.
- Do not implement real login.
- Do not implement real payment.
- Do not integrate real third-party SDKs.
- All APIs must use mock data.
- Do not introduce complex dependencies.
- Prefer simple, readable, interview-friendly implementations over production-scale infrastructure.

## Android Architecture Principles

- Use layered architecture: UI / Domain / Data.
- Use Repository to isolate data sources from business logic.
- The `core` module owns shared capabilities.
- The `feature` modules own business pages and feature-specific logic.
- The `bridge` module centrally manages communication between Android Native and Uniapp.
- The `privacy` module centrally manages permissions, privacy prompts, compliance status, and related policy boundaries.
- Android code should make architecture roles obvious from package and module names.
- Each module should be explainable in an interview: what it owns, what it does not own, and why that boundary exists.

## Uniapp Architecture Principles

- Use Vue / Uniapp page structure.
- Keep page code layered into `pages`, `api`, `store`, `bridge`, and `utils`.
- Place high-frequency-change pages in Uniapp when they benefit from dynamic iteration.
- Do not place the core transaction chain in Uniapp.
- Uniapp should demonstrate page orchestration, mock APIs, state flow, and Bridge calls without pretending to be a full production App.
- Native-Uniapp interaction must go through the unified Bridge boundary instead of scattered direct calls.

## File Modification Rules

- Complete only one small issue at a time.
- Do not implement the full project in one pass.
- Before making changes, read `docs/CONTEXT.md` and `docs/architecture.md`.
- If either document does not exist yet, create or update the relevant documentation before implementing business-facing structure.
- Keep each change scoped to the current issue.
- Avoid unrelated refactors.
- Avoid introducing heavy frameworks or dependencies unless the architecture document explicitly justifies them.
- After each task, output:
  - modified files
  - design rationale
  - acceptance criteria
  - interview explanation

## Interview Orientation

- All documents and code must support interview explanation.
- Every module must explain why it exists and why its boundary is designed that way.
- Prefer architecture clarity over feature completeness.
- Prefer visible tradeoffs over hidden implementation detail.
- The sample should help an interviewer quickly understand how Android Native, Uniapp, Bridge, privacy compliance, performance, CI/CD, AI usage, and team collaboration fit together.
- When adding code or documentation, include the interview narrative: what problem this design solves, what risks it avoids, and what can be extended later.

## Agent skills

### Issue tracker

Issues are tracked as local markdown files under `docs/issues/`. See `docs/agents/issue-tracker.md`.

### Triage labels

Use the default mattpocock/skills triage vocabulary unless a local issue explicitly says otherwise. See `docs/agents/triage-labels.md`.

### Domain docs

This is a single-context repo: long-term context lives in `docs/CONTEXT.md`, architecture overview lives in `docs/architecture.md`, and ADRs live in `docs/adr/`. See `docs/agents/domain.md`.
