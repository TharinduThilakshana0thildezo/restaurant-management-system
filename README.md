Thought for a second


Here’s a simple, beginner‑friendly README snippet that tells a new developer exactly what to do after they clone, and how to work with the two main branches (`main` and `dev`) and their own feature branch.

````markdown
# Restaurant Management System

A simple Java Spring Boot web app.  

---

## 1. Setup after you clone

1. Clone the repo  
   ```bash
   git clone https://github.com/YourUser/restaurant-management-system.git
   cd restaurant-management-system
````

2. Make sure you have Java and Maven installed:

   * Java 17 (`java -version`)
   * Maven (`mvn -version`)

3. Copy the example config and edit your database settings:

   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   # then open application.properties and set your DB username/password
   ```

---

## 2. Branches you need

* **main**: our “production” code (stable).
* **dev**: where we put completed features before they go to main.
* **feature/…**: your own branch for the work you’re assigned.

---

## 3. How to start working

1. Switch to the dev branch and get the latest code:

   ```bash
   git checkout dev
   git pull origin dev
   ```
2. Create your own feature branch (replace `JIRA-123` with your ticket or name):

   ```bash
   git checkout -b feature/JIRA-123
   ```

---

## 4. Daily workflow

1. **Code**: make changes in your feature branch.
2. **Save**:

   ```bash
   git add .
   git commit -m "JIRA-123: short description of what I did"
   ```
3. **Share**:

   ```bash
   git push -u origin feature/JIRA-123
   ```
4. **Ask for review**: open a Pull Request on GitHub from `feature/JIRA-123` into `dev`.

---

## 5. Keeping up to date

Before you finish your work, bring in any new changes from dev:

```bash
git checkout dev
git pull origin dev
git checkout feature/JIRA-123
git merge dev
# fix any conflicts if they appear, then:
git push
```

---

## 6. Finishing up

Once your PR is approved and merged into `dev`, switch back to dev:

```bash
git checkout dev
git pull origin dev
```

And you’re done! 😊

---

> *That’s it—simple steps to get started.*
