# Domain Docs

How engineering skills should consume this repo's domain documentation.

## Before Every Task

Read these files before making changes:

- `AGENTS.md`
- `docs/CONTEXT.md`
- `docs/architecture.md`

Then read any relevant ADRs under `docs/adr/`.

## Layout

This is a single-context repo:

```text
/
├── AGENTS.md
├── docs/
│   ├── CONTEXT.md
│   ├── architecture.md
│   ├── adr/
│   ├── agents/
│   └── issues/
```

## Context Rules

- Long-term project context belongs in `docs/CONTEXT.md`.
- Architecture decisions belong in `docs/adr/`.
- Development tasks belong in `docs/issues/`.
- Use the vocabulary from `docs/CONTEXT.md` when writing issues, ADRs, code comments, or interview explanations.
- If a task contradicts an existing ADR, surface the conflict explicitly before changing the design.
- Keep every task small and independently reviewable.

