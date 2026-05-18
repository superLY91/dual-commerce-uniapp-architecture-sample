# Issue Tracker: Local Markdown

Issues and PRDs for this repo live as markdown files under `docs/issues/`.

## Conventions

- One issue per markdown file: `docs/issues/<NN>-<slug>.md`.
- Number issues from `001`.
- Keep each issue small enough to complete independently.
- Record triage state as a `Status:` line near the top of each issue file.
- Use the triage role strings from `docs/agents/triage-labels.md`.
- Add decisions that outlive the issue to `docs/CONTEXT.md` or `docs/adr/`, not only inside the issue.

## When a skill says "publish to the issue tracker"

Create a new markdown file under `docs/issues/`.

## When a skill says "fetch the relevant ticket"

Read the referenced file under `docs/issues/`. If the user gives only an issue number, find the matching numbered markdown file.

