import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

class ContactLogic{
    private static ArrayList<Contact> contacts=new ArrayList<>();
    static Scanner sc=new Scanner(System.in);
    static ContactLogic logic=new ContactLogic();
    private static final String path="C:\\Users\\Aditya Dhadway\\Desktop\\contacts.txt";
    public static void main(String[] args) {
        logic.loadContactsFromFile();
        int choice;

        while(true)
        {
            logic.Menu();
            choice= sc.nextInt();
            switch (choice){
                case 1:
                    logic.addContact();
                    break;

                case 2:
                    logic.editContact();
                    break;
                case 3:
                    logic.viewContact();
                    break;
                case 4:
                    logic.deleteContact();
                    break;
                default: {
                    logic.saveContactsToFile();
                    System.out.println("Exiting...");
                    exit(0);
                }

            }
        }
    }

    private void deleteContact() {
        if(contacts.isEmpty()){
            System.out.println("There are no contacts to delete");
            return;
        }
        System.out.println("Enter the name of the contact to be deleted:");
        String del=sc.next();
        int count = 0;
        for(Contact c:contacts)
        {

            if(c.name.equalsIgnoreCase(del))
            {  count++;
                contacts.remove(c);
                return;
            }

        }
        if(count==0)
        {
            System.out.println("No such contact exists.");
        }
        else
        {
            System.out.println("Contact was deleted successfully.");
        }
    }

    private void viewContact() {
        if(contacts.isEmpty()){
            System.out.println("There are no contacts at this moment");
            return;
        }

            for (Contact c : contacts) {
                System.out.println(c.name + "  "+c.phoneNo+"  "+c.email);
            }

    }

    private void editContact() {
        if(contacts.isEmpty()){
            System.out.println("There are no contacts at this moment.");
            return;
        }
        System.out.println("Enter the name of the contact to be Edited.");
        String edit=sc.next();
        boolean temp=false;
        for(Contact c:contacts)
        {

            if(c.name.equalsIgnoreCase(edit))
            {
                temp=true;
                System.out.println("Enter the new name: ");
                String newName=sc.next();
                System.out.println("Enter the new phone number: ");
                String newPhone=sc.next();
                System.out.println("Enter the new email address: ");
                String newEmail=sc.next();
                c.name=newName;
                c.phoneNo=newPhone;
                c.email=newEmail;
                break;
            }

        }
        if(temp) {
            System.out.println("Contact was successfully edited.");
        }
        else
        {
            System.out.println("No such contact with the given name exists.");
        }
    }

    private void addContact() {
        System.out.println("Enter your name: ");
        String name=sc.next();
        System.out.println("Enter your phone number: ");
        String phone=sc.next();
        System.out.println("Enter your email address: ");
        String emailAddress=sc.next();

        contacts.add(new Contact(name,phone,emailAddress));
        System.out.println("Contact was added successfully");
    }
    private void saveContactsToFile() {
        try(PrintWriter w=new PrintWriter(new FileWriter(path)))
        {
            for(Contact c:contacts)
            {
                w.println(c.name+","+c.phoneNo+","+c.email);
            }
        }
        catch (IOException e)
        {
            System.out.println("An error occured.");
        }
    }
    private void loadContactsFromFile() {
        try(BufferedReader r=new BufferedReader(new FileReader(path))) {
            String line;
            while((line=r.readLine())!=null)
            {

                String data[]=line.split(",");
                contacts.add(new Contact(data[0],data[1],data[2]));
            }

        } catch (IOException e) {
            System.out.println("An error occured");
        }

    }
    void Menu(){
        System.out.println("Enter your choice...");
        System.out.println("Press 1 to add a new contact");
        System.out.println("Press 2 to edit a contact");
        System.out.println("Press 3 to view contacts");
        System.out.println("Press 4 to delete a contact");
        System.out.println("Press any other key to exit the application");
    }
}