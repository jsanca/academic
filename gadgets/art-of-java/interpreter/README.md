

# BASIC Interpreter (Java)

A clean, educational implementation of a **BASIC-like interpreter** written in Java.

This project started from a classic “Art of Java” style implementation and has been **refactored for clarity, readability, and architectural understanding**.

---

## 🧠 Overview

This interpreter executes BASIC-style programs directly from source using a **two-pass execution model**:

1. **Scan phase** → builds a label table
2. **Execution phase** → interprets statements using a lexer + parser

No AST, no bytecode — just **direct execution over the source**.

---

## 🏗 Architecture

The system is composed of three main layers:

### 1. Interpreter (Orchestration)

`BasicInterpreter`

- Entry point: `run()`
- Controls execution flow
- Handles statements:
  - `PRINT`
  - `INPUT`
  - `IF / THEN`
  - `FOR / NEXT`
  - `GOTO`
  - `GOSUB / RETURN`
  - Assignments

---

### 2. Expression Parser

`ExpressionParser`

A **recursive descent parser** that evaluates expressions with proper precedence.

#### Supported operations:

- Arithmetic: `+ - * / % ^`
- Relational: `< > <= >= = <>`
- Unary: `+ -`
- Parentheses
- Variables (A-Z)

#### Precedence levels (top → bottom):

```
parseRelational
  → parseAddSub
      → parseMulDivMod
          → parseExponent
              → parseUnary
                  → parsePrimary
                      → parseAtom
```

---

### 3. Lexer (Tokenizer + Execution Pointer)

`BasicLexer`

- Converts source into tokens
- Maintains a cursor (`index`)
- Supports:
  - `nextToken()`
  - `putBack()` (single-token lookahead)
  - `setIndex()` (jump execution)

💥 The lexer also acts as the **instruction pointer**.

---

## 🔁 Execution Model

This interpreter behaves like a very simple virtual machine:

| Concept            | Implementation          |
|------------------|------------------------|
| Memory           | `char[] source`        |
| Instruction Ptr  | `lexer.index`          |
| Fetch            | `nextToken()`          |
| Jump             | `setIndex()`           |
| Labels           | `Map<String, Integer>` |

---

## 🏷 Labels & GOTO

Labels are numeric values at the start of a line:

```
10 PRINT "Hello"
20 GOTO 10
```

During the scan phase:

```
"10" → index 0
"20" → index 15
```

Execution jumps using:

```java
lexer.setIndex(location);
```

---

## 🔤 Variables

Variables are single letters: `A-Z`

Internally stored as:

```java
double[] variables = new double[26];
```

Mapping:

```java
int index = Character.toUpperCase(var) - 'A';
```

| Variable | Index |
|----------|------|
| A        | 0    |
| B        | 1    |
| ...      | ...  |
| Z        | 25   |

---

## ✨ Key Design Decisions

- No AST → simpler mental model
- Direct execution → closer to early interpreters
- Array-based variables → O(1) access
- Two-pass execution → avoids forward reference issues
- `putBack()` → minimal lookahead mechanism

---

## 📚 Example Program

```
10 A = 5
20 PRINT A * 2
30 IF A < 10 THEN GOTO 10
40 END
```

---

## 🎯 Goals of the Project

- Understand interpreter design
- Learn recursive descent parsing
- Explore execution models without heavy abstraction
- Refactor legacy-style code into modern, readable Java

---

## ☕ Notes

This project intentionally favors:

- clarity over cleverness
- explicit structure over abstraction
- learning over optimization

---

## 🚀 Future Ideas

- Support multi-character variables (`total`, `x1`)
- Introduce a symbol table (Map instead of array)
- Add debugging / tracing mode
- Convert to bytecode-based interpreter
- Build a small REPL

---

## ❤️ Final Thought

> This is not just a parser.  
> It is a tiny machine that *understands and executes language*.

---

Enjoy exploring it.