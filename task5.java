import tkinter as tk
from tkinter import messagebox

class Contact:
    def __init__(self, name, phone, email, address):
        self.name = name
        self.phone = phone
        self.email = email
        self.address = address

class ContactManager:
    def __init__(self):
        self.contacts = []

    def add_contact(self, contact):
        self.contacts.append(contact)

    def delete_contact(self, contact):
        if contact in self.contacts:
            self.contacts.remove(contact)
            return True
        else:
            return False

class ContactApp:
    def __init__(self, master):
        self.master = master
        self.master.title("Contact Manager")

        self.contact_manager = ContactManager()

        self.name_label = tk.Label(master, text="Name:")
        self.name_label.grid(row=0, column=0)
        self.name_entry = tk.Entry(master)
        self.name_entry.grid(row=0, column=1)

        self.phone_label = tk.Label(master, text="Phone:")
        self.phone_label.grid(row=1, column=0)
        self.phone_entry = tk.Entry(master)
        self.phone_entry.grid(row=1, column=1)

        self.email_label = tk.Label(master, text="Email:")
        self.email_label.grid(row=2, column=0)
        self.email_entry = tk.Entry(master)
        self.email_entry.grid(row=2, column=1)

        self.address_label = tk.Label(master, text="Address:")
        self.address_label.grid(row=3, column=0)
        self.address_entry = tk.Entry(master)
        self.address_entry.grid(row=3, column=1)

        self.add_button = tk.Button(master, text="Add Contact", command=self.add_contact)
        self.add_button.grid(row=4, column=0, columnspan=2, pady=10)

        self.view_button = tk.Button(master, text="View Contacts", command=self.view_contacts)
        self.view_button.grid(row=5, column=0, columnspan=2, pady=10)

        self.search_label = tk.Label(master, text="Search:")
        self.search_label.grid(row=6, column=0)
        self.search_entry = tk.Entry(master)
        self.search_entry.grid(row=6, column=1)

        self.search_button = tk.Button(master, text="Search", command=self.search_contact)
        self.search_button.grid(row=7, column=0, columnspan=2, pady=10)

        self.delete_button = tk.Button(master, text="Delete Contact", command=self.delete_contact)
        self.delete_button.grid(row=8, column=0, columnspan=2, pady=10)

    def add_contact(self):
        name = self.name_entry.get()
        phone = self.phone_entry.get()
        email = self.email_entry.get()
        address = self.address_entry.get()

        new_contact = Contact(name, phone, email, address)
        self.contact_manager.add_contact(new_contact)
        messagebox.showinfo("Success", "Contact added successfully!")

    def view_contacts(self):
        contacts_text = "Contact List:\n"
        for contact in self.contact_manager.contacts:
            contacts_text += f"\nName: {contact.name}\nPhone: {contact.phone}\nEmail: {contact.email}\nAddress: {contact.address}\n"
        messagebox.showinfo("Contacts", contacts_text)

    def search_contact(self):
        search_term = self.search_entry.get().lower()

        found_contacts = []
        for contact in self.contact_manager.contacts:
            if search_term in contact.name.lower() or search_term in contact.phone:
                found_contacts.append(contact)

        if found_contacts:
            search_result = "Search Result:\n"
            for contact in found_contacts:
                search_result += f"\nName: {contact.name}\nPhone: {contact.phone}\nEmail: {contact.email}\nAddress: {contact.address}\n"
            messagebox.showinfo("Search Result", search_result)
        else:
            messagebox.showinfo("Search Result", "No contacts found.")

    def delete_contact(self):
        name_to_delete = self.name_entry.get()
        phone_to_delete = self.phone_entry.get()

        for contact in self.contact_manager.contacts:
            if contact.name == name_to_delete and contact.phone == phone_to_delete:
                if self.contact_manager.delete_contact(contact):
                    messagebox.showinfo("Success", "Contact deleted successfully!")
                else:
                    messagebox.showinfo("Error", "Contact not found.")
                return

        messagebox.showinfo("Error", "Contact not found.")

if __name__ == "__main__":
    root = tk.Tk()
    app = ContactApp(root)
    root.mainloop()
