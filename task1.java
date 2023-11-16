import os
import pickle

class TodoList:
    def __init__(self):
        self.tasks = []

    def add_task(self, task):
        self.tasks.append(task)

    def remove_task(self, task):
        if task in self.tasks:
            self.tasks.remove(task)
        else:
            print("Task not found!")

    def display_tasks(self):
        if not self.tasks:
            print("No tasks found.")
        else:
            for index, task in enumerate(self.tasks, start=1):
                print(f"{index}. {task}")

    def save_to_file(self, filename):
        with open(filename, 'wb') as file:
            pickle.dump(self.tasks, file)

    def load_from_file(self, filename):
        if os.path.exists(filename):
            with open(filename, 'rb') as file:
                self.tasks = pickle.load(file)

def main():
    todo_list = TodoList()

    filename = 'todo_list.pkl'

    # Load tasks from a file if it exists
    todo_list.load_from_file(filename)

    while True:
        print("\n===== To-Do List =====")
        print("1. Add Task")
        print("2. Remove Task")
        print("3. Display Tasks")
        print("4. Save and Quit")

        choice = input("Enter your choice (1-4): ")

        if choice == '1':
            task = input("Enter the task: ")
            todo_list.add_task(task)
        elif choice == '2':
            task = input("Enter the task to remove: ")
            todo_list.remove_task(task)
        elif choice == '3':
            todo_list.display_tasks()
        elif choice == '4':
            todo_list.save_to_file(filename)
            print("To-Do List saved. Quitting...")
            break
        else:
            print("Invalid choice. Please enter a number between 1 and 4.")

if __name__ == "__main__":
    main()
